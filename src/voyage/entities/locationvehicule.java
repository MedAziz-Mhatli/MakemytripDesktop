package voyage.entities;



import java.sql.Date;

public class locationvehicule {

    private int idlocationvehicule;
    private String localisation;
    private Date datedebutlocation;

    @Override
    public String toString() {
        return "locationvehicule{" +
                "idlocationvehicule=" + idlocationvehicule +
                ", localisation='" + localisation + '\'' +
                ", datedebutlocation=" + datedebutlocation +
                ", datefinlocation=" + datefinlocation +
                ", idchauffeur=" + idchauffeur +
                ", existence='" + existence + '\'' +
                '}';
    }

    private Date datefinlocation;
    private int idchauffeur;

    public int getIdlocationvehicule() {
        return idlocationvehicule;
    }

    public void setIdlocationvehicule(int idlocationvehicule) {
        this.idlocationvehicule = idlocationvehicule;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Date getDatedebutlocation() {
        return datedebutlocation;
    }

    public void setDatedebutlocation(Date datedebutlocation) {
        this.datedebutlocation = datedebutlocation;
    }

    public Date getDatefinlocation() {
        return datefinlocation;
    }

    public void setDatefinlocation(Date datefinlocation) {
        this.datefinlocation = datefinlocation;
    }

    public int getIdchauffeur() {
        return idchauffeur;
    }

    public void setIdchauffeur(int idchauffeur) {
        this.idchauffeur = idchauffeur;
    }

    public String getExistence() {
        return existence;
    }

    public void setExistence(String existence) {
        this.existence = existence;
    }

    private String existence;



}
