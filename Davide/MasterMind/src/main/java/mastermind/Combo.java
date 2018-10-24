package mastermind;

import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combo {

    ArrayList<Integer> combo;

    Combo(int a, int b, int c, int d){
        this.combo = new ArrayList<Integer>();
        this.combo.add(0, a);
        this.combo.add(1, b);
        this.combo.add(2, c);
        this.combo.add(3, d);
    }

    Combo(){
        this.combo = new ArrayList<Integer>();
    }

    Combo(Combo y){
        this.combo = y.combo;
    }

    void add(int x){
        combo.add(x);
    }

    void boardToString(){
        for(int i = 0; i < 4; i ++) {
            printDot(i);
        }
        System.out.println();
    }

    void printDot(int x){
        switch (combo.get(x)) {
            case 0:
                System.out.print(Ansi.ansi().reset().fgBright(Ansi.Color.BLUE) + "\u23FA" + Ansi.ansi().reset().fgBright(Ansi.Color.DEFAULT));
                break;
            case 1:
                System.out.print(Ansi.ansi().reset().fgBright(Ansi.Color.RED) + "\u23FA" + Ansi.ansi().reset().fgBright(Ansi.Color.DEFAULT));
                break;
            case 2:
                System.out.print(Ansi.ansi().reset().fgBright(Ansi.Color.YELLOW) + "\u23FA" + Ansi.ansi().reset().fgBright(Ansi.Color.DEFAULT));
                break;
            case 3:
                System.out.print(Ansi.ansi().reset().fgBright(Ansi.Color.MAGENTA) + "\u23FA" + Ansi.ansi().reset().fgBright(Ansi.Color.DEFAULT));
                break;
            case 4:
                System.out.print(Ansi.ansi().reset().fgBright(Ansi.Color.BLACK) + "\u23FA" + Ansi.ansi().reset().fgBright(Ansi.Color.DEFAULT));
                break;
            case 5:
                System.out.print(Ansi.ansi().reset().fgBright(Ansi.Color.GREEN) + "\u23FA" + Ansi.ansi().reset().fgBright(Ansi.Color.DEFAULT));
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public List<Integer> compare(Combo dest){
        List<Integer> feedback = new ArrayList<Integer>();
        int j = 0;
        for(int i = 0; i < 4; i ++){
            if(dest.combo.get(i).equals(combo.get(i))) {
                feedback.add(100);
            }
            else{
                for(j = 0; j < 4; j++){
                    if(dest.combo.get(i).equals(combo.get(j))) {
                        feedback.add(1);
                        break;
                    }
                }
                if(j == 4) feedback.add(0);
            }
        }
        return feedback;
    }

    static boolean compareFeeds(List<Integer> feed1, List<Integer> feed2){
        int score = 0;
        int sum1 = 0;
        int sum2 = 0;
        int[] f1 = new int[4];
        int[] f2 = new int[4];
        for(int i = 0; i < 4; i++){
            f1[i] = feed1.get(i);
            f2[i] = feed2.get(i);
        }
        Arrays.sort(f1);
        Arrays.sort(f2);
        for(int i = 0; i < 4; i++){
            if(f1[i] == f2[i]) score++;
        }
        if(score==4) return true;
            else return false;
    }

    public String trim(){
        String s = "Brugola";
        char[] temp = new char [s.length()];
        int k = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != ' ' && s.charAt(i) != '\n' && s.charAt(i) != '\t'){
                temp[k] = s.charAt(i);
                k++;
            }
        }
        s = Arrays.toString(temp);
        return s;
    }



}
