package logic.game;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.components.Constants;
import logic.components.gameScene.GameCanvas;
import logic.components.gameScene.GameClock;
import logic.components.player.Player;

public class GameController {
	private static int score;
	private static Player player;
	private static GameClock gameClock;
	private static double gameDarkness;
	private static GameCanvas gameCanvas;
	
	public static void setup(Stage stage) {
		reset();
		Scene scene = new Scene(new StackPane(gameCanvas), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		stage.setScene(scene);
	}
	
	public static void reset() {
		score = 0;
		player = new Player(100, 100, 4, 30, 30);
		gameClock = new GameClock(5);
		gameCanvas = new GameCanvas(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		player.setup();
		gameDarkness = 0.5; // ค่าเริ่มต้น
	}
	
	public static void updateGameLogic() {
		updateGameDarkness();
	}
	
	private static void updateGameDarkness() {
		if(gameClock.getGameHours() >= 5 && gameClock.getGameHours() < 7) {
			gameDarkness = 0.35;
		}
		else if(gameClock.getGameHours() >= 7 && gameClock.getGameHours() < 18) {
			gameDarkness = 0;
		}
		else if(gameClock.getGameHours() >= 18 && gameClock.getGameHours() <= 19) {
			gameDarkness = 0.35;
		}
		else {
			gameDarkness = 0.5;
		}
	}
	
	public static int getGameScore() {
		return score;
	}
	
	public static double getGameDarkness() {
		return gameDarkness;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static GameClock getGameClock() {
		return gameClock;
	}
	
	public static GameCanvas getGameCanvas() {
		return gameCanvas;
	}
}
