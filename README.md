# Gradecord - Aplikasi Pengelolaan Nilai Siswa

## Deskripsi Proyek
Gradecord adalah aplikasi desktop sederhana berbasis javafx 21 yang digunakan untuk merekam nilai siswa.

Proyek ini dibuat untuk memenuhi tugas UTS pada mata kuliah Algoritma dan Pemrograman 2.

---

## Studi Kasus
Sebuah sekolah ingin memiliki aplikasi desktop sederhana untuk mengelola nilai 3 mata pelajaran siswa. Aplikasi ini harus bisa:

- Membaca dan menyimpan data siswa dari/ke file teks (CSV).
- Menampilkan daftar siswa pada GUI.
- Menambahkan data siswa baru ke file dan tampilan.
- Mengurutkan siswa berdasarkan nilai akhir secara descending.
- Mencari siswa berdasarkan nama atau nim menggunakan linear search.
- Menghitung total dan rata-rata nilai akhir menggunakan rekursi.

---

## Fitur Utama
1. **Membaca Data Siswa**  
   Data siswa dibaca dari file `siswa.csv` yang berisi informasi NIM, nama, dan nilai 3 mata pelajaran.

2. **Menampilkan Daftar Siswa**  
   Daftar siswa ditampilkan dalam tabel pada GUI menggunakan JavaFX.

3. **Menambahkan Data Siswa Baru**  
   Pengguna dapat menambahkan data siswa baru melalui form, yang kemudian disimpan ke file dan langsung ditampilkan di GUI.

4. **Mengurutkan Siswa Berdasarkan Nilai Akhir**  
   Siswa diurutkan secara descending berdasarkan nilai akhir (rata-rata nilai 3 mata pelajaran).

5. **Mencari Siswa Berdasarkan Nama atau NIM**  
   Pencarian menggunakan metode **linear search**, karena urutan data pada `TableView` dapat berubah secara dinamis sesuai interaksi pengguna (misalnya saat mengurutkan tabel). Linear search memastikan hasil pencarian tetap akurat tanpa tergantung pada urutan data. Meski begitu metode pencarian menggunakan metode **binary search** untuk menemukan siswa berdasarkan nama atau NIM tetap ada pada source code dengan komentar.

6. **Menghitung Total dan Rata-rata Nilai Akhir**  
   Menggunakan rekursi untuk menghitung jumlah total nilai akhir dan rata-rata nilai seluruh siswa.

7. **Prinsip OOP**  
   Penggunaan class dan method yang terstruktur untuk memisahkan logika aplikasi, model data, dan tampilan.

---

## Struktur Proyek

```
gradecord
│
├── src
│   └── main
│       ├── java
│       │   └── com/uts/gradecord
│       │       ├── controller
│       │       │   ├── FormController.java
│       │       │   ├── FormRowController.java
│       │       │   └── MainController.java
│       │       ├── model
│       │       │   └── Siswa.java
│       │       ├── util
│       │       │   └── FileHandler.java
│       │       ├── GradecordApp.java
│       │       └── module-info.java
│       └── resources
│           └── com/uts/gradecord
│               ├── form-row.fxml
│               ├── form-view.fxml
│               └── main-view.fxml
│
├── siswa.csv
├── pom.xml
├── .gitignore
└── mvnw, mvnw.cmd
```

- **controller**: Berisi kelas pengontrol untuk mengatur interaksi GUI dan logika aplikasi.
- **model**: Berisi kelas `Siswa` yang merepresentasikan data siswa.
- **util**: Berisi kelas `FileHandler` untuk operasi baca/tulis file CSV.
- **resources**: Berisi file FXML untuk layout GUI.
- **siswa.csv**: File data siswa yang digunakan aplikasi.

---

## Cara Menjalankan Aplikasi

1. Pastikan Anda sudah menginstall JDK 21 atau lebih baru dan Maven.
2. Clone repository ini atau download source code.
3. Buka terminal/command prompt di folder proyek.
4. Jalankan perintah berikut untuk menjalankan aplikasi:

   ```
   mvn clean javafx:run
   ```

5. Aplikasi akan terbuka dengan tampilan GUI untuk mengelola data siswa.

---

## Penjelasan Implementasi Fitur

### Membaca dan Menyimpan Data Siswa
- File `siswa.csv` berisi data siswa dengan format CSV.
- `FileHandler.java` bertugas membaca file ini dan mengubahnya menjadi list objek `Siswa`.
- Penambahan data baru juga disimpan ke file ini agar persistensi data terjaga.

### Menampilkan Daftar Siswa
- Menggunakan JavaFX TableView di `main-view.fxml`.
- Data siswa yang sudah dibaca ditampilkan secara dinamis.

### Menambahkan Data Siswa Baru
- Form input di `form-view.fxml` memungkinkan pengguna memasukkan data siswa baru.
- Data baru ditambahkan ke list dan disimpan ke file CSV.

### Mengurutkan Siswa Berdasarkan Nilai Akhir
- Nilai akhir dihitung sebagai rata-rata dari 3 mata pelajaran.
- Sorting dilakukan secara descending menggunakan method di controller.

### Mencari Siswa Berdasarkan Nama atau NIM
- Pencarian dilakukan menggunakan **linear search**.
- Metode ini dipilih karena urutan data dalam `TableView` bisa berubah secara dinamis sesuai interaksi pengguna (misalnya saat mengurutkan kolom).
- Linear search menjamin hasil pencarian tetap akurat tanpa tergantung pada urutan data.

### Menghitung Total dan Rata-rata Nilai Akhir dengan Rekursi
- Fungsi rekursif menghitung jumlah nilai akhir seluruh siswa.
- Rata-rata dihitung dari total nilai dibagi jumlah siswa.

### Prinsip OOP
- Setiap kelas memiliki tanggung jawab spesifik.
- Pemisahan antara model, view, dan controller (MVC pattern) diterapkan.

---

## Video Penjelasan
Link video penjelasan disertakan [di sini](https://youtu.be/rnreYW9-11Y)
