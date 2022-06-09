package voyage.services;

import voyage.entities.Voiture;
import voyage.entities.chauffeur;
import edu.voyage.IService.IService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import voyage.utils.MyConnectionn;

public class ServiceChauffeur implements IService<chauffeur> {
    Connection cnx;

    public ServiceChauffeur() {
        cnx = MyConnectionn.getConnect();
    }

    @Override
    public void add(chauffeur chauffeur) throws SQLException {

    }

    @Override
    public List<chauffeur> read() throws SQLException {
        List<chauffeur> ls = new ArrayList<chauffeur>();
        Statement st = cnx.createStatement();
        String req = "select * from chauffeur order by id";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Integer idchauffeur = rs.getInt("id");
            String nom = rs.getString("nom");
            //   LocalDate born = rs.getDate("born").toLocalDate();
            String email = rs.getString("email");

            String dob = rs.getString("dob");
            String image = rs.getString("image");

            chauffeur p = new chauffeur(idchauffeur,nom,email,dob,image);
            ls.add(p);
        }

        return ls;
    }

    @Override
    public void update(chauffeur chauffeur) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
