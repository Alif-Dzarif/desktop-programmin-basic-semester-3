import java.util.Scanner;

// ===================== KELAS MENU =====================
class Menu {
    private String nama;
    private double harga;
    private String kategori; // "makanan" atau "minuman"

    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama()     { return nama; }
    public double getHarga()    { return harga; }
    public String getKategori() { return kategori; }
}

// ===================== KELAS MAIN =====================
public class Main {

    // --- Data Menu ---
    static Menu[] daftarMenu = {
        // Makanan (index 0-3)
        new Menu("Nasi Padang",  15000, "makanan"),
        new Menu("Ayam Bakar",   20000, "makanan"),
        new Menu("Mie Goreng",   12000, "makanan"),
        new Menu("Nasi Goreng",  13000, "makanan"),
        // Minuman (index 4-7)
        new Menu("Es Teh Manis",  5000, "minuman"),
        new Menu("Jus Alpukat",  12000, "minuman"),
        new Menu("Es Jeruk",      7000, "minuman"),
        new Menu("Air Mineral",   3000, "minuman")
    };

    // --- Array Pesanan (maks 4 item) ---
    static Menu[] pesananMenu   = new Menu[4];
    static int[]  pesananJumlah = new int[4];
    static int    jumlahPesanan = 0;

    // ===================== MAIN =====================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        tampilkanDaftarMenu();
        terimaPesanan(sc);
        cetakStruk();

