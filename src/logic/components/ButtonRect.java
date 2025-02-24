package logic.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ButtonRect {
    private double x, y, width, height;
    private String text;
    private Runnable onClick;

    public ButtonRect(double x, double y, double width, double height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(x, y, width, height);

        gc.setFill(Color.WHITE);
        gc.fillText(text, x + width / 4, y + height / 2);
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
