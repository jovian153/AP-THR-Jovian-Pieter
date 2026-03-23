import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class The_Newly_Born_Law {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Queue<String> antrean = new LinkedList<>();
        Stack<String> penjara = new Stack<>();

        System.out.println("Masukkan nama detektif(Josuke, Jotaro, Okuyasu, Koichi, Rohan) ");
        String namaDetektif = sc.nextLine();
        System.out.println("Masukkan nama tersangka :");
        String namaTersangka = sc.nextLine();

        Stand detektifBertugas = new Stand();

        switch (namaDetektif.toLowerCase()) {
            case "josuke":
                detektifBertugas = new Josuke();
                break;
            case "jotaro":
                detektifBertugas = new Jotaro();
                break;
            case "okuyasu":
                detektifBertugas = new Okuyasu();
                break;
            case "koichi":
                detektifBertugas = new Koichi();
                break;
            case "rohan":
                detektifBertugas = new Rohan();
                break;
            default:
                System.out.println("Detektif tidak dikenal!");
                break;
        }
        String[] daftarNamaTersangka = namaTersangka.split(" ");

        for(int i = 0;i < daftarNamaTersangka.length;i++){
            antrean.add(daftarNamaTersangka[i]);    
        }
        while(!antrean.isEmpty()){
            String tersangkaSaatIni = antrean.poll();

            boolean apakahBersalah = detektifBertugas.expose(tersangkaSaatIni);

            if(apakahBersalah == true){
                penjara.push(tersangkaSaatIni);
            }
        }
    
        if (penjara.isEmpty()) {
            System.out.println(namaDetektif + " exposed no one.");
        } else {
            System.out.println(namaDetektif + " exposed someone!");
        }
        System.out.println("Arrested: " + penjara);

        sc.close();
    }
}

class Stand{

    public Stand(){};
    public boolean expose(String namaTersangka){
        return false;
    }
}

class Josuke extends Stand{
    public Josuke(){
        super();
    }

    @Override
    public boolean expose(String namaTersangka){
        String namaKecil = namaTersangka.toLowerCase();
        return namaKecil.startsWith("k");
    }
}

class Jotaro extends Stand{
    public Jotaro(){
        super();
    }

    @Override
    public boolean expose(String namaTersangka){
        return namaTersangka.length() <= 3;
    }
}

class Okuyasu extends Stand{
    public Okuyasu(){
        super();
    }

    @Override
    public boolean expose(String namaTersangka){
        String namaKecil = namaTersangka.toLowerCase();

        for(int i = 0;i < namaKecil.length() - 1;i++){
            char hurufSekarang = namaKecil.charAt(i);
            char hurufSebelah = namaKecil.charAt(i+1);

            if(hurufSekarang == hurufSebelah){
                return true;
            }
        }
        return false;
    }
}
class Koichi extends Stand{
    public Koichi(){
        super();
    }

    @Override
    public boolean expose(String namaTersangka){
        int totalVowels = 0;
        String namaKecil = namaTersangka.toLowerCase();
        for(int i = 0;i < namaKecil.length();i++){
            char hurufSekarang = namaKecil.charAt(i);

            if(hurufSekarang == 'a' || hurufSekarang == 'i' || hurufSekarang == 'u' || hurufSekarang == 'e' || hurufSekarang == 'o'){
                totalVowels++;
            }
        }
        if(totalVowels >= 3){
            return true;
        }else{
            return false;
        }
    }
}
class Rohan extends Stand{
    public Rohan(){
        super();
    }

    @Override
    public boolean expose(String namaTersangka){
        String namaKecil = namaTersangka.toLowerCase();
        String namaDibalik = "";

        for(int i = namaKecil.length() - 1;i >= 0; i--){
            namaDibalik += namaKecil.charAt(i);
        }
        return namaKecil.equals(namaDibalik);
    }
}