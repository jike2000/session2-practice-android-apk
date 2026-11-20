# Cowbell for Practice 4

Open the app and gently rock an Android phone side to side to swing and ring the cowbell. The app uses the Gradle Wrapper, JDK 17, and Android SDK 35.

- **Android Debug Build** runs on pushes to `main` or `develop`, pull requests into `main`, or manual dispatch. It uploads `app-debug.apk`, installs it in an Android emulator, and injects an accelerometer reading to verify that the app starts playing the bell sound.
- **Android Release Build** runs on version tags such as `v1.0.0` or manual dispatch. It signs and verifies `app-release.apk` using `KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, and `KEY_PASSWORD` from GitHub Actions secrets.

Open a successful run under **Actions** and download the APK from **Artifacts**. The debug and release packages have different application IDs so they can be installed side by side.

## Attribution

Based on [Cowbell by Jeffrey Charles](https://github.com/jeffcharles/cowbell/tree/f3a66c5afec514b65cd2156a4736b8c63626f0a6), copyright 2012. This port uses the upstream ring audio, roll smoothing, and swing animation. It updates the build, sensor handling, and interface, and uses a new bell illustration. The project is licensed under GPL-3.0-or-later. See [LICENSE.txt](LICENSE.txt) and [COPYING](COPYING).
