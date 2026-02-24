import java.util.Scanner;

class Siswa {
    String nama;
    char jenisKelamin; // 'P' atau 'L'
    int nilai;

    public Siswa(String nama, char jenisKelamin, int nilai) {
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.nilai = nilai;
    }
}

public class Driver2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah total data (N): ");
        int n = input.nextInt();
        input.nextLine(); 
        Siswa[] daftarSiswa = new Siswa[n];

        System.out.println("\n--- Masukkan Data Siswa ---");
        for (int i = 0; i < n; i++) {
            System.out.println("Data ke-" + (i + 1));
            System.out.print("Nama: ");
            String nama = input.nextLine();
            
            System.out.print("Jenis Kelamin (P/L): ");
            char jk = input.nextLine().toUpperCase().charAt(0);
            
            System.out.print("Nilai: ");
            int nilai = input.nextInt();
            input.nextLine(); 
            System.out.println("---------------------------");

            daftarSiswa[i] = new Siswa(nama, jk, nilai);
        }

        System.out.println("\n--- Pilih Kode Kelompok ---");
        System.out.println("1 = Kelompok Perempuan");
        System.out.println("2 = Kelompok Laki-laki");
        System.out.println("3 = Kelompok Nilai Ganjil");
        System.out.println("4 = Kelompok Nilai Genap");
        System.out.print("Masukkan kode kelompok (1/2/3/4): ");
        int kodeKelompok = input.nextInt();

        int totalNilai = 0;
        System.out.println("\n--- Rincian Perhitungan ---");

        for (int i = 0; i < n; i++) {
            boolean sesuaiKriteria = false;

            if (kodeKelompok == 1 && daftarSiswa[i].jenisKelamin == 'P') {
                sesuaiKriteria = true;
            } else if (kodeKelompok == 2 && daftarSiswa[i].jenisKelamin == 'L') {
                sesuaiKriteria = true;
            } else if (kodeKelompok == 3 && daftarSiswa[i].nilai % 2 != 0) {
                sesuaiKriteria = true;
            } else if (kodeKelompok == 4 && daftarSiswa[i].nilai % 2 == 0) {
                sesuaiKriteria = true;
            }

            if (sesuaiKriteria) {
                totalNilai += daftarSiswa[i].nilai;
                System.out.println("- Menambahkan nilai " + daftarSiswa[i].nama + " (" + daftarSiswa[i].nilai + ")");
            }
        }

        System.out.println("\n--- Hasil Akhir ---");
        System.out.println("Total nilai untuk kelompok " + kodeKelompok + " adalah: " + totalNilai);

        input.close();
    }
}
