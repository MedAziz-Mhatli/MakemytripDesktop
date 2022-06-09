/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.entities;

import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author MSI GAMMING
 */
public class Reclamation  extends TimerTask  {
    
    private int id_reclamation;
    private Date date_reclamation;
    private String email_reclamation;
    private String description_reclamation;
    private String etat_reclamation;
    private int id_client;

    public Reclamation(int id_reclamation, Date date_reclamation, String email_reclamation, String description_reclamation, String etat_reclamation, int id_client) {
        this.id_reclamation = id_reclamation;
        this.date_reclamation = date_reclamation;
        this.email_reclamation = email_reclamation;
        this.description_reclamation = description_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.id_client = id_client;
    }

    public Reclamation(Date date_reclamation, String email_reclamation, String description_reclamation, String etat_reclamation, int id_client) {
        this.date_reclamation = date_reclamation;
        this.email_reclamation = email_reclamation;
        this.description_reclamation = description_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.id_client = id_client;
    }

    public Reclamation() {
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public String getEmail_reclamation() {
        return email_reclamation;
    }

    public void setEmail_reclamation(String email_reclamation) {
        this.email_reclamation = email_reclamation;
    }

    public String getDescription_reclamation() {
        return description_reclamation;
    }

    public void setDescription_reclamation(String description_reclamation) {
        this.description_reclamation = description_reclamation;
    }

    public String getEtat_reclamation() {
        return etat_reclamation;
    }

    public void setEtat_reclamation(String etat_reclamation) {
        this.etat_reclamation = etat_reclamation;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", date_reclamation=" + date_reclamation + ", email_reclamation=" + email_reclamation + ", description_reclamation=" + description_reclamation + ", etat_reclamation=" + etat_reclamation + ", id_client=" + id_client + '}';
    }

    
 @Override
    public void run() {
        
        System.out.println("Réclamation ne peut pas etre modifiée apres 24H de date de création ");
    }
}
