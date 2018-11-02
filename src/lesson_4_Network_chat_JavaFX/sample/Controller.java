package lesson_4_Network_chat_JavaFX.sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javax.swing.plaf.basic.BasicSplitPaneUI;

public class Controller {

    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button buttonSend;

    @FXML
    Button buttonExit;

    @FXML
    VBox vBoxChatPlace;

    boolean rightAlignment = false;
    public void sendMsg(ActionEvent actionEvent) {
        Label label = new Label();
        label.setText(textField.getText() + "\n");
        if (rightAlignment){
            vBoxChatPlace.setAlignment(Pos.BOTTOM_RIGHT);
            vBoxChatPlace.getChildren().add(label);
            rightAlignment = false;
        } else {
            vBoxChatPlace.setAlignment(Pos.BOTTOM_LEFT);
            vBoxChatPlace.getChildren().add(label);
            rightAlignment = true;
        }


//        vBoxChatPlace.getChildren().add(label);
//        vBoxChatPlace.setAlignment(Pos.BOTTOM_LEFT);

//        textArea.appendText(textField.getText() + "\n");
        textField.clear();
        textField.requestFocus();
    }
    public void exitChat (ActionEvent actionEvent){
        System.exit(0);
    }
}
