package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import logic.game.GameSceneController;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		GameSceneController.init(primaryStage);
		GameSceneController.showMenu();
		primaryStage.setTitle("Progmeth Project");
		
		primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
		primaryStage.setResizable(false);
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
