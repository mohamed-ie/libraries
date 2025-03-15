import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
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
            .forEach { it.accept(UiStateVisitor(), Unit) }
        return ret
    }

    inner class UiStateVisitor() : KSVisitorVoid() {
        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            super.visitClassDeclaration(classDeclaration, data)
            val superTypeReference = classDeclaration.superTypes.firstOrNull() ?: return
            val superTypeDeclaration = superTypeReference.resolve().declaration
            val superTypeName = superTypeDeclaration.simpleName.asString()

            val containingFile = classDeclaration.containingFile ?: return
            val packageName = containingFile.packageName.asString()
            val className = classDeclaration.simpleName.asString()
            val decapitalizeClassName = className.replaceFirstChar(Char::lowercase)
            val fileName = "${className}UiState"

            val fileContent =
                """package $packageName
                        |
                        |import ${superTypeDeclaration.qualifiedName?.asString()}
                        |import ${classDeclaration.qualifiedName?.asString()}
                        |import kotlinx.coroutines.flow.MutableStateFlow
                        |import kotlinx.coroutines.flow.update
                        |
                        |inline fun MutableStateFlow<$superTypeName>.update$className(block: ($className) -> $className) = 
                        |    update { it.$decapitalizeClassName?.let(block) ?: it }
                        |
                        |inline val MutableStateFlow<$superTypeName>.$decapitalizeClassName
                        |    inline get() = value as? $className
                        |
                        |inline fun MutableStateFlow<$superTypeName>.if$className(block: ($className) -> Unit) =
                        |    apply { $decapitalizeClassName?.let(block) }
                        |
                        |inline val MutableStateFlow<$superTypeName>.is${className}
                        |    inline get() = value is $className
                        |   
                        |inline val $superTypeName.$decapitalizeClassName
                        |    inline get() = this as? $className
                        |
                        |inline fun $superTypeName.if$className(block: ($className) -> Unit) =
                        |    apply { $decapitalizeClassName?.let(block) }
                        |
                        |inline val $superTypeName.is${className}
                        |    inline get() = this is $className
                        |                       
                """.trimMargin()

            codeGenerator.createNewFile(
                dependencies = Dependencies(aggregating = true, containingFile),
                packageName = packageName,
                fileName = fileName
            ).use { it.appendText(fileContent) }
        }
    }
}

private fun OutputStream.appendText(str: String) {
    this.write(str.toByteArray())
}