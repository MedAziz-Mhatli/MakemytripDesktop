/* Decompiler 4ms, total 777ms, lines 47 */
package voyage.entities;

public class reservation_guide {
   private int id_resGuide;
   private int id_guide;
   private int id_resVol;

   public reservation_guide(int id_resGuide, int id_guide, int id_resVol) {
      this.id_guide = id_guide;
      this.id_resGuide = id_resGuide;
      this.id_resVol = id_resVol;
   }

   public reservation_guide(int id_guide, int id_resVol) {
      this.id_guide = id_guide;
      this.id_resVol = id_resVol;
   }

   public int getId_resGuide() {
      return this.id_resGuide;
   }

   public void setId_resGuide(int id_resGuide) {
      this.id_resGuide = id_resGuide;
   }

   public int getId_guide() {
      return this.id_guide;
   }

   public void setId_guide(int id_guide) {
      this.id_guide = id_guide;
   }

   public int getId_resVol() {
      return this.id_resVol;
   }

   public void setId_resVol(int id_resVol) {
      this.id_resVol = id_resVol;
   }

   public String toString() {
      return "reservation_guide [id_resGuide=" + this.id_resGuide + ", id_guide=" + this.id_guide + ", id_resVol=" + this.id_resVol + "]";
   }
}
