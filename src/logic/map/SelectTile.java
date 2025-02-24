package logic.map;

import javafx.scene.canvas.GraphicsContext;
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
		this.player = GameController.getPlayer();
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public void render(GraphicsContext gc) {
		super.render(gc, calculatePosition()[0], calculatePosition()[1]);
	}
	
	public int[] calculatePosition() {
	    if (player == null) {
	        player = GameController.getPlayer(); // ลองดึงค่าผู้เล่นอีกครั้ง
	    }

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
	    tilePosition[0] *= Constants.TILE_SIZE;
	    tilePosition[1] *= Constants.TILE_SIZE;
	    setTile(GameController.getGameCanvas().getTileMapsLayers().get(0).getTileAt(tilePosition[0]/Constants.TILE_SIZE, tilePosition[1]/Constants.TILE_SIZE));
	    System.out.println(tile.getId());

	    return tilePosition;
	}

}	