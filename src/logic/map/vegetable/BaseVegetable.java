package logic.map.vegetable;

import javafx.scene.image.Image;
import logic.components.Constants;
import logic.components.gameScene.GameCanvas;
import logic.game.GameController;
import logic.map.Tile;

public class BaseVegetable extends Tile {
    private String vegetableImageURL[];
    private GameCanvas gameCanvas;
    private int currentStage = 0;// max stage = 6
    private int growTime;

    public BaseVegetable(int x, int y, String[] vegetableImageURL, int growTime, String name) {
        super(vegetableImageURL[0], x, y);
        setVegetableImageURL(vegetableImageURL);
        setGrowTime(growTime);
        startGrowthTimer();
        this.gameCanvas = GameController.getGameCanvas();
        this.gameCanvas.getTileMapsLayers().get(Constants.LAYER_VEG).setTile(this, x, y);
        setName(name);
    }

    private void startGrowthTimer() {
        Thread growthThread = new Thread(() -> {
            try {
                while (currentStage < vegetableImageURL.length - 1 && !Thread.currentThread().isInterrupted()) {
                    Thread.sleep(growTime); // รอ 3 วินาที
                    currentStage++;
                    setImage(new Image(vegetableImageURL[currentStage]));
                }
            } catch (InterruptedException e) {
                System.out.println("Growth thread interrupted!");
            }
        });
        growthThread.setDaemon(true);
        growthThread.start();
    }
    
	public String[] getVegetableImageURL() {
		return vegetableImageURL;
	}

	public void setVegetableImageURL(String[] vegetableImageURL) {
		this.vegetableImageURL = vegetableImageURL;
	}

	public int getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(int currentStage) {
		this.currentStage = currentStage;
	}

	public int getGrowTime() {
		return growTime;
	}

	public void setGrowTime(int growTime) {
		this.growTime = growTime;
	}
    
    public void harvest() {
    	if(this.currentStage != vegetableImageURL.length - 1) {
    		return;
    	}
    	gameCanvas.getTileMapsLayers().get(Constants.LAYER_VEG).setTile(null, getX(), getY());
    }
}
