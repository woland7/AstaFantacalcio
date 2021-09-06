import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Extractor {
    private ArrayList<Player> playersList, playersDrawnList, playersToDrawList;
    private Random random;
    private String playersDrawn;

    public Extractor(String players, String playersDrawn){
        this.playersDrawn = playersDrawn;
        random = new Random(System.currentTimeMillis());
        playersList = new ArrayList<>();
        playersDrawnList = new ArrayList<>();
        playersToDrawList = new ArrayList<>();
        CSVParser csvParser;
        try {
            csvParser = new CSVParser(new FileReader(players), CSVFormat.DEFAULT);
            for (
                    CSVRecord record: csvParser) {
                Player s = new Player(record.get(0),record.get(1),Integer.parseInt(record.get(2)));
                playersList.add(s);
            }
            csvParser.close();

            csvParser = new CSVParser(new FileReader(playersDrawn), CSVFormat.DEFAULT);
            for (
                    CSVRecord record: csvParser) {
                Player s = new Player(record.get(0),record.get(1),Integer.parseInt(record.get(2)));
                playersDrawnList.add(s);
            }
            csvParser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        playersToDrawList.addAll(playersList);
        System.out.println(playersToDrawList.removeAll(playersDrawnList));
        System.out.println("Il numero di calciatori totale è: " + playersList.size());
        System.out.println("Il numero di calciatori precedentemente estratti è: " + playersDrawnList.size());
        System.out.println("Il numero di calciatori da estrarre: " + playersToDrawList.size());
    }

    private Player extract(){
        Collections.shuffle(playersToDrawList,random);
        return playersToDrawList.get(0);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 to skip, 2 to remove, -1 to exit:");
        int j;
        do {
            Player p = extract();
            System.out.println("Il calciatore è " + p.getName() + ", " + p.getTeam() + ", " + p.getValue());
            System.out.println("Scegli cosa fare: 1 avanti, 2 assegnato, -1 esci");
            j = sc.nextInt();
            switch (j){
                case 1:
                    System.out.println("Passa");
                    break;
                case 2:
                    System.out.println("Rimuovi il calciatore assegnato");
                    try {
                        PrintWriter pw = new PrintWriter(new FileWriter(playersDrawn, true));
                        String data = p.getName() + "," + p.getTeam() + "," + p.getValue();
                        pw.println(data);
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    playersToDrawList.remove(0);
                    System.out.println("Adesso sono rimasti: " + playersToDrawList.size() + " calciatori.");
                    break;
                case -1:
                    System.out.println("Sto uscendo");
                    break;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }

        }while(j != -1);
    }
}