        sc.close();
    }

    // ===================== 1. TAMPILKAN DAFTAR MENU =====================
    static void tampilkanDaftarMenu() {
        System.out.println("========================================");
        System.out.println("        RESTORAN NUSANTARA RASA         ");
        System.out.println("========================================");

        System.out.println("\n--- DAFTAR MAKANAN ---");
        System.out.printf("%-3s %-20s %s%n", "No", "Nama Menu", "Harga");
        System.out.println("------------------------------------");
        System.out.printf("%-3d %-20s Rp. %,.0f%n", 1, daftarMenu[0].getNama(), daftarMenu[0].getHarga());
        System.out.printf("%-3d %-20s Rp. %,.0f%n", 2, daftarMenu[1].getNama(), daftarMenu[1].getHarga());
        System.out.printf("%-3d %-20s Rp. %,.0f%n", 3, daftarMenu[2].getNama(), daftarMenu[2].getHarga());
        System.out.printf("%-3d %-20s Rp. %,.0f%n", 4, daftarMenu[3].getNama(), daftarMenu[3].getHarga());

        System.out.println("\n--- DAFTAR MINUMAN ---");
        System.out.printf("%-3s %-20s %s%n", "No", "Nama Menu", "Harga");
        System.out.println("------------------------------------");
        System.out.printf("%-3d %-20s Rp. %,.0f%n", 5, daftarMenu[4].getNama(), daftarMenu[4].getHarga());
        System.out.printf("%-3d %-20s Rp. %,.0f%n", 6, daftarMenu[5].getNama(), daftarMenu[5].getHarga());
        System.out.printf("%-3d %-20s Rp. %,.0f%n", 7, daftarMenu[6].getNama(), daftarMenu[6].getHarga());
        System.out.printf("%-3d %-20s Rp. %,.0f%n", 8, daftarMenu[7].getNama(), daftarMenu[7].getHarga());
        System.out.println();
    }

    // ===================== 2. TERIMA PESANAN =====================
    static void terimaPesanan(Scanner sc) {
        System.out.println("========================================");
        System.out.println("           INPUT PESANAN                ");
        System.out.println("  Format: <Nama Menu> = <Jumlah>        ");
        System.out.println("  Ketik 'selesai' untuk mengakhiri      ");
        System.out.println("  Maksimal 4 item pesanan               ");
        System.out.println("========================================");

        System.out.print("Pesanan 1: ");
        String input1 = sc.nextLine().trim();
        if (!input1.equalsIgnoreCase("selesai")) {
            prosesPesanan(input1, 0);
            jumlahPesanan = 1;

            System.out.print("Pesanan 2: ");
            String input2 = sc.nextLine().trim();
            if (!input2.equalsIgnoreCase("selesai")) {
                prosesPesanan(input2, 1);
                jumlahPesanan = 2;

                System.out.print("Pesanan 3: ");
                String input3 = sc.nextLine().trim();
                if (!input3.equalsIgnoreCase("selesai")) {
                    prosesPesanan(input3, 2);
                    jumlahPesanan = 3;

                    System.out.print("Pesanan 4: ");
                    String input4 = sc.nextLine().trim();
                    if (!input4.equalsIgnoreCase("selesai")) {
                        prosesPesanan(input4, 3);
                        jumlahPesanan = 4;
                    }
                }
            }
        }
    }

    // ===================== PROSES SATU BARIS PESANAN =====================
    static void prosesPesanan(String input, int slot) {
        String[] bagian = input.split("=");
        if (bagian.length != 2) {
            System.out.println("  [!] Format salah, pesanan diabaikan.");
            return;
        }
        String namaPesan = bagian[0].trim();
        int jumlah = 0;
        try {
            jumlah = Integer.parseInt(bagian[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("  [!] Jumlah tidak valid, pesanan diabaikan.");
            return;
        }

        Menu ditemukan = cariMenu(namaPesan);

        if (ditemukan == null) {
            System.out.println("  [!] Menu '" + namaPesan + "' tidak ditemukan, pesanan diabaikan.");
        } else {
            pesananMenu[slot]   = ditemukan;
            pesananJumlah[slot] = jumlah;
            System.out.printf("  [v] %s x%d ditambahkan.%n", ditemukan.getNama(), jumlah);
        }
    }

    // ===================== CARI MENU (tanpa loop) =====================
    static Menu cariMenu(String nama) {
        if (daftarMenu[0].getNama().equalsIgnoreCase(nama)) return daftarMenu[0];
        if (daftarMenu[1].getNama().equalsIgnoreCase(nama)) return daftarMenu[1];
        if (daftarMenu[2].getNama().equalsIgnoreCase(nama)) return daftarMenu[2];
        if (daftarMenu[3].getNama().equalsIgnoreCase(nama)) return daftarMenu[3];
        if (daftarMenu[4].getNama().equalsIgnoreCase(nama)) return daftarMenu[4];
        if (daftarMenu[5].getNama().equalsIgnoreCase(nama)) return daftarMenu[5];
        if (daftarMenu[6].getNama().equalsIgnoreCase(nama)) return daftarMenu[6];
        if (daftarMenu[7].getNama().equalsIgnoreCase(nama)) return daftarMenu[7];
        return null;
    }

    // ===================== 3. HITUNG SUBTOTAL (tanpa loop) =====================
    static double hitungSubtotal() {
        double total = 0;
        if (jumlahPesanan >= 1 && pesananMenu[0] != null) total += pesananMenu[0].getHarga() * pesananJumlah[0];
        if (jumlahPesanan >= 2 && pesananMenu[1] != null) total += pesananMenu[1].getHarga() * pesananJumlah[1];
        if (jumlahPesanan >= 3 && pesananMenu[2] != null) total += pesananMenu[2].getHarga() * pesananJumlah[2];
        if (jumlahPesanan >= 4 && pesananMenu[3] != null) total += pesananMenu[3].getHarga() * pesananJumlah[3];
        return total;
    }

    // Cek apakah ada pesanan minuman (tanpa loop)
    static boolean adaMinuman() {
        if (jumlahPesanan >= 1 && pesananMenu[0] != null && pesananMenu[0].getKategori().equals("minuman")) return true;
        if (jumlahPesanan >= 2 && pesananMenu[1] != null && pesananMenu[1].getKategori().equals("minuman")) return true;
        if (jumlahPesanan >= 3 && pesananMenu[2] != null && pesananMenu[2].getKategori().equals("minuman")) return true;
        if (jumlahPesanan >= 4 && pesananMenu[3] != null && pesananMenu[3].getKategori().equals("minuman")) return true;
        return false;
    }

    // ===================== 4. CETAK STRUK =====================
    static void cetakStruk() {
        double subtotal       = hitungSubtotal();
        double pajak          = subtotal * 0.10;
        double biayaPelayanan = 20000;
        double diskon         = 0;
        String keteranganDiskon = "";
        String keteranganBonus  = "";

        // Diskon 10% jika subtotal > Rp 100.000
        if (subtotal > 100000) {
            diskon = subtotal * 0.10;
            keteranganDiskon = "Diskon 10% (subtotal > Rp 100.000)";
        }

        // Buy 1 Get 1 minuman jika subtotal > Rp 50.000
        if (subtotal > 50000 && adaMinuman()) {
            keteranganBonus = "BONUS: Beli 1 Gratis 1 untuk salah satu minuman!";
        }

        double totalAkhir = (subtotal - diskon) + pajak + biayaPelayanan;

        System.out.println("\n========================================");
        System.out.println("           STRUK PESANAN                ");
        System.out.println("        RESTORAN NUSANTARA RASA         ");
        System.out.println("========================================");
        System.out.printf("%-22s %-5s %-12s %s%n", "Item", "Qty", "Harga/item", "Subtotal");
        System.out.println("----------------------------------------");

        if (jumlahPesanan >= 1 && pesananMenu[0] != null) cetakBarisPesanan(0);
        if (jumlahPesanan >= 2 && pesananMenu[1] != null) cetakBarisPesanan(1);
        if (jumlahPesanan >= 3 && pesananMenu[2] != null) cetakBarisPesanan(2);
        if (jumlahPesanan >= 4 && pesananMenu[3] != null) cetakBarisPesanan(3);

        System.out.println("----------------------------------------");
        System.out.printf("%-30s Rp. %,.0f%n", "Total Biaya Pesanan", subtotal);
        System.out.printf("%-30s Rp. %,.0f%n", "Pajak 10%",           pajak);
        System.out.printf("%-30s Rp. %,.0f%n", "Biaya Pelayanan",     biayaPelayanan);

        if (diskon > 0) {
            System.out.println("----------------------------------------");
            System.out.println("PENAWARAN KHUSUS:");
            System.out.printf("  %-28s -Rp. %,.0f%n", keteranganDiskon, diskon);
        }

        if (!keteranganBonus.isEmpty()) {
            if (diskon == 0) System.out.println("----------------------------------------");
            System.out.println("PENAWARAN KHUSUS:");
            System.out.println("  " + keteranganBonus);
        }

        System.out.println("========================================");
        System.out.printf("%-30s Rp. %,.0f%n", "TOTAL YANG DIBAYAR", totalAkhir);
        System.out.println("========================================");
        System.out.println("   Terima kasih telah berkunjung! :)    ");
        System.out.println("========================================");
    }

    static void cetakBarisPesanan(int idx) {
        String nama     = pesananMenu[idx].getNama();
        int    qty      = pesananJumlah[idx];
        double harga    = pesananMenu[idx].getHarga();
        double subtotal = harga * qty;
        System.out.printf("%-22s %-5d Rp.%,7.0f    Rp.%,.0f%n", nama, qty, harga, subtotal);
    }
}