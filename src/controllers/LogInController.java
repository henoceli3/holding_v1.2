package controllers;

import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.holding.models.Proprietaire;
import com.holding.util.HibernateUtil;
import com.jfoenix.controls.JFXButton.ButtonType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import animatefx.animation.*;
import application.ConnexionMysql;

public class LogInController implements Initializable {
	
	//connexion à la bd
	Connection cnx;	
	public PreparedStatement st;
	public ResultSet result;
	
	 @FXML
	 private AnchorPane anchRoot;
	 
	@FXML
    private ImageView btnBack;

    @FXML
    private Circle btnClose;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lblForgot;

    @FXML
    private Pane pnlSignIn;

    @FXML
    private Pane pnlSignUp;

    @FXML
    private StackPane pnlStack;

    @FXML
    private PasswordField tfPass;

    @FXML
    private TextField tfUsername;

    @FXML
    private Label error_message;
   
    @FXML
    void handleButtonAction(ActionEvent event) {
    	if(event.getSource().equals(btnSignUp)) {
    		new ZoomIn(pnlSignUp).play();
    		pnlSignUp.toFront();
    	}
    }
    
    @FXML
    void handleMouseEvent(MouseEvent event) {
    	if(event.getSource() == btnClose) {
    		new animatefx.animation.FadeOut(anchRoot).play();
    		System.exit(0);
    		
    	}
    	if(event.getSource().equals(btnBack)) {
    		new ZoomIn(pnlSignIn).play();
    		pnlSignIn.toFront();
    	}
    }
    
    
    @FXML
    void openHome() {
    	String nom_utilisateur = tfUsername.getText();
    	String mot_passe = tfPass.getText();
    	
    	
    	//Animation du textfield si c'est vide
    	if (nom_utilisateur.length()==0) {
			tfUsername.setStyle("-fx-border-color:red; -fx-border-width:2px");
			new Shake(tfUsername).play();
		}else {
			tfUsername.setStyle(null);
		}
    	if (mot_passe.length()==0) {
    		tfPass.setStyle("-fx-border-color:red; -fx-border-width:2px");
			new Shake(tfPass).play();
		}else {
			tfPass.setStyle(null);
		}
    	
    	String sql ="SELECT NOM_UTILISATEUR, MOT_DE_PASSE FROM proprietaire";
    	try {
			st = cnx.prepareStatement(sql);
			result = st.executeQuery();
			if (result.next()) {
				if (nom_utilisateur.equals(result.getString("NOM_UTILISATEUR"))&& mot_passe.equals(result.getString("MOT_DE_PASSE"))) {
					System.out.println("Accès");
					
					//Affichage d'une notification de succes
					showNotification(NotificationType.SUCCESS, "Connexion", "Connexion effectuée avec succes ! Content de vous revoir.");
					
					anchRoot.getScene().getWindow().hide();
					Stage home = new Stage();
					Parent fxml;
					try {
						fxml = FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
						Scene scene = new Scene(fxml);
						home.setScene(scene);
						home.show();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}else {
					//Alert alert = new Alert(AlertType.ERROR, "Nom d'utilisateur ou mot de passe incorrect !", javafx.scene.control.ButtonType.OK);
					//alert.showAndWait();
					new ZoomIn(error_message).play();
					error_message.setText("Nom d'utilisateur ou mot de passe incorrect !");
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	
    }  
  


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cnx = ConnexionMysql.connexionDB();
		
		tfUsername.textProperty().addListener((observable,oldValue,newValue )->{
			tfUsername.setStyle(null);
		});
		tfPass.textProperty().addListener((observable,oldValue,newValue )->{
			tfPass.setStyle(null);
		});
		
		
	}
	
	/**
	 * fonction permettant d'afficher une notification
	 * @param type_notif
	 * @param titre
	 * @param message
	 */
	public void showNotification(NotificationType type_notif, String titre, String message) {
		//librairie TrayTester
		
		TrayNotification notification = new TrayNotification();
		AnimationType type =  AnimationType.POPUP;
		
		notification.setAnimationType(type);
		notification.setTitle(titre);
		notification.setMessage(message);
		notification.setNotificationType(type_notif);
		notification.showAndDismiss(Duration.millis(3000));
		
		
	}

	
}
