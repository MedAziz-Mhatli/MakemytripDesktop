/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.entities;

/**
 *
 * @author MSI GAMMING
 */
public class ReservationChambre {
     private int id_resChambre;
    private int id_chambre;
    private int id_client;
    private int duree_reservation;
    private int id_facture;

    public ReservationChambre() {
    }

    public ReservationChambre(int id_resChambre, int id_chambre, int id_client, int duree_reservation,int id_facture) {
        this.id_resChambre = id_resChambre;
        this.id_chambre = id_chambre;
        this.id_client = id_client;
        this.duree_reservation = duree_reservation;
        this.id_facture = id_facture;
    }

    public int getId_resChambre() {
        return id_resChambre;
    }

    public int getId_chambre() {
        return id_chambre;
    }

    public int getId_client() {
        return id_client;
    }

    public int getDuree_reservation() {
        return duree_reservation;
    }

   


    public int getId_facture() {
        return id_facture;
    }

    public void setId_resChambre(int id_resChambre) {
        this.id_resChambre = id_resChambre;
    }

    public void setId_chambre(int id_chambre) {
        this.id_chambre = id_chambre;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setDuree_reservation(int duree_reservation) {
        this.duree_reservation = duree_reservation;
    }

    


    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    @Override
    public String toString() {
        return "ReservationChambre{" + "id_resChambre=" + id_resChambre + ", id_chambre=" + id_chambre + ", id_client=" + id_client + ", duree_reservation=" + duree_reservation +  ", id_facture=" + id_facture + '}';
    }
    
     
}
