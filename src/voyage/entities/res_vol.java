/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.entities;


import java.util.Date;

/**
 *
 * @author sahra
 */
public class res_vol {
    private int id_resVol;
    private int id_vol;
    private int id_client;
    private Date date_resvol;
    private String compagnie_aerienne;
    private String classe;
    private int nb_personnes;

    public res_vol() {
    }

    public res_vol(int id_resVol, int id_vol, int id_client, Date date_resvol, String compagnie_aerienne, String classe, int nb_personnes) {
        this.id_resVol = id_resVol;
        this.id_vol = id_vol;
        this.id_client = id_client;
        this.date_resvol = date_resvol;
        this.compagnie_aerienne = compagnie_aerienne;
        this.classe = classe;
        this.nb_personnes = nb_personnes;
    }

    public res_vol(int id_resVol) {
        this.id_resVol = id_resVol;
    }

    public int getId_resVol() {
        return id_resVol;
    }

    public void setId_resVol(int id_resVol) {
        this.id_resVol = id_resVol;
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Date getDate_resvol() {
        return date_resvol;
    }

    public void setDate_resvol(Date date_resvol) {
        this.date_resvol = date_resvol;
    }

    public String getCompagnie_aerienne() {
        return compagnie_aerienne;
    }

    public void setCompagnie_aerienne(String compagnie_aerienne) {
        this.compagnie_aerienne = compagnie_aerienne;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getNb_personnes() {
        return nb_personnes;
    }

    public void setNb_personnes(int nb_personnes) {
        this.nb_personnes = nb_personnes;
    }

    @Override
    public String toString() {
        //return "res_vol{" + "id_resVol=" + id_resVol + ", id_vol=" + id_vol + ", id_client=" + id_client + ", date_resvol=" + date_resvol + ", compagnie_aerienne=" + compagnie_aerienne + ", classe=" + classe + ", nb_personnes=" + nb_personnes + '}';
       return String.format( "%-20s",id_resVol)
                + String.format( "%-20s",id_vol)
                + String.format( "%-20s",id_client)
                + String.format( "%-20s",date_resvol)
                + String.format( "%-20s",compagnie_aerienne)
                + String.format( "%-20s",classe)
                + String.format( "%-20s",nb_personnes);
    }

  
}
