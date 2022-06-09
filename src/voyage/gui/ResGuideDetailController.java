/* Decompiler 17ms, total 195ms, lines 167 */
package voyage.gui;

import voyage.entities.vol;
import voyage.entities.guide_touristique;
import voyage.entities.reservation_guide;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import voyage.services.GuideService;
import voyage.services.reservationGuideService;
import voyage.utils.MyConnectionn;


public class ResGuideDetailController implements Initializable {
   @FXML
   private TableView<reservation_guide> Table;
   @FXML
   private TableColumn<reservation_guide, Integer> idResT;
   @FXML
   private TableColumn<reservation_guide, Integer> idguideT;
   @FXML
   private TableColumn<reservation_guide, Integer> idResVolT;
   @FXML
   private Button SuppT;
   @FXML
   private Button mettreT;
   @FXML
   private Button Chargerh;
   @FXML
   private Button retour2;
   @FXML
   private ComboBox<guide_touristique> cIdGuide;
   @FXML
   private ComboBox<vol> cResVol;
   private reservationGuideService rs;
   private GuideService guideService;
   @FXML
   private PieChart pieChart;

   public void initialize(URL url, ResourceBundle rb) {
      this.rs = new reservationGuideService();

      try {
         ObservableList<reservation_guide> list = this.rs.getAll();
         System.out.println("im here");
         this.idResT.setCellValueFactory(new PropertyValueFactory("id_resGuide"));
         this.idguideT.setCellValueFactory(new PropertyValueFactory("id_guide"));
         this.idResVolT.setCellValueFactory(new PropertyValueFactory("id_resVol"));
         this.Table.setItems(list);
         this.guideService = new GuideService();
         ObservableList<guide_touristique> guideList = FXCollections.observableArrayList();
         guideList.addAll(this.guideService.readAll());
         this.cIdGuide.setItems(guideList);
         ObservableList<vol> volList = FXCollections.observableArrayList();
         volList.addAll(this.getVols());
         this.cResVol.setItems(volList);
      } catch (SQLException var10) {
         Logger.getLogger(DetailGuideController.class.getName()).log(Level.SEVERE, (String)null, var10);
         System.out.println(var10);
      }

      try {
         String sql = "SELECT COUNT(DISTINCT(id_guide)) FROM reservation_guide";
         ResultSet rs = MyConnectionn.getInstance().getCnx().createStatement().executeQuery(sql);
         rs.next();
         int numYes = rs.getInt(1);
         sql = "SELECT COUNT(*) FROM guide_touristique";
         rs = MyConnectionn.getInstance().getCnx().createStatement().executeQuery(sql);
         rs.next();
         int numTotal = rs.getInt(1);
         ObservableList<Data> pieChartData = FXCollections.observableArrayList(new Data[]{new Data("Résérvés", (double)numYes), new Data("Non Résérvés", (double)(numTotal - numYes))});
         this.pieChart.setData(pieChartData);
      } catch (Exception var9) {
         System.out.print(var9);
      }

   }

   public List<vol> getVols() {
      String req = "select * from vol";
      ArrayList list = new ArrayList();

      try {
         Connection conn = MyConnectionn.getInstance().getCnx();
         Statement ste = conn.createStatement();
         ResultSet resArr = ste.executeQuery(req);
         System.out.println("Affichage :");

         while(resArr.next()) {
            list.add(new vol(resArr.getInt("id_vol"), resArr.getString("départ"), resArr.getString("destination"), resArr.getDate("date_départ"), resArr.getDate("date_retour"), resArr.getInt("nb_escales"), (double)resArr.getInt("prix")));
         }

         System.out.print(list);
      } catch (SQLException var6) {
         Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return list;
   }

   @FXML
   private void onDelete(ActionEvent event) throws SQLException {
      reservation_guide selected = (reservation_guide)this.Table.getSelectionModel().getSelectedItem();
      this.rs.delete(selected);
      this.Table.setItems(this.rs.getAll());
   }

   @FXML
   private void onUpdate(ActionEvent event) throws SQLException {
      ObservableList<reservation_guide> items = FXCollections.observableArrayList();
      this.guideService = new GuideService();
      this.rs = new reservationGuideService();
      reservation_guide selected = (reservation_guide)this.Table.getSelectionModel().getSelectedItem();
      int x = selected.getId_resGuide();
      System.out.println(x);
      reservation_guide g1 = new reservation_guide(((guide_touristique)this.cIdGuide.getSelectionModel().getSelectedItem()).getId_guide(), ((vol)this.cResVol.getSelectionModel().getSelectedItem()).getId_vol());
      System.out.println(g1.toString());
      this.rs.update(g1, x);
      this.Table.getItems().clear();
      this.Table.setItems(this.rs.getAll());
   }

   @FXML
   private void loadResGuid(ActionEvent event) throws SQLException {
      this.Table.setItems(this.rs.getAll());
   }

   @FXML
   private void GotoAjouterResGuide(ActionEvent event) {
      Parent root = null;

      try {
         root = (Parent)FXMLLoader.load(this.getClass().getResource("ajouterResGuide.fxml"));
      } catch (IOException var5) {
        
      }

      Stage mainStage = new Stage();
      Scene scene = new Scene(root);
      mainStage.setScene(scene);
      mainStage.show();
   }
}
