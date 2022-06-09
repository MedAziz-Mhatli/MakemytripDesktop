/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import voyage.entities.Hotel;
import voyage.services.HotelCrud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import voyage.utils.MyConnectionn;


/**
 * FXML Controller class
 *
 * @author siwar
 */
public class DetailsWindowController implements Initializable {

   @FXML
    private TableColumn<Hotel, String> nom;
    @FXML
    private TableColumn<Hotel, String> desciption;
    @FXML
    private TableColumn<Hotel, String> addresse;
    @FXML
    private TableColumn<Hotel, String> categorie;
    @FXML
    private TableColumn<Hotel, Integer> nbrch;
    @FXML
    private TableColumn<Hotel, String> ema;
    @FXML
    private TableColumn<Hotel, Integer> tel;
    public ObservableList<Hotel> data=FXCollections.observableArrayList();
    
   
   
    @FXML
    private Button log;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Button clc;
    @FXML
    private Button hh;
  
    private TextArea desc;
    private TextField add;
    private TextField cat;
    private TextField nbr;
    private TextField tel1;
    private TextField nomm;
   
    @FXML
    private TextField tfNom;
    @FXML
    private TextArea trDes;
    @FXML
    private TextField tfAdd;
    @FXML
    private TextField tfCat;
    @FXML
    private TextField tfNbr;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfTel;
    @FXML
    private TableView<Hotel> tablee;
    @FXML
    private Button btnisert;
    @FXML
    private Button bttnsupp;
    private String diretorio;
    private static final String DIR="QRDir";
    @FXML
    private Button btnev;
    @FXML
    private Button btnstat;
    @FXML
    private ImageView im_qrcode;
    @FXML
    private Button btnaff;
    @FXML
    private Button btnouvrir;
    private String deriterio;
    private  static final String DIRR ="QRDir";
int index=-1;
    @FXML
    private Button btnaccueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         deriterio= new File("").getAbsolutePath();
        deriterio+= File.separator +DIR;
        File file=new File(deriterio);
        if(!file.isDirectory()){
        file.mkdir();}
       showhotels();
    }  
    public String readQRCode(String filePath, String charset, Map hintMap)throws FileNotFoundException, IOException, NotFoundException{
        BinaryBitmap binaryBitmap= new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult= new MultiFormatReader().decode(binaryBitmap,hintMap);
        return qrCodeResult.getText();
    }
       @FXML
    private void hundleButtonAction(ActionEvent event) {
        if(event.getSource()==btnisert){
        SaveHotel();
        }
        else if(event.getSource()==bttnsupp){
        Delete();
        }
       
        
    }
  
    
 public ObservableList<Hotel> getHotels(){
      ObservableList<Hotel> Hotellist=FXCollections.observableArrayList();
      
      
         try {
            String requete="SELECT * FROM hotel"; 
            Statement st = MyConnectionn.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            Hotel hot;
            while(rs.next()){

                hot=new Hotel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getInt(8));
              Hotellist.add(hot);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return   Hotellist;}
   public void showhotels(){
   ObservableList<Hotel> list= getHotels();
                 nom.setCellValueFactory(new PropertyValueFactory<Hotel, String>("nom")); 
                desciption.setCellValueFactory(new PropertyValueFactory<Hotel, String>("description"));
                addresse.setCellValueFactory(new PropertyValueFactory<Hotel, String>("adresse"));
                 categorie.setCellValueFactory(new PropertyValueFactory<Hotel, String>("categorie"));
                 nbrch.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("nb_chDispo"));
                 ema.setCellValueFactory(new PropertyValueFactory<Hotel, String>("email"));
                 tel.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("telephone"));
                 tablee.setItems(list);
   }

    private void ajouterhotel(ActionEvent event) {
        
             Stage stage =new Stage();
         try {
            Parent root=
                   FXMLLoader.load(getClass().getResource("AjouterHotel.fxml"));
            
            Scene scene = new Scene(root);
             
           stage.setTitle("Ajouter hotel!");
            stage.setScene(scene);
             stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
    }
}

   
Stage stage;
    @FXML
    private void logout(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("logout");
            alert.setHeaderText("do you want to save befor exiting");
            if(alert.showAndWait().get()==ButtonType.OK){
        stage=(Stage) scenePane.getScene().getWindow();
        System.out.println("succeé");
        stage.close();}
    }

    @FXML
    private void Calculerrr(ActionEvent event) {
        
                     Stage stage =new Stage();
         try {
            Parent root=
                   FXMLLoader.load(getClass().getResource("Calculchambre.fxml"));
            
            Scene scene = new Scene(root);
             
           stage.setTitle("Calcul chambre");
            stage.setScene(scene);
             stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
    }
    }

       private void executeQuery(String requete) {
        try {
            Statement st= MyConnectionn.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     private boolean validatenom(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(tfNom.getText());
      
        if((m.find() && m.group().equals(tfNom.getText()))){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Name hotel");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid First Name");
                alert.showAndWait();
           
            return false;            
        }
    }
         private boolean validateadd(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(tfAdd.getText());
      
        if((m.find() && m.group().equals(tfAdd.getText()))){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Name hotel");
                alert.setHeaderText(null);
                alert.setContentText("Entrer Valid Adress");
                alert.showAndWait();
           
            return false;            
        }
    }
            private boolean validatecat(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(tfCat.getText());
      
        if((m.find() && m.group().equals(tfCat.getText()))){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Categorie hotel");
                alert.setHeaderText(null);
                alert.setContentText("Entrer Valid Categorie");
                alert.showAndWait();
           
            return false;            
        }
    }
      private boolean validatedes(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(trDes.getText());
      
        if((m.find() && m.group().equals(trDes.getText()))){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Description hotel");
                alert.setHeaderText(null);
                alert.setContentText("Entrer Valid Description");
                alert.showAndWait();
           
            return false;            
        }
    }
            
    private boolean validatel(){
        Pattern p = Pattern.compile("[1-9]{7}");
        Matcher m = p.matcher(tfTel.getText());
        if(m.find() && m.group().equals(tfTel.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Telephone");
                alert.setHeaderText(null);
                alert.setContentText(" Entrer Valid Telephone");
                alert.showAndWait();
           
            return false;            
        }
         
    }
        private boolean validanbr(){
        Pattern p = Pattern.compile("[1-8]{1}");
        Matcher m = p.matcher(tfNbr.getText());
        if(m.find() && m.group().equals(tfNbr.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate nombre des chambres");
                alert.setHeaderText(null);
                alert.setContentText(" nombres des chambres est entier");
                alert.showAndWait();
           
            return false;            
        }
         
    }
    
 private boolean Emailvalide(){
        Pattern p = Pattern.compile( "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$");
        Matcher m = p.matcher(tfEmail.getText());
        if(m.find() && m.group().equals(tfEmail.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate email");
                alert.setHeaderText(null);
                alert.setContentText(" Entrer Valid email");
                alert.showAndWait();
           
            return false;            
        }
        }
    
  
    private void SaveHotel() {
        
        if( validatenom()&& validatedes()&& validateadd()&& validatecat()&& Emailvalide() ){
         Hotel h =new Hotel();
        String nom=tfNom.getText();
        String description=trDes.getText();
        String adresse=tfAdd.getText();
        String categorie=tfCat.getText();
        String sid=tfNbr.getText();
        int nbch=Integer.parseInt(sid);
        String email=tfEmail.getText();
         String t=tfTel.getText();
        int tel=Integer.parseInt(t);
        h.setNom(nom);
      h.setDescription(description);
      h.setAdresse(adresse);
      h.setCategorie(categorie);
      h.setNb_chDispo(nbch);
      h.setEmail(email);
      h.setTelephone(tel);
         
      HotelCrud hc = new HotelCrud();
     int stats= hc.ajouterHotel2(h);
         
       if(stats>0){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("add personne");
            alert.setHeaderText("Information");
            alert.setContentText("person bien ajouter");
            alert.showAndWait();
        }
        else{
         Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("add personne");
            alert.setHeaderText("Information");
            alert.setContentText("person non ajouter");
            alert.showAndWait();
        }
         
        }
        showhotels();
    }

   private void Delete() {
        HotelCrud hot=new HotelCrud();
        Hotel h=new Hotel();
        h=tablee.getSelectionModel().getSelectedItem();
        hot.supprimerHotel(h);
          showhotels();
       }

    @FXML
    private void rating(ActionEvent event) {
        
          Stage stage =new Stage();
         try {
            Parent root=
                   FXMLLoader.load(getClass().getResource("ratiing.fxml"));
            
            Scene scene = new Scene(root);
             
           stage.setTitle("Calcul chambre");
            stage.setScene(scene);
             stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
    }
    }

    @FXML
    private void stati(ActionEvent event) {
        
              Stage stage =new Stage();
         try {
            Parent root=
                   FXMLLoader.load(getClass().getResource("Chart.fxml"));
            
            Scene scene = new Scene(root);
             
           stage.setTitle("Calcul chambre");
            stage.setScene(scene);
             stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
    }
    }

    

    @FXML
    private void ouvrir(ActionEvent event) {
         FileChooser  fileChooser =new FileChooser();
        fileChooser.setInitialDirectory(new File(deriterio));
        FileChooser.ExtensionFilter extfilterpng= new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        FileChooser.ExtensionFilter extfilterJPG= new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
         FileChooser.ExtensionFilter extfilterjpg= new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
          FileChooser.ExtensionFilter extfilterPNG= new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
         fileChooser.getExtensionFilters().addAll(extfilterpng, extfilterJPG, extfilterjpg, extfilterPNG);
         File file=fileChooser.showOpenDialog(null);
         if(file!= null){
            try {
                Image image= new Image(new FileInputStream(file.getAbsolutePath()));
                im_qrcode.setImage(image);
                 String charset="UTF-8";
            Map hintMap= new HashMap();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            String readQRCode= readQRCode(file.getAbsolutePath(),charset, hintMap);
            tfNom.setText(readQRCode.substring(readQRCode.indexOf("Nom:") + 6, readQRCode.indexOf("description:")));
            trDes.setText(readQRCode.substring(readQRCode.indexOf("description:") + 7, readQRCode.indexOf("adresse:")));
            tfAdd.setText(readQRCode.substring(readQRCode.indexOf("adresse:") + 8, readQRCode.indexOf("categorie:")));
             tfCat.setText(readQRCode.substring(readQRCode.indexOf("categorie:") + 9, readQRCode.indexOf("nombre de chambre:")));
             tfNbr.setText(readQRCode.substring(readQRCode.indexOf("nombre de chambre:") + 10, readQRCode.indexOf("email:")));
             tfEmail.setText(readQRCode.substring(readQRCode.indexOf("email:") + 11, readQRCode.indexOf("tel:")));
             tfTel.setText(readQRCode.substring(readQRCode.indexOf("tel:") + 12, readQRCode.length()));
              
            } catch (IOException | NotFoundException ex) {
                System.out.println(ex);
            }

         }
  }
    @FXML
    private void affich(ActionEvent event) throws IOException {
          String nome= tfNom.getText();
        if(!nome.isEmpty()){
            try{
        String cnt_qrcode="";
        cnt_qrcode +="Nom:" +tfNom.getText()+"\n";
        cnt_qrcode +="description:" +trDes.getText()+"\n";
        cnt_qrcode +="adresse:" +tfAdd.getText()+"\n";
        cnt_qrcode +="categorie:" +tfCat.getText()+"\n";
        cnt_qrcode +="nombre de chambre:" +tfNbr.getText()+"\n";
        cnt_qrcode +="email:" +tfEmail.getText()+"\n";
        cnt_qrcode +="telephone:" +tfTel.getText()+"\n";
        
            FileOutputStream fout=new FileOutputStream(deriterio +File.separator +nome +".png");
            ByteArrayOutputStream bos=QRCode.from(cnt_qrcode).withSize(125, 125).to(ImageType.PNG).stream();
            fout.write(bos.toByteArray());
            bos.close();
            fout.close();
            fout.flush();
            Image image=new Image(new FileInputStream(deriterio +File.separator +nome + ".png"));
            im_qrcode.setImage(image);}
            catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
            
            
        }
    }

    @FXML
    void getSelected(javafx.scene.input.MouseEvent event) {
         
        index = tablee.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    return;
        }
    
    tfNom.setText(nom.getCellData(index).toString());
    trDes.setText(desciption.getCellData(index).toString());
    tfAdd.setText(addresse.getCellData(index).toString());
    tfCat.setText(categorie.getCellData(index).toString());
    tfNbr.setText(nbrch.getCellData(index).toString());
    tfEmail.setText(ema.getCellData(index).toString());
    tfTel.setText(tel.getCellData(index).toString());}
    
   @FXML
 public void edit(ActionEvent event){
       try {
        
           String val1= tfNom.getText();
           String val2= trDes.getText();
           String val3= tfAdd.getText();
           String val4= tfCat.getText();
           String val5= tfNbr.getText();
           String val6= tfEmail.getText();
           String val7= tfTel.getText();
           String query = "UPDATE hotel SET nom = '" + val1 + "', description = '" + val2 + "', adresse = '" +val3 + ", categorie = " + val4 + "', nb_chDispo = '" + val5 + "', email = '" + val6 + "', telephone = '" +val7 + "' WHERE nom = '" + val1 +"' ";
           PreparedStatement ps= MyConnectionn.getInstance().getCnx().prepareStatement(query);
           ps.executeUpdate();
       } catch (SQLException ex) {
           System.out.println(ex.getMessage()); }
 
 showhotels();
 } 

    @FXML
    private void accueil(ActionEvent event) {
        
        
              Stage stage =new Stage();
         try {
            Parent root=
                   FXMLLoader.load(getClass().getResource("Menu.fxml"));
            
            Scene scene = new Scene(root);
             
           stage.setTitle("Calcul chambre");
            stage.setScene(scene);
             stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
    }
    }
}
    
     



            
   
    
    
    
    
    
    
    
    
    

