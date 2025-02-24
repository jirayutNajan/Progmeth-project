package logic.game;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.components.Constants;
import logic.components.menuScene.MenuCanvas;

public class GameSceneController {
	private static Stage stage;
	
	public static void init(Stage primaryStage) {
		stage = primaryStage;
	}
	
	public static void showMenu() {
		MenuCanvas menuCanvas = new MenuCanvas(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		Scene scene = new Scene(new StackPane(menuCanvas), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		stage.setScene(scene);
	}
	
	
	public static void startGame() {
		GameController.setup(stage);
	}
}
