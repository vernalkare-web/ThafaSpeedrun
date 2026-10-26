# ThafaSpeedrun

ThafaSpeedrun adalah aplikasi pendamping speedrun untuk Minecraft Bedrock Edition di Android. Aplikasi ini dirancang untuk membantu pemain memantau informasi permainan melalui panel melayang (floating overlay).

🎯 Tujuan Proyek

Mengembangkan aplikasi Android yang dapat membantu pemain speedrun Minecraft Bedrock Edition tanpa harus terus-menerus berpindah aplikasi.

✨ Fitur

Fitur yang Direncanakan

- [ ] Floating overlay yang dapat dipindahkan.
- [ ] Timer untuk mengukur durasi speedrun.
- [ ] Pemindaian teks di layar menggunakan OCR.
- [ ] Pembacaan koordinat Minecraft dari layar.
- [ ] Antarmuka yang sederhana dan mudah digunakan.
- [ ] Build APK otomatis menggunakan GitHub Actions.

🛠️ Teknologi

- Bahasa: Java
- Platform: Android
- Build System: Gradle
- CI/CD: GitHub Actions
- OCR: Akan menggunakan teknologi OCR yang kompatibel dengan Android.

📂 Struktur Proyek

ThafaSpeedrun/
├── .github/
│   └── workflows/
│       └── android.yml
├── app/
│   ├── build.gradle
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           └── java/
│               └── com/
│                   └── thafa/
│                       └── speedrun/
│                           ├── MainActivity.java
│                           └── OverlayService.java
├── build.gradle
├── gradle.properties
├── settings.gradle
└── README.md

🚧 Status Pengembangan

Proyek masih dalam tahap pengembangan awal. Fitur akan ditambahkan secara bertahap, dimulai dari fondasi aplikasi Android dan floating overlay, kemudian timer dan OCR.

⚖️ Penggunaan yang Bertanggung Jawab

ThafaSpeedrun ditujukan sebagai alat bantu untuk memantau informasi permainan. Penggunaan aplikasi dalam kompetisi atau leaderboard harus mengikuti peraturan penyelenggara yang bersangkutan.

📜 Lisensi

MIT 