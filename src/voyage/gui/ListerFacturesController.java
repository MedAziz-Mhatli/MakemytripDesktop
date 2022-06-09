/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import com.itextpdf.text.DocumentException;
import voyage.entities.Facture;
import voyage.entities.SendMail;
import voyage.services.FactureCRUD;
import voyage.utils.MyConnectionn;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI GAMMING
 */
public class ListerFacturesController implements Initializable {

    @FXML
    private TableView<Facture> factureTable;
    @FXML
    private Label searchFacture;
    @FXML
    private TableColumn<Facture, java.sql.Date> datefacture;
    @FXML
    private TableColumn<Facture, Double> remisefacture;
    @FXML
    private TableColumn<Facture, Double> Totalfacture;
    @FXML
    private TableColumn<Facture, String> typefacture;
    @FXML
    private TableColumn<Facture, Integer> clientfacture;

    ObservableList<Facture> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Facture, Integer> idfacture;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnfeedback;
    @FXML
    private Pane btnimprimer;
    @FXML
    private Button buttonimprimer;
    @FXML
    private TextField recherche;
    @FXML
    private Button btnpayer;
    @FXML
    private Button btnreset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Aff();

             //  SendMail.sendMail("mohamedaziz.mhatli@esprit.tn", "Demande Approuvé", "approuvée");

    }

    public void Aff(){
                list.clear();
                try {
            String requete = "SELECT * FROM facture";
            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {

                list.add(new Facture(rs.getInt(1), rs.getDate(2),rs.getDouble(3), rs.getDouble(4), rs.getString(5), rs.getInt(6)));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        idfacture.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("id"));
        datefacture.setCellValueFactory(new PropertyValueFactory<Facture, java.sql.Date>("date_facture"));
        remisefacture.setCellValueFactory(new PropertyValueFactory<Facture, Double>("remise_facture"));
        Totalfacture.setCellValueFactory(new PropertyValueFactory<Facture, Double>("total_facture"));
        typefacture.setCellValueFactory(new PropertyValueFactory<Facture, String>("type_facture"));
        clientfacture.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("id_client"));
        factureTable.setItems(list);        
        RechercheAV();
    }
    private Parent fxml;

    @FXML
    private void afficherInterfaceAjout(ActionEvent event) {
        Stage ajout = new Stage();
        System.out.println("interface d'ajout");
        try {
            fxml = FXMLLoader.load(getClass().getResource("ajouterFacture.fxml"));
            Scene scene = new Scene(fxml);
            ajout.setScene(scene);
            ajout.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void afficherInterfaceModifier(ActionEvent event) {

             ObservableList<Facture> all,Single ;
             all=factureTable.getItems();
             Single=factureTable.getSelectionModel().getSelectedItems();
             Facture A = Single.get(0);
             
        Stage ajout = new Stage();
        System.out.println("interface de modification");
        try {
            
              FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                  "modifierFacture.fxml"
                )
              );

              Stage stage = new Stage();
              stage.setScene(
                new Scene(loader.load())
              );

            ModifierFactureController mf = loader.getController();
            mf.setData(A);

              stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Facture> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(tmp -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (tmp.getType_facture().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(tmp.getId_client()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(tmp.getRemise_facture()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Facture> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(factureTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		factureTable.setItems(sortedData);
    }
    

    @FXML
    private void afficherInterface(ActionEvent event) {

        Stage ajout = new Stage();
        System.out.println("reclamation");
        try {
            fxml = FXMLLoader.load(getClass().getResource("menureclamation.fxml"));
            Scene scene = new Scene(fxml);
            ajout.setScene(scene);
            ajout.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void resetFacture(ActionEvent event){
             list.clear();
                try {
            String requete = "SELECT * FROM facture";
            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {

                list.add(new Facture(rs.getInt(1), rs.getDate(2),rs.getDouble(3), rs.getDouble(4), rs.getString(5), rs.getInt(6)));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        idfacture.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("id"));
        datefacture.setCellValueFactory(new PropertyValueFactory<Facture, java.sql.Date>("date_facture"));
        remisefacture.setCellValueFactory(new PropertyValueFactory<Facture, Double>("remise_facture"));
        Totalfacture.setCellValueFactory(new PropertyValueFactory<Facture, Double>("total_facture"));
        typefacture.setCellValueFactory(new PropertyValueFactory<Facture, String>("type_facture"));
        clientfacture.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("id_client"));
        factureTable.setItems(list);        
        RechercheAV();
    }

    @FXML
    private void imprimerFacture(ActionEvent event) throws DocumentException, SQLException, IOException {
        
        PDF a = new PDF();
        a.GeneratePDF("Liste des Factures");
    }

    @FXML
    private void Supprimer(ActionEvent event) {
             ObservableList<Facture> all,Single ;
             all=factureTable.getItems();
             Single=factureTable.getSelectionModel().getSelectedItems();
             Facture A = Single.get(0);
             FactureCRUD fc = new FactureCRUD();
             fc.supprimerFacture(A.getId_facture());
             Aff();
    }

    @FXML
    private void gointerfacepaiement(ActionEvent event) {
        
        Stage ajout = new Stage();
        System.out.println("paiement");
        try {
            fxml = FXMLLoader.load(getClass().getResource("paiementenligne.fxml"));
            Scene scene = new Scene(fxml);
            ajout.setScene(scene);
            ajout.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

   

   

}
