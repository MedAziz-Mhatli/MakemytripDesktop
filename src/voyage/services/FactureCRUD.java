/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.services;

import voyage.entities.Facture;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import voyage.utils.MyConnectionn;

/**
 *
 * @author MSI GAMMING
 */
public class FactureCRUD {

//    public void ajouterFacture() {
//        try {
//            Date d1 = new Date();
//            String requete = "INSERT INTO facture(date_facture,remise_facture,total_facture,type_facture) VALUES (2022-02-14,4,3,'vol')";
//            Statement st = MyConnection.getInstance().getCnx().createStatement();
//            st.executeUpdate(requete);
//            System.out.println("Facture ajoutée !");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//
//    }
    @FXML
    private TextField confirmation;

    public void ajouterFacture(Facture f) {
        try {
            String requete = "INSERT INTO facture(date_facture,remise_facture,total_facture,type_fature) VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            System.out.println(f);
            pst.setDate(1, f.getDate_facture());
            pst.setDouble(2, f.getRemise_facture());
            System.out.println(f.getRemise_facture()+"SLT");
            pst.setDouble(3, f.getTotal_facture());
            pst.setString(4, f.getType_facture());
        //    pst.setInt(5, f.getId_client());
            System.out.println(pst);

            int insert = pst.executeUpdate();
            if (insert > 0) {
                Alert added = new Alert(AlertType.CONFIRMATION);
                added.setHeaderText("Facture ajoutée avec succes");
                added.show();

            } else {
                Alert notadded = new Alert(AlertType.ERROR);
                notadded.setHeaderText("Facture non ajoutée");
                notadded.show();

            }
//            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Probleme systeme attendu ");
            System.err.println(ex.getMessage());

        }

    }

