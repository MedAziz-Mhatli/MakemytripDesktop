/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import voyage.entities.Facture;
import voyage.services.FactureCRUD;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author MSI GAMMING
 */
public class AjouterFactureController implements Initializable {

    @FXML
    private Label title;
    private TextField TFAidfacture;
    @FXML
    private DatePicker TFAdatefacture;
    @FXML
    private TextField TFAremisefacture;
    @FXML
    private ComboBox<String> TFAtypefacture;

    @FXML
    private Button btnajouterfacture;
    @FXML
    private Button btnresetaj;
    @FXML
    private TextField TFAtotalfacture;

//    ObservableList<String> liste_type_facture = FXCollections.observableArrayList("vol","vehicule","chambre");
    @FXML
    private TextField TFAidclientfacture;
    @FXML
    private TextField affichertype1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TFAtypefacture.setItems(FXCollections.observableArrayList("vol", "vehicule", "chambre", "totale"));
    }
            double resultat =0;

    @FXML
    private void ajouterFacture(ActionEvent event) {

        if ((TFAdatefacture.getValue().toString().isEmpty()) || (TFAremisefacture.getText().isEmpty())
                || (TFAtotalfacture.getText().isEmpty()) || (TFAtypefacture.getValue().toString().isEmpty())
                || (TFAidclientfacture.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle(" Champs invalides ");
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir les champs !");
            alert.showAndWait();
            
        } 
        else 
        {
            LocalDate date = TFAdatefacture.getValue();
            java.util.Date date_facture = java.sql.Date.valueOf(date);
            //String date1 = date.format(DateTimeFormatter.ofPattern("DD-MM-yyyy"));
            double remise_facture = Double.parseDouble(TFAremisefacture.getText());
            double total_facture = Double.parseDouble(TFAtotalfacture.getText());

            TFAtypefacture.getItems().add(affichertype1.getText());
            affichertype1.clear();

            String type_facture = TFAtypefacture.getValue().toString();
            int id_client = Integer.parseInt(TFAidclientfacture.getText());

            FactureCRUD fcd = new FactureCRUD();
            double res = 0;
            if (type_facture.equals("vol")) {
                res = fcd.calculerPrixReservationVol(id_client);
                Facture f1 = new Facture((Date) date_facture, remise_facture, res, type_facture, id_client);
                fcd.ajouterFacture(f1);

            } else if (type_facture.equals("chambre")) {
                res = fcd.calculerPrixReservationChambre(id_client);
                Facture f1 = new Facture((Date) date_facture, remise_facture, res, type_facture, id_client);
                fcd.ajouterFacture(f1);

            } else if (type_facture.equals("vehicule")) {
                res = fcd.calculerPrixReservationVehicule(id_client);
                Facture f1 = new Facture((Date) date_facture, remise_facture, res, type_facture, id_client);
                fcd.ajouterFacture(f1);

            } else {
            int nmbfact = fcd.coutidClient(id_client);
             System.out.println(remise_facture);
            if(nmbfact >= 2)
            {
                resultat = fcd.calculerRemiseFacture(id_client);
                System.out.println(resultat);
                Facture f1 = new Facture((Date) date_facture, remise_facture, resultat, type_facture, id_client);
                fcd.ajouterFacture(f1);
            }
            else
            {                
                res = fcd.CalculerPrixTotalInterface(id_client);
                Facture f1 = new Facture((Date) date_facture, 0, res, type_facture, id_client);
                fcd.ajouterFacture(f1);
            }

            }
        }
    }

    @FXML
    public void reset() {
        TFAidfacture.setText("");

        TFAdatefacture.setValue(null);
        TFAremisefacture.setText("");
        TFAtypefacture.setValue("");
        TFAtotalfacture.setText("");
        TFAidclientfacture.setText("");

    }

    private void reset(ActionEvent event) {
        reset();

//    ObservableList<String> liste_type_facture = FXCollections.observableArrayList("vol","vehicule","chambre");
    }

    @FXML
    private void selecttypefacture(ActionEvent event) {
        System.out.println(TFAtypefacture.getValue());
    }

}
