/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package voyage.gui;

import voyage.entities.guide_touristique;
import static voyage.entities.guide_touristique.filename;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import voyage.services.GuideService;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * FXML Controller class
 *
 * @author Amine JAOUADI
 */
public class AjouterGuideController implements Initializable {
    public String imagecomp; 
    @FXML
    private TextField zonePrenom;
    @FXML
    private TextField zoneNom;
    @FXML
    private TextField zoneAdresse;
    @FXML
    private TextField zoneEmail;
    @FXML
    private Button zoneDiplome;
    @FXML
    private TextField zoneTelephone;
  
    @FXML
    private Button btnSubmit;
    
    @FXML
    private ImageView ImageField; 
    
    private GuideService guideService ;
    @FXML
    private ImageView ImageFieldLogo;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.guideService= new GuideService();
    }    
    
    @FXML
    private void uploadImg(ActionEvent event) throws FileNotFoundException, IOException
    {
        FileChooser fc = new FileChooser();
        String img; 
        
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image","*.png","*.jpg",".jpeg"));
        File f = fc.showOpenDialog(null); 
        if (f!=null) 
        {
        InputStream is = null; 
        OutputStream os = null; 
        try{
            img = f.getAbsoluteFile().toURI().toString(); 
            Image i = new Image(img); 
            ImageField .setImage(i);
            is = new FileInputStream(new File(f.getAbsolutePath()));
            os = new FileOutputStream(new File("C:\\Users\\MSI GAMMING\\Desktop\\uploads\\" +f.getName()));
            guide_touristique.filename = "C:\\Users\\MSI GAMMING\\Desktop\\uploads\\" +f.getName();
            byte[] buffer = new byte[1024];
            int length; 
            while ((length = is.read(buffer))>0)
            {
                os.write(buffer,0, length); 
            }
        }
        finally {
            is.close();
            os.close(); 
            
        }
        }
 
    }
    
    private boolean controleDeSaisi() {  

        if (zoneNom.getText().isEmpty() || zonePrenom.getText().isEmpty() || zoneAdresse.getText().isEmpty() || zoneEmail.getText().isEmpty() ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", zoneTelephone.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le code de votre carte ! ");
                zoneTelephone.requestFocus();
                zoneTelephone.selectEnd();
                return false;
            }

           
        }
        return true;
    }
    
    
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
        

    @FXML
    private void onCreate(ActionEvent event) throws IOException, TesseractException {
        String Nom = zoneNom.getText();
        String Adresse = zoneAdresse.getText();
        String email = zoneEmail.getText();
        int telephone = Integer.parseInt(zoneTelephone.getText());
        String prenom = zonePrenom.getText();
        String diplome = zoneDiplome.getText();
        //ImageField
   
        
        
        guide_touristique g = new guide_touristique( Nom,  prenom,  Adresse,  telephone,  email,  guide_touristique.filename);
        
             Tesseract tesseract = new Tesseract();
		try {

			tesseract.setDatapath("C:\\Users\\Public\\Netbeans\\Integration\\tessdata");

			// the path of your tess data folder
			// inside the extracted file
			File x = new File(g.filename);
			String text
				= tesseract.doOCR(x);

			
			System.out.print(text);
                        if (text.contains("guide") || text.contains("COLLEGE") || text.contains("touristique") || text.contains("diplome") || text.contains("diploma"))
                        {
                               guideService.insert(g);
                               Parent root = FXMLLoader.load(getClass().getResource("GuideDetail.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                        }   
                        else {
                               Alert alert = new Alert(Alert.AlertType.ERROR);
                               alert.show(); 
                               alert.setTitle("Failed to add ! "); 
                               alert.setContentText("Failed to add, try again with a convenient diploma.");
                               
                            }
		}
		catch (TesseractException e) {
			e.printStackTrace();
		}
                
                
        
        
        
        
        
                
        
        
        
        
        
        
        
        
    }



                
                   
    }
    