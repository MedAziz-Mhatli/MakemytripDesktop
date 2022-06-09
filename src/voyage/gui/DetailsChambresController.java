/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import voyage.entities.Chambre;
import voyage.entities.Type;
import voyage.entities.Vue;
import voyage.services.ChambreCrud;
import voyage.services.HotelCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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
public class DetailsChambresController implements Initializable {

     @FXML
    private TableView<Chambre> table;
    @FXML
    private TableColumn<Chambre, Integer> idc;
    @FXML
    private TableColumn<Chambre, Type> type;
    @FXML
    private TableColumn<Chambre, Vue> vue;
    @FXML
    private TableColumn<Chambre, Double> prix;
     final ObservableList data=FXCollections.observableArrayList();
    @FXML
    private TableColumn<Chambre, Integer> id_hotell;
    @FXML
    private Button bttn;
    @FXML
    private Button iih;
    @FXML
    private Label iid;
    @FXML
    private ToggleGroup typee;
    @FXML
    private ToggleGroup vuee;
    @FXML
    private TextField prix_n;
    @FXML
    private ComboBox<?> cmbo;
    @FXML
    private Button btnajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    selectnom();
    showchambres();
    }

    
    

    private ObservableList<Chambre> getChambres() {
     ObservableList<Chambre> Chambrelist=FXCollections.observableArrayList();
       
     //  Type h=Type.valueOf(p)
       //Type chh= ch.getType().Double;
       
       
       try { 
            String requete="SELECT * FROM chambre";
            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            Chambre ch;
            while(rs.next()){
               
                ch=(new Chambre(rs.getInt(1),Type.find(rs.getString(2)),Vue.find(rs.getString(3)),rs.getDouble(4),rs.getInt(5)));
                Chambrelist.add(ch);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
       return   Chambrelist;}

    public void showchambres(){
        ObservableList<Chambre> list= getChambres();
        idc.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("id_chambre"));
          type.setCellValueFactory(new PropertyValueFactory<Chambre, Type>("type"));
          vue.setCellValueFactory(new PropertyValueFactory<Chambre, Vue>("vue"));
          prix.setCellValueFactory(new PropertyValueFactory<Chambre, Double>("prix_nuitee"));
           id_hotell.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("id_hotel"));
         
          table.setItems(list);
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
    private void Delete(ActionEvent event) {
         HotelCrud h=new HotelCrud();
        Chambre ch=new Chambre();
        ch=table.getSelectionModel().getSelectedItem();
        h.supprimerChambre(ch);
                showchambres();
    }

    @FXML
    private void ajoutt(ActionEvent event) {
    }

    @FXML
    private void edit(ActionEvent event) {
        
        try {
        Chambre ch=new Chambre();
            RadioButton selectedRadioButton = (RadioButton) typee.getSelectedToggle();
        String t = selectedRadioButton.getText();
          RadioButton selctedRadioButton = (RadioButton) vuee.getSelectedToggle();
           String v = selctedRadioButton.getText();
           if(t.compareTo("Single")==0){
      ch.setType(Type.Types.Single);}
      else
        ch.setVue(Vue.Veus.Normale);
      if(v.compareTo("Sur_mer")==0) 
      ch.setVue(Vue.Veus.Sur_mer);
      else if(v.compareTo("Sur_piscine")==0)
      ch.setVue(Vue.Veus.Sur_piscine);
      
      else
          ch.setVue(Vue.Veus.Normale);
           String prix = prix_n.getText();
          String s=cmbo.getSelectionModel().getSelectedItem().toString();
          
        
           String query = "UPDATE chambre SET type= '" + t+ "', vue = '" + v + "', prix_nuitee	 = '" +prix + "', id_hotel = '" + s + "' WHERE id_hotel = '" + s +"' ";
           PreparedStatement ps= MyConnectionn.getInstance().getCnx().prepareStatement(query);
           ps.executeUpdate();
             } catch (SQLException ex) {
           System.out.println(ex.getMessage()); }
     
 
           showchambres();
    }


    @FXML
    private void AjouterChambre(ActionEvent event) {
        
          
        Chambre ch=new Chambre();
        RadioButton selectedRadioButton = (RadioButton) typee.getSelectedToggle();
        String t = selectedRadioButton.getText();
       
       
      if(t.compareTo("Single")==0){
      ch.setType(Type.Types.Single);}
      else{
         ch.setType(Type.Types.Double); }
       RadioButton selctedRadioButton = (RadioButton) vuee.getSelectedToggle();
       String v = selctedRadioButton.getText();
      if(v.compareTo("Sur_mer")==0) 
      ch.setVue(Vue.Veus.Sur_mer);
      else if(v.compareTo("Sur_piscine")==0)
      ch.setVue(Vue.Veus.Sur_piscine);
      
      else
          ch.setVue(Vue.Veus.Normale);
      
      String p =prix_n.getText();
       Double prix=Double.parseDouble(p);
   
           
       //int id=Integer.parseInt(i);
         ch.setPrix_nuitee(prix);
        String sl=cmbo.getSelectionModel().getSelectedItem().toString();
            int i=Integer.parseInt(sl);
         ch.setId_hotel(i);
       ChambreCrud chh=new ChambreCrud();
        int stats= chh.ajouterChambre(ch);
       if(stats>0){
          Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("add chambre");
            alert.setHeaderText("Information");
            alert.setContentText("chambre bien ajouter");
            alert.showAndWait();
         
        }
        else{
         Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("add chambre");
            alert.setHeaderText("Information");
            alert.setContentText("chambre non ajouter");
            alert.showAndWait();
        }
      // ChambreCrud c = new ChambreCrud();
       //c.sms();
       showchambres();
    }

    @FXML
    private void accce(ActionEvent event) {
        
           
              Stage stage =new Stage();
         try {
            Parent root=
                   FXMLLoader.load(getClass().getResource("Menu.fxml"));
            
            Scene scene = new Scene(root);
             
           stage.setTitle("Calcul chambre");
            stage.setScene(scene);
             stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
    }
    }
    
}
