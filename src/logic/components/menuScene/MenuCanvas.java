package logic.components.menuScene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.components.ButtonRect;
import logic.game.GameSceneController;

public class MenuCanvas extends Canvas {
    private ButtonRect startButton;
    private Image logo;
    private GraphicsContext gc;

    public MenuCanvas(int width, int height) {
        super(width, height);
        gc = this.getGraphicsContext2D();

        startButton = new ButtonRect(
            width / 2 - 100, height / 2 + 50, 200, 50, 
            "", "Play button.png", "Play button_hover.png"
        );

        logo = new Image("Logo.png");

        draw();
        setupEventListeners();
    }

    private void draw() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Enable image smoothing for the logo
        gc.setImageSmoothing(true);

        double logoX = (this.getWidth() - logo.getWidth()) / 2;
        double logoY = (this.getHeight() - logo.getHeight()) / 3;
        gc.drawImage(logo, logoX, logoY);

        // Disable image smoothing for pixel-perfect button drawing
        gc.setImageSmoothing(false);
        startButton.draw(gc);
    }


    private void setupEventListeners() {
        this.setOnMouseMoved(event -> {
            boolean isHovering = startButton.isHovered(event.getX(), event.getY());
            startButton.setHovered(isHovering);
            draw(); // Redraw the canvas to update the button state
        });

        this.setOnMouseClicked(event -> {
            if (startButton.isHovered(event.getX(), event.getY())) {
                System.out.println("Game is starting...");
                GameSceneController.startGame();
            }
        });
    }
}