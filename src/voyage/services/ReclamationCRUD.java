/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import voyage.entities.Reclamation;
import voyage.utils.MyConnectionn;


/**
 *
 * @author MSI GAMMING
 */
public class ReclamationCRUD { public void ajouterReclamation() {

        try {
            Date d1 = new Date();
            java.sql.Date sqlDate1 = new java.sql.Date(d1.getTime());
            System.out.println(sqlDate1);
            String requete = "INSERT INTO reclamation(date_reclamation,desription_reclamation,etat_reclamation) values('2022-02-14','Reclamation sur un vol',1)";
            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Réclamation ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterReclamation2(Reclamation r) {

        try {
            String requete = "INSERT INTO reclamation(date_reclamation,email_reclamation,desription_reclamation,etat_reclamation,id_client) values (?,?,?,?,?)";
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            pst.setDate(1, (java.sql.Date) r.getDate_reclamation());
            pst.setString(2, r.getEmail_reclamation());
            pst.setString(3, r.getDescription_reclamation());
            pst.setString(4, r.getEtat_reclamation());
            pst.setInt(5, r.getId_client());
            pst.executeUpdate();
            System.out.println("Reclamation ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<Reclamation> listerReclamations() {
        List<Reclamation> listR = new ArrayList<>();

        try {

            String requete = "SELECT * FROM reclamation";
            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {

                Reclamation r1 = new Reclamation();
                r1.setId_reclamation(rs.getInt(1));
                r1.setDate_reclamation(rs.getDate("date_reclamation"));
                r1.setEmail_reclamation(rs.getString(3));
                r1.setDescription_reclamation(rs.getString(4));
                r1.setEtat_reclamation(rs.getString(5));
                r1.setId_client(rs.getInt(6));

                listR.add(r1);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listR;

    }

    public void modifierReclamation(int id, Reclamation r) {

        try {
            String requete = "update reclamation set id_reclamation = ? ,date_reclamation = ? ,email_reclamation = ? , desription_reclamation = ? ,etat_reclamation = ? , id_client = ? where id_reclamation = ?";
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, r.getId_reclamation());
            pst.setDate(2, (java.sql.Date) r.getDate_reclamation());
            pst.setString(3, r.getEmail_reclamation());
            pst.setString(4, r.getDescription_reclamation());
            pst.setString(5, r.getEtat_reclamation());
            pst.setInt(6, r.getId_client());
            pst.setInt(7, id);
            pst.executeUpdate();

            System.out.println("Reclamation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void supprimerReclamation(int id) {

        try {
            String requete = "DELETE FROM reclamation where id_reclamation = ?";

            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Reclamation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

//    public void retournerReclamationActives() {
//        List<Reclamation> listR = new ArrayList<>();
//
//        try {
//            String requete = "select * from reclamation where etat_reclamation = 1";
//            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
//            ResultSet rs = pst.executeQuery(requete);
//            System.out.println("Les reclamations actives sont : ");
//            while (rs.next()) {
//
//                Reclamation r1 = new Reclamation();
//                r1.setId_reclamation(rs.getInt(1));
//                r1.setDate_reclamation(rs.getDate("date_reclamation"));
//                r1.setDescription_reclamation(rs.getString("description_reclamation"));
//                r1.setEtat_reclamation(rs.getInt(4));
//                r1.setId_client(rs.getInt(5));
//
//                listR.add(r1);
//
//            }
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        System.out.println(listR);
//
//    }

//    public List<Reclamation> Desactivermodification(int id, Reclamation r) {
//        List<Reclamation> listR = new ArrayList<>();
//
//        Date d = new Date();
//
//        if (d.compareTo(r.getDate_reclamation()) > 24) {
//            try {
//                String requete = "UPDATE reclamation set etat_modification=false";
//                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
//                ResultSet rs = pst.executeQuery(requete);
//                while (rs.next()) {
//
//                    Reclamation r1 = new Reclamation();
//                    r1.setId_reclamation(rs.getInt(1));
//                    r1.setDate_reclamation(rs.getDate("date_reclamation"));
//                    r1.setDescription_reclamation(rs.getString("description_reclamation"));
//                    r1.setEtat_reclamation(rs.getInt(4));
//                    r1.setId_client(rs.getInt(5));
//                    System.out.println("Désactivation de modification!");
//
//                    listR.add(r1);
//
//                }
//            } catch (SQLException ex) {
//                System.err.println(ex.getMessage());
//            }
//
//        }
//
//        return listR;
//    }

    public void desactiver_etat_modification_reclamation(int id) {
        Timer t = new Timer();
        Date d = new Date();
        Reclamation r = new Reclamation(d, "x.y@gmail.com","bonne", "en cours de traitement", 1);

        System.out.println(r.getDate_reclamation());
        t.scheduleAtFixedRate(r, r.getDate_reclamation(), 86400); // 

    }
        public int calculerEnCours() {
          int l = 0 ;
         String requete = "SELECT COUNT(*) FROM reclamation WHERE etat_reclamation=\"En cours\" " ;
        try {
           
           Statement st =MyConnectionn.getInstance().getCnx().createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;
    }
            public int calculertrait() {
          int l = 0 ;
         String requete = "SELECT COUNT(*) FROM reclamation WHERE etat_reclamation=\"Traité\" " ;
        try {
           
           Statement st =MyConnectionn.getInstance().getCnx().createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;
    }
}
