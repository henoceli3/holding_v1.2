package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import animatefx.animation.ZoomIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class HomeController  implements Initializable{
	private Parent fxml;
    @FXML
    private StackPane pnl_principal;
	  @FXML
	    private AnchorPane root;
	  
	    @FXML
	    private GridPane pnl_ajouter;

	    @FXML
	    private GridPane pnl_liste;

	    @FXML
	    private GridPane pnl_modifier;  

    @FXML
    private Button btn_appartements;

    @FXML
    private Button btn_contrats;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Button btn_immeubles;

    @FXML
    private Button btn_locataires;

    @FXML
    private Button btn_paiements;

    @FXML
    private Button btn_parametres;

    @FXML
    private Label labelStatus;

    @FXML
    private Label labelStatutMin;

    @FXML
    private Pane pnlStatus;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub	
		
	}
	

    @FXML
    void btn_ajouter_nouveau() {
    	pnl_ajouter.toFront();
    }
    
	@FXML
	void handleClicks(MouseEvent event) {
		if(event.getSource() == btn_dashboard) {
			labelStatutMin.setText("/Accueil/Dashbord");
			labelStatus.setText("Dashboard Holding V1 | Votre applicataion de Gestion d'immeuble.");
			pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 43, 99), CornerRadii.EMPTY,Insets.EMPTY)));	
		}else if(event.getSource() == btn_locataires) {
			labelStatutMin.setText("/Accueil/Locataire");
			labelStatus.setText("Gestion des Locataires.");
			pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 43, 99), CornerRadii.EMPTY,Insets.EMPTY)));
			
			
			try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/AddLocataire.fxml"));
				pnl_principal.getChildren().removeAll();
				pnl_principal.getChildren().setAll(fxml);
				new ZoomIn(pnl_principal).play();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pnl_liste.toFront();
			
		}
		else if(event.getSource() == btn_immeubles) {
			labelStatutMin.setText("/Accueil/Immeubles");
			labelStatus.setText("Gestion des Immeubles.");
			pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 43, 99), CornerRadii.EMPTY,Insets.EMPTY)));
			try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/Immeuble.fxml"));
				pnl_principal.getChildren().removeAll();
				pnl_principal.getChildren().setAll(fxml);
				new ZoomIn(pnl_principal).play();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(event.getSource() == btn_appartements) {
			labelStatutMin.setText("/Accueil/Appartements");
			labelStatus.setText("Gestion des Appartements.");
			pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 43, 99), CornerRadii.EMPTY,Insets.EMPTY)));
			pnl_liste.toFront();
		}
		else if(event.getSource() == btn_paiements) {
			
		}
		else if(event.getSource() == btn_contrats) {
			
		}
		else if(event.getSource() == btn_parametres) {
			
		}
	}
}
