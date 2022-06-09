/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import voyage.entities.Facture;
import voyage.entities.Reclamation;
import voyage.services.ReclamationCRUD;
import voyage.utils.MyConnectionn;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tray.notification.NotificationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI GAMMING
 */
public class ReclamationController implements Initializable {

    @FXML
    private Button buttonAjouterReclamation;
    @FXML
    private TableView<Reclamation> TableViewReclamation;
    @FXML
    private TableColumn<Reclamation, Integer> idReclamation;
    @FXML
    private TableColumn<Reclamation, java.sql.Date> dateReclamation;
    @FXML
    private TableColumn<Reclamation, String> etatReclamation;
    @FXML
    private TextArea descinput;
    @FXML
    private ComboBox<String> comboetat;
    @FXML
    private DatePicker dateinput;
    @FXML
    private TextField emailinput;
    @FXML
    private TextField clientinput;
    @FXML
    private TableColumn<Reclamation, String> emailReclamation;
    @FXML
    private TableColumn<Reclamation, String> descReclamation;
    @FXML
    private TableColumn<Reclamation, Integer> clientReclamation;
    @FXML
    private TextField affichertyperec;
    @FXML
    private Button btnmodifierrec;

    /**
     * Initializes the controller class.
     */
    ObservableList<Reclamation> list = FXCollections.observableArrayList();
    @FXML
    private Label idlabel;
    @FXML
    private TextField recherche;
    @FXML
    private BarChart<String, Number> BarChart;
    ReclamationCRUD rc = new ReclamationCRUD();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        comboetat.setItems(FXCollections.observableArrayList("En cours", "Traité"));
        Aff();
    }

    public void Stat() {
        BarChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Répartition des Reclamations");
        series.getData().add(new XYChart.Data<>("En cours", rc.calculerEnCours()));
        series.getData().add(new XYChart.Data<>("Traité", rc.calculertrait()));
        BarChart.getData().addAll(series);

    }

    public void Aff() {
        list.clear();

        try {

            String requete = "SELECT * FROM RECLAMATION";
            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id_reclamation"), rs.getDate("date_reclamation"), rs.getString("email_reclamation"), rs.getString("desription_reclamation"), rs.getString("etat_reclamation"), rs.getInt("id_client")));
            }
            idReclamation.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
            dateReclamation.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
            emailReclamation.setCellValueFactory(new PropertyValueFactory<>("email_reclamation"));
            descReclamation.setCellValueFactory(new PropertyValueFactory<>("description_reclamation"));
            etatReclamation.setCellValueFactory(new PropertyValueFactory<>("etat_reclamation"));
            clientReclamation.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            TableViewReclamation.setItems(list);

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        RechercheAV();
        Stat();
    }

    private boolean Emailvalide() {
        Pattern p = Pattern.compile("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$");
        Matcher m = p.matcher(emailinput.getText());
        if (m.find() && m.group().equals(emailinput.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid email");
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) {

        if ((dateinput.getValue().toString().isEmpty()) || (descinput.getText().isEmpty()) 
                ||(comboetat.getValue().isEmpty()) || (clientinput.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" Champs invalides ");
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir les champs !");

            alert.showAndWait();
        } else {
            if (Emailvalide()) {
                LocalDate date = dateinput.getValue();
                java.util.Date date_reclamation = java.sql.Date.valueOf(date);
                String email_reclamation = emailinput.getText();
                String description_reclamation = descinput.getText();

                comboetat.getItems().add(affichertyperec.getText());
                affichertyperec.clear();
                String etat_reclamation = comboetat.getValue();
                int client_reclamation = Integer.parseInt(clientinput.getText());
                Reclamation r = new Reclamation(date_reclamation, email_reclamation, description_reclamation, etat_reclamation, client_reclamation);
                ReclamationCRUD rcd = new ReclamationCRUD();

                rcd.ajouterReclamation2(r);
                //   rcd.desactiver_etat_modification_reclamation(r.getId_reclamation());
                Aff();
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Ajout");
                tray.setMessage("Reclamation ajouter avec succés");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndWait();
            }

        }

    }

    @FXML
    private void modifierRec(ActionEvent event) {

        if ((dateinput.getValue().toString().isEmpty()) || (descinput.getText().isEmpty()) 
            ||(comboetat.getValue().isEmpty())|| (clientinput.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" Champs invalides ");
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir les champs !");

            alert.showAndWait();
        } else {
            if (Emailvalide()) {
                LocalDate date = dateinput.getValue();
                java.util.Date date_reclamation = java.sql.Date.valueOf(date);
                String email_reclamation = emailinput.getText();
                String description_reclamation = descinput.getText();

                comboetat.getItems().add(affichertyperec.getText());
                affichertyperec.clear();
                String etat_reclamation = comboetat.getValue();
                int client_reclamation = Integer.parseInt(clientinput.getText());
                Reclamation r = new Reclamation(date_reclamation, email_reclamation, description_reclamation, etat_reclamation, client_reclamation);
                ReclamationCRUD rcd = new ReclamationCRUD();
                rcd.modifierReclamation(Integer.valueOf(idlabel.getText()), r);
                Aff();

                TrayNotification tray = new TrayNotification();
                tray.setTitle("----Modification----");
                tray.setMessage("**Réclamation modifié avec succès**");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndWait();

            }
        }

    }

    @FXML
    private void loadModif(MouseEvent event) {
        ObservableList<Reclamation> all , Single;
        all = TableViewReclamation.getItems();
        Single = TableViewReclamation.getSelectionModel().getSelectedItems();
        Reclamation A = Single.get(0);

        idlabel.setText(String.valueOf(A.getId_reclamation()));
        LocalDate localDate = LocalDate.parse(A.getDate_reclamation().toString());
        dateinput.setValue(localDate);
        comboetat.setValue(A.getEtat_reclamation());
        emailinput.setText(A.getEmail_reclamation());
        descinput.setText(A.getDescription_reclamation());
        clientinput.setText(String.valueOf(A.getId_client()));

    }

    @FXML
    private void Supprimer(ActionEvent event) {
        TableViewReclamation.setItems(list);

        ObservableList<Reclamation> all, Single;
        all = TableViewReclamation.getItems();
        Single = TableViewReclamation.getSelectionModel().getSelectedItems();
        Reclamation A = Single.get(0);
        ReclamationCRUD STD = new ReclamationCRUD(); // STD = Service TAB DEMANDE
        STD.supprimerReclamation(A.getId_reclamation());
        Single.forEach(all::remove);
        Aff();
        TrayNotification tray = new TrayNotification();
        tray.setTitle("---Supprimer---");
        tray.setMessage("**Reclamation supprimeée **");
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndWait();

    }

    public void RechercheAV() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Reclamation> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tmp -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (tmp.getEmail_reclamation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (tmp.getEtat_reclamation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(TableViewReclamation.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        TableViewReclamation.setItems(sortedData);
    }

}
