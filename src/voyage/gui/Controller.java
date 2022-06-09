package voyage.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public Button btn5;
    public Button btn6;
    @FXML
    private Circle btn4;
    @FXML
    private Circle btn3;
    @FXML
    private Circle btn2;
    public void action2(MouseEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        Stage window =(Stage) btn2.getScene().getWindow();
        window.setScene(new Scene(root, 500, 1000));

    }

    public void action3(MouseEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("Ajouter.fxml"));

        Stage window =(Stage) btn3.getScene().getWindow();
        window.setScene(new Scene(root, 800, 700));
    }
    public void action4(MouseEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("menuReclamation.fxml"));

        Stage window =(Stage) btn2.getScene().getWindow();
        window.setScene(new Scene(root, 500, 1000));

    }

    public void btn5Action(MouseEvent event) throws Exception{
        Parent root  = FXMLLoader.load(getClass().getResource("ListerFactures.fxml"));

        Stage window =(Stage) btn5.getScene().getWindow();
        window.setScene(new Scene(root, 500, 1000));
    }

    public void btn6Action(MouseEvent event) throws Exception{
        Parent root  = FXMLLoader.load(getClass().getResource("../gui/voiture.fxml"));

        Stage window =(Stage) btn6.getScene().getWindow();
        window.setScene(new Scene(root, 1175, 575));
    }
}