    public List<Facture> listerFactures() {
        List<Facture> listF = new ArrayList<>();
        try {
            String requete = "SELECT * FROM facture";
            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Facture f1 = new Facture();
                f1.setId_facture(rs.getInt(1));
                f1.setDate_facture(rs.getDate("date_facture"));
                f1.setRemise_facture(rs.getDouble(3));
                f1.setTotal_facture(rs.getDouble(4));
                f1.setType_facture("type_fature");
                listF.add(f1);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listF;

    }

    public void modifierFacture(int id, Facture f) {
        try {
            String requete = "update facture set date_facture = ?,  remise_facture = ?, total_facture = ?, type_fature = ?  where id_facture = ?";
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            pst.setDate(1, f.getDate_facture());
            pst.setDouble(2, f.getRemise_facture());
            pst.setDouble(3, f.getTotal_facture());
            pst.setString(4, f.getType_facture());
            pst.setInt(5, f.getId_client());
            pst.setInt(6, id);
            
            int update = pst.executeUpdate();
            if (update > 0) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de modification");
                alert.setContentText("Voulez-vous modifier la facture n°" + id + "?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    pst.executeUpdate();
                } else {
                    System.out.println("Facture non modifiée");
                }
                System.out.println("Facture modifiée !");

            } else {
                System.out.println("Probleme de modification");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerFacture(int id) {
        try {
            String requete = "DELETE FROM facture WHERE id_facture =  "+id;
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            int delete = pst.executeUpdate();
            if (delete > 0) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setContentText("Voulez-vous supprimer la facture n°" + id + "?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    pst.executeUpdate();
                } else {
                    System.out.println("Facture non modifiée");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public Facture getFactureById(int id_facture) {
        Facture res = new Facture();

        try {
            String requete = "SELECT * FROM facture WHERE id_facture ='" + id_facture + "'";
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {

                Facture f1 = new Facture();
                f1.setId_facture(rs.getInt(1));
                f1.setDate_facture(rs.getDate("date_facture"));
                f1.setRemise_facture(rs.getDouble(3));
                f1.setTotal_facture(rs.getDouble(4));
                f1.setType_facture("type_fature");

                res = f1;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }

    public List<Facture> TrierFactureParDateCreation() {
        List<Facture> res = new ArrayList<>();

        try {
            String requete = "SELECT * FROM facture ORDER BY date_facture ASC";
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                Facture f1 = new Facture();
                f1.setId_facture(rs.getInt(1));
                f1.setDate_facture(rs.getDate("date_facture"));
                f1.setRemise_facture(rs.getDouble(3));
                f1.setTotal_facture(rs.getDouble(4));
                f1.setType_facture("type_facture");
                res.add(f1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;

    }

    public boolean TrouverFacture(int id) {

        try {
            boolean res = false;
            String requete = "select * from facture where id_facture='" + id + "'";
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            return rs.next();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

//    public void CalculerPrixTotal(int id_client) {
//
//        try {
//            String requete1 = "select (chambre.prix_nuitee_chambre * reservation_chambre.duree_res_chambre) as prix_loc_chambre from chambre JOIN reservation_chambre join client on chambre.id_chambre= reservation_chambre.id_chambre and client.id_client = reservation_chambre.id_client WHERE client.id_client =" + id_client;
//            String requete2 = "select (reservation_vehicule.duree_location_res_vehicule*vehicule.prix_location_vehicule) as prix_loc_vehicule from vehicule join reservation_vehicule join client on vehicule.id_vehicule= reservation_vehicule.id_vehicule and reservation_vehicule.id_client=client.id_client where client.id_client =" + id_client;
//            String requete3 = "select (reservation_vol.nb_personnes_res_vol*vol.prix_vol) as prix_vol from vol join reservation_vol join client on vol.id_vol = reservation_vol.id_vol and reservation_vol.id_client = client.id_client where client.id_client =" + id_client;
//            PreparedStatement pst1 = MyConnection.getInstance().getCnx().prepareStatement(requete1);
//            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete1);
//            PreparedStatement pst3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
//            ResultSet rs1 = pst1.executeQuery(requete1);
//            ResultSet rs2 = pst2.executeQuery(requete2);
//            ResultSet rs3 = pst3.executeQuery(requete3);
//            int factchambre = 0;
//            int factVehicule = 0;
//            int factVol = 0;
//
//            while (rs1.next()) {
//                factchambre = rs1.getInt(1);
//            }
//
//            while (rs2.next()) {
//                factVehicule = rs2.getInt(1);
//            }
//
//            while (rs3.next()) {
//                factVol = rs3.getInt(1);
//            }
//            System.out.println("-------- Facture------");
//            System.out.println("Votre facture du chambre : ");
//            System.out.println(factchambre);
//            System.out.println("votre facture de vehicule : ");
//            System.out.println(factVehicule);
//            System.out.println("votre facture de vol : ");
//            System.out.println(factVol);
//            System.out.println("-----------------------");
//            System.out.println("Totale : " + (factchambre + factVehicule + factVol));
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//
//    }
    public double calculerPrixReservationChambre(int id_client) {
        double factChambre = 0;

        try {
            String requete = "select (chambre.prix_nuitee_chambre * reservation_chambre.duree_res_chambre) as prix_loc_chambre from chambre JOIN reservation_chambre join client on chambre.id_chambre= reservation_chambre.id_chambre and client.id_client = reservation_chambre.id_client WHERE client.id_client =" + id_client;
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                factChambre = rs.getDouble(1);
            }
        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
        }

        return factChambre;
    }

    public double calculerPrixReservationVol(int id_client) {
        double factVol = 0;

        try {
            String requete = "select (reservation_vol.nb_personnes_res_vol*vol.prix_vol) as prix_vol from vol join reservation_vol join client on vol.id_vol = reservation_vol.id_vol and reservation_vol.id_client = client.id_client where client.id_client =" + id_client;
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                factVol = rs.getDouble(1);
            }
        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
        }

        return factVol;
    }

    public double calculerPrixReservationVehicule(int id_client) {
        double factVehicule = 0;

        try {
            String requete = "select (reservation_vehicule.duree_location_res_vehicule*vehicule.prix_location_vehicule) as prix_loc_vehicule from vehicule join reservation_vehicule join client on vehicule.id_vehicule= reservation_vehicule.id_vehicule and reservation_vehicule.id_client=client.id_client where client.id_client =" + id_client;
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                factVehicule = rs.getDouble(1);
            }
        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
        }

        return factVehicule;
    }

    public double CalculerPrixTotalInterface(int id_client) {
        double factchambre = 0;
        double factVehicule = 0;
        double factVol = 0;
        double remise = 0;
     
        try {
            String requete1 = "select (chambre.prix_nuitee_chambre * reservation_chambre.duree_res_chambre) as prix_loc_chambre from chambre JOIN reservation_chambre join client on chambre.id_chambre= reservation_chambre.id_chambre and client.id_client = reservation_chambre.id_client WHERE client.id_client =" + id_client;
            String requete2 = "select (reservation_vehicule.duree_location_res_vehicule*vehicule.prix_location_vehicule) as prix_loc_vehicule from vehicule join reservation_vehicule join client on vehicule.id_vehicule= reservation_vehicule.id_vehicule and reservation_vehicule.id_client=client.id_client where client.id_client =" + id_client;
            String requete3 = "select (reservation_vol.nb_personnes_res_vol*vol.prix_vol) as prix_vol from vol join reservation_vol join client on vol.id_vol = reservation_vol.id_vol and reservation_vol.id_client = client.id_client where client.id_client =" + id_client;

            PreparedStatement pst1 = MyConnectionn.getInstance().getCnx().prepareStatement(requete1);
            PreparedStatement pst2 = MyConnectionn.getInstance().getCnx().prepareStatement(requete1);
            PreparedStatement pst3 = MyConnectionn.getInstance().getCnx().prepareStatement(requete3);

            ResultSet rs1 = pst1.executeQuery(requete1);
            ResultSet rs2 = pst2.executeQuery(requete2);
            ResultSet rs3 = pst3.executeQuery(requete3);

            while (rs1.next()) {
                factchambre = rs1.getDouble(1);
            }

            while (rs2.next()) {
                factVehicule = rs2.getDouble(1);
            }

            while (rs3.next()) {
                factVol = rs3.getDouble(1);
            }
           
//            System.out.println("-------- Facture------");
//            System.out.println("Votre facture du chambre : ");
//            System.out.println(factchambre);
//            System.out.println("votre facture de vehicule : ");
//            System.out.println(factVehicule);
//            System.out.println("votre facture de vol : ");
//            System.out.println(factVol);
//            System.out.println("-----------------------");
//            System.out.println("Totale : " + (factchambre + factVehicule + factVol));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return factchambre + factVehicule + factVol;

    }

    public double calculerRemiseFacture(int id_client) {
        double remise_facture =0;
        double res=0;
        double resultat =0;
        try {
            String requete = "select remise_facture from facture where id_client='" + id_client + "'";
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            
             while (rs.next()) {
                remise_facture = rs.getDouble(1);
                res = CalculerPrixTotalInterface(id_client);
                resultat = res - res * (remise_facture/100);
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return resultat;

    }

    public int coutidClient(int id_client) {
        int l = 0 ;
         String requete = "SELECT COUNT(*) FROM facture WHERE id_client= "+id_client+" and type_facture = \"totale\"" ;
        try {
           
           Statement st =MyConnectionn.getInstance().getCnx().createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(FactureCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;

    }


}
