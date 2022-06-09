package voyage.services;

import voyage.entities.Chambre;
import edu.voyage.IService.IService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import voyage.entities.Voiture;
import voyage.utils.MyConnectionn;

public class ServiceVoiture implements IService<Voiture> {
    Connection cnx;

    public ServiceVoiture() {
        cnx = MyConnectionn.getConnect();
    }

    public void add(Voiture t) throws SQLException {

        Statement st = cnx.createStatement();
        String query = "insert into voiture (id,matricule,nbplace,prix,marque,modele,color,image)values(NULL, '" + t.getMatricule() + "', '" + t.getNbplace() + "', '" +t.getPrix()+ "', '" + t.getMarque() +"','"+t.getModele()+ "','" + t.getColor() + "','" + t.getImage() + "')";
        st.executeUpdate(query);

    }

    public List<Voiture> read() throws SQLException {
        List<Voiture> ls = new ArrayList<Voiture>();
        Statement st = cnx.createStatement();
        String req = "select * from voiture order by id";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String matricule = rs.getString("matricule");
            //   LocalDate born = rs.getDate("born").toLocalDate();
            String nbplace = rs.getString("nbplace");
            String prix = rs.getString("prix");
            String marque = rs.getString("marque");
            String modele = rs.getString("modele");
            String color = rs.getString("color");
            String image = rs.getString("image");
            Voiture p = new Voiture(id,matricule,nbplace,prix,marque,modele,color,image);
            ls.add(p);
        }

        return ls;
    }


    public void update(Voiture t) throws SQLException {

        Statement st = cnx.createStatement();


        String query = "UPDATE `Voiture` SET `matricule` = '" + t.getMatricule() + "',`nbplace` = '" + t.getNbplace() + "', `prix` = '" + t.getPrix() + "', `marque` = '" + t.getMarque() + "', `modele` = '" + t.getModele() + "', `color` = '"
                + t.getColor() + "', `image` = '" + t.getImage() + "' WHERE `Voiture`.`id` = " + t.getId() + " ;";
        st.executeUpdate(query);
    }

    public void delete(Long id) throws SQLException {

        Statement st = cnx.createStatement();
        String query = "DELETE FROM `Voiture` WHERE `Voiture`.`id` = '" + id + "'";
        st.executeUpdate(query);

    }




}

