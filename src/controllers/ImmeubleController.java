package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.holding.dao.IImmeubleDao;

import com.holding.dao.ImmeubleDao;
import com.holding.models.Immeuble;
import com.mysql.jdbc.Connection;

import application.ConnexionMysql;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ImmeubleController implements Initializable {
	Connection cnx;	
	public PreparedStatement st;
	public ResultSet result;
	Immeuble immeuble = null;
	
	@FXML
    private ComboBox<String> cbx_type;

    @FXML
    private Label lab_url;

    @FXML
    private ImageView photo_immeuble;

    @FXML
    private GridPane pnl_ajouter;

    @FXML
    private GridPane pnl_liste;

    @FXML
    private GridPane pnl_modifier;

    @FXML
    private StackPane pnl_principal;

    @FXML
    private TableColumn<Immeuble, String> tb_adresse;

    @FXML
    private TableColumn<Immeuble, Boolean> tb_ascenseur;

    @FXML
    private TableColumn<Immeuble, Integer> tb_coutConstruction;

    @FXML
    private TableColumn<Immeuble, Integer> tb_idImmeuble;

    @FXML
    private TableColumn<Immeuble, Integer> tb_idProprietaire;

    @FXML
    private TableColumn<Immeuble, String> tb_libelle;

    @FXML
    private TableColumn<Immeuble, Short> tb_nombreAppartement;

    @FXML
    private TableColumn<Immeuble, String> tb_photo;

    @FXML
    private TableColumn<Immeuble, Boolean> tb_stationnement;

    @FXML
    private TableColumn<Immeuble, Integer> tb_superficie;

    @FXML
    private TableColumn<Immeuble, String> tb_type;
    
    @FXML
    private TableColumn<Immeuble, String> tb_actions;
    
    @FXML
    private TableView<Immeuble> table_immeuble;

    @FXML
    private TextField txt_adresse;

    @FXML
    private TextField txt_coutConstruction;

    @FXML
    private TextField txt_idProprietaire;

    @FXML
    private TextField txt_libelle;

    @FXML
    private TextField txt_nbApparte;

    @FXML
    private TextField txt_superficie; 
    
    @FXML
    private TextField input_immeuble;
    
    @FXML
    private CheckBox chk_ascenseur;
    
    @FXML
    private CheckBox chk_stationnement;
    
    ObservableList<String> type_liste = FXCollections.observableArrayList("Maison unifamiliale","Appartement en copropriété","Maison en rangée","Immeuble résidentiel à logements multiples","Immeuble commercial");
   
    private boolean update;
    void setUpdate(boolean b) {
        this.update = b;
    }
    private Integer idImmeubleST;
    
    void setTextField(Integer idImmeuble, int idProprietaire, String libelle, String adresse,
			Integer superficie, Short nombreAppartement, String type, Boolean ascenseur, Boolean stationnement,
			Integer coutConstruction, String photo) {
		// TODO Auto-generated method stub
    	txt_adresse.setText(adresse);
    	txt_libelle.setText(libelle);
		lab_url.setText(photo);
	
		chk_ascenseur.setSelected(ascenseur);
		
		chk_stationnement.setSelected(stationnement);
		
		String idProprietaireX = String.valueOf(idProprietaire);
		txt_idProprietaire.setText(idProprietaireX);
		
		String superficieX = String.valueOf(superficie);
		txt_superficie.setText(superficieX);
		
		String coutConstructionX = String.valueOf(coutConstruction);
		txt_coutConstruction.setText(coutConstructionX);
		
		String nbAppartX = String.valueOf(nombreAppartement);
		txt_nbApparte.setText(nbAppartX);
		/*String typeX = String.valueOf(type);
		cbx_type.setText(typeX);*/
	}
	
    @FXML
	 void btn_ajouter_nouveau() {
	    	pnl_ajouter.toFront();
	 }
    @FXML
    void close() {
    	pnl_liste.toFront();
    }
    
     ImmeubleDao immeubleDao = new ImmeubleDao();
	 List <Immeuble> immeubles = immeubleDao.getAllImmeuble();
	 ObservableList <Immeuble> liste_Immeubles = FXCollections.observableArrayList(immeubles);
	 
	 /*Mettre a jours le tableau*/
	 
	 private void refreshTable() {        
		 liste_Immeubles.clear();
         
		 ImmeubleDao immeubleDao = new ImmeubleDao();
		 List <Immeuble> immeuble = immeubleDao.getAllImmeuble();
		 ObservableList <Immeuble> liste_Immeubles = FXCollections.observableArrayList(immeuble);
     	
     	table_immeuble.setItems(liste_Immeubles);       
     }
	 
	 /*Afficher la liste des Immeubles*/
	 public void showImmeuble() {
		 tb_adresse.setCellValueFactory(new PropertyValueFactory<Immeuble,String>("adresse"));
		 tb_ascenseur.setCellValueFactory(new PropertyValueFactory<Immeuble,Boolean>("ascenseur"));
		 tb_coutConstruction.setCellValueFactory(new PropertyValueFactory<Immeuble,Integer>("coutConstruction"));
		 tb_idImmeuble.setCellValueFactory(new PropertyValueFactory<Immeuble,Integer>("idImmeuble"));
		 tb_idProprietaire.setCellValueFactory(new PropertyValueFactory<Immeuble,Integer>("idProprietaire"));
		 tb_libelle.setCellValueFactory(new PropertyValueFactory<Immeuble,String>("libelle"));
		 tb_nombreAppartement.setCellValueFactory(new PropertyValueFactory<Immeuble,Short>("nombreAppartement"));
		 tb_photo.setCellValueFactory(new PropertyValueFactory<Immeuble,String>("photo"));
		 tb_stationnement.setCellValueFactory(new PropertyValueFactory<Immeuble,Boolean>("stationnement"));
		 tb_superficie.setCellValueFactory(new PropertyValueFactory<Immeuble,Integer>("superficie"));
		 tb_type.setCellValueFactory(new PropertyValueFactory<Immeuble,String>("type"));
		 
		//add cell of button edit 
	    Callback<TableColumn<Immeuble, String>, TableCell<Immeuble, String>> cellFoctory = (TableColumn<Immeuble, String> param) -> {
	    	   // make cell containing buttons
	           final TableCell<Immeuble, String> cell = new TableCell<Immeuble, String>(){
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
	                	   ImageView imageViewEditer = new ImageView(imageEditer);
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
	                       
	                    	   immeuble = table_immeuble.getSelectionModel().getSelectedItem();
	                    	   
	                    	   //Si la liste est vide
	                    	   if (immeuble != null) {
	                    		   Alert alert = new Alert(AlertType.CONFIRMATION,"Voulez vous vraiment supprimer ce immeuble ?",javafx.scene.control.ButtonType.YES);
	                  				alert.showAndWait();
	                  				
	                  				immeubleDao.deleteImmeuble(immeuble.getIdImmeuble()); 
	                  				refreshTable();
							}  else {
								Alert alert = new Alert(AlertType.WARNING,"Veuillez selectionner une ligne de l'immeuble pour la Supprimer définitivement !.",javafx.scene.control.ButtonType.OK);
								alert.showAndWait();
							}                    	  

	                       });
	                       
	                    // Au clique du bouton Editer
	                       editIcon.setOnMouseClicked((MouseEvent event) -> {
	                           
	                           immeuble = table_immeuble.getSelectionModel().getSelectedItem();
	                           
	                         //Si la liste n'est pas vide
	                        if (immeuble != null) {
	                        	pnl_modifier.toFront();
	                        	idImmeubleST = immeuble.getIdImmeuble();
	                        	setTextField(immeuble.getIdImmeuble(), immeuble.getIdProprietaire(),immeuble.getLibelle(),immeuble.getAdresse(),immeuble.getSuperficie(),immeuble.getNombreAppartement(),immeuble.getType(),immeuble.getAscenseur(),immeuble.getStationnement(),immeuble.getCoutConstruction(),immeuble.getPhoto());
	                               
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
        tb_actions.setCellFactory(cellFoctory);
    	
    	table_immeuble.setItems(liste_Immeubles);
	 }

	@FXML
    void importer_image() {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png","*.jpg","*.jpeg"));
    	File f = fc.showOpenDialog(null);
    	if (f != null) {
			lab_url.setText(f.getAbsolutePath());
			Image image = new Image(f.toURI().toString(), photo_immeuble.getFitWidth(), photo_immeuble.getFitHeight(), true, true);
			photo_immeuble.setImage(image);
		}
    }
	
	
	@FXML
	void vider_champ() {
		txt_idProprietaire.setText("");
		txt_libelle.setText("");
		txt_adresse.setText("");
		txt_superficie.setText("");
		txt_nbApparte.setText("");
		txt_coutConstruction.setText("");
		photo_immeuble.setImage(null);
		lab_url.setText("");
	}
	
	 @FXML
	 void Ajouter_Immeuble() {
		 
		 String txtIdProprietaire = txt_idProprietaire.getText();
		 int intIdProprietaire = Integer.parseInt(txtIdProprietaire);
		 String txtLibelle = txt_libelle.getText();
		 String txtAdresse = txt_adresse.getText();
		 String txtSuperficie = txt_superficie.getText();
		 int intSuperficie = Integer.parseInt(txtSuperficie);
		 String txtNbApparte = txt_nbApparte.getText();
		 int intNbApparte = Integer.parseInt(txtNbApparte);
		 String txtCoutConstruction = txt_coutConstruction.getText();
		 int intCoutConstruction = Integer.parseInt(txtCoutConstruction);
		 File image = new File(lab_url.getText());
		 
		 if(!txtIdProprietaire.equals("") && !txtLibelle.equals("") && !txtAdresse.equals("") && !txtSuperficie.equals("")) {
			 /*IImmeubleDao immeubleDao = new ImmeubleDao();*/
	    	 Immeuble immeuble = new Immeuble();
	    	 
	    	 immeuble.setIdProprietaire(intIdProprietaire);
	    	 immeuble.setLibelle(txtLibelle);
	    	 immeuble.setAdresse(txtAdresse);
	    	 immeuble.setSuperficie(intSuperficie);
	    	 immeuble.setNombreAppartement((short) intNbApparte);
	    	 immeuble.setCoutConstruction(intCoutConstruction);
	    	 immeuble.setAscenseur(chk_ascenseur.isSelected());
     		 immeuble.setStationnement(chk_stationnement.isSelected());
     		 immeuble.setType(cbx_type.getValue());
	    	 
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
	  		 
     		immeuble.setPhoto(fileName);
     		immeubleDao.saveImmeuble(immeuble);
     		close();
     		refreshTable();
     		 
     		/*if (update == false) {
    			immeubleDao.saveImmeuble(immeuble);
    			vider_champ();
			}else {
				immeubleDao.updateImmeuble(immeuble);
			}*/
		 }
	 }
	 
	 @SuppressWarnings("unlikely-arg-type")
	@FXML
	 void Modifier_Immeuble() throws SQLException {
		 File image = new File(lab_url.getText());	 
		 
		 Alert alert;
		 if(txt_idProprietaire.getText().isEmpty() || txt_libelle.getText().isEmpty() || txt_adresse.getText().isEmpty() || txt_nbApparte.getText().isEmpty()) {
			    alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Remplissez tous les champs s'il vous plait ");
				alert.showAndWait();
		 }else {
			    alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION MESSAGE");
				alert.setHeaderText(null);
				alert.setContentText("Êtes vous sures de vouloir modifier les Informations de l'immeuble: "
						             + txt_libelle.getText() + " ? Cette action est irreversible");
				Optional<ButtonType> option = alert.showAndWait();
				
				if (option.get().equals(ButtonType.OK)) {
					for(Immeuble e: immeubleDao.getAllImmeuble()) {
						
						if(e.getLibelle().equals(txt_libelle)) {
							
							 e.setIdProprietaire(Integer.parseInt(txt_idProprietaire.getText()));
					    	 e.setLibelle(txt_libelle.getText());
					    	 e.setAdresse(txt_adresse.getText());
					    	 e.setSuperficie(Integer.parseInt(txt_superficie.getText()));
					    	 e.setNombreAppartement((short) Integer.parseInt(txt_nbApparte.getText()));
					    	 e.setCoutConstruction(Integer.parseInt(txt_coutConstruction.getText()));
					    	 e.setAscenseur(chk_ascenseur.isSelected());
					     	 e.setStationnement(chk_stationnement.isSelected());
					     	 
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
					     		} catch (IOException a) {
					     		  a.printStackTrace();
					     		}
					     		
					         e.setPhoto(fileName);	
					         
					     	 immeubleDao.updateImmeuble(e);
					     	 
					     	close();
					     	refreshTable();
						}
					}
				}
		 }
	 }
	 
	 
	 @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
		      showImmeuble();
		   //gestion de la combobox des types
			 cbx_type.setItems(type_liste);
		    
		    
		    // rechercher un locataire
			ImmeubleDao immeubleDao = new ImmeubleDao();
			List <Immeuble> immeubles = immeubleDao.getAllImmeuble();
			ObservableList <Immeuble> liste_Immeubles = FXCollections.observableArrayList(immeubles);		
		
			FilteredList<Immeuble> filterData =new FilteredList<>(liste_Immeubles, b -> true);
			input_immeuble.textProperty().addListener((observable,oldValue,newValue )->{
				filterData.setPredicate(immeuble ->{
					//si la liste est vide
					if(newValue == null || newValue.isEmpty()) {
						return true;
					}
					
					String lowerCaseFilter = newValue.toLowerCase();
					if (immeuble.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true;
					}
					else if (immeuble.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true;
					}				
					/*else if (immeuble.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true;
					}*/
					
					else if (String.valueOf(immeuble.getPhoto()).indexOf(lowerCaseFilter)!= -1) 
						return true;
						else
							return false;
					
				});
			});
			
			SortedList<Immeuble> sortedData = new SortedList<>(filterData);
			
			sortedData.comparatorProperty().bind(table_immeuble.comparatorProperty());
			table_immeuble.setItems(sortedData);
		}
	 
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
