package logic.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.components.gameScene.GameCanvas;
import logic.game.GameController;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Inventory {
    private Map<Integer, Image> inventoryImages; // Store images with number keys
    private int currentImageIndex; // Track current image (1 to 4)
    private double x, y; // Position of the inventory bar
    private double width, height; // Image dimensions
    private final double SCALE_FACTOR = 2.5; // Adjust this value to increase size
    private GameCanvas gameCanvas;

    public Inventory() {
        inventoryImages = new HashMap<>();
        inventoryImages.put(1, new Image("inventory/inventory1.png"));
        inventoryImages.put(2, new Image("inventory/inventory2.png"));
        inventoryImages.put(3, new Image("inventory/inventory3.png"));
        inventoryImages.put(4, new Image("inventory/inventory4.png"));

        currentImageIndex = 1; 
    }
    
    public void setup() {
    	this.gameCanvas = GameController.getGameCanvas();
    	updateSizeAndPosition();
    }

    private void updateSizeAndPosition() {
        Image inventoryImage = inventoryImages.get(currentImageIndex);
        
        this.width = inventoryImage.getWidth() * SCALE_FACTOR;
        this.height = inventoryImage.getHeight() * SCALE_FACTOR;
        this.x = (gameCanvas.getWidth() - width) / 2;
        this.y = gameCanvas.getHeight() - height - 10; 
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(inventoryImages.get(currentImageIndex), x, y, width, height);
    }

    public void setInventoryImage(int imageKey) {
        if (inventoryImages.containsKey(imageKey)) {
            currentImageIndex = imageKey;
            updateSizeAndPosition();
        }
    }
    
    public void updateInvetory() {
    	Set<KeyCode> activeKeys = gameCanvas.getActiveKeys();
    	
    	if(activeKeys.contains(KeyCode.DIGIT1)) {
    		setInventoryImage(1);
    	}
    	if(activeKeys.contains(KeyCode.DIGIT2)) {
    		setInventoryImage(2);
    	}
    	if(activeKeys.contains(KeyCode.DIGIT3)) {
    		setInventoryImage(3);
    	}
    	if(activeKeys.contains(KeyCode.DIGIT4)) {
    		setInventoryImage(4);
    	}
    }

	public int getCurrentImageIndex() {
		return currentImageIndex;
	}

	public void setCurrentImageIndex(int currentImageIndex) {
		this.currentImageIndex = currentImageIndex;
	}
}