# Custom Utility Libraries for Enhanced Development

This repository contains a collection of **custom libraries** designed to simplify and enhance application development. These utilities are tailored for use in **Kotlin Multiplatform projects** and **Jetpack Compose** applications, providing flexible solutions to common challenges.

## Libraries Overview

### 1. App Event

The **App Event** is a utility for managing **application-wide events**. It enables event-driven communication between different parts of your app in a thread-safe and efficient manner.

#### Features:
- **Event Emission and Observation**: Emit events using `AppEventInvoker` and observe them across your app.
- **Type-Safe Filtering**: Collect only the events you need with reified generics.
- **Compose Integration**: Easily integrate with Jetpack Compose via `LocalAppEventInvoker`.

#### Use Cases:
- Managing UI state changes across different screens.
- Handling global notifications or alerts.
- Communicating between loosely coupled modules.

---

## Why These Libraries?

These libraries are custom-built to:
- **Reduce Boilerplate**: Simplify repetitive tasks in app development.
- **Improve Code Readability**: Offer clear and concise APIs.
- **Enhance Maintainability**: Provide reusable components to keep your codebase clean and organized.