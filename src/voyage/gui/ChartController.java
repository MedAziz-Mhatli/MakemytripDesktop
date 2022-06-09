/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import voyage.utils.MyConnectionn;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class ChartController implements Initializable {

    @FXML
    private PieChart piechart;
ObservableList<PieChart.Data> piechartdata;
 
  
   
    /**
     * Initializes the controller class.
     */
   
    
    
       public void loaddata(){
    piechartdata=FXCollections.observableArrayList();
        try {
        String requete="SELECT nom, nb_chDispo FROM hotel";
        // requete ="SELECT ch.id_chambre,S(ch.type) AS nb_chambre FROM chambre ch inner join hotel h on h.id_hotel = ch.id_hotel WHERE ch.id_hotel ="+i+"";

            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            ArrayList<String> names=new ArrayList<String>();
            ArrayList<Integer> ids=new ArrayList<Integer>();
            while(rs.next()){
                //   data.add(new Personne(rs.getString(1),rs.getString(2)));
                piechartdata.add(new PieChart.Data(rs.getString(1),rs.getInt(2)));
               // names.add(rs.getString(1));
                //ids.add(rs.getInt(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loaddata();
      
        piechart.setData(piechartdata);
    }    
   
}
