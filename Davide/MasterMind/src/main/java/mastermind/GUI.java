package mastermind;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GUI extends Application {

    public static void main(String args[]){ launch();}

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mastermind.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Mastermind!");
        primaryStage.setScene(new Scene(root, 379, 679));
        primaryStage.setResizable(false);
        //primaryStage.setMaxHeight(420);
        //primaryStage.setMaxWidth(720);
        //primaryStage.setMinHeight(720);
        //primaryStage.setMinWidth(420);
        loader.setController(new Controller(primaryStage));

        primaryStage.show();
    }



}
