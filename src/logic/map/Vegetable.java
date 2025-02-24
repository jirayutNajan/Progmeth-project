package logic.map;

import javafx.scene.image.Image;
import logic.components.gameScene.GameCanvas;
import logic.game.GameController;

public class Vegetable extends Tile {
    private static String vegetableImageURL[] = {
        "tileImage/tile111.png",
        "tileImage/tile112.png",
        "tileImage/tile113.png",
        "tileImage/tile114.png",
        "tileImage/tile115.png",
        "tileImage/tile116.png",
    };

    private GameCanvas gameCanvas;
    private int currentStage = 0;// max stage = 4
    private Thread growthThread;

    public Vegetable(int x, int y) {
        super(vegetableImageURL[0], x, y);
        this.gameCanvas = GameController.getGameCanvas();
        this.gameCanvas.getTileMapsLayers().get(2).setTile(this, x, y);
        startGrowthTimer();
    }

    private void startGrowthTimer() {
        growthThread = new Thread(() -> {
            try {
                while (currentStage < vegetableImageURL.length - 1 && !Thread.currentThread().isInterrupted()) {
                    Thread.sleep(2000); // รอ 3 วินาที
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

    public void stopGrowth() {
        if (growthThread != null && growthThread.isAlive()) {
            growthThread.interrupt(); // หยุดเธรด
        }
    }
}
