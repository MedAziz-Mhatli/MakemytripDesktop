/* Decompiler 9ms, total 171ms, lines 116 */
package voyage.services;

import edu.voyage.IService.IreservationGuide;
import voyage.entities.reservation_guide;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import voyage.utils.MyConnectionn;


public class reservationGuideService implements IreservationGuide<reservation_guide> {
   private Connection conn = MyConnectionn.getInstance().getCnx();
   private PreparedStatement pst;
   private ResultSet rs;
   private Statement ste;

   public void insert(reservation_guide t) {
      String req = "insert into reservation_guide (id_guide,id_resGuide,id_resVol) values (?,?,?)";

      try {
         this.pst = this.conn.prepareStatement(req);
         this.pst.setInt(1, t.getId_guide());
         this.pst.setInt(2, t.getId_resGuide());
         this.pst.setInt(3, t.getId_resVol());
         this.pst.executeUpdate();
      } catch (SQLException var4) {
         Logger.getLogger(reservationGuideService.class.getName()).log(Level.SEVERE, (String)null, var4);
      }

   }

   public void update(reservation_guide t, int id) {
      String req = "UPDATE reservation_guide SET id_guide =?, id_resVol=?  WHERE id_resGuide='" + id + "' ";

      try {
         this.pst = this.conn.prepareStatement(req);
         this.pst.setInt(1, t.getId_guide());
         this.pst.setInt(2, t.getId_resVol());
         this.pst.executeUpdate();
         System.out.println("updated succefuly...");
      } catch (SQLException var5) {
         Logger.getLogger(reservationGuideService.class.getName()).log(Level.SEVERE, (String)null, var5);
      }

   }

   public void delete(int id) {
      try {
         Statement stm = this.conn.createStatement();
         String query = "delete from reservation_guide where id_resGuide = '" + id + "'";
         stm.executeUpdate(query);
         System.out.println("reservation guide with id " + id + " deleted");
      } catch (SQLException var4) {
         Logger.getLogger(reservationGuideService.class.getName()).log(Level.SEVERE, (String)null, var4);
      }

   }

   public void delete(reservation_guide h) {
      String req = "delete from reservation_guide WHERE id_guide=? and id_resVol=? ";

      try {
         this.pst = this.conn.prepareStatement(req);
         this.pst.setInt(1, h.getId_guide());
         this.pst.setInt(2, h.getId_resVol());
         this.pst.executeUpdate();
         System.out.println("deleted succefully");
      } catch (SQLException var4) {
         Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, (String)null, var4);
      }

   }

   public ObservableList<reservation_guide> getAll() throws SQLException {
      ObservableList<reservation_guide> guideList = FXCollections.observableArrayList();
      new ArrayList();
      this.ste = this.conn.createStatement();
      String query = "select id_resGuide, id_guide, id_resVol from reservation_guide";
      this.rs = this.ste.executeQuery(query);

      while(this.rs.next()) {
         reservation_guide reservation_guide = new reservation_guide(this.rs.getInt("id_resGuide"), this.rs.getInt("id_guide"), this.rs.getInt("id_resVol"));
         guideList.add(reservation_guide);
      }

      return guideList;
   }

   public List<reservation_guide> readAll() {
      String req = "select * from reservation_guide";
      ArrayList list = new ArrayList();

      try {
         this.ste = this.conn.createStatement();
         this.rs = this.ste.executeQuery(req);
         System.out.println("Affichage :");

         while(this.rs.next()) {
            list.add(new reservation_guide(this.rs.getInt("id_resGuide"), this.rs.getInt("id_guide"), this.rs.getInt("id_resVol")));
         }

         System.out.println(list);
      } catch (SQLException var4) {
         Logger.getLogger(reservationGuideService.class.getName()).log(Level.SEVERE, (String)null, var4);
      }

      return list;
   }
}
