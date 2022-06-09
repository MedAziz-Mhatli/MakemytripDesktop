/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.services;


import voyage.entities.Chambre;
import voyage.entities.Hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import voyage.utils.MyConnectionn;


/**
 *
 * @author siwar
 */
public class HotelCrud {
      public void ajouterHotel(){
        String requete ="INSERT INTO hotel(nom,description,adresse,categorie,nb_chDispo,email,telephone) VALUES ('rochelle','welcom','marsa','5 etoile',8,'larouchelle@gmail.com',50744654)";
        try {
            Statement st= MyConnectionn.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Hotel Ajouteé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public int ajouterHotel2(Hotel h){
            int st=0;
    String requete="INSERT INTO hotel(nom,description,adresse,categorie,nb_chDispo,email ,telephone) VALUES (?,?,?,?,?,?,?)";
        try {
           PreparedStatement pst= MyConnectionn.getInstance().getCnx().prepareStatement(requete);
             pst.setString(1, h.getNom());
             pst.setString(2, h.getDescription());
             pst.setString(3, h.getAdresse());
            pst.setString(4, h.getCategorie());
             pst.setInt(5, h.getNb_chDispo());
             pst.setString(6, h.getEmail());
              pst.setInt(7, h.getTelephone());
            st= pst.executeUpdate();
             System.out.println("element Ajouteé!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return st;
    }
      public ObservableList<Hotel> listerHoteles(){
          ObservableList<Hotel>  myList=FXCollections.observableArrayList();
          try {
           String requete="SELECT * FROM hotel";
           Statement st = MyConnectionn.getInstance().getCnx().createStatement();
           ResultSet rs =st.executeQuery(requete);
           while(rs.next()){
               Hotel hot=new Hotel();
                hot.setId_hotel(rs.getInt(1));
               hot.setNom(rs.getString("nom"));
               hot.setDescription(rs.getString("description"));
               hot.setAdresse(rs.getString("adresse"));
               hot.setCategorie(rs.getString("categorie"));
               hot.setNb_chDispo(rs.getInt(6));
               hot.setEmail(rs.getString("email"));
               hot.setTelephone(rs.getInt(8));
               myList.add(hot);
               System.out.println("\n");
           }
          } catch (SQLException ex) {
             System.out.println(ex.getMessage());
          }
       return myList;
      }
   public void supprimerHotel(Hotel hh){
         try { 
    String requete="DELETE FROM hotel WHERE id_hotel =" +hh.getId_hotel();
       
            Statement st= MyConnectionn.getInstance().getCnx().createStatement();
          st.executeUpdate(requete);
             System.out.println("hotel supprimer!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
       /*public void modifierHotel(Hotel h,String nom, String description,String adresse,String categorie,int nb_chDispo, String email,int telephone){
           try { 
         String requete="UPDATE hotel SET  nom=?, description=?, adresse=? , categorie=? , nb_chDispo=? , email=? , telephone =?  WHERE telephone="+3;
         
              PreparedStatement ps= MyConnection.getInstance().getCnx().prepareStatement(requete);
              // ps.setInt(1, h.getId_hotel());
               ps.setString(1, h.getNom());
               ps.setString(2, h.getDescription());
               ps.setString(3, h.getAdresse());
               ps.setString(4, h.getCategorie());
               ps.setInt(5, h.getNb_chDispo());
               ps.setString(6, h.getEmail());
               ps.setInt(7, h.getTelephone());
                ps.executeUpdate();
                System.out.println("hotel Modifier!");
          } catch (SQLException ex) {
              
             System.out.println(ex.getMessage());
          }
}*/
   public void modifierHotel(Hotel h)throws SQLException {
      
    String query = "UPDATE `hotel` SET `description` = '" + h.getDescription() + "', `adresse` = '" +h.getAdresse() + ", `categorie` = " + h.getCategorie() + "', `nb_chDispo` = '" + h.getNb_chDispo() + "', `email` = '" + h.getEmail() + "', `telephone` = '" + h.getTelephone() + "' WHERE `hotel`.`nom` = " + h.getNom() + ";";
         //    String requete="UPDATE hotel SET  nom=?, description=?, adresse=? , categorie=? , nb_chDispo=? , email=? , telephone =?  WHERE nom=?";

  PreparedStatement ps= MyConnectionn.getInstance().getCnx().prepareStatement(query);
              ps.executeUpdate();

}
        
       
      public void listerchm(int i){
           String requete ="SELECT ch.type,  COUNT(ch.type) AS nb_chambre   FROM chambre ch inner join hotel h on h.id_hotel = ch.id_hotel WHERE ch.id_hotel ="+i+" GROUP BY ch.type";
          
            
          try {
            PreparedStatement  ps = MyConnectionn.getInstance().getCnx().prepareStatement(requete);
              ResultSet rs =ps.executeQuery(requete);
              
              while(rs.next()){
              int nb=rs.getInt("nb_chambre");
              
              //System.out.println(nb);
              String nbb=rs.getString("type");
              System.out.println("le nombre des chambres du type "+nbb+" est :"+nb);
              }
           
          } catch (SQLException ex) {
          System.out.println(ex.getMessage());
          }
           
           
          }
      
      public void supprimerChambre(Chambre ch){
       try {    
    String requete="DELETE FROM chambre WHERE id_chambre ="+ch.getId_chambre();
         
             Statement st= MyConnectionn.getInstance().getCnx().createStatement();
          st.executeUpdate(requete);
             System.out.println("chambre supprimer!");
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());}
         
       }
       
      }

