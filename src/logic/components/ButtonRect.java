package logic.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.geometry.VPos;

public class ButtonRect {
    private double x, y, width, height;
    private String text;
    private Runnable onClick;
    private Image image;

    public ButtonRect(double x, double y, double width, double height, String text, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;

        if (imagePath != null && !imagePath.isEmpty()) {
            // Load image with specified size & disable smooth scaling
            this.image = new Image(imagePath, width, height, false, false);
        }
    }

    public void draw(GraphicsContext gc) {
        // Disable image smoothing before drawing
        gc.setImageSmoothing(false);

        // Draw image if available
        if (image != null) {
            gc.drawImage(image, x, y, width, height);
        } else {
            // Default rectangle background if no image
            gc.setFill(Color.DARKGRAY);
            gc.fillRect(x, y, width, height);
        }

        // Draw text centered on button
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(text, x + width / 2, y + height / 2);
    }

    public boolean isClicked(double clickX, double clickY) {
        return clickX >= x && clickX <= x + width && clickY >= y && clickY <= y + height;
    }

    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }

    public void click() {
        if (onClick != null) {
            onClick.run();
        }
    }
}
