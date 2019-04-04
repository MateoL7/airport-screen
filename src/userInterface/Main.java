package userInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private FlightController fc;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FlightScreen.fxml"));
		Parent root = fxmlLoader.load();
		fc = fxmlLoader.getController();
		fc.setStage(stage);
		


		Scene scene = new Scene(root);
		stage.setTitle("Flight Screen");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
		
	}
	
}
