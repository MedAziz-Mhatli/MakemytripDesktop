/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;


import voyage.services.res_volCRUD;
import voyage.entities.res_vol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField res;
    @FXML
    private TextField vol;
    @FXML
    private TextField client;
    @FXML
    private TextField nbp;
    @FXML
    private DatePicker dater;
    @FXML
    private ComboBox<String> comp;
    @FXML
    private ComboBox<String> classe;
    @FXML
    private Button ba;
    @FXML
    private Button bm;
      ObservableList Listc = FXCollections.observableArrayList ( 
"Tunisair", "Nouvelair", "Emirates");
         ObservableList Listcl = FXCollections.observableArrayList ( 
"Economique", "Economique premium", "Affaires","premium_classe");
         

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       comp.setItems(Listc);
       classe.setItems(Listcl);
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
          java.sql.Date Date_r = java.sql.Date.valueOf(dater.getValue());
        
       res_volCRUD vc = new res_volCRUD() ;
        if ( res.getText().isEmpty() || vol.getText().isEmpty() || vol.getText().isEmpty() || nbp.getText().isEmpty() || Integer.parseInt(nbp.getText())<0 || comp.getValue().equals("Choisir une compagnie") || classe.getValue().equals("Choisir une classe")   )
            {Notifications notificationBuilder=Notifications.create()
              .title("Erreur").text("Veuillez verifier vos champs").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();} 
        else {
        vc.ajouterRes_vol(new res_vol(Integer.parseInt(res.getText()), Integer.parseInt(vol.getText()), Integer.parseInt(client.getText()), Date_r, comp.getValue(), classe.getValue(), Integer.parseInt(nbp.getText())));
        }}

    @FXML
    private void modifier(ActionEvent event) {
          res_volCRUD sr = new res_volCRUD();
         res_vol c = new res_vol();
        c.setId_resVol(Integer.parseInt(res.getText()));
                c.setId_vol(Integer.parseInt(vol.getText()));
                c.setId_client(Integer.parseInt(client.getText()));
                c.setCompagnie_aerienne(comp.getValue());
                 c.setClasse(classe.getValue());
              c.setNb_personnes(Integer.parseInt(nbp.getText()));
              c.setDate_resvol(java.sql.Date.valueOf(dater.getValue()));
                sr.modifierRes_vol(c);
    }

    @FXML
    private void supprimer(ActionEvent event) {
    res_volCRUD sr = new res_volCRUD();
         res_vol c = new res_vol();
         sr.supprimerReservationvol(Integer.parseInt(res.getText()));
    }
    
    
}
