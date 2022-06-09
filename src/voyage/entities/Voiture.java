package voyage.entities;


public class Voiture {
    Integer   id;
    String matricule;
    String nbplace;
    String prix;
    String marque , modele, color,image;

    public Voiture(Integer id, String matricule, String nbplace, String prix, String marque, String modele, String color, String image) {
        this.id = id;
        this.matricule = matricule;
        this.nbplace = nbplace;
        this.prix = prix;
        this.marque = marque;
        this.modele = modele;
        this.color = color;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", matricule='" + matricule + '\'' +
                ", nbplace='" + nbplace + '\'' +
                ", prix='" + prix + '\'' +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", color='" + color + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNbplace() {
        return nbplace;
    }

    public void setNbplace(String nbplace) {
        this.nbplace = nbplace;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}