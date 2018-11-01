package lesson_4_Network_chat_JavaFX.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.plaf.basic.BasicSplitPaneUI;

public class Controller {

    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    public void sendMsg(ActionEvent actionEvent) {

        textArea.appendText(textField.getText() + "\n");
        textField.clear();
        textField.requestFocus();
    }

}
