/* Decompiler 14ms, total 918ms, lines 103 */
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import voyage.services.GuideService;
import voyage.services.reservationGuideService;
import voyage.utils.MyConnectionn;

public class AjouterResGuideController implements Initializable {
   private TextField zoneGuidId;
   private TextField zoneVolId;
   @FXML
   private Button btnSubmit;
   @FXML
   private ChoiceBox<guide_touristique> guidR;
   private reservationGuideService rs;
   private GuideService guideService;
   private reservationGuideService reservationguideService;
   @FXML
   private ChoiceBox<vol> volR;
   @FXML
   private ImageView imageDepart;
   @FXML
   private ImageView imageArrivee;

   public void initialize(URL url, ResourceBundle rb) {
      this.rs = new reservationGuideService();
      this.guideService = new GuideService();
      ObservableList<guide_touristique> guideList = FXCollections.observableArrayList();
      guideList.addAll(this.guideService.readAll());
      this.guidR.setItems(guideList);
      ObservableList<vol> volList = FXCollections.observableArrayList();
      volList.addAll(this.getVols());
      this.volR.setItems(volList);
      this.volR.setOnAction((event) -> {
         this.imageDepart.setImage(new Image("https://maps.googleapis.com/maps/api/staticmap?center=" + ((vol)this.volR.getSelectionModel().getSelectedItem()).getDepart_vol() + "&zoom=10&size=400x400&key=AIzaSyAKjOrEv3g_Ns4n4vyXqtovG1dzgUcemW4"));
         this.imageArrivee.setImage(new Image("https://maps.googleapis.com/maps/api/staticmap?center=" + ((vol)this.volR.getSelectionModel().getSelectedItem()).getDestination_vol() + "&zoom=10&size=400x400&key=AIzaSyAKjOrEv3g_Ns4n4vyXqtovG1dzgUcemW4"));
      });
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
            list.add(new vol(resArr.getInt("id_vol"), resArr.getString("depart_vol"), resArr.getString("destination_vol"), resArr.getDate("date_depart_vol"), resArr.getDate("date_retour_vol"), resArr.getInt("nb_escales_vol"), (double)resArr.getInt("prix_vol")));
         }

         System.out.print(list);
      } catch (SQLException var6) {
         Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return list;
   }

   @FXML
   private void onCreate(ActionEvent event) throws IOException {
      vol vol = (vol)this.volR.getSelectionModel().getSelectedItem();
      guide_touristique Guide = (guide_touristique)this.guidR.getSelectionModel().getSelectedItem();
      reservation_guide newReservation = new reservation_guide(Guide.getId_guide(), vol.getId_vol());
      System.out.println(newReservation);
      this.rs.insert(newReservation);
      Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("ResGuideDetail.fxml"));
      Stage mainStage = new Stage();
      Scene scene = new Scene(root);
      mainStage.setScene(scene);
      mainStage.show();
   }
}
