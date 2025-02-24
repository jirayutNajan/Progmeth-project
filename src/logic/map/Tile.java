package logic.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.components.Constants;

public class Tile {
    private Image image;
    private int layer;
    private int id;

    public Tile(String imagePath, int layer, int id) {
    	setImage(new Image(imagePath));
    	setLayer(layer);
    	setId(id);
    }
    
    public Tile(String imageString) {
    	setImage(new Image(imageString));
    }

    public void render(GraphicsContext gc, int x, int y) {
    	gc.drawImage(this.getImage(), x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
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
    
    
}
