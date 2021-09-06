import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Extractor e = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Vuoi estrarre:\n1. Attaccanti\n2. Centrocampisti.\n3. Difensori\n");
        int i = sc.nextInt();
        switch (i){
            case 1:
                e = new Extractor("attaccanti.csv", "attaccanti_estratti.csv");
                break;
            case 2:
                e = new Extractor("centrocampisti.csv", "centrocampisti_estratti.csv");
                break;
            case 3:
                e = new Extractor("difensori.csv", "difensori_estratti.csv");
                break;
            default:
                break;
        }
        if(e != null)
            e.run();
        else
            System.out.println("Hai operato una scelta non valida.");
    }
}
