/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import voyage.entities.res_vol;
import voyage.services.res_volCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class BackReservationController implements Initializable {
  res_volCRUD sr = new res_volCRUD();
 ObservableList<res_vol> listt=FXCollections.observableList(sr.listerRes_vol());
    @FXML
    private ListView<res_vol> listr;
    @FXML
    private TextField rech;
    @FXML
    private Button consulter;
    @FXML
    private PieChart chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listr.setItems(listt);
        chart.setTitle("Stat des compagnie aerienne "); 
        chart.getData().setAll(new PieChart.Data("Tunisaire", sr.tun()), new PieChart.Data("Nouvelair", sr.nouv()),  
                new PieChart.Data("Emirates", sr.Emir()));
    }    

  

    @FXML
    private void rechercherid(ActionEvent event) {
          res_volCRUD sr =new res_volCRUD();
       res_vol rec = new res_vol(Integer.parseInt(rech.getText()));
       ObservableList<res_vol> listrc=FXCollections.observableList(sr.rechercherid(rec));
        listr.setItems((listrc));
    }

    @FXML
    private void tri(ActionEvent event) {
         res_volCRUD sr =new res_volCRUD();
         ObservableList<res_vol> listri=FXCollections.observableList(sr.Tri());
         listr.setItems((listri));
         
    }

 
    private void rechercheidd(MouseEvent event) {
          res_volCRUD sr =new res_volCRUD();
        
       
      
       res_vol rec = new res_vol(Integer.parseInt(rech.getText()));
       ObservableList<res_vol> listrc=FXCollections.observableList(sr.rechercherid(rec));
        listr.setItems((listrc));
    }

    @FXML
    private void supprimer(ActionEvent event) {
        res_volCRUD sr = new res_volCRUD();
         res_vol c = new res_vol();
         sr.supprimerReservationvol(listr.getSelectionModel().getSelectedItem().getId_resVol());
    }

    @FXML
    private void refresh(ActionEvent event) {
        ObservableList<res_vol> listtr=FXCollections.observableList(sr.listerRes_vol());
          listr.setItems(listtr);
    }

    @FXML
    private void vol(ActionEvent event) throws IOException {
          Stage primaryStage;
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ajouter.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
//                  stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root1));
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    Stage CurrentStage = (Stage) consulter.getScene().getWindow();
                    CurrentStage.close();
    }
    }
    
    

