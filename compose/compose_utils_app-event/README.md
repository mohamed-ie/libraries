
# App Event Invoker Library

The **App Event Invoker** library provides a robust and flexible way to emit and observe application-wide events. Designed for Kotlin Multiplatform projects, it enables seamless event-driven communication in Compose-based applications.

## Features

- **Event Emission and Observation**: Emit and observe events across the app using `AppEventInvoker`.
- **Type-Safe Filtering**: Filter events by type with the `ObserveAppEvents` function.
- **Coroutine-Based Flow**: Built on Kotlin's `SharedFlow` for scalability and efficiency.
- **Jetpack Compose Integration**: Easy integration with Compose via `LocalAppEventInvoker`.

## Installation

Add the following dependencies to your project:

### Gradle
```kotlin
// Add this to your commonMain dependencies
implementation("io.github.mohamed-ie:compose-utils-app-event:1.0.0")
```

## Usage

### 1. Define an Event Invoker

Use the `DefaultAppEventInvoker` to emit and observe events:

```kotlin
val appEventInvoker = DefaultAppEventInvoker()
```

### 2. Emit Events

```kotlin
appEventInvoker.send(MyEvent("Hello World"))
```

### 3. Observe Events

#### Observe All Events

```kotlin
ObserveAppEvents(invoker = appEventInvoker) { event ->
    println("Received event: $event")
}
```

#### Observe Specific Event Types

```kotlin
ObserveAppEvents<MyEvent>(invoker = appEventInvoker) { event ->
    println("Received MyEvent: ${event.message}")
}
```

## API Documentation

### AppEventInvoker

An interface for emitting and observing events:

- `val event: Flow<Any>`: A flow of all emitted events.
- `fun send(event: Any): Job`: Emits a new event.

### DefaultAppEventInvoker

A default implementation of `AppEventInvoker` using `SharedFlow`.

- **Properties**:
    - `event`: Exposes the shared flow of events.
- **Functions**:
    - `send(event: Any)`: Emits a new event.
    - `close()`: Cancels the internal coroutine scope.

### CompositionLocal

`LocalAppEventInvoker` provides the current `AppEventInvoker` instance.

### Composable Functions

- `ObserveAppEvents`: Collects all emitted events.
- `ObserveAppEvents<T>`: Collects events of a specific type.

## License

This library is licensed under the [Apache License 2.0](../../LICENSE).

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests to improve the library.

## Contact

For questions or support, please reach out to [Mohamed Ibrahim El Sayed Mostafa](mailto:mohamed@example.com).