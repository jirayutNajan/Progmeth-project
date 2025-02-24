package logic.components.menuScene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.components.ButtonRect;
import logic.game.GameSceneController;

public class MenuCanvas extends Canvas {
    private ButtonRect startButton;

    public MenuCanvas(int width, int height) {
        super(width, height);
        
        startButton = new ButtonRect(512, 384, 200, 50, "Start Game");

        draw();
        setupClickListener();
    }

    private void draw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());

        startButton.draw(gc);
    }

    private void setupClickListener() {
        this.setOnMouseClicked((MouseEvent event) -> {
            double clickX = event.getX();
            double clickY = event.getY();

            if (startButton.isClicked(clickX, clickY)) {	
            	System.out.println("test");
                GameSceneController.startGame();
            }
        });
    }
}
