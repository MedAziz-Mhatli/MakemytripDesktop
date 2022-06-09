/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.services;

import voyage.entities.res_vol;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import voyage.utils.MyConnectionn;

/**
 *
 * @author sahra
 */
public class res_volCRUD {

    public void ajouterRes_vol(res_vol r) {
        try {
            Date d1 = new Date();
            String requete = "INSERT INTO `reservation_vol`(`id_resVol`,`id_vol`,`id_client`,`date_resVol`,`compagnie_aerienne`,`classe`,`nb_personnes`) VALUES ('"+r.getId_resVol()+"','"+r.getId_vol()+"','"+r.getId_client()+"','"+r.getDate_resvol()+"','"+r.getCompagnie_aerienne()+"','"+r.getClasse()+"','"+r.getNb_personnes()+"')";
            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Reservation vol ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<res_vol> listerRes_vol() {

        List<res_vol> myList = new ArrayList();

        try {
            String req = "SELECT * FROM reservation_vol";
           Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                myList.add(new res_vol(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5),rs.getString(6),rs.getInt(7)));
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return myList;
    }

    public void modifierRes_vol( res_vol r) {
        try {
            String requete = "UPDATE reservation_vol SET id_resVol = ?, id_vol = ?, id_client = ? ,date_resvol = ? ,compagnie_aerienne = ?,classe = ?,nb_personnes = ?   WHERE id_resVol = ?";
            PreparedStatement ps = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, r.getId_resVol());
            ps.setInt(2, r.getId_vol());
            ps.setInt(3, r.getId_client());
            ps.setDate(4, (java.sql.Date) r.getDate_resvol());
            ps.setString(5,r.getCompagnie_aerienne().toString());
            ps.setString(6,r.getClasse().toString());
            ps.setInt(7, r.getNb_personnes());
            ps.setInt(8, r.getId_resVol());
            ps.executeUpdate();
            System.out.println("Reservation vol modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
      public void supprimerReservationvol(int id){
            
         String requete="DELETE FROM reservation_vol WHERE id_resVol = ?" ;
         try {
             PreparedStatement pst= MyConnectionn.getInstance().getCnx().prepareStatement(requete);
             pst.setInt(1, id);
             pst.executeUpdate();
             System.out.println("reservation vol supprimée !");
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());          }
       }
      public List<res_vol> rechercherid(res_vol r) {
        List<res_vol> res_vol = listerRes_vol();
        return res_vol.stream().filter(b -> r.getId_resVol()==b.getId_resVol()).collect(Collectors.toList());
}
       public List<res_vol> Tri() {
        Comparator<res_vol> comparator = Comparator.comparing(res_vol::getId_vol);
        List<res_vol> res_vol = listerRes_vol();
        return res_vol.stream().sorted(comparator).collect(Collectors.toList());
    }
           public long tun() {
        List<res_vol> res_vol = listerRes_vol();
        return res_vol.stream().filter(b -> b.getCompagnie_aerienne().equals("Tunisair")).count();
}
                  public long nouv() {
        List<res_vol> res_vol = listerRes_vol();
        return res_vol.stream().filter(b -> b.getCompagnie_aerienne().equals("Nouvelair")).count();
                  }
                  public long Emir() {
        List<res_vol> res_vol = listerRes_vol();
        return res_vol.stream().filter(b -> b.getCompagnie_aerienne().equals("Emirates")).count();
}


}


