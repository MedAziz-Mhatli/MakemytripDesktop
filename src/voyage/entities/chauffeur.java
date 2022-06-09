package voyage.entities;

import java.time.LocalDate;

public class chauffeur {
    private int idchauffeur;
    private String nom ;
    private String email;
    private String dob;
    private String image;
    public chauffeur(String nom, String email, String dob, String image) {
        this.nom = nom;
        this.email = email;
        this.dob = dob;
        this.image = image;
    }

    public chauffeur(int idchauffeur, String nom, String email, String dob, String image) {
        this.idchauffeur = idchauffeur;
        this.nom = nom;
        this.email = email;
        this.dob = dob;
        this.image = image;
    }



    @Override
    public String toString() {
        return "chauffeur{" +
                "idchauffeur=" + idchauffeur +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", image='" + image + '\'' +
                '}';
    }



    public int getIdchauffeur() {
        return idchauffeur;
    }

    public void setIdchauffeur(int idchauffeur) {
        this.idchauffeur = idchauffeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
