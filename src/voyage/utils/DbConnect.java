/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.utils;
import voyage.entities.Voiture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author MOHAMMEDHABIBKHATTAT
 */
public class DbConnect {
    
        private static String HOST = "127.0.0.1";
        private static int PORT = 3306;
        private static String DB_NAME = "vehicule";
        private static String USERNAME = "root";
        private static String PASSWORD = "";
        private static Connection connection ;
        
        
        public static Connection getConnect (){
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,DB_NAME),USERNAME,PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return  connection;
        }


        public static ObservableList<Voiture> getDatausers(){
        Connection conn = getConnect();
        ObservableList<Voiture> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Voiture");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Voiture(Integer.parseInt(rs.getString("id")), rs.getString("matricule"), rs.getString("nbplace"), rs.getString("prix"), rs.getString("marque"), rs.getString("modele"), rs.getString("color"), rs.getString("image")));
            }
        } catch (Exception e) {
        }
        return list;
    }




}
