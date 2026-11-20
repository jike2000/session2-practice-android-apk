# Practice 4 Android APK builds

This sample Java app uses the Gradle Wrapper, JDK 17, and Android SDK 35.

- **Android Debug Build** runs on pushes to `main` or `develop`, pull requests into `main`, or manual dispatch. It uploads `app-debug.apk` and smoke-tests installation and launch in an Android emulator.
- **Android Release Build** runs on version tags such as `v1.0.0` or manual dispatch. It signs and verifies `app-release.apk` using `KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, and `KEY_PASSWORD` from GitHub Actions secrets.

Open a successful run under **Actions** and download the APK from **Artifacts**. The debug and release packages have different application IDs so they can be installed side by side.
