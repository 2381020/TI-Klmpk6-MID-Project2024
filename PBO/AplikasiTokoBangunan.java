package PBO;
import java.io.*;
import java.util.*;

class BarangBangunan {
    private String id;
    private String nama;
    private double harga;

    public BarangBangunan(String id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return id + " - " + nama + " - Rp " + harga;
    }
}

public class AplikasiTokoBangunan {
    // Simpan data barang dalam ArrayList
    private ArrayList<BarangBangunan> data = new ArrayList<>(); // Ubah tipe data menjadi ArrayList<BarangBangunan>
    private boolean loggedIn = false;
    private String loggedInPassword;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AplikasiTokoBangunan app = new AplikasiTokoBangunan();

        // Menambahkan data barang secara otomatis
        app.data.add(new BarangBangunan("B001", "Semen", 50000));
        app.data.add(new BarangBangunan("B002", "Bata Merah", 1000));
        app.data.add(new BarangBangunan("B003", "Cat Tembok", 75000));
        app.data.add(new BarangBangunan("B004", "Keramik 40x40", 120000));
        app.data.add(new BarangBangunan("B005", "Pasir 1 Kubik", 600000));
        app.data.add(new BarangBangunan("B006", "Kayu Balok", 30000));
        app.data.add(new BarangBangunan("B007", "Paku 3cm", 15000));
        app.data.add(new BarangBangunan("B008", "Besi Beton 12mm", 90000));
        app.data.add(new BarangBangunan("B009", "Pintu Kayu Jati", 3500000));
        app.data.add(new BarangBangunan("B010", "Genteng", 5000));

