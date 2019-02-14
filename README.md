
# Tantangan Android dan iOS

Untuk bisa bergabung di HiApp dengan posisi Android atau iOS engineer, Anda harus menyelesaikan tantangan berikut ini.

*Catatan: Tantangan ini seharusnya tidak memerlukan waktu pengerjaan lebih dari 8 jam.*

### Prasyarat

- Perlu dicatat bahwa tantangan ini membutuhkan pengetahuan dasar Java atau Kotlin pada Android, Objective-C atau Swift pada iOS. 
- Jika Anda tidak menguasai Java, [luangkan waktu beberapa saat untuk mempelajari dasarnya di sini](http://mobile.tutsplus.com/series/learn-java-android-development/)
- Jika Anda tidak menguasai Kotlin, [luangkan waktu beberapa saat untuk mempelajari dasarnya di sini](https://kotlinlang.org/docs/tutorials/)
- Jika Anda tidak menguasai Objective-C, [luangkan waktu beberapa saat untuk mempelajari dasarnya di sini](http://cocoadevcentral.com/d/learn_objectivec/)
- Jika Anda tidak menguasai Swift, [luangkan waktu beberapa saat untuk mempelajari dasarnya di sini](https://learnswift.tips/)
- Jika Anda tidak memahami Android [silakan pelajari di sini](http://d.android.com/resources/index.html).  
- Jika Anda tidak memahami iOS [silakan pelajari di sini](https://www.apple.com/everyone-can-code/).
- Untuk Android, Anda akan membutuhkan [Java](http://www.java.com/en/download/), [Android Studio](http://developer.android.com/sdk/installing/studio.html) atau [IntelliJ](http://www.jetbrains.com/idea/download/), dan [Android SDK](http://d.android.com/sdk/index.html) ter-*install* di perangkat kerja Anda.
- Untuk iOS, Anda akan memerlukan [MacOS](https://www.apple.com/lae/macos/mojave/) dan [Xcode](https://developer.apple.com/xcode/)

## Penjelasan
Permasalahan umum yang terjadi pada aplikasi pesan instan yaitu pengelompokan berdasarkan kriteria tertentu. Pada Code Challenge kali ini, terdapat masalah untuk pengelompokan pesan gambar dan kontak.

Terdapat empat jenis pesan yang dipertukarkan, yaitu teks, gambar, dokumen, dan kontak. Penjelasan dari setiap jenis pesan adalah sebagai berikut:

1. Pesan Teks
	- Pesan teks hanya berisi teks dan tidak memiliki lampiran.
	- Pesan teks tidak dikelompokkan.
2. Pesan Gambar
	- Pesan gambar merupakan pesan yang berisi lampiran berupa gambar.
	- Pesan gambar berisi tautan yang merujuk pada berkas gambar.
	- Judul pada pesan gambar disertakan pada atribut body pada pesan utama.
	- Pesan gambar dikelompokkan berdasarkan kriteria.
3. Pesan Dokumen
	- Pesan dokumen merupakan pesan teks yang berisi lampiran berupa dokumen.
	- Pesan dokumen berisi tautan yang merujuk pada berkas dokumen.
	- Pesan dokumen tidak memiliki judul.
	- Pesan dokumen tidak dikelompokkan.
4. Pesan Kontak
	- Pesan kontak merupakan pesan teks dengan lampiran berupa info kontak.
	- Pesan kontak berisi nama kontak.
	- Pesan kontak tidak memiliki judul.
	- Pesan kontak dikelompokkan berdasarkan kriteria.

Daftar pesan dapat dikelompokkan berdasarkan kriteria sebagai berikut:
1.  Pesan Gambar
	- Tanggal yang sama
	- Pengirim yang sama
	- Tidak ada judul
	- Berulang minimal 4 kali
2. Pesan Kontak
	- Tanggal yang sama
	- Pengirim yang sama
	- Berulang minimal 2 kali

## Tugas

1. *Fork* repositori ini (jika Anda tidak tahu bagaimana caranya, Google adalah teman Anda)
2. Buatlah sebuah *source folder* untuk kemudian diisi dengan kode Anda. 
3. Di dalam *source directory* Anda, buat sebuah aplikasi Android atau iOS yang dapat melakukan hal berikut:
	- Tampilkan semua pesan yang ada dalam data set berdasarkan tanggal/*timestamp* *ascending*.
    - Kelompokkan data yang tersedia pada file `message_dataset.json` sesuai dengan kriteria. Data yang dapat dikelompokkan disajikan dalam bentuk *group*/*collection* (misalkan tampilan yg berbeda atau informasi *collection* seperti *collage* pada aplikasi pesan instan).
    Contohnya:
      - pesan teks
      - pesan kontak
      - collection message [pesan gambar, pesan gambar, pesan gambar, pesan gambar]
      - pesan dokumen
      - collection message [pesan kontak, pesan kontak]
      - pesan gambar
      - pesan teks
      - pesan teks
      - dst..
    - Penyajian data dalam bentuk list.
4. *Commit* dan *Push* kode Anda ke repositori baru Anda.
5. Kirimkan *pull request* kepada kami. 
6. Kami akan melakukan peninjauan terhadap kode Anda dan Anda akan kami hubungi kembali.

*Tantangan ini dibuat oleh [Donn Felker](https://github.com/donnfelker/) di repositori [Example Android Challenge](https://github.com/donnfelker/example-android-challenge) miliknya dan telah diubah sesuai kebutuhan*
