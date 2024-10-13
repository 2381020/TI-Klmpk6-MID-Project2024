package PBO;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

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

   //Fitur Login
    private boolean login() {
        System.out.println("Masukkan Username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        if ("Andrew".equals(username) && "andrew123".equals(password)) {
            loggedIn = true;
            loggedInPassword = password;
            System.out.println("Login Berhasil!, Selamat Datang Andrew");
            return true;
        }
        if ("Asaryant".equals(username) && "asaryant123".equals(password)) {
            loggedIn = true;
            loggedInPassword = password;
            System.out.println("Login Berhasil!, Selamat Datang Asaryant");
            return true;
        }
        if ("Joshua".equals(username) && "joshua123".equals(password)) {
            loggedIn = true;
            loggedInPassword = password;
            System.out.println("Login Berhasil!, Selamat Datang Joshua");
            return true;
        }
        return false;
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

    //Method Untuk Tambah Data Barang
    private void tambahData(){
        System.out.print("Masukkan ID Barang: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Barang: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Harga Barang: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();//Membersihkan Buffer

        if(id.isEmpty()||nama.isEmpty()){
            System.out.println("ID dan Nama Tidak Boleh Kosong!");
            return;
        }
        data.add(new BarangBangunan(id, nama, harga));
        //Tambahkan Objek Barang Bangunann ke Dalam ArrayList
        tampilkanData();
    }

    //Method Untuk Edit Data Barang
    private void editData(){
        System.out.print("Masukkan ID barang yang ingin diubah: ");
        String id = scanner.nextLine();
        for(BarangBangunan barang : data){
            if(barang.getId().equals(id)){
                System.out.print("Masukkan Nama Baru: ");
                String namaBaru = scanner.nextLine();
                System.out.print("Masukkan Harga Baru: ");
                double hargaBaru = scanner.nextDouble();
                scanner.nextLine();//Membersihkan Buffer
                barang.setNama(namaBaru);
                barang.setHarga(hargaBaru);
                tampilkanData();
                return;
            }
        }
        System.out.println("Data Barang Tidak Ditemukan.");
    }

    //Method Untuk Hapus Data Barang
    private void hapusData(){
        System.out.print("Masukkan ID Barang yang ingin dihapus: ");
        String id = scanner.nextLine();
        data.removeIf(barang -> barang.getId().equals(id));//Gunakan Method GetID Untuk Mencari Barang
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

