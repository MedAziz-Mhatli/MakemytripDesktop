/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package voyage.gui;

import voyage.entities.guide_touristique;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import voyage.services.GuideService;
////////////////////
import voyage.entities.guide_touristique;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Jaouadi Oussama
 */
public class DetailGuideController implements Initializable {

    @FXML
    private TableView<guide_touristique> Table;
    @FXML
    private TableColumn<guide_touristique, String> NomT;
    @FXML
    private TableColumn<guide_touristique, String> AdresseT;
    @FXML
    private TableColumn<guide_touristique, String> EmailT;
    @FXML
    private TableColumn<guide_touristique, String> CodeT;
    @FXML
    private TableColumn<guide_touristique, String> PrenomT;
    @FXML
    private TableColumn<guide_touristique, Integer> TelephoneT;
    @FXML
    private Button SuppT;
    @FXML
    private Button mettreT;
    @FXML
    private TextField NomText;
    @FXML
    private TextField AdresseText;
    @FXML
    private TextField EmailText;
    private TextField IDGuideText;
    @FXML
    private TextField PrenomText;
    @FXML
    private TextField telephoneText;
    private GuideService guideService;
    @FXML
    private Button Chargerh;
    @FXML
    private Button retour2;
     GuideService Gs = new GuideService();
    @FXML
    private TableColumn<?, ?> DiplomeT;
    @FXML
    private TextField DiplomeText;
    @FXML
    private TextField searchText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();

         
        //items.addAll(guideService.read());
        //Table.setItems(items);
         
    }
    
    private boolean controleDeSaisi() {  

        if (NomText.getText().isEmpty() || PrenomText.getText().isEmpty() || AdresseText.getText().isEmpty() || EmailText.getText().isEmpty() ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", telephoneText.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le code de votre carte ! ");
                telephoneText.requestFocus();
                telephoneText.selectEnd();
                return false;
            }

           
        }
        return true;
    }
    
    
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
    public void refreshTable()
    {
        ObservableList<guide_touristique> list;
        try {
            list = Gs.getAll(searchText.getText());

            // list = Gs.readAll();
            CodeT.setCellValueFactory(new PropertyValueFactory<>("id_guide"));
            NomT.setCellValueFactory(new PropertyValueFactory<>("nom"));
            PrenomT.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            AdresseT.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            TelephoneT.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            EmailT.setCellValueFactory(new PropertyValueFactory<>("email"));


            //list.addAll(Gs.read());
            Table.setItems(list);
        
         } catch (SQLException ex) {
            Logger.getLogger(DetailGuideController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    @FXML
    private void onDelete(ActionEvent event) throws SQLException {
        //alert 
        guide_touristique selected = Table.getSelectionModel().getSelectedItem();
        Gs.delete(selected);
        Table.setItems(Gs.getAll());
    }

    @FXML
    private void onUpdate(ActionEvent event) throws SQLException {
        
        ObservableList<guide_touristique>items = FXCollections.observableArrayList();
        this.guideService= new GuideService();
        
        
        guide_touristique selected = Table.getSelectionModel().getSelectedItem();
        guide_touristique g = new guide_touristique(selected.getNom(),selected.getAdresse(),selected.getEmail(),selected.getTelephone(),selected.getPrenom());
    
        int x = selected.getId_guide();
        System.out.println(x);
        System.out.println(g.toString());
        String Nom = NomText.getText();
        String Adresse = AdresseText.getText();
        String email = EmailText.getText();
        int telephone = Integer.parseInt(telephoneText.getText());
        String prenom = PrenomText.getText();
        
        guide_touristique g1= new guide_touristique(Nom, Adresse, email, telephone, prenom);
        System.out.println(g1.toString());
        Gs.update(g1, x);
        
        Table.getItems().clear();
                
       // items.addAll(guideService.read());
        Table.setItems(Gs.getAll());
        
        NomText.setText(""); 
        AdresseText.setText("");
        EmailText.setText("");
        IDGuideText.setText("");
        PrenomText.setText("");
        telephoneText.setText("");
        
    }

 

    @FXML
    private void onSrearchChange(ActionEvent event) {
        try{
            refreshTable();
        }
        catch(Exception e)
        {

        }
    }

    @FXML
    private void Gotomain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ajouterResGuide.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

        
    }

    @FXML
    private void loadhotel(ActionEvent event) {
    }
}
    