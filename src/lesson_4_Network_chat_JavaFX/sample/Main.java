package lesson_4_Network_chat_JavaFX.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.UNDECORATED); //Убираем оформление ОС
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("AC-TEPPA messager");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
