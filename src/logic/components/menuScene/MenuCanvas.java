package logic.components.menuScene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.components.ButtonRect;
import logic.game.GameSceneController;

public class MenuCanvas extends Canvas {
    private ButtonRect startButton;
    private Image logo;

    public MenuCanvas(int width, int height) {
        super(width, height);
        startButton = new ButtonRect(width / 2 - 100, height / 2 + 50, 200, 50, "", "Play button.png");

        // Load the logo image
        logo = new Image("Logo.png"); // Make sure "logo.png" is in the correct resources folder

        draw();
        setupClickListener();
    }

    private void draw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Draw the logo in the center
        double logoX = (this.getWidth() - logo.getWidth()) / 2;
        double logoY = (this.getHeight() - logo.getHeight()) / 3; // Slightly above center
        gc.drawImage(logo, logoX, logoY);

        // Draw the start button
        startButton.draw(gc);
    }

    private void setupClickListener() {
        this.setOnMouseClicked((MouseEvent event) -> {
            double clickX = event.getX();
            double clickY = event.getY();

            if (startButton.isClicked(clickX, clickY)) {
                System.out.println("Game is starting...");
                GameSceneController.startGame();
            }
        });
    }
}
