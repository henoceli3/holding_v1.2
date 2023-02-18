package controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.holding.dao.ILocataireDao;
import com.holding.dao.LocataireDao;
import com.holding.models.Locataire;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.mysql.jdbc.Connection;

import animatefx.animation.FadeInDown;
import animatefx.animation.FadeInUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import net.bytebuddy.matcher.FilterableList;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class LocataireController implements Initializable {
	Connection cnx;	
	public PreparedStatement st;
	public ResultSet result;
	Locataire locataire = null;
	
	  @FXML
	    private StackPane pnl_principal;
	 @FXML
	 private GridPane pnl_ajouter;

	 @FXML
	 private GridPane pnl_liste;

	 @FXML
	 private GridPane pnl_modifier;  
	 
	@FXML
    private TableColumn<Locataire, String> cln_action;	
	
    @FXML
    private TableColumn<Locataire, String> cln_adresse;

    @FXML
    private TableColumn<Locataire, Integer> cln_avatar;

    @FXML
    private TableColumn<Locataire, String> cln_email;

    @FXML
    private TableColumn<Locataire, String> cln_nom;
    
    @FXML
    private TableColumn<Locataire, String> cln_prenom;

    @FXML
    private TableColumn<Locataire, String> cln_numero_piece;

    @FXML
    private TableColumn<Locataire, Integer> cln_telephone;

    @FXML
    private TableView<Locataire> table_locataires;
    
    
    @FXML
    private TextField txt_nom;
    
	@FXML
    private Button btn_annuler;

    @FXML
    private Button btn_enregister;

    @FXML
    private ImageView photo_locataire;

    @FXML
    private TextField txt_adresse;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField input_recherche;

    @FXML
    private TextField txt_numero_piece;

    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_telephone;

   
    @FXML
    private ComboBox<String> comboBox;
    
    private FileInputStream fs;
    
    @FXML
    private Label lab_url;
    
    @FXML
    private VBox form_ajouter;
    
    
    private boolean update;
    void setUpdate(boolean b) {
        this.update = b;

    }
    
    // Remplir les champs lors de la modification
    void setTextField(String nom,String prenom,String adresse,String email,String n_piece, int tel,String photo) {

        txt_nom.setText(nom);
        txt_prenom.setText(prenom);
        
        txt_adresse.setText(adresse);
        txt_email.setText(email);
        txt_numero_piece.setText(n_piece);
        String tele = String.valueOf(tel);        
        txt_telephone.setText(tele);        
        //	Chargement de l'image
        lab_url.setText(photo);
        String url = "/assets/images/upb.jpg";
        Image image = new Image(url, photo_locataire.getFitWidth(), photo_locataire.getFitHeight(), true, true);
		photo_locataire.setImage(image);
    }
    
    @FXML
    void btn_close(MouseEvent event) {
    	pnl_liste.toFront();
    }
    
    @FXML
    void btn_ajouter_nouveau() {
    	pnl_ajouter.toFront();
    }
  
    LocataireDao locataireDao = new LocataireDao();
 	List <Locataire> locataires = locataireDao.getAllLocataire();
	ObservableList <Locataire> liste_locataires = FXCollections.observableArrayList(locataires);
	
	/**
	 * 
	 */
    private void refreshTable() {        
            liste_locataires.clear();
            
            LocataireDao locataireDao = new LocataireDao();
         	List <Locataire> locataires = locataireDao.getAllLocataire();
        	ObservableList <Locataire> liste_locataires = FXCollections.observableArrayList(locataires);  
        	
        	table_locataires.setItems(liste_locataires);       
    }
	
    public void showLocataire() {
    	//table_locataires.getItems().clear();   	
   
    	// avatar
    	String avatarFileName = "/assets/images/oweb.png";
    	Image imageAvatar = new Image(avatarFileName);
    	ImageView avatar = new ImageView(imageAvatar);
    	avatar.setFitHeight(40);
    	avatar.setFitWidth(40);
    	avatar.setPreserveRatio(true);
    	avatar.setPickOnBounds(true);
    	
    	
    	cln_nom.setCellValueFactory(new PropertyValueFactory<Locataire,String>("nom"));
    	cln_prenom.setCellValueFactory(new PropertyValueFactory<Locataire,String>("prenom"));
    	cln_adresse.setCellValueFactory(new PropertyValueFactory<Locataire,String>("adresse"));
    	cln_telephone.setCellValueFactory(new PropertyValueFactory<Locataire,Integer>("telephone"));
    	cln_email.setCellValueFactory(new PropertyValueFactory<Locataire,String>("email"));
    	
    	
    	
    	//add cell of button edit 
        Callback<TableColumn<Locataire, String>, TableCell<Locataire, String>> cellFoctory = (TableColumn<Locataire, String> param) -> {
           // make cell containing buttons
           final TableCell<Locataire, String> cell = new TableCell<Locataire, String>() {
               @Override
               public void updateItem(String item, boolean empty) {
                   super.updateItem(item, empty);
                   //that cell created only on non-empty rows
                   if (empty) {
                       setGraphic(null);
                       setText(null);

                   } else {
                	   //icon du bouton editer
                	   Image imageEditer = new Image("/assets/icons/icons8_pencil_20px.png");
                	   ImageView  imageViewEditer = new ImageView(imageEditer);
                	   imageViewEditer.setPreserveRatio(true);
                	   imageViewEditer.setPickOnBounds(true);
                	   imageViewEditer.setFitWidth(15.0);
                	   imageViewEditer.setFitHeight(15.0);
                	   
                	   //icon du bouton supprimer
                	   Image imageDelete = new Image("/assets/icons/icons8_trash_can_20px.png");
                	   ImageView imageViewDelete= new ImageView(imageDelete);
                	   imageViewDelete.setPreserveRatio(true);
                	   imageViewDelete.setPickOnBounds(true);
                	   imageViewDelete.setFitWidth(15.0);
                	   imageViewDelete.setFitHeight(15.0);
                	   
                	   
                       Button deleteIcon = new Button("",imageViewDelete);
                       Button editIcon = new Button("",imageViewEditer);
                       
                       // style du bouton Supprimer
                       deleteIcon.setStyle(
                    		   " -fx-cursor: hand ;"
                    		  + "-fx-background-color: #E37A7A;"
                    		  + "-fx-background-radius: 20;"
                       );
                       deleteIcon.setPrefHeight(30.0);
                       deleteIcon.setPrefWidth(30.0);
                       
                       // style du bouton editer
                       editIcon.setStyle(
                    		   " -fx-cursor: hand ;"
                    		+ "-fx-background-color: #54D39D;"
                    		+   "-fx-border-radius: 5; "
                    		+  "-fx-background-radius: 20;"
                       );
                       editIcon.setPrefHeight(30.0);
                       editIcon.setPrefWidth(30.0);
                       
                       // Au clique du bouton delete
                       deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                       
                    	   locataire = table_locataires.getSelectionModel().getSelectedItem();
                    	   
                    	   //Si la liste est vide
                    	   if (locataire != null) {
                    		   Alert alert = new Alert(AlertType.CONFIRMATION,"Voulez vous vraiment supprimer ce locataire ?",javafx.scene.control.ButtonType.YES);
                  				alert.showAndWait();
                  				
                  				locataireDao.deleteLocataire(locataire.getIdLocataire()); 
                  				refreshTable();
						}  else {
							Alert alert = new Alert(AlertType.WARNING,"Veuillez selectionner une ligne de locataire pour Supprimer définitivement !.",javafx.scene.control.ButtonType.OK);
							alert.showAndWait();
						}                    	  

                       });
                       
                       
                       // Au clique du bouton Editer
                       editIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           locataire = table_locataires.getSelectionModel().getSelectedItem();
                         //Si la liste est vide
                           if (locataire != null) {
                        	   FXMLLoader loader = new FXMLLoader ();
                               loader.setLocation(getClass().getResource("/interfaces/EditLocataire.fxml"));                            
                                                          
                             
                               
                              
                               try {
                                   loader.load();
                               } catch (IOException ex) {
                                   Logger.getLogger(LocataireController.class.getName()).log(Level.SEVERE, null, ex);
                               }
                               
                               LocataireController addLocataireController = loader.getController();
                               addLocataireController.setUpdate(true);
                               addLocataireController.setTextField(locataire.getNom(), locataire.getPrenom(),locataire.getAdresse(),locataire.getEmail(),locataire.getNumeroPiece(),locataire.getTelephone(),locataire.getPhoto());
                               Parent parent = loader.getRoot();
                               Stage stage = new Stage();
                               stage.setScene(new Scene(parent));
                               stage.initStyle(StageStyle.UTILITY);
                               stage.show();
                               
						}else {
							Alert alert = new Alert(AlertType.WARNING,"Veuillez selectionner une ligne de locataire pour voir plus d'information.",javafx.scene.control.ButtonType.OK);
							alert.showAndWait();
						}
                          

                          

                       });

                       HBox managebtn = new HBox(editIcon, deleteIcon);
                       managebtn.setStyle("-fx-alignment:center");
                       HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                       HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                       setGraphic(managebtn);

                       setText(null);

                   }
               }

           };

           return cell;
       };
       
        cln_action.setCellFactory(cellFoctory);
    	
    	table_locataires.setItems(liste_locataires);
    	
    	
    	
    }
	
	
	
	

       
   
    
    @FXML
    void importer_image() {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png","*.jpg","*.jpeg"));
    	File f = fc.showOpenDialog(null);
    	if (f != null) {
			lab_url.setText(f.getAbsolutePath());
			Image image = new Image(f.toURI().toString(), photo_locataire.getFitWidth(), photo_locataire.getFitHeight(), true, true);
			photo_locataire.setImage(image);
		}
    }
    

    
 
    //Fonction pour vider les champs après enregistrement
    void vider_champ() {
    	txt_nom.setText("");
    	txt_prenom.setText("");
    	txt_numero_piece.setText("");
    	txt_adresse.setText("");
    	txt_telephone.setText("");
    	txt_email.setText("");
    	lab_url.setText("Aucune selectionnée");
    //	Image defaultimage = new Image("..\\assets\\icons\\icons8_administrator_male_60px.png", photo_locataire.getFitWidth(), photo_locataire.getFitHeight(), true, true);
    	photo_locataire.setImage(null);
    	
    }
    
    @FXML
    void btn_vider_champ(MouseEvent event) {
    	vider_champ();
    }
   
    /**
     * Ajouter un locataire dans la base de données
     * @param event
     * @throws IOException
     */
    @FXML
    void ajouter_locataire(MouseEvent event) throws IOException {
    	String nom = txt_nom.getText();
    	String prenom = txt_prenom.getText();
    //	String type_piece = txt_type_piece.getPromptText();
    	String type_piece = "CNI";
    	String numero_piece = txt_numero_piece.getText();
    	String adresse = txt_adresse.getText();
    	String tel = txt_telephone.getText();
    	int telephone = Integer.parseInt(tel);
    	String email = txt_email.getText();
    	
    	File image = new File(lab_url.getText());
    	
    	if (!nom.equals("") && !prenom.equals("") && !adresse.equals("") && !tel.equals("")) {
    	    		
    		ILocataireDao locataireDao = new LocataireDao();
    		Locataire locataire = new Locataire();
    		
    		locataire.setIdContrat(0);
    		locataire.setNom(nom);
    		locataire.setPrenom(prenom);
     	    locataire.setTypePiece(type_piece);
    		locataire.setNumeroPiece(numero_piece);
    		locataire.setEmail(email);
    		locataire.setAdresse(adresse);
    		//locataire.setAdresse(adresse);
    		locataire.setTelephone(telephone);
    		
    		// Traitement de l'image
    		/*
    		FileInputStream fis = new FileInputStream(lab_url.getText());
    		byte[] data = new byte[fis.available()];
    		fis.read(data);
    		locataire.setPhoto(data);
    		locataire.setPhoto(null);;
    		*/
    		/*
    		FileChooser fc = new FileChooser();
        	fc.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png","*.jpg","*.jpeg"));
        	File f = fc.showOpenDialog(null);
        	*/
    		/*
        	if (f != null) {
        		*/
        		String directory = "src\\assets\\images";
        		String fileName = image.getName();
        		File newFile = new File(directory + "/" + fileName);
        		try (FileInputStream is = new FileInputStream(image);
        		FileOutputStream os = new FileOutputStream(newFile)) {
        		byte[] buffer = new byte[1024];
        		int length;
        		while ((length = is.read(buffer)) > 0) {
        		os.write(buffer, 0, length);
        		}
        		} catch (IOException e) {
        		e.printStackTrace();
        		}
        		
        		locataire.setPhoto(fileName);
        		/*
    		}
    	*/
    		
    		
    		
    		if (update == false) {
    			locataireDao.saveLocataire(locataire); 
    			vider_champ();
			}else {
				locataireDao.updateLocataire(locataire);
			}
    		
    		   		
    		
    		
    		
		}else {
			Alert alert = new Alert(AlertType.WARNING,"Vueillez remplir tous les champs",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
		}

    }
		private Parent fxml;
		
	   @FXML
	    private VBox locatairesLayout;
	   
	   @FXML
	    private BorderPane panel_ajouter;
	   
	   @FXML
	    private HBox contenu_principal;
	   
	   
	    @FXML
	    void ajouterNouveau(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/AjouterLocataire.fxml"));
				contenu_principal.getChildren().removeAll();
				new FadeInDown(contenu_principal).play();
				contenu_principal.getChildren().setAll(fxml);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    	/*
	    	try {
	    		Parent parent = FXMLLoader.load(getClass().getResource("/interfaces/AjouterLocataire.fxml"));
	    		Scene scene = new Scene(parent);
	    		Stage stage = new Stage();
	    		stage.setScene(scene);
	    		stage.initStyle(StageStyle.UTILITY);
	    		stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
	    }
	    
	    @FXML
	    void fermer_ajouter(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/Locataire.fxml"));
				form_ajouter.getChildren().removeAll();
				new FadeInUp(form_ajouter).play();
				form_ajouter.getChildren().setAll(fxml);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    
	    	
	    
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		showLocataire();
		
		// rechercher un locataire
		  LocataireDao locataireDao = new LocataireDao();
		 List <Locataire> locataires = locataireDao.getAllLocataire();
		ObservableList <Locataire> liste_locataires = FXCollections.observableArrayList(locataires);		
	
		FilteredList<Locataire> filterData =new FilteredList<>(liste_locataires, b -> true);
		input_recherche.textProperty().addListener((observable,oldValue,newValue )->{
			filterData.setPredicate(locataire ->{
				//si la liste est vide
				if(newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				if (locataire.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				else if (locataire.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}				
				else if (locataire.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				
				else if (String.valueOf(locataire.getTelephone()).indexOf(lowerCaseFilter)!= -1) 
					return true;
					else
						return false;
				
			});
		});
		
		SortedList<Locataire> sortedData = new SortedList<>(filterData);
		
		sortedData.comparatorProperty().bind(table_locataires.comparatorProperty());
		 table_locataires.setItems(sortedData);
		
		
		
	//	showNotification(NotificationType.SUCCESS, "Connexion", "Connexion effectuée avec succes !");
		//ObservableList<String> options = FXCollections.observableArrayList("Option 1", "Option 2", "Option 3");
	    //comboBox.setItems(options);
		
	//	ObservableList<String> list = FXCollections.observableArrayList("CNI","Attestation");
	//	this.comboBox.setItems(list);
		
	//	combobox.setItems(FXCollections.observableArrayList("CNI","Attestation"));
		
		
	//combobox.setItems(FXCollections.observableArrayList("CNI","Attestation"));
		
		
		/*
		
		LocataireDao locataireDao = new LocataireDao();
	    	List <Locataire> locataires = locataireDao.getAllLocataire();	
			
		
		for(int i=0; i<locataires.size();i++) {			
			
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/interfaces/LocataireItem.fxml"));			
			
			try {
				HBox hBox = fxmlLoader.load();
				LocataireItemController cic = fxmlLoader.getController();
				cic.setData(locataires.get(i));
				locatairesLayout.getChildren().add(hBox);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}*/
		
		
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
		
		//parametrage de la notification
		notification.setAnimationType(type);
		notification.setTitle(titre);
		notification.setMessage(message);
		notification.setNotificationType(type_notif);
		notification.showAndDismiss(Duration.millis(3000));
		
		
	}
		
	}
	

	


