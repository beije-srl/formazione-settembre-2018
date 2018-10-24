package mastermind;

import java.util.*;

public class cli {

    public static void main(String args[]){

        Random r = new Random();
        Combo secret = new Combo(r.nextInt(6), r.nextInt(6), r.nextInt(6), r.nextInt(6));
        List<Integer> right = Arrays.asList(100,100,100,100);
        List<Integer> feedback = new ArrayList<Integer>();
        Combo trial = new Combo();
        int[] display = new int[4];
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci quattro cifre: (0 Blu, 1 Rosso, 2 Giallo, 3 Magenta, 4 Nero, 5 Verde)");
        for(int i = 0; i < 4; i++){
            trial.add(scan.nextInt());
        }
        while(!trial.compare(secret).equals(right)){
            System.out.println("Hai digitato: ");
            trial.boardToString();
            for(int i = 0; i < 4; i++) display[i] = trial.compare(secret).get(i);
            Arrays.sort(display);
            System.out.println("Risultato: " + Arrays.toString(display));
            System.out.println("Inserisci quattro cifre: (0 Blu, 1 Rosso, 2 Giallo, 3 Magenta, 4 Nero, 5 Verde)");
            trial = new Combo();
            for(int i = 0; i < 4; i++){
                trial.add(scan.nextInt());
            }
        }
        System.out.println("Hai indovinato! La risposta era: ");
        secret.boardToString();

    }

}
