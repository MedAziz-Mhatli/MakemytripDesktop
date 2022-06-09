/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import voyage.entities.Facture;
import voyage.services.FactureCRUD;
import voyage.utils.MyConnectionn;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI GAMMING
 */
public class PDF {

    public PDF() {
    }

    private Statement ste;
    private Connection con;

    public PDF(Statement ste, Connection con) {
        this.ste = ste;
        this.con = con;
    }

    public Statement getSte() {
        return ste;
    }

    public void setSte(Statement ste) {
        this.ste = ste;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    @Override
    public String toString() {
        return "PDF{" + "ste=" + ste + ", con=" + con + '}';
    }

    public void GeneratePDF(String filename) throws DocumentException, SQLException, IOException  {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
            document.open();
            FactureCRUD fcd = new FactureCRUD();
            con = MyConnectionn.getInstance().getCnx();
            List<Facture> list = new ArrayList<>();
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from facture");
            while (rs.next()) {
                int id_facture = rs.getInt(1);
                Date date_facture = rs.getDate(2);
                double remise_facture = rs.getDouble(3);
                double total_facture = rs.getDouble(4);
                String type_facture = rs.getString(5);
                int client_facture = rs.getInt(6);
                
                Facture f = new Facture((java.sql.Date) date_facture, remise_facture, total_facture, type_facture, client_facture);
                list.add(f);
            }
            for (Facture f : list) {
                document.add(new Paragraph("Date :" + f.getDate_facture()));
                document.add(new Paragraph("Remise :" + f.getRemise_facture()));
                document.add(new Paragraph("Total :"+f.getTotal_facture()));
                document.add(new Paragraph("Type :"+f.getType_facture()));
                document.add(new Paragraph("Client :"+f.getId_client()));
                document.add(new Paragraph("------------------------"));
            }
            document.close();
            Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
