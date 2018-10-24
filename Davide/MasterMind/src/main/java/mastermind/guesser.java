package mastermind;
import java.util.*;
public class guesser {

    //Creazione set completo
    public static void main(String args[]){
        Combo secret = new Combo();
        ArrayList<Combo> box = new ArrayList<Combo>();
        ArrayList<Combo> special = new ArrayList<Combo>();
        Combo temp;
        for(int i = 0; i <= 5; i++){
            for(int j = 0; j <= 5; j++){
                for(int k = 0; k <= 5; k++){
                    for(int h = 0; h <= 5; h++){
                        temp = new Combo(i, j, k, h);
                        box.add(temp);
                    }
                }
            }
        }

        //Creazione set speciale

        for(int i = 0; i <= 5; i++){
            for(int j = 0; j <= 5; j++){
                if(i!=j){
                    temp = new Combo(i, i, j, j);
                    special.add(temp);
                }
            }
        }

        for(int i = 0; i <= 5; i++){
            for(int j = 0; j <= 5; j++){
                if(i!=j){
                    temp = new Combo(i, i, i, j);
                    special.add(temp);
                    temp = new Combo(j, j, j, i);
                    special.add(temp);
                }
            }
        }

        for(int i = 0; i <= 5; i++){
            temp = new Combo(i, i, i, i);
            special.add(temp);
        }
        //Fine creazione

        //Ricezione input

        for(int i = 0; i < 4; i ++){
            System.out.println("Scegli il " + i + "° colore:");
            Scanner scanner = new Scanner(System.in);
            secret.add(scanner.nextInt());
        }
        //Inizio algoritmo
        int move = 1;
        ArrayList<Combo> candidates = new ArrayList<Combo>();
        Combo guess = new Combo(0, 0, 1, 1);
        List<Integer> feedback = secret.compare(guess);
        List<Integer> tempfeed = null;
        List<Integer> notalike = Arrays.asList(0,0,0,0);
        int i = 1;
        while(true){
            if(move==1) {
                System.out.println("La combo è: ");
                secret.boardToString();
                System.out.println("TENTATIVO:");
                guess.boardToString();
                System.out.println("CON RISULTATO " + feedback.toString());
                while (feedback.equals(notalike)) {
                    move++;
                    System.out.println("TENTATIVO A VUOTO. RIPROVO CON: ");
                    guess = new Combo(special.get(i));
                    feedback = new ArrayList<Integer>(secret.compare(guess));
                    guess.boardToString();
                    System.out.println("CON RISULTATO " + feedback.toString());
                    i++;
                }
                if(guess.combo.equals(secret.combo)) break;
            }
            else{
                System.out.println("La combo è: ");
                secret.boardToString();
                System.out.println("TENTATIVO:");
                guess.boardToString();
                feedback = new ArrayList<Integer>(secret.compare(guess));
                System.out.println("CON RISULTATO " + feedback.toString());
                if(guess.combo.equals(secret.combo)) break;
            }
            //Scarto soluzioni che non danno il feedback del tentativo appena compiuto
            for (i = 0; i < box.size(); i++) {
                if(box.get(i)!=null){
                    tempfeed = new ArrayList<Integer>(box.get(i).compare(guess));
                    if(!Combo.compareFeeds(tempfeed, feedback) || box.get(i).combo.equals(guess.combo)){
                        box.remove(i);
                        box.add(i, null);
                    }
                }
            }
            /*System.out.println("\n\n\n\n\n");
            for (Combo c:
                 box) {
                if(c!=null) c.boardToString();
            }*/
            //scelgo una delle soluzioni rimanenti
            i = (int) (Math.random()*1296);
            while(box.get(i)==null){
                i = (int) (Math.random()*1296);
            }
            guess = new Combo(box.get(i));
            move++;
        }
        System.out.println("Ho indovinato in " + move + " mosse!");
        }

}