import com.google.devtools.ksp.getVisibility
import com.google.devtools.ksp.isLocal
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.symbol.Visibility
import com.google.devtools.ksp.validate
import java.io.OutputStream

class UiStateProcessor(
    val codeGenerator: CodeGenerator,
    val logger: KSPLogger
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols =
            resolver.getSymbolsWithAnnotation("io.github.mohamed_ie.uistate.annotations.UiState")
        val ret = symbols.filterNot(KSAnnotated::validate).toList()

        symbols.filter { it is KSClassDeclaration && it.validate() }
            .forEach {
                logger.info("Generating UiState utilities for class: ${(it as KSClassDeclaration).simpleName.asString()}")
                it.accept(UiStateVisitor(), Unit)
            }
        return ret
    }

    inner class UiStateVisitor() : KSVisitorVoid() {
        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            super.visitClassDeclaration(classDeclaration, data)

            val containingFile = classDeclaration.containingFile
            if (containingFile == null) {
                logger.warn("Could not get containing file for class: ${classDeclaration.qualifiedName?.asString() ?: classDeclaration.simpleName.asString()}, skipping UiState generation.")
                return
            }

            // Skip local classes (defined inside a function/initializer)
            if (classDeclaration.isLocal()) {
                logger.warn("Skipping local class: ${classDeclaration.qualifiedName?.asString() ?: classDeclaration.simpleName.asString()}. UiState generation is not supported for local classes.")
                return
            }

            // Skip private nested classes
            if (classDeclaration.getVisibility() == Visibility.PRIVATE && classDeclaration.parentDeclaration != null) {
                logger.warn("Skipping private nested class: ${classDeclaration.qualifiedName?.asString() ?: classDeclaration.simpleName.asString()}. UiState generation for private nested classes is not supported as extensions would not be accessible.")
                return
            }

            val superTypeReference = classDeclaration.superTypes.firstOrNull()
            if (superTypeReference == null) {
                logger.warn("Class ${classDeclaration.simpleName.asString()} does not have a supertype, skipping UiState generation.")
                return
            }

            val superTypeResolved = superTypeReference.resolve()
            val superTypeDeclaration = superTypeResolved.declaration as? KSClassDeclaration
            if (superTypeDeclaration == null) {
                logger.warn("Supertype of ${classDeclaration.simpleName.asString()} is not a class or could not be resolved, skipping UiState generation. Supertype resolved to: ${superTypeResolved.declaration}")
                return
            }
            val superTypeName = superTypeDeclaration.simpleName.asString()
            val superTypeQualifiedName = superTypeDeclaration.qualifiedName?.asString()
            if (superTypeQualifiedName == null) {
                logger.warn("Could not get qualified name for supertype ${superTypeName} of class ${classDeclaration.simpleName.asString()}, skipping UiState generation.")
                return
            }

            val packageName = containingFile.packageName.asString()
            val className = classDeclaration.simpleName.asString()

            val classQualifiedName = classDeclaration.qualifiedName?.asString()
            if (classQualifiedName == null) {
                logger.warn("Could not get qualified name for class ${className}, skipping UiState generation.")
                return
            }

            val decapitalizeClassName = className.replaceFirstChar(Char::lowercase)
            val fileName = "${className}UiState"

            val superVisibility = superTypeDeclaration.getVisibility()
            val classVisibility = classDeclaration.getVisibility()

            val visibility = when (superVisibility) {
                Visibility.PRIVATE -> Visibility.PRIVATE
                Visibility.PROTECTED, Visibility.INTERNAL -> Visibility.INTERNAL
                else -> classVisibility
            }

            val visibilityKeyword = when (visibility) {
                Visibility.PUBLIC -> "public "
                Visibility.INTERNAL, Visibility.PROTECTED -> "internal "
                Visibility.PRIVATE -> "private "
                else -> ""
            }

            val fileContent =
                """package $packageName
                        |
                        |import $superTypeQualifiedName
                        |import $classQualifiedName
                        |import kotlinx.coroutines.flow.MutableStateFlow
                        |import kotlinx.coroutines.flow.update
                        |
                        |${visibilityKeyword}inline fun MutableStateFlow<$superTypeName>.update$className(block: ($className) -> $className) = 
                        |    update { it.$decapitalizeClassName?.let(block) ?: it }
                        |
                        |${visibilityKeyword}inline val MutableStateFlow<$superTypeName>.$decapitalizeClassName
                        |    inline get() = value as? $className
                        |
                        |${visibilityKeyword}inline fun MutableStateFlow<$superTypeName>.if$className(block: ($className) -> Unit) =
                        |    apply { $decapitalizeClassName?.let(block) }
                        |
                        |${visibilityKeyword}inline val MutableStateFlow<$superTypeName>.is${className}
                        |    inline get() = value is $className
                        |   
                        |${visibilityKeyword}inline val $superTypeName.$decapitalizeClassName
                        |    inline get() = this as? $className
                        |
                        |${visibilityKeyword}inline fun $superTypeName.if$className(block: ($className) -> Unit) =
                        |    apply { $decapitalizeClassName?.let(block) }
                        |
                        |${visibilityKeyword}inline val $superTypeName.is${className}
                        |    inline get() = this is $className
                        |                       
                """.trimMargin()


            try {
                codeGenerator.createNewFile(
                    dependencies = Dependencies(aggregating = true, containingFile),
                    packageName = packageName,
                    fileName = fileName
                ).use { it.appendText(fileContent) }
                logger.info("Successfully generated UiState utilities for $className to $fileName.kt")
            } catch (e: Exception) {
                logger.error("Error generating file for $className: ${e.message}", classDeclaration)
            }
        }
    }
}

private fun OutputStream.appendText(str: String) {
    this.write(str.toByteArray())
}
