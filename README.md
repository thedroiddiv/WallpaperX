## ⚠️ Migration Warning

> [!WARNING]
> **This project is currently undergoing a Kotlin Multiplatform (KMP) migration.**
> The `master` branch reflects the stable Android-only version. Active migration work is happening on the `kmp-migration` branch. Expect breaking changes and instability until the migration is complete.

### KMP Migration Checkpoints

The migration follows a deliberate order — AGP 9 upgrade first, then KMP+CMP setup — to handle AGP 9 breaking changes cleanly before introducing multiplatform structure.

- [x] **Upgrade to AGP 9** — Update Android Gradle Plugin to version 9, resolving breaking changes introduced in this version before introducing KMP
- [x] **Set up KMP + Compose Multiplatform (CMP) project structure** — Add `composeApp`, `iosApp` and shared modules; configure multiplatform targets
- [ ] **Migrate `:data` module to KMP** — Move data sources, repositories, and models to shared Kotlin code
  - [x] Add KMP-compatible dependencies — Ktor, kotlinx-serialization, Coil3, Napier, Room multiplatform, KSP
  - [x] Set up `roomMain` intermediate source set — Android/iOS/JVM depend on it; JS/WasmJS excluded (custom DB abstraction pending)
  - [x] Migrate domain models — `Wallpaper`, `WallpaperCollection` as plain KMP data classes
  - [x] Migrate remote layer — `WallpaperApi` interface, `PixabayApi` + `UnsplashApi` Ktor implementations with kotlinx-serialization DTOs
  - [x] Migrate local/database layer — `FavouriteEntity`, `FavouriteDao`, `WallpaperDatabase` in `roomMain`; `LocalDataSource` interface in `commonMain`; `RoomLocalDataSource` + platform database builders in `androidMain`/`iosMain`/`jvmMain`
  - [ ] JS/WasmJS DB abstraction — custom in-memory or IndexedDB-backed implementation for unsupported targets
- [ ] **Migrate `:domain` module to KMP** — Move use cases and domain logic to shared Kotlin code
- [ ] **Migrate `:ui` module to CMP** — Port Jetpack Compose UI to Compose Multiplatform
- [ ] **iOS app integration** — Wire up the shared KMP modules to the iOS app
- [ ] **Verify Android app parity** — Ensure the Android app (`androidApp`) works correctly with the new shared modules
- [ ] **Clean up legacy Android-only code** — Remove redundant platform-specific code replaced by shared implementations

---


<p align="center">
<img src="https://github.com/divyansh-dxn/WallpaperX/blob/master/assets/icons/ic_launcher_round/android/res/mipmap-xxxhdpi/ic_launcher_round.png?raw=true"/>

</p>

<p align="center">
<a href="https://play.google.com/store/apps/details?id=com.dxn.wallpaperx.ui" target="_blank"><img src="https://user-images.githubusercontent.com/69595691/203753454-6dd20127-9dee-4c17-a28d-15236b477db5.png" width="200"/></a>
</p>

<h1 align="center">WallpaperX</h1>



WallpaperX is a simple android application built using <a href="https://developer.android.com/jetpack/compose">Jetpack compose - Android’s modern toolkit for building native UI</a>. It uses pixabay and unsplash api to fetch wallpapers. 
<br><br>
WallpaperX brings cool and fresh wallpapers just to you from unsplash.com. Set wallpapers for your home and lock screen, download and share.
<br><br>
The project uses multiple modules saparated on basis DATA-DOMAIN-UI, three layered architecutre.
<br><br>

![Frame 7](https://user-images.githubusercontent.com/69595691/183310290-77a10919-f9a0-4a76-8cac-23b018f4d67b.png)

### Technologies used
- Jetpack compose
- Dagger-Hilt
- Kotlin-flow
- Animated navigation from accompanist library

### Previews
<div>
<img src = "https://raw.githubusercontent.com/divyanshdxn/WallpaperX/master/assets/samples/preview_all.jpeg"/>
</div>

### MAD Overview
<div>
<img src="https://raw.githubusercontent.com/divyansh-dxn/WallpaperX/master/assets/mad/summary.png" width="720"/>
<img src="https://raw.githubusercontent.com/divyansh-dxn/WallpaperX/master/assets/mad/jetpack.png" width="720"/>
<img src="https://raw.githubusercontent.com/divyansh-dxn/WallpaperX/master/assets/mad/kotlin.png" width="720"/>
</div>

![logo](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![logo](	https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white)



