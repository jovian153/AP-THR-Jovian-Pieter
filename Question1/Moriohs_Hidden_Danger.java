import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays; // Tambahan untuk mencetak array dengan format [a, b, c]

public class Moriohs_Hidden_Danger {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Stack<String> Input = new Stack<>();

        System.out.println("Masukkan input (contoh: holly may interesting MARCH corey November junior january paul december):");
        String NamadanBulan = sc.nextLine();
        String[] pemisah = NamadanBulan.split(" ");

        // 1. Memasukkan input ke Stack Pertama (Sesuai Constraint 2)
        for(int i = 0; i < pemisah.length; i++){
            Input.push(pemisah[i]);
        }

        // 2. Mengeluarkan isi Stack dan membuat objek Pasangan
        Pasangan[] daftarPasangan = new Pasangan[pemisah.length / 2];
        int index = 0;
        
        while(!Input.isEmpty()){
            // Karena LIFO, yang keluar duluan adalah bulan, baru nama
            String bulan = Input.pop();
            String nama = Input.pop();

            Pasangan p = new Pasangan(nama, bulan);
            daftarPasangan[index] = p;
            index++;
        }

        // 3. Mengurutkan array menggunakan Bubble Sort (Bulan 1 sampai 12)
        int n = daftarPasangan.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (daftarPasangan[j].getUrutanBulan() > daftarPasangan[j + 1].getUrutanBulan()) {
                    // Proses Swap (Tukar posisi)
                    Pasangan temp = daftarPasangan[j];
                    daftarPasangan[j] = daftarPasangan[j + 1];
                    daftarPasangan[j + 1] = temp;
                }
            }
        }

        // 4. Memasukkan hasil urut ke Stack Final (Sesuai Constraint 3)
        Stack<String> finalStack = new Stack<>();
        
        // Kita looping dari belakang agar data Januari (indeks 0) berada di pucuk Stack (Top)
        for (int i = n - 1; i >= 0; i--) {
            finalStack.push(daftarPasangan[i].getBulan());
            finalStack.push(daftarPasangan[i].getNama());
        }

        // 5. Mengubah isi Stack Final menjadi Array of Strings (Sesuai Constraint 4)
        String[] outputArray = new String[finalStack.size()];
        int outIndex = 0;
        
        while (!finalStack.isEmpty()) {
            // Karena Januari ada di pucuk, dia akan keluar duluan mengisi array dari indeks 0
            outputArray[outIndex] = finalStack.pop();
            outIndex++;
        }

        // Mencetak hasil akhir
        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(outputArray));
        
        sc.close();
    }
}

class Pasangan {
    private String nama;
    private String bulan;
    private int urutanBulan;

    protected Pasangan(String nama, String bulan){
        this.nama = nama;
        this.bulan = bulan;
        this.urutanBulan = hitungUrutanBulan(bulan);
    }

    public String getNama(){
        return nama;
    }
    
    public String getBulan(){
        return bulan;
    }
    
    public int getUrutanBulan(){
        return urutanBulan;
    }
    
    private int hitungUrutanBulan(String namaBulanInput){
        String[] kamusBulan = {"january", "february", "march", "april", "may", "june",
                                "july", "august", "september","october", "november", "december"};
        String bulanKecil = namaBulanInput.toLowerCase();

        for (int i = 0; i < kamusBulan.length; i++) {
            if (bulanKecil.equals(kamusBulan[i])) {
                return i + 1;
            }
        }
        return 0;
    }
}