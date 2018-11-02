package lesson_4_Network_chat_JavaFX.sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

    public void sendMsg(ActionEvent actionEvent) {
        textArea.appendText(textField.getText() + "\n");
        textField.clear();
        textField.requestFocus();
    }
    public void exitChat (ActionEvent actionEvent){
        System.exit(0);
    }
}
