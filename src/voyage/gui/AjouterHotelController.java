/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import voyage.entities.Hotel;
import voyage.services.HotelCrud;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class AjouterHotelController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextArea trDes;
    @FXML
    private TextField tfAdd;
    @FXML
    private TextField tfCat;
    @FXML
    private TextField tfNbr;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnVider;
    @FXML
    private Button acc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
     private boolean validatenom(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(tfNom.getText());
        if(m.find() && m.group().equals(tfNom.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Name hotel");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid First Name");
                alert.showAndWait();
           
            return false;            
        }
    }
    private boolean validateEtoiles(){
        Pattern p = Pattern.compile("[1-9]{2}");
        Matcher m = p.matcher(tfNbr.getText());
        if(m.find() && m.group().equals(tfNbr.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate nombre etoiles");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid nombres etoiles");
                alert.showAndWait();
           
            return false;            
        }
         
    }
    
 private boolean Emailvalide(){
        Pattern p = Pattern.compile( "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$");
        Matcher m = p.matcher(tfEmail.getText());
        if(m.find() && m.group().equals(tfEmail.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate email");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid email");
                alert.showAndWait();
           
            return false;            
        }
        }
    @FXML
    private void SaveHotel(ActionEvent event) {
        if( Emailvalide()&& validatenom()&& validateEtoiles() ){
        
        
        
         Hotel h =new Hotel();
        String nom=tfNom.getText();
        String description=trDes.getText();
        String adresse=tfAdd.getText();
        String categorie=tfCat.getText();
        String sid=tfNbr.getText();
        int nbch=Integer.parseInt(sid);
        String email=tfEmail.getText();
         String t=tfTel.getText();
        int tel=Integer.parseInt(t);
        if(nom.isEmpty()){
        tfNom.setText("Remplir votre nom");
        tfNom.setFont(Font.font(10));
      //  tfNom.setText(Color.BLACK);
        
        }
        else if(description.isEmpty()){
        trDes.setText("Remplir votre description");
        }
        
        else if(adresse.isEmpty()){
        tfAdd.setText("Remplir votre adresse");
        }
        else if(categorie.isEmpty()){
        tfCat.setText("Remplir votre categorie");
        }
        else if(sid.isEmpty()){
        tfNbr.setText("Remplir votre nombre");
        }
        else if(email.isEmpty()){
        tfEmail.setText("Remplir votre Email");
        }
      
         else if(t.isEmpty()){
        tfTel.setText("Remplir votre telephone");
        }
        else {
      
        h.setNom(nom);
      h.setDescription(description);
      h.setAdresse(adresse);
      h.setCategorie(categorie);
      h.setNb_chDispo(nbch);
      h.setEmail(email);
      h.setTelephone(tel);
         
      HotelCrud hc = new HotelCrud();
     int stats= hc.ajouterHotel2(h);
         
       if(stats>0){
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("add personne");
            alert.setHeaderText("Information");
            alert.setContentText("person bien ajouter");
            alert.showAndWait();
        }
        else{
         Alert alert=new Alert(AlertType.ERROR);
            alert.setTitle("add personne");
            alert.setHeaderText("Information");
            alert.setContentText("person non ajouter");
            alert.showAndWait();
        }
         }
        }
    }

    @FXML
    private void Vider(ActionEvent event) {
       
    tfNom.clear();
    trDes.clear();
    tfAdd.clear();
    tfCat.clear();
    tfNbr.clear();
    tfEmail.clear();
    tfTel.clear();
    }

    @FXML
    private void aceuil(ActionEvent event) {
        
             try {
            Parent root= 
                    FXMLLoader.load(getClass().getResource("../gui/Menu.fxml"));
                    //FXMLLoader.load(getClass().getResource("Metiee.fxml"));
       Stage stage =new Stage();
            
            Scene scene = new Scene(root);
             
           stage.setTitle("Hello World!");
            stage.setScene(scene);
             stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
    }
    
    
    }
    
    

}