
# EIO Android SDK

> **Target platform : Star1s AR glasses (Android 9 / API 28)**  
> This repository hosts the Android client libraries that let you build, test and
ship AR applications for EIO glasses.  
If you only need firmware source, visit [`eio-android-firmware`](https://github.com/zhenghaoyu-EIO/eio-android-firmware).

---

## ✨ What’s inside

| Module | AAR artifact | Purpose |
|--------|--------------|---------|
| **core**   | `io.eio.sdk:core`   | Lifecycle helpers, runtime‑permission wrapper, BLE Ring key events (`KEY_LEFT/RIGHT/OK/BACK/POWER`) |
| **camera** | `io.eio.sdk:camera` | CameraX preview + distortion‑correction LUT for 640 × 480 wave‑guide optics |
| **voice**  | `io.eio.sdk:voice`  | Microphone recording helper & on‑device TTS playback |

All modules are **Kotlin‑only**, minSdk 21, compile/targetSdk 28.

---

## 📦 Installation

1. **Add EIO Maven repository** (GitHub Packages placeholder):

```gradle
// settings.gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/zhenghaoyu-EIO/eio-android-sdk")
            credentials {
                username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key")  ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
```

2. **Pull dependencies**:

```gradle
dependencies {
    implementation("io.eio.sdk:core:0.1.0")
    implementation("io.eio.sdk:camera:0.1.0")
    implementation("io.eio.sdk:voice:0.1.0")
}
```

---

## 🚀 Quick Start

```kotlin
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EioTheme {
                // Ask for camera / audio permissions
                EioPermission.RequestAll()

                // Center video window (CameraX) 320×240
                EioCameraPreview(
                    modifier = Modifier
                        .width(320.dp)
                        .height(240.dp)
                )

                // BLE Ring events
                val ringEvent = rememberRingEvent()
                LaunchedEffect(Unit) {
                    ringEvent.collect { evt -> Log.d("Ring", evt.name) }
                }

                // Speak welcome
                Voice.speak("Welcome to EIO glasses!")
            }
        }
    }
}
```

Full example in [`eio-samples/subtitle-demo`](https://github.com/zhenghaoyu-EIO/eio-samples/tree/main/subtitle-demo).

---

## 🛠️ Build from source

```bash
git clone https://github.com/zhenghaoyu-EIO/eio-android-sdk.git
./gradlew publishToMavenLocal
```

---

## 🔗 Firmware Environment

| Repo | Description |
|------|-------------|
| [`eio-android-firmware`](https://github.com/zhenghaoyu-EIO/eio-android-firmware) | AOSP patches, device tree, vendor services |
| [`eio-samples`](https://github.com/zhenghaoyu-EIO/eio-samples) | Sample apps using this SDK |

Flash firmware, enable USB debugging, and install your APK via `adb install`.

---

## 🗺️ Roadmap

- [ ] `sensor` module – 9‑axis IMU fusion
- [ ] `ar-core` – image tracking helpers
- [ ] JavaScript bridge for web apps

---

**Happy hacking – build something amazing on EIO AR glasses!**
