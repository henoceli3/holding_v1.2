package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.holding.dao.ILocataireDao;
import com.holding.dao.LocataireDao;
import com.holding.models.Locataire;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LocataireItemController implements Initializable {
	
    @FXML
    private Label adresse;

    @FXML
    private Label email;

    @FXML
    private Label nom_prenom;

    @FXML
    private Label numero_piece;

    @FXML
    private ImageView photo;

    @FXML
    private Label telephone; 
    
    @FXML
    void btn_edit(MouseEvent event) {
    	try {
			Parent parent =FXMLLoader.load(getClass().getResource("/interfaces/locataireEdit.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void setData(Locataire locataire){
    	//Image imgProfile = new Image(getClass().getResource(locataire.getPhoto()));
    	//photo.setImage(imgProfile);
    	
    	nom_prenom.setText(locataire.getNom().concat(" ").concat(locataire.getPrenom()));    
    	numero_piece.setText(locataire.getNumeroPiece());
    	adresse.setText(locataire.getAdresse());
    	telephone.setText(locataire.getTelephone().toString());
    	email.setText(locataire.getEmail());
    	
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		List < Locataire > locataires = null;
		ILocataireDao locataireDao = new LocataireDao();
		locataires = locataireDao.getAllLocataire();
	}

}
