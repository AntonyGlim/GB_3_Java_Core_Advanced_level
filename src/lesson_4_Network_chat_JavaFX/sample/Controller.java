package lesson_4_Network_chat_JavaFX.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


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
    VBox vBoxChatPlaceLeft;

    @FXML
    VBox vBoxChatPlaceRight;


    boolean rightAlignment = false;
    public void sendMsg(ActionEvent actionEvent) {
        Label label = new Label();
        label.setText("  " + textField.getText() + "  ");
        Label label2 = new Label("\n" + " ");

        if (rightAlignment){
            vBoxChatPlaceRight.setAlignment(Pos.BOTTOM_RIGHT);
            vBoxChatPlaceRight.getChildren().add(label);
            vBoxChatPlaceLeft.getChildren().add(label2);
            rightAlignment = false;
        } else {
            vBoxChatPlaceLeft.setAlignment(Pos.BOTTOM_LEFT);
            vBoxChatPlaceLeft.getChildren().add(label);
            vBoxChatPlaceRight.getChildren().add(label2);
            rightAlignment = true;
        }

        textField.clear();
        textField.requestFocus();
    }


    public void exitChat (ActionEvent actionEvent){
        System.exit(0);
    }
}
