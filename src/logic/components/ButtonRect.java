package logic.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.geometry.VPos;

public class ButtonRect {
    private double x, y, width, height;
    private String text;
    private Image normalImage;
    private Image hoverImage;
    private boolean isHovered = false;

    public ButtonRect(double x, double y, double width, double height, String text, String normalImagePath, String hoverImagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;

        if (normalImagePath != null && !normalImagePath.isEmpty()) {
            this.normalImage = new Image(normalImagePath, width, height, false, false);
        }
        if (hoverImagePath != null && !hoverImagePath.isEmpty()) {
            this.hoverImage = new Image(hoverImagePath, width, height, false, false);
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setImageSmoothing(false);
        Image imageToDraw = isHovered && hoverImage != null ? hoverImage : normalImage;

        if (imageToDraw != null) {
            gc.drawImage(imageToDraw, x, y, width, height);
        } else {
            gc.setFill(Color.DARKGRAY);
            gc.fillRect(x, y, width, height);
        }

        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(text, x + width / 2, y + height / 2);
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public void setHovered(boolean hovered) {
        this.isHovered = hovered;
    }
}