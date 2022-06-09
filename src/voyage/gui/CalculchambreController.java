/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import voyage.utils.MyConnectionn;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class CalculchambreController implements Initializable {

    @FXML
    private Button tfc;
    private TextField id;
    @FXML
    private ComboBox cmbo;
    final ObservableList data=FXCollections.observableArrayList();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       selectnom();
    } 
    private void selectnom() {
         
        // data.clear();
          try {
              
            String requete="SELECT id_hotel FROM hotel"; 
            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            while(rs.next()){
              

                data.add(new Integer(rs.getInt(1)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
cmbo.setItems(null);
cmbo.setItems(data);

    }
    @FXML
    private void calculerch(ActionEvent event) {
       
    String sl=cmbo.getSelectionModel().getSelectedItem().toString();
         
            int i=Integer.parseInt(sl);
        
     String requete ="SELECT ch.type,  COUNT(ch.type) AS nb_chambre   FROM chambre ch inner join hotel h on h.id_hotel = ch.id_hotel WHERE ch.id_hotel ="+i+" GROUP BY ch.type";
          
            
          try {
            PreparedStatement  ps = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
              ResultSet rs =ps.executeQuery(requete);
              
              while(rs.next()){
              int nb=rs.getInt("nb_chambre");
              
              //System.out.println(nb);
              String nbb=rs.getString("type");
               Alert alert=new Alert(Alert.AlertType.INFORMATION);

           alert.setTitle("fair le calcule");
          alert.setContentText("Informations");
            alert.setContentText("le nombre des chambres du type "+nbb+" est :"+nb);
            alert.show();

              }
           
          } catch (SQLException ex) {
          System.out.println(ex.getMessage());
          }
           
    }
    
}
