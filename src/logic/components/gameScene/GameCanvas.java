package logic.components.gameScene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import logic.components.Constants;
import logic.components.player.Player;
import logic.game.GameController;
import logic.map.FarmMap;
import logic.map.TileMap;

public class GameCanvas extends Canvas {
    private ArrayList<TileMap> tileMapsLayers;
    private FarmMap farmMap;
    private Player player;
    private Set<KeyCode> activeKeys;
    private Thread gameLoop;
    private boolean isRunning = true;
    
    public GameCanvas(int width, int height) {
        super(width, height);

        this.farmMap = new FarmMap();
        this.activeKeys = new HashSet<>();

        this.player = GameController.getPlayer();

        this.loadFarmScene();
        
        this.setFocusTraversable(true);
        
        setupKeyPressed();
        startGameLoop();
    }

    private void setupKeyPressed() {
    	this.setOnKeyPressed((event) -> {
            activeKeys.add(event.getCode());
        });

        this.setOnKeyReleased((event) -> {
            activeKeys.remove(event.getCode());
        });
    }
    
    private void startGameLoop() {
        gameLoop = new Thread(() -> {
            while (isRunning) {
                Platform.runLater(() -> {
                	// update gamelogic
                	GameController.updateGameLogic();
                	player.updatePlayer(this.activeKeys, this.tileMapsLayers);
                	
                    updateCanvas();
                });
                
                try {
                    Thread.sleep(Constants.SLEEPTIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        gameLoop.start();
    }

    private void updateCanvas() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setImageSmoothing(false);

        gc.setFill(Color.DARKGREEN);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        // render tilemap
        for(TileMap tilemap: tileMapsLayers) {
        	tilemap.render(gc);
        }
        
        // update and render player
        player.render(gc);
        
        // render gameclock
        GameController.getGameClock().render(gc, 50, 50);
        
        // render game darkness
        gc.setFill(new Color(0, 0, 0, GameController.getGameDarkness()));
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void loadFarmScene() {
        this.tileMapsLayers = new ArrayList<TileMap>();
        for(int i=0; i<this.farmMap.tileMapLayers.length; i++) {
            this.tileMapsLayers.add(i, this.farmMap.tileMapLayers[i]);
        }
    }

    public void loadFarmScene2() {
        this.tileMapsLayers = new ArrayList<TileMap>();
        this.tileMapsLayers.add(0, this.farmMap.tileMapLayers[0]);
    }
    
    public void stopGameLoop() {
        isRunning = false;
    }

	public ArrayList<TileMap> getTileMapsLayers() {
		return tileMapsLayers;
	}

	public void setTileMapsLayers(ArrayList<TileMap> tileMapsLayers) {
		this.tileMapsLayers = tileMapsLayers;
	}

	public Set<KeyCode> getActiveKeys() {
		return activeKeys;
	}

	public void setActiveKeys(Set<KeyCode> activeKeys) {
		this.activeKeys = activeKeys;
	}
}