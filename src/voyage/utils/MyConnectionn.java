/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.utils;

import static com.itextpdf.text.pdf.BaseField.PASSWORD;
import static com.mysql.cj.conf.PropertyKey.HOST;
import static com.mysql.cj.conf.PropertyKey.PORT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import voyage.entities.Voiture;

/**
 *
 * @author MSI GAMMING
 */
public class MyConnectionn {
     public String url = "jdbc:mysql://localhost:3306/siwar3";
    public String login = "root";
    public String pwd = "";
    
     private static String HOST = "127.0.0.1";
        private static int PORT = 3306;
        private static String DB_NAME = "siwar3";
        private static String USERNAME = "root";
        private static String PASSWORD = "";
        private static Connection connection ;

    Connection cnx;
    public static MyConnectionn instance;

    public MyConnectionn() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static MyConnectionn getInstance() {
        if (instance == null) {
            instance = new MyConnectionn();
        }
        return instance;
    }
    
    
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
            PreparedStatement ps = conn.prepareStatement("select * from voiture");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Voiture(Integer.parseInt(rs.getString("id")), rs.getString("matricule"), rs.getString("nbplace"), rs.getString("prix"), rs.getString("marque"), rs.getString("modele"), rs.getString("color"), rs.getString("image")));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