        if (app.login()) {
            app.runMenu();
        } else {
            System.out.println("Login gagal. Program berhenti.");
        }
    }

    private boolean login() {
        // Simpan username dan password dalam HashMap
        HashMap<String, String> users = new HashMap<>();
        users.put("andrew", "andrew123");
        users.put("asaryant", "asaryant123");
        users.put("joshua", "joshua123");

        System.out.print("Masukkan Username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        // Cek apakah username ada dan password cocok
        if (users.containsKey(username) && users.get(username).equals(password)) {
            loggedIn = true;
            loggedInPassword = password;
            System.out.println("Login berhasil!, Selamat Datang " + username.substring(0, 1).toUpperCase() + username.substring(1));
            return true;
        } else {
            System.out.println("Login gagal!");
            return false;
        }
    }


    // Menu utama
    private void runMenu() {
        while (true) {
            System.out.println("\nDaftar fitur: ");
            System.out.println("1. Tambah Data Barang");
            System.out.println("2. Edit Data Barang");
            System.out.println("3. Hapus Data Barang");
            System.out.println("4. Tampilkan Semua Barang");
            System.out.println("5. Cari Barang Berdasarkan ID");
            System.out.println("6. Cari Barang Berdasarkan Nama");
            System.out.println("7. Sortir Barang");
            System.out.println("8. Hitung Jumlah Barang");
            System.out.println("9. Ekspor Data Barang");
            System.out.println("10. Impor Data Barang");
            System.out.println("11. Reset Form");
            System.out.println("12. Validasi Input");
            System.out.println("13. Logout");
            System.out.println("14. Keluar");

            System.out.print("Masukkan pilihan Anda: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // membersihkan buffer
            menuUtama(pilihan);
        }
    }

    // Switch-case untuk menu utama
    private void menuUtama(int pilihan) {
        switch (pilihan) {
            case 1:
                tambahData();
                break;
            case 2:
                editData();
                break;
            case 3:
                hapusData();
                break;
            case 4:
                tampilkanData();
                break;
            case 5:
                cariDataByID();
                break;
            case 6:
                cariDataByNama();
                break;
            case 7:
                menuSortir();
                break;
            case 8:
                hitungJumlahData();
                break;
            case 9:
                eksporData();
                break;
            case 10:
                imporData();
                break;
            case 11:
                resetForm();
                break;
            case 12:
                validasiInput();
                break;
            case 13:
                logout();
                break;
            case 14:
                keluarAplikasi();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                break;
        }
    }


    // Method untuk tambah data barang
    private void tambahData() {
        System.out.print("Masukkan ID Barang: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Barang: ");
        String nama = scanner.nextLine();

        // Validate that ID and Nama are not empty
        if (id.isEmpty() || nama.isEmpty()) {
            System.out.println("ID dan Nama tidak boleh kosong!");
            return;
        }

        // Validate if ID is unique (if necessary)
        for (BarangBangunan barang : data) {
            if (barang.getId().equals(id)) {
                System.out.println("ID Barang sudah ada. Gunakan ID yang berbeda.");
                return;
            }
        }

        // Validate numeric input for harga
        double harga = 0;
        while (true) {
            System.out.print("Masukkan Harga Barang: ");
            if (scanner.hasNextDouble()) {
                harga = scanner.nextDouble();
                scanner.nextLine(); // Clear buffer
                break;
            } else {
                System.out.println("Harga harus berupa angka! Silakan coba lagi.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Tambahkan objek BarangBangunan ke dalam ArrayList
        data.add(new BarangBangunan(id, nama, harga));

        // Tampilkan data yang baru ditambahkan atau semua data yang ada
        tampilkanData();
    }


    // Method untuk edit data barang
    private void editData() {
        System.out.print("Masukkan ID barang yang ingin diubah: ");  //DIBUAT OLEH ASARYANT
        String id = scanner.nextLine();

        // Mencari barang berdasarkan ID
        BarangBangunan barangDitemukan = null;
        for (BarangBangunan barang : data) {
            if (barang.getId().equals(id)) {
                barangDitemukan = barang;
                break;
            }
        }

        // Jika barang dengan ID yang dicari tidak ditemukan
        if (barangDitemukan == null) {
            System.out.println("Data barang dengan ID tersebut tidak ditemukan.");
            return;
        }

        // Proses edit jika barang ditemukan
        System.out.print("Masukkan Nama baru (Kosongkan jika tidak ingin mengubah): ");
        String namaBaru = scanner.nextLine();

        // Hanya mengubah nama jika input baru tidak kosong
        if (!namaBaru.isEmpty()) {
            barangDitemukan.setNama(namaBaru);
        }

        // Loop untuk validasi input harga baru
        double hargaBaru = 0;
        while (true) {
            System.out.print("Masukkan Harga baru (Masukkan angka): ");
            if (scanner.hasNextDouble()) {
                hargaBaru = scanner.nextDouble();
                scanner.nextLine(); // Membersihkan buffer
                break;
            } else {
                System.out.println("Harga harus berupa angka! Silakan coba lagi.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Set the new price
        barangDitemukan.setHarga(hargaBaru);

        System.out.println("Data barang berhasil diperbarui!");
        tampilkanData(); // Tampilkan data yang sudah diubah
    }

    // Method untuk hapus data barang
    private void hapusData() {
        System.out.print("Masukkan ID barang yang ingin dihapus: ");  // DIBUAT OLEH ASARYANT
        String id = scanner.nextLine();

        // Cari barang berdasarkan ID
        boolean barangDitemukan = false;
        for (BarangBangunan barang : data) {
            if (barang.getId().equals(id)) {
                // Tanyakan konfirmasi sebelum menghapus
                System.out.print("Apakah Anda yakin ingin menghapus barang ini? (y/n): ");
                String confirm = scanner.nextLine();

                if (!confirm.equalsIgnoreCase("y")) {
                    System.out.println("Penghapusan dibatalkan.");
                    return;
                }

                // Hapus barang jika konfirmasi diberikan
                barangDitemukan = data.remove(barang);
                break;
            }
        }

        // Memberikan umpan balik kepada pengguna
        if (barangDitemukan) {
            System.out.println("Data barang dengan ID " + id + " berhasil dihapus.");
        } else {
            System.out.println("Data barang dengan ID " + id + " tidak ditemukan.");
        }

        // Tampilkan data setelah proses hapus
        tampilkanData();
    }

    // Method untuk menampilkan semua barang
    private void tampilkanData() {
        System.out.println("\nData barang yang tersimpan:");
        for (BarangBangunan item : data) {
            System.out.println(item); // toString method dari BarangBangunan akan dipanggil otomatis
        }
    }

    // Method untuk cari barang berdasarkan ID
    private void cariDataByID() {
        System.out.print("Masukkan ID barang yang ingin dicari: ");
        String id = scanner.nextLine();
        for (BarangBangunan item : data) {
            if (item.getId().equals(id)) {
                System.out.println("Barang ditemukan: " + item);
                return;
            }
        }
        System.out.println("Barang tidak ditemukan.");
    }

    // Method untuk cari barang berdasarkan nama
    private void cariDataByNama() {
        System.out.print("Masukkan Nama barang yang ingin dicari: ");
        String nama = scanner.nextLine();
        for (BarangBangunan item : data) {
            if (item.getNama().toLowerCase().contains(nama.toLowerCase())) {
                System.out.println("Barang ditemukan: " + item);
                return;
            }
        }
        System.out.println

                ("Barang tidak ditemukan.");
    }

    //Method untuk menu sortir
    private void menuSortir() {
        System.out.println("\nPilih metode sortir: ");
        System.out.println("1. Berdasarkan ID");
        System.out.println("2. Berdasarkan Nama");
        System.out.println("3. Berdasarkan Harga");

        System.out.println("Masukkan pilihan anda: ");
        int pilihanSortir = scanner.nextInt();
        scanner.nextLine();

        switch (pilihanSortir){
            case 1:
                Collections.sort(data, Comparator.comparing(BarangBangunan::getId));
                System.out.println("Data barang diurutkan berdasarkan ID.");
                tampilkanData();
                break;
            case 2:
                Collections.sort(data, Comparator.comparing(BarangBangunan::getNama));
                System.out.println("Data barang diurutkan berdasarkan Nama.");
                tampilkanData();
                break;
            case 3:
                Collections.sort(data, Comparator.comparingDouble(BarangBangunan::getHarga));
                System.out.println("Data barang diurutkan berdasarkan Harga.");
                tampilkanData();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                break;
        }
    }

    // Method untuk hitung jumlah barang
    private void hitungJumlahData() {
        System.out.println("Jumlah barang: " + data.size());
    }

    // Method untuk ekspor data barang ke file
    private void eksporData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:/Users/AdrewJordy/IdeaProjects/TI-Klmpk6-MID-Project2024/BarangBangunan.txt", true))) {
            for (BarangBangunan item : data) {
                writer.println(item);
            }
            System.out.println("Data barang berhasil diekspor ke: " + new File("C:/Users/AdrewJordy/IdeaProjects/TI-Klmpk6-MID-Project2024/BarangBangunan.txt").getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Gagal mengekspor data.");
            e.printStackTrace();
        }
    }

    // Method untuk impor data barang dari file
    private void imporData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/AdrewJordy/IdeaProjects/TI-Klmpk6-MID-Project2024/BarangBangunan.txt"))) {
            data.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 3) {
                    data.add(new BarangBangunan(parts[0], parts[1], Double.parseDouble(parts[2].replace("Rp ", ""))));
                }
            }
            System.out.println("Data barang berhasil diimpor.");
            tampilkanData();
        } catch (IOException e) {
            System.out.println("Gagal mengimpor data.");
            e.printStackTrace();
        }
    }

    // Method untuk reset form
    private void resetForm() {
        System.out.println("Form reset.");
    }

    // Method untuk validasi input
    private void validasiInput() {
        System.out.print("Masukkan ID Barang: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Barang: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Harga Barang: ");
        double harga = scanner.nextDouble();
        scanner.nextLine(); // membersihkan buffer

        if (id.isEmpty() || nama.isEmpty()) {
            System.out.println("Input tidak valid! ID dan Nama harus diisi.");
        } else if (harga <= 0) {
            System.out.println("Input tidak valid! Harga harus lebih dari 0.");
        } else {
            System.out.println("Input valid.");
        }
    }

    private void logout() {
        while (true) {
            System.out.print("Masukkan Password untuk logout: ");
            String password = scanner.nextLine();

            if (password.equals(loggedInPassword)) {
                loggedIn = false;
                System.out.println("Anda telah logout.");
                break;
            } else {
                System.out.println("Password salah, logout gagal. Coba lagi.");
            }
        }
    }

    // Method untuk keluar dari aplikasi
    private void keluarAplikasi() {
        if (loggedIn) {
            System.out.println("Anda harus logout terlebih dahulu sebelum keluar!");
        } else {
            System.out.println("Aplikasi ditutup.");
            System.exit(0);
        }
    }
}
