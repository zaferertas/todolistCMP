[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.20-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)

![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-desktop](http://img.shields.io/badge/platform-ios-EAEAEA.svg?style=flat)
![badge-desktop](http://img.shields.io/badge/platform-desktop-4D76CD.svg?style=flat)

## General Info

A Kotlin Multiplatform demo project targeting Android, iOS and Desktop using Compose Multiplatform as the common ui.

Please visit https://github.com/zaferertas/todolist for the same app that is developed using native UI libraries: Jetpack Compose for Android and SwiftUI for IOS.

## Architecture
The app is shared between Android, iOS and Desktop. The shared code is written in Kotlin and the UI is built with Compose Multiplatform. Shared code, written in Kotlin, is compiled to JVM bytecode for Android and Desktop with Kotlin/JVM and to native binaries for iOS with Kotlin/Native.

## Run project
### Android
To run the application on android device/emulator:
- open project in Android Studio and run imported android run configuration

### Desktop
Run the desktop application: `./gradlew :desktop:run`

### iOS
To run the application on iPhone device/simulator:
- Open `ios/iosApp.xcworkspace` in Xcode and run standard configuration
- Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio

## Screenshots
### Android
<img src="art/Screenshot_Android.png"  width="250" alt="Android"/>

### iOS
<img src="art/Screenshot_IOS.png"  width="250" alt="IOS"/>

### Desktop
<img src="art/Screenshot_Desktop.png"  width="500"  alt="Desktop"/>

## Libraries used
- 🧩 [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform); for shared UI
- 🔷 [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library support for Kotlin coroutines with multiplatform support
- 📦 [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization); for content negotiation
- 🕰️ [Kotlinx Datetime](https://github.com/Kotlin/kotlinx-datetime); for datetime
- 🗄 [SQLDelight](https://github.com/cashapp/sqldelight) - for the sqlite database
- ⧉  [Koin](https://insert-koin.io/) - For dependency injection
- 🔶 [Kermit](https://kermit.touchlab.co/) - For logging
- 
## Related Resources

- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform-get-started.html)
- [Get started with Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Using Koin in a Kotlin Multiplatform Project](https://johnoreilly.dev/posts/kotlinmultiplatform-koin/)
- [Kotlin Multiplatform samples](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-samples.html)
