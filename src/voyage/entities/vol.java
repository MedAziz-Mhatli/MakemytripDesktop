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
    public class vol {
        private int id_vol;
        private String depart_vol;
        private String destination_vol;
        private Date date_departVol;
        private Date date_retourVol;
        private int nb_escalesVol;
        private double prixVol;

        public vol() {
        }

        public vol(String depart_vol, String destination_vol, Date date_departVol, Date date_retourVol, int nb_escalesVol, double prixVol) {
            this.depart_vol = depart_vol;
            this.destination_vol = destination_vol;
            this.date_departVol = date_departVol;
            this.date_retourVol = date_retourVol;
            this.nb_escalesVol = nb_escalesVol;
            this.prixVol = prixVol;
        }

        public vol(int id_vol, String depart_vol, String destination_vol, Date date_departVol, Date date_retourVol, int nb_escalesVol, double prixVol) {
            this.id_vol = id_vol;
            this.depart_vol = depart_vol;
            this.destination_vol = destination_vol;
            this.date_departVol = date_departVol;
            this.date_retourVol = date_retourVol;
            this.nb_escalesVol = nb_escalesVol;
            this.prixVol = prixVol;
        }

        public int getId_vol() {
            return id_vol;
        }

        public String getDepart_vol() {
            return depart_vol;
        }

        public String getDestination_vol() {
            return destination_vol;
        }

        public Date getDate_departVol() {
            return date_departVol;
        }

        public Date getDate_retourVol() {
            return date_retourVol;
        }

        public int getNb_escalesVol() {
            return nb_escalesVol;
        }

        public double getPrixVol() {
            return prixVol;
        }

        public void setId_vol(int id_vol) {
            this.id_vol = id_vol;
        }

        public void setDepart_vol(String depart_vol) {
            this.depart_vol = depart_vol;
        }

        public void setDestination_vol(String destination_vol) {
            this.destination_vol = destination_vol;
        }

        public void setDate_departVol(Date date_departVol) {
            this.date_departVol = date_departVol;
        }

        public void setDate_retourVol(Date date_retourVol) {
            this.date_retourVol = date_retourVol;
        }

        public void setNb_escalesVol(int nb_escalesVol) {
            this.nb_escalesVol = nb_escalesVol;
        }

        public void setPrixVol(double prixVol) {
            this.prixVol = prixVol;
        }

        @Override
        public String toString() {
         //   return "vol{" + "id_vol=" + id_vol + ", depart_vol=" + depart_vol + ", destination_vol=" + destination_vol + ", date_departVol=" + date_departVol + ", date_retourVol=" + date_retourVol + ", nb_escalesVol=" + nb_escalesVol + ", prixVol=" + prixVol + '}';
        return String.format( "%-20s",id_vol)
                    + String.format( "%-20s",depart_vol)
                    + String.format( "%-20s",destination_vol)
                    + String.format( "%-20s",date_departVol)
                    + String.format( "%-20s",date_retourVol)
                    + String.format( "%-20s",nb_escalesVol)
                    + String.format( "%-20s",prixVol);

        }





    }


