/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author MSI GAMMING
 */
public class NewFXMaintest extends Application {

    double xOffset, yOffset;
 public Button btn5;
    public Button btn6;
    @FXML
    private Circle btn4;
    @FXML
    private Circle btn3;
    @FXML
    private Circle btn2;
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//           Scene scence = new Scene(root);
    //        primaryStage.setScene(scence);
           primaryStage.setTitle("---Facture ajoutée---");
          primaryStage.show();
          HBox layout = new HBox();
          layout.setAlignment(Pos.CENTER);
//          Scene scene2 = new Scene(layout, 300, 300);
//            Button button = new Button("Forward");
//            button.setOnAction(e -> primaryStage.setScene(scene2));

//            Parent root1 = FXMLLoader.load(getClass().getResource("paiementenligne.fxml"));
//            Scene scence1 = new Scene(root1);
//            primaryStage.setScene(scence1);
//            primaryStage.setTitle("---Paiement en Ligne---");
//            primaryStage.show();

//
//            Parent root = FXMLLoader.load(getClass().getResource("menureclamation.fxml"));
//
//            Scene scence = new Scene(root);
//            primaryStage.setScene(scence);
//            primaryStage.setTitle("---Liste des Réclamations --");
//            primaryStage.show();

        //    Parent root = FXMLLoader.load(getClass().getResource("ListerFactures.fxml"));
            Scene scence = new Scene(root);

            primaryStage.setScene(scence);
            primaryStage.setTitle("---Liste des factures---");
            primaryStage.show();
            
            
//            
//             Parent root1 = FXMLLoader.load(getClass().getResource("paiement.fxml"));
//            Scene scence1 = new Scene(root1);
//            primaryStage.setScene(scence1);
//                       primaryStage.setTitle("---Paiement---");
//            primaryStage.show();
//           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
      public void action1(MouseEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("ajouterGuide.fxml"));

        Stage window =(Stage) btn2.getScene().getWindow();
        window.setScene(new Scene(root, 500, 1000));

    }
    
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
        Parent root  = FXMLLoader.load(getClass().getResource("menureclamation.fxml"));

        Stage window =(Stage) btn2.getScene().getWindow();
        window.setScene(new Scene(root, 500, 1000));

    }
    
      public void action5(MouseEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("ListerFactures.fxml"));

        Stage window =(Stage) btn2.getScene().getWindow();
        window.setScene(new Scene(root, 500, 1000));

    }
        public void action6(MouseEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("BackReservation.fxml"));

        Stage window =(Stage) btn2.getScene().getWindow();
        window.setScene(new Scene(root, 500, 1000));

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
