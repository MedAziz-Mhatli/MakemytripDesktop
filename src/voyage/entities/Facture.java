/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.entities;

import java.sql.Date;
import javafx.scene.control.CheckBox;

/**
 *
 * @author MSI GAMMING
 */
public class Facture {

    private int id_facture;
    private Date date_facture;
    private double remise_facture;
    private double total_facture;
    private String type_facture;
    private int id_client;
    private CheckBox check;

    public CheckBox getCheck() {
        return check;
    }

    public void setCheck(CheckBox check) {
        this.check = check;
    }

    public Facture(Date date_facture, double remise_facture, double total_facture, String type_facture) {
        this.date_facture = date_facture;
        this.remise_facture = remise_facture;
        this.total_facture = total_facture;
        this.type_facture = type_facture;
        
    }

    public Facture() {
    }

    public Facture(Date date_facture, double remise_facture, double total_facture, String type_facture, int id_client) {

        this.date_facture = date_facture;
        this.remise_facture = remise_facture;
        this.total_facture = total_facture;
        this.type_facture = type_facture;
        this.id_client = id_client;
    }

    public Facture(Date date_facture, double remise_facture, double total_facture, String type_facture, int id_client, CheckBox check) {
        this.date_facture = date_facture;
        this.remise_facture = remise_facture;
        this.total_facture = total_facture;
        this.type_facture = type_facture;
        this.id_client = id_client;
        this.check = check;
    }

    public Facture(int id_facture, Date date_facture, double remise_facture, double total_facture, String type_facture, int id_client, CheckBox check) {
        this.id_facture = id_facture;
        this.date_facture = date_facture;
        this.remise_facture = remise_facture;
        this.total_facture = total_facture;
        this.type_facture = type_facture;
        this.id_client = id_client;
        this.check = check;
    }

    public Facture(int id_facture, Date date_facture, double remise_facture, double total_facture, String type_facture, int id_client) {
        this.id_facture = id_facture;
        this.date_facture = date_facture;
        this.remise_facture = remise_facture;
        this.total_facture = total_facture;
        this.type_facture = type_facture;
        this.id_client = id_client;
    }

    public int getId_facture() {
        return id_facture;
    }

    @Override
    public String toString() {
        return "Facture{" + "id_facture=" + id_facture + ", date_facture=" + date_facture + ", remise_facture=" + remise_facture + ", total_facture=" + total_facture + ", type_facture=" + type_facture + ", id_client=" + id_client + '}';
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public Date getDate_facture() {
        return date_facture;
    }

    public void setDate_facture(Date date_facture) {
        this.date_facture = date_facture;
    }

    public double getRemise_facture() {
        return remise_facture;
    }

    public void setRemise_facture(double remise_facture) {
        this.remise_facture = remise_facture;
    }

    public double getTotal_facture() {
        return total_facture;
    }

    public void setTotal_facture(double total_facture) {
        this.total_facture = total_facture;
    }

    public String getType_facture() {
        return type_facture;
    }

    public void setType_facture(String type_facture) {
        this.type_facture = type_facture;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

}
