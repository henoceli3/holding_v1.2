package application;
	
import animatefx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
			Scene scene = new Scene(root);
		//	scene.setFill(Color.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.setTitle("holding V1");
		//	primaryStage.initStyle(StageStyle.TRANSPARENT);
			new ZoomIn(root).play();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
