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
import logic.components.Inventory;
import logic.components.player.Player;
import logic.game.GameController;
import logic.map.CityMap;
import logic.map.FarmMap;
import logic.map.TileMap;

public class GameCanvas extends Canvas {
    private ArrayList<TileMap> tileMapsLayers;
    private FarmMap farmMap;
    private CityMap cityMap;
    private Player player;
    private Set<KeyCode> activeKeys;
    private Thread gameLoop;
    private boolean isRunning = true;
    private Inventory inventory;
    private String mapName = "farm";
    
    public GameCanvas(int width, int height) {
        super(width, height);

        this.farmMap = new FarmMap();
        this.cityMap = new CityMap();
        this.activeKeys = new HashSet<>();
        this.player = GameController.getPlayer();
        this.inventory = GameController.getInventory();

        this.start();
        
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
                	player.updatePlayer();
                	inventory.updateInvetory();
                	
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
        tileMapsLayers.get(Constants.LAYER_BACKGROUND).render(gc);
        tileMapsLayers.get(Constants.LAYER_UNPASS).render(gc);
        tileMapsLayers.get(Constants.LAYER_FARMLAND).render(gc);
        tileMapsLayers.get(Constants.LAYER_DOOR).render(gc);
        tileMapsLayers.get(Constants.LAYER_VEG).render(gc);
        
        // update and render player
        player.render(gc);
        
        tileMapsLayers.get(Constants.LAYER_PASS3D).render(gc);
        
        // render game darkness
        gc.setFill(new Color(0, 0, 0, GameController.getGameDarkness()));
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        // render gameclock
        GameController.getGameClock().render(gc, 50, 50);
        
        inventory.render(gc);
    }

    public void start() {
    	setMapName("farm");
    	this.tileMapsLayers = new ArrayList<TileMap>();
    	for(int i=0; i<this.farmMap.tileMapLayers.length; i++) {
    		this.tileMapsLayers.add(i, this.farmMap.tileMapLayers[i]);
    	}
    }
    
    public void loadFarmScene() {
    	player.setX(900);
    	player.setY(500);
    	setMapName("farm");
    	this.tileMapsLayers = new ArrayList<TileMap>();
    	for(int i=0; i<this.farmMap.tileMapLayers.length; i++) {
    		this.tileMapsLayers.add(i, this.farmMap.tileMapLayers[i]);
    	}
    }
    
    public void loadCityScene() {
    	player.setX(0);
    	player.setY(224);
    	setMapName("city");
        this.tileMapsLayers = new ArrayList<TileMap>();
        for(int i=0; i<this.cityMap.tileMapLayers.length; i++) {
            this.tileMapsLayers.add(i, this.cityMap.tileMapLayers[i]);
        }
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

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String map) {
		this.mapName = map;
	}
}