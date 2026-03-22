import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;

public class Moriohs_Hidden_Danger {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Stack<String> Input = new Stack<>();

        System.out.println("Masukkan input (contoh: holly may interesting MARCH corey November junior january paul december):");
        String NamadanBulan = sc.nextLine();
        String[] pemisah = NamadanBulan.split(" ");

        for(int i = 0; i < pemisah.length; i++){
            Input.push(pemisah[i]);
        }
        Pasangan[] daftarPasangan = new Pasangan[pemisah.length / 2];
        int index = 0;
        
        while(!Input.isEmpty()){
            String bulan = Input.pop();
            String nama = Input.pop();

            Pasangan p = new Pasangan(nama, bulan);
            daftarPasangan[index] = p;
            index++;
        }
        int n = daftarPasangan.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (daftarPasangan[j].getUrutanBulan() > daftarPasangan[j + 1].getUrutanBulan()) {
                    Pasangan temp = daftarPasangan[j];
                    daftarPasangan[j] = daftarPasangan[j + 1];
                    daftarPasangan[j + 1] = temp;
                }
            }
        }
        Stack<String> finalStack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            finalStack.push(daftarPasangan[i].getBulan());
            finalStack.push(daftarPasangan[i].getNama());
        }
        String[] outputArray = new String[finalStack.size()];
        int outIndex = 0;
        
        while (!finalStack.isEmpty()) {
            outputArray[outIndex] = finalStack.pop();
            outIndex++;
        }

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