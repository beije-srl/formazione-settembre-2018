package mastermind;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Controller {

    private Combo secret;
    private Combo trial;
    private TextField input;
    private List<GridPane> feeds;
    private GridPane board;
    private int tries;
    private Stage primaryStage;

    Controller(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Random r = new Random();
        this.secret = new Combo(r.nextInt(6), r.nextInt(6), r.nextInt(6), r.nextInt(6));
        secret.boardToString();
        this.input = (TextField) primaryStage.getScene().lookup("#input");
        this.feeds = new ArrayList<>();
        this.input.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode().equals(KeyCode.ENTER)){
                if(input.getText().length()!=4){
                    try {
                        throw new IllegalArgumentException();
                    }
                    catch(IllegalArgumentException exc){
                        exc.printStackTrace();
                        PauseTransition pause = new PauseTransition(Duration.seconds(1));
                        input.setText("Input scorretto!");
                        pause.play();
                        pause.setOnFinished((ActionEvent ae) -> {
                            input.setText("");
                        });
                    }
                }
                else{
                    trial = new Combo(Character.getNumericValue(input.getText().charAt(0)),Character.getNumericValue(input.getText().charAt(1)),Character.getNumericValue(input.getText().charAt(2)),Character.getNumericValue(input.getText().charAt(3)));
                    tryCombo(trial);
                    this.tries++;
                    System.out.println(tries);
                }

            }
        });
        for(int i = 0; i < 10; i++){
            feeds.add((GridPane) primaryStage.getScene().lookup("#f" + (9 - i)));
        }
        this.board = (GridPane) primaryStage.getScene().lookup("#board");
    }

    private void tryCombo(Combo trial) {
        for (int i : trial.combo) {
            if(i < 0 || i > 5){
                try {
                    throw new IllegalArgumentException();
                }
                catch(IllegalArgumentException exc){
                    exc.printStackTrace();
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    input.setText("Input scorretto!");
                    pause.play();
                    pause.setOnFinished((ActionEvent e) -> {
                        input.setText("");
                    });
                    tries--;
                    return;
                }
            }
        }
        if(tries == 9 && !trial.combo.equals(secret.combo)){
            System.out.println("SUGA");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            input.setText("HAI PERSO!");
            pause.play();
            pause.setOnFinished((ActionEvent ae) -> {
                input.setText("");
                reset();
            });
        }
        for(int i = 0; i < 4; i++){
            printSphere(tries, i, trial.combo.get(i));
        }
        addToFeeds();
        if(trial.combo.equals(secret.combo)) {
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            input.setText("HAI VINTO!");
            pause.play();
            pause.setOnFinished((ActionEvent e) -> {
                reset();
            });
        }
    }

    private void addToFeeds() {
        Circle sphere = null;
        int[] display = new int[4];
        for(int i = 0; i < 4; i++) display[i] = trial.compare(secret).get(i);
        Arrays.sort(display);
        System.out.println(Arrays.toString(display));
        for(int i = 0; i < 4; i++){
            switch (i){
                case 0:
                    if(display[i]==100){
                        sphere = new Circle(8, Color.BLACK);
                        feeds.get(tries).add(sphere, 1, 1);
                        GridPane.setHalignment(sphere, HPos.CENTER);
                    }
                    if(display[i]==1){
                        sphere = new Circle(8, Color.WHITE);
                        feeds.get(tries).add(sphere, 1, 1);
                        GridPane.setHalignment(sphere, HPos.CENTER);
                    }
                    break;
                case 1:
                    if(display[i]==100){
                        sphere = new Circle(8, Color.BLACK);
                        feeds.get(tries).add(sphere, 0, 1);
                        GridPane.setHalignment(sphere, HPos.CENTER);
                    }
                    if(display[i]==1){
                        sphere = new Circle(8, Color.WHITE);
                        feeds.get(tries).add(sphere, 0, 1);
                        GridPane.setHalignment(sphere, HPos.CENTER);
                    }
                    break;
                case 2:
                    if(display[i]==100){
                        sphere = new Circle(8, Color.BLACK);
                        feeds.get(tries).add(sphere, 1, 0);
                        GridPane.setHalignment(sphere, HPos.CENTER);
                    }
                    if(display[i]==1){
                        sphere = new Circle(8, Color.WHITE);
                        feeds.get(tries).add(sphere, 1, 0);
                        GridPane.setHalignment(sphere, HPos.CENTER);
                    }
                    break;
                case 3:
                    if(display[i]==100){
                        sphere = new Circle(8, Color.BLACK);
                        feeds.get(tries).add(sphere, 0,0);
                        GridPane.setHalignment(sphere, HPos.CENTER);
                    }
                    if(display[i]==1){
                        sphere = new Circle(8, Color.WHITE);
                        feeds.get(tries).add(sphere, 0,0);
                        GridPane.setHalignment(sphere, HPos.CENTER);
                    }
                    break;
                default: break;
            }
        }
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){


            }
        }
    }

    private void reset() {
        primaryStage.close();
    }

    private void printSphere(int trial, int col, int color){
        Circle sphere = new Circle(24);
        DropShadow shadow = new DropShadow(10, 5, 5, new Color(0,0,0, 1));
        shadow.setHeight(18);
        shadow.setWidth(18);
        shadow.setBlurType(BlurType.THREE_PASS_BOX);
        RadialGradient grad;
        List<Stop> stops = new ArrayList<Stop>();
        switch(color){
            case 0:
                stops.add(new Stop(1, new Color(0,0.313, 1, 1)));
                stops.add(new Stop(0, new Color(0, 0.533, 1, 1)));
                grad = new RadialGradient(0,0,0,0,0.5,true, CycleMethod.NO_CYCLE, stops);
                sphere.setFill(grad);
                board.add(sphere,col, trial);
                GridPane.setHalignment(sphere, HPos.CENTER);
                break;
            case 1:
                stops.add(new Stop(1, new Color(0.788,0, 0, 1)));
                stops.add(new Stop(0, new Color(1, 0, 0, 1)));
                grad = new RadialGradient(0,0,0,0,0.5,true, CycleMethod.NO_CYCLE, stops);
                sphere.setFill(grad);
                board.add(sphere,col,trial);
                GridPane.setHalignment(sphere, HPos.CENTER);
                break;
            case 2:
                stops.add(new Stop(1, new Color(0.78,0.7, 0, 1)));
                stops.add(new Stop(0, new Color(1, 0.898, 0, 1)));
                grad = new RadialGradient(0,0,0,0,0.5,true, CycleMethod.NO_CYCLE, stops);
                sphere.setFill(grad);
                board.add(sphere,col, trial);
                GridPane.setHalignment(sphere, HPos.CENTER);
                break;
            case 3:
                stops.add(new Stop(1, new Color(0.827,0.027, 0.588, 1)));
                stops.add(new Stop(0, new Color(1, 0, 0.827, 1)));
                grad = new RadialGradient(0,0,0,0,0.5,true, CycleMethod.NO_CYCLE, stops);
                sphere.setFill(grad);
                board.add(sphere,col, trial);
                GridPane.setHalignment(sphere, HPos.CENTER);
                break;
            case 4:
                stops.add(new Stop(1, new Color(0.078,0.709, 0.019, 1)));
                stops.add(new Stop(0, new Color(0.172, 1, 0, 1)));
                grad = new RadialGradient(0,0,0,0,0.5,true, CycleMethod.NO_CYCLE, stops);
                sphere.setFill(grad);
                board.add(sphere,col, trial);
                GridPane.setHalignment(sphere, HPos.CENTER);
                break;
            case 5:
                stops.add(new Stop(1, new Color(0.631,0.419, 0.027, 1)));
                stops.add(new Stop(0, new Color(0.827, 0.6, 0.027, 1)));
                grad = new RadialGradient(0,0,0,0,0.5,true, CycleMethod.NO_CYCLE, stops);
                sphere.setFill(grad);
                board.add(sphere,col, trial);
                GridPane.setHalignment(sphere, HPos.CENTER);
                break;
            default:
                break;

        }


    }

}
