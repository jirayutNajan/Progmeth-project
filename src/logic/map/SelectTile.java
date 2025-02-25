package logic.map;

import logic.components.Constants;
import logic.components.Direction;
import logic.components.player.Player;
import logic.game.GameController;

public class SelectTile extends Tile {
	private static String selectTileImageURL = "util/select_tile.png";
	private Tile tile;
	private Player player;
	
	public SelectTile() {
		super(selectTileImageURL);
		setTile(null);
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public int[] calculatePosition() {
	    double dX = player.getX();
	    double dY = player.getY();
	    Direction direction = player.getDirection();

	    if (direction == Direction.UP) {
	        dY -= Constants.TILE_SIZE * 0.5;
	    } else if (direction == Direction.DOWN) {
	        dY += Constants.TILE_SIZE * 1.5;
	    } else if (direction == Direction.LEFT) {
	        dX -= Constants.TILE_SIZE * 0.5;
	    } else if (direction == Direction.RIGHT) {
	        dX += Constants.TILE_SIZE * 1.5;
	    }

	    int[] tilePosition = player.getTilePosition(dX, dY);
	    setTile(GameController.getGameCanvas().getTileMapsLayers().get(Constants.LAYER_VEG).getTileAt(tilePosition[0], tilePosition[1]));
	    if(getTile() == null) {
	    	setTile(GameController.getGameCanvas().getTileMapsLayers().get(Constants.LAYER_FARMLAND).getTileAt(tilePosition[0], tilePosition[1]));
	    }
	    
	    return tilePosition;
	}
	
	public void loadPlayer() {
		this.player = GameController.getPlayer();
	}
	
	public void setX() {
		super.setX(calculatePosition()[0]);
	}
	
	public void setY() {
		super.setY(calculatePosition()[1]);
	}

}	