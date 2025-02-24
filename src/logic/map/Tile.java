package logic.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.components.Constants;

public class Tile {
    private Image image;
    private int layer;
    private int id;
    private int x;
    private int y;

    public Tile(String imagePath, int layer, int id, int x, int y) {
    	setImage(new Image(imagePath));
    	setLayer(layer);
    	setId(id);
    	setX(x);
    	setY(y);
    }
    
    public Tile(String imageString) {
    	setImage(new Image(imageString));
    }
    
    public Tile(String imageString, int x, int y) {
    	setImage(new Image(imageString));
    	setX(x);
    	setY(y);
    }

    public void render(GraphicsContext gc) {
    	gc.drawImage(this.getImage(), this.x * Constants.TILE_SIZE, this.y * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }
    
    public Image getImage() {
        return image;
    }

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
    
	
}
