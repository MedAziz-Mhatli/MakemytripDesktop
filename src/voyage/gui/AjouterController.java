/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import voyage.entities.vol;
import voyage.services.volCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author sahra
 */
public class AjouterController implements Initializable {

    @FXML
    private TextField tfDateDepart;
    private TextField tfDateRetour;
    @FXML
    private TextField tfDestinationVol;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfNBEscales;
    @FXML
    private TextField tfDepartVol;
    @FXML
    private DatePicker dater;
    @FXML
    private DatePicker dated;
    @FXML
    private ListView<vol> listv;
      volCRUD vc = new volCRUD() ;
 ObservableList<vol> listt=FXCollections.observableList(vc.listerVols());
    @FXML
    private Button bm;
    @FXML
    private Button bs;

    /**
     * Initializes the controller class.
     */
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        volCRUD vc = new volCRUD() ;
       listv.setItems(listt);
    }    

//    @FXML
//    private void saveVol(ActionEvent event) {
//         java.sql.Date Date_r = java.sql.Date.valueOf(dater.getValue());
//        java.sql.Date Date_d = java.sql.Date.valueOf(dated.getValue());
//        volCRUD vc = new volCRUD() ;
//        vc.ajouterVol(new vol(Integer.parseInt(tfIDVol.getText()), tfDepartVol.getText(), tfDestinationVol.getText(), Date_d, Date_r, Integer.parseInt(tfNBEscales.getText()), Integer.parseInt(tfPrix.getText())));
//    }
    
       @FXML
    private void saveVol(ActionEvent event) {
         java.sql.Date Date_r = java.sql.Date.valueOf(dater.getValue());
        java.sql.Date Date_d = java.sql.Date.valueOf(dated.getValue());
        volCRUD vc = new volCRUD() ;
        vc.ajouterVol(new vol( tfDepartVol.getText(), tfDestinationVol.getText(), Date_d, Date_r, Integer.parseInt(tfNBEscales.getText()), Integer.parseInt(tfPrix.getText())));
    }
    

//    @FXML
//    private void modifier(ActionEvent event) {
//         volCRUD sr = new volCRUD();
//         vol c = new vol();
//        c.setId_vol(listv.getSelectionModel().getSelectedItem().getId_vol());
//                c.setDepart_vol(tfDepartVol.getText());
//                c.setDestination_vol(tfDestinationVol.getText());
//                c.setNb_escalesVol(Integer.parseInt(tfNBEscales.getText()));
//                 c.setPrixVol(Integer.parseInt(tfPrix.getText()));
//              c.setId_vol(listv.getSelectionModel().getSelectedItem().getId_vol());
//              c.setDate_departVol(java.sql.Date.valueOf(dater.getValue()));
//              c.setDate_retourVol(java.sql.Date.valueOf(dater.getValue()));
//                sr.modifierVol(c);
//    }
        @FXML
    private void modifier(ActionEvent event) {
         volCRUD sr = new volCRUD();
         vol c = new vol();
                c.setDepart_vol(tfDepartVol.getText());
                c.setDestination_vol(tfDestinationVol.getText());
                c.setNb_escalesVol(Integer.parseInt(tfNBEscales.getText()));
                 c.setPrixVol(Integer.parseInt(tfPrix.getText()));
              c.setId_vol(listv.getSelectionModel().getSelectedItem().getId_vol());
              c.setDate_departVol(java.sql.Date.valueOf(dater.getValue()));
              c.setDate_retourVol(java.sql.Date.valueOf(dater.getValue()));
                sr.modifierVol(c);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        volCRUD sr = new volCRUD();
         vol c = new vol();
         sr.supprimerVol(listv.getSelectionModel().getSelectedItem().getId_vol());
    }

    
    @FXML
    private void refresh(ActionEvent event) {
        ObservableList<vol> listtr=FXCollections.observableList(vc.listerVols());
          listv.setItems(listtr);
    }
    
    
}
