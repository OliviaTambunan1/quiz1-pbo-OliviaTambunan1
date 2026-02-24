import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 

enum JenisAsrama { LAKI_LAKI, PEREMPUAN }
enum StatusPesanan { MENUNGGU_DIANGKUT, SEDANG_DICUCI, SIAP_DIAMBIL, SELESAI }


class ItemPakaian {
    String jenisPakaian;
    int jumlah;

    public ItemPakaian(String jenisPakaian, int jumlah) {
        this.jenisPakaian = jenisPakaian;
        this.jumlah = jumlah;
    }
}


class PesananLaundry {
    String nimMahasiswa;
    String namaMahasiswa;
    String nomorKamar;
    JenisAsrama asrama;
    List<ItemPakaian> daftarPakaian;
    StatusPesanan statusCucian;

    public PesananLaundry(String nimMahasiswa, String namaMahasiswa, String nomorKamar, JenisAsrama asrama) {
        this.nimMahasiswa = nimMahasiswa;
        this.namaMahasiswa = namaMahasiswa;
        this.nomorKamar = nomorKamar;
        this.asrama = asrama;
        this.daftarPakaian = new ArrayList<>();
        this.statusCucian = StatusPesanan.MENUNGGU_DIANGKUT; 
    }

    public void tambahPakaian(String jenis, int jumlah) {
        daftarPakaian.add(new ItemPakaian(jenis, jumlah));
    }

    public int getTotalPotong() {
        int total = 0;
        for (ItemPakaian item : daftarPakaian) {
            total += item.jumlah;
        }
        return total;
    }

    public void cetakBuktiPengambilan() {
        System.out.println("\n=====================================");
        System.out.println("        BUKTI PENGAMBILAN LAUNDRY    ");
        System.out.println("=====================================");
        System.out.println("NIM / Nama : " + this.nimMahasiswa + " / " + this.namaMahasiswa);
        System.out.println("Asrama     : " + this.nomorKamar + " (" + this.asrama + ")");
        System.out.println("-------------------------------------");
        for (ItemPakaian item : daftarPakaian) {
            System.out.println("- " + item.jenisPakaian + " : " + item.jumlah + " potong");
        }
        System.out.println("-------------------------------------");
        System.out.println("TOTAL PAKAIAN : " + getTotalPotong() + " potong");
        System.out.println("STATUS        : LENGKAP & " + this.statusCucian);
        System.out.println("Mohon periksa kembali pakaian Anda.");
        System.out.println("Terima kasih telah menggunakan Laundry Del!");
        System.out.println("=====================================\n");
    }
}

class PengelolaLaundry {
    List<PesananLaundry> databasePesanan = new ArrayList<>();

    public void terimaPesanan(PesananLaundry pesanan) {
        databasePesanan.add(pesanan);
        System.out.println(">> Berhasil: Cucian " + pesanan.namaMahasiswa + " masuk ke sistem.");
    }

    public PesananLaundry cariByNIM(String nim) {
        for (PesananLaundry pesanan : databasePesanan) {
            if (pesanan.nimMahasiswa.equals(nim)) {
                return pesanan;
            }
        }
        return null;
    }
}

public class Driver3 {
    public static void main(String[] args) {
        PengelolaLaundry sistemUtama = new PengelolaLaundry();
        Scanner input = new Scanner(System.in);
        boolean jalan = true;

        System.out.println("Selamat Datang di Sistem Operasional Laundry Del");

        while (jalan) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Input Cucian Baru");
            System.out.println("2. Status Cucian");
            System.out.println("3. Ambil Cucian ");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");
            int pilihan = input.nextInt();
            input.nextLine(); 

            switch (pilihan) {
                case 1:
                    // PROSES INPUT
                    System.out.println("\n-- INPUT DATA MAHASISWA --");
                    System.out.print("Masukkan NIM: ");
                    String nim = input.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan No Kamar (Misal: P-102): ");
                    String kamar = input.nextLine();
                    
                    System.out.print("Pilih Asrama (1: Laki-laki, 2: Perempuan): ");
                    int pilAsrama = input.nextInt();
                    input.nextLine(); 
                    JenisAsrama asrama = (pilAsrama == 1) ? JenisAsrama.LAKI_LAKI : JenisAsrama.PEREMPUAN;

                    PesananLaundry pesananBaru = new PesananLaundry(nim, nama, kamar, asrama);

                    boolean tambahPakaian = true;
                    while (tambahPakaian) {
                        System.out.print("Jenis Pakaian (Cth: Kemeja): ");
                        String jenis = input.nextLine();
                        System.out.print("Jumlah (angka): ");
                        int jumlah = input.nextInt();
                        input.nextLine(); 

                        pesananBaru.tambahPakaian(jenis, jumlah);

                        System.out.print("Tambah pakaian lain? (y/n): ");
                        String jawab = input.nextLine();
                        if (jawab.equalsIgnoreCase("n")) {
                            tambahPakaian = false;
                        }
                    }
                    sistemUtama.terimaPesanan(pesananBaru);
                    break;

                case 2:
                    System.out.print("\nMasukkan NIM yang cuciannya sudah selesai dicuci: ");
                    String nimUpdate = input.nextLine();
                    PesananLaundry cucianUpdate = sistemUtama.cariByNIM(nimUpdate);
                    
                    if (cucianUpdate != null) {
                        cucianUpdate.statusCucian = StatusPesanan.SIAP_DIAMBIL;
                        System.out.println(">> Status cucian " + cucianUpdate.namaMahasiswa + " diupdate menjadi SIAP DIAMBIL.");
                    } else {
                        System.out.println(">> NIM tidak ditemukan di sistem.");
                    }
                    break;

                case 3:
                    System.out.print("\nMasukkan NIM Mahasiswa yang mengambil cucian: ");
                    String nimAmbil = input.nextLine();
                    PesananLaundry cucianAmbil = sistemUtama.cariByNIM(nimAmbil);

                    if (cucianAmbil != null) {
                        if (cucianAmbil.statusCucian == StatusPesanan.SIAP_DIAMBIL) {
                            cucianAmbil.statusCucian = StatusPesanan.SELESAI; // Ubah status akhir
                            cucianAmbil.cetakBuktiPengambilan(); // Keluarkan OUTPUT
                        } else {
                            System.out.println(">> Maaf, cucian atas nama " + cucianAmbil.namaMahasiswa + " masih berstatus: " + cucianAmbil.statusCucian);
                        }
                    } else {
                        System.out.println(">> NIM tidak ditemukan di sistem.");
                    }
                    break;

                case 4:
                    System.out.println("Keluar dari sistem. Terima kasih!");
                    jalan = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
        input.close();
    }
}