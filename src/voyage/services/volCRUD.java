/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.services;

import voyage.entities.vol;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import voyage.utils.MyConnectionn;


/**
 *
 * @author sahra
 */
public class volCRUD {


    public void ajouterVol(vol  r) {
        try {
            Date d1 = new Date();
            String requete = "INSERT INTO vol(id_vol,depart_vol,destination_vol,date_depart_vol,date_retour_vol,nb_escales_vol,prix_vol) VALUES ('"+r.getId_vol()+"','"+r.getDepart_vol()+"','"+r.getDestination_vol()+"','"+r.getDate_departVol()+"','"+r.getDate_retourVol()+"','"+r.getNb_escalesVol()+"','"+r.getPrixVol()+"')";
            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Vol ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterVol2(vol v) {
        try {
            String requete = "INSERT INTO vol(id_vol,depart_vol,destination_vol,date_depart_vol,date_retour_vol,nb_escales_vol,prix_vol) values (?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, v.getId_vol());
            pst.setString(2, v.getDepart_vol());
            pst.setString(3, v.getDestination_vol());
            pst.setDate(4, (java.sql.Date) v.getDate_departVol());
            pst.setDate(5, (java.sql.Date) v.getDate_retourVol());
            pst.setInt(6, v.getNb_escalesVol());
            pst.setDouble(7, v.getPrixVol());
            pst.executeUpdate();
            System.out.println("Vol ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<vol> listerVols() {
        List<vol> mylist = new ArrayList();
        try {
            String req = "SELECT * FROM vol";
           Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                mylist.add(new vol(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5),rs.getInt(6),rs.getDouble(6)));
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return mylist;
    }

    public void modifierVol(vol v) {
        String requete = "UPDATE vol SET id_vol = ?,depart_vol = ?,destination_vol = ?,date_depart_vol = ?,date_retour_vol = ?,nb_escales_vol = ?,prix_vol = ? WHERE id_vol =?";
        try {
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, v.getId_vol());
            pst.setString(2, v.getDepart_vol());
            pst.setString(3, v.getDestination_vol());
            pst.setDate(4, (java.sql.Date) v.getDate_departVol());
            pst.setDate(5, (java.sql.Date) v.getDate_retourVol());
            pst.setInt(6, v.getNb_escalesVol());
            pst.setDouble(7, v.getPrixVol());
            pst.setInt(8, v.getId_vol());
            pst.executeUpdate();
            System.out.println("Vol a été modifier!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerVol(int id) {
        String requete = "DELETE FROM vol WHERE id_vol=?";
        try {
            PreparedStatement pst = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Vol a été annulé!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }
    public void listvoll(){
        
        
        String requete ="SELECT COUNT(id_vol) AS Nb_vol FROM vol ";
          
            
          try {
            PreparedStatement  ps = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
              ResultSet rs =ps.executeQuery(requete);
              
              while(rs.next()){
              
               int nb = rs.getInt("Nb_vol");
              System.out.println(nb);
           
           
          }
          }catch (SQLException ex) {
          System.out.println(ex.getMessage());
          }
    }
}


