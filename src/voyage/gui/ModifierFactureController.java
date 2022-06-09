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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MSI GAMMING
 */
public class ModifierFactureController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private DatePicker TFMdateFacture;
    @FXML
    private TextField TFMRemisefacture;
    @FXML
    private TextField TFMTotalfacture;
    @FXML
    private ComboBox<String> TFMTypefacture;
    @FXML
    private TextField TFMClientfacture;
    @FXML
    private Button BtnModifier;
    @FXML
    private Button Btnsupprimer;
    @FXML
    private TextField affichertype;

    Facture fact= new Facture();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        TFMTypefacture.setItems(FXCollections.observableArrayList("vol", "vehicule", "chambre"));
        
    }

    @FXML
    private void modifierFacture(ActionEvent event) {
        if ((TFMdateFacture.getValue().toString().isEmpty()) || (TFMRemisefacture.getText().isEmpty()) || (TFMTotalfacture.getText().isEmpty())
                || (TFMTypefacture.getValue().isEmpty()) || (TFMClientfacture.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" Champs invalides ");
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir les champs !");

            alert.showAndWait();
        } else {
            LocalDate date = TFMdateFacture.getValue();
            java.util.Date date_facture = java.sql.Date.valueOf(date);
            //String date1 = date.format(DateTimeFormatter.ofPattern("DD-MM-yyyy"));
            double remise_facture = Double.parseDouble(TFMRemisefacture.getText());
            double total_facture = Double.parseDouble(TFMTotalfacture.getText());

            
            
            
            TFMTypefacture.getItems().add(affichertype.getText());
            affichertype.clear();

            String type_facture = TFMTypefacture.getValue();
            int id_client = Integer.parseInt(TFMClientfacture.getText());
            FactureCRUD fcd = new FactureCRUD();

            double res = 0;
            if (type_facture.equals("vol")) {
                res = fcd.calculerPrixReservationVol(id_client);
                Facture f1 = new Facture((Date) date_facture, remise_facture, res, type_facture, id_client);
                fcd.modifierFacture(this.fact.getId_facture(), f1);

            } else if (type_facture.equals("chambre")) {
                res = fcd.calculerPrixReservationChambre(id_client);
                Facture f1 = new Facture((Date) date_facture, remise_facture, res, type_facture, id_client);
                fcd.modifierFacture(this.fact.getId_facture(), f1);

            } else if (type_facture.equals("vehicule")) {
                res = fcd.calculerPrixReservationVehicule(id_client);
                Facture f1 = new Facture((Date) date_facture, remise_facture, res, type_facture, id_client);
                fcd.modifierFacture(this.fact.getId_facture(), f1);

            } else {
                res = fcd.CalculerPrixTotalInterface(id_client);
                Facture f1 = new Facture((Date) date_facture, remise_facture, res, type_facture, id_client);
                fcd.modifierFacture(this.fact.getId_facture(), f1);

            }
            
       
        }
    }
    
    

    @FXML
    private void supprimerFacture(ActionEvent event) 
    {
        if ((TFMdateFacture.getValue().toString().isEmpty()) || (TFMRemisefacture.getText().isEmpty()) || (TFMTotalfacture.getText().isEmpty())
                || (TFMTypefacture.getValue().isEmpty()) || (TFMClientfacture.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" Champs invalides ");
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir les champs !");

            alert.showAndWait();
        } else {
           
            int id_client = Integer.parseInt(TFMClientfacture.getText());

            FactureCRUD fcd = new FactureCRUD();

            fcd.supprimerFacture(this.fact.getId_facture());
        
        
    }

}

    void setData(Facture A) {
        this.fact=A;
        System.out.println(this.fact);
        LocalDate localDate  = LocalDate.parse(A.getDate_facture().toString());
        TFMdateFacture.setValue(localDate);
        TFMRemisefacture.setText(String.valueOf(A.getRemise_facture()));
        TFMTotalfacture.setText(String.valueOf(A.getTotal_facture()));
        TFMTypefacture.setValue(A.getType_facture());
        TFMClientfacture.setText(String.valueOf(A.getId_client()));
    }

}
