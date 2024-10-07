package PBO;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AplikasiTokoBangunan {
    // Simpan data dalam ArrayList
    private ArrayList<String> data = new ArrayList<>();
    private boolean loggedIn = false;
    private String loggedInPassword;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AplikasiTokoBangunan app = new AplikasiTokoBangunan();
        if (app.login()) {
            app.runMenu();
        } else {
            System.out.println("Login gagal. Program berhenti.");
        }
    }

    // Fitur login
    private boolean login() {
        System.out.print("Masukkan Username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        if ("andrew".equals(username) && "andrew123".equals(password)) {
            loggedIn = true;
            loggedInPassword = password;
            System.out.println("Login berhasil!, Selamat Datang Andrew");
            return true;
        }
        if ("asaryant".equals(username) && "asaryant123".equals(password)) {
            loggedIn = true;
            loggedInPassword = password;
            System.out.println("Login berhasil!, Selamat Datang Asaryant");
            return true;
        }
        if ("joshua".equals(username) && "joshua123".equals(password)) {
            loggedIn = true;
            loggedInPassword = password;
            System.out.println("Login berhasil!, Selamat Datang Joshua");
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
            System.out.println("1. Tambah Data");
            System.out.println("2. Edit Data");
            System.out.println("3. Hapus Data");
            System.out.println("4. Tampilkan Semua Data");
            System.out.println("5. Cari Data Berdasarkan ID");
            System.out.println("6. Cari Data Berdasarkan Nama");
            System.out.println("7. Hitung Jumlah Data");
            System.out.println("8. Ekspor Data");
            System.out.println("9. Impor Data");
            System.out.println("10. Reset Form");
            System.out.println("11. Validasi Input");
            System.out.println("12. Logout");
            System.out.println("13. Keluar");

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
                hitungJumlahData();
                break;
            case 8:
                eksporData();
                break;
            case 9:
                imporData();
                break;
            case 10:
                resetForm();
                break;
            case 11:
                validasiInput();
                break;
            case 12:
                logout();
                break;
            case 13:
                keluarAplikasi();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                break;
        }
    }

    // Method untuk tambah data
    private void tambahData() {
        System.out.print("Masukkan ID: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        if (id.isEmpty() || nama.isEmpty()) {
            System.out.println("ID dan Nama tidak boleh kosong!");
            return;
        }

        data.add(id + " - " + nama);
        tampilkanData();
    }

    // Method untuk edit data
    private void editData() {
        System.out.print("Masukkan ID data yang ingin diubah: ");
        String id = scanner.nextLine();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).startsWith(id)) {
                System.out.print("Masukkan Nama baru: ");
                String namaBaru = scanner.nextLine();
                data.set(i, id + " - " + namaBaru);
                tampilkanData();
                return;
            }
        }
        System.out.println("Data tidak ditemukan.");
    }

    // Method untuk hapus data
    private void hapusData() {
        System.out.print("Masukkan ID data yang ingin dihapus: ");
        String id = scanner.nextLine();
        data.removeIf(d -> d.startsWith(id));
        tampilkanData();
    }

    // Method untuk menampilkan semua data
    private void tampilkanData() {
        System.out.println("\nData yang tersimpan:");
        for (String item : data) {
            System.out.println(item);
        }
    }

    // Method untuk cari data berdasarkan ID
    private void cariDataByID() {
        System.out.print("Masukkan ID yang ingin dicari: ");
        String id = scanner.nextLine();
        for (String item : data) {
            if (item.startsWith(id)) {
                System.out.println("Data ditemukan: " + item);
                return;
            }
        }
        System.out.println("Data tidak ditemukan.");
    }

    // Method untuk cari data berdasarkan nama
    private void cariDataByNama() {
        System.out.print("Masukkan Nama yang ingin dicari: ");
        String nama = scanner.nextLine();
        for (String item : data) {
            if (item.contains(nama)) {
                System.out.println("Data ditemukan: " + item);
                return;
            }
        }
        System.out.println("Data tidak ditemukan.");
    }

    // Method untuk hitung jumlah data
    private void hitungJumlahData() {
        System.out.println("Jumlah data: " + data.size());
    }

    // Method untuk ekspor data ke file
    private void eksporData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Bangunan.txt"))) {
            for (String item : data) {
                writer.println(item);
            }
            System.out.println("Data berhasil diekspor ke: " + new File("Bangunan.txt").getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Gagal mengekspor data.");
            e.printStackTrace();
        }
    }

    // Method untuk impor data dari file
    private void imporData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Bangunan.txt"))) {
            data.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            System.out.println("Data berhasil diimpor.");
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
        System.out.print("Masukkan ID: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        if (id.isEmpty() || nama.isEmpty()) {
            System.out.println("Input tidak valid! ID dan Nama harus diisi.");
        } else {
            System.out.println("Input valid.");
        }
    }


    private void logout() {
        while (true) {
            System.out.print("Masukkan Password untuk logout: ");
            String password = scanner.nextLine();

            if (password.equals(loggedInPassword)) { // Cek apakah password cocok
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

