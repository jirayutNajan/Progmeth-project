package logic.map;

import javafx.scene.canvas.GraphicsContext;
import logic.components.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TileMap {
    private Tile[][] map;
    private int layer;

    //tile layer 1 new tile ปกติไม่ต้องมี static
    //tile ที่ต้องมี static คืออันพิเศษ
    
    public TileMap(String csvFile, int tileSize, int layer) {
        setLayer(layer);
        loadTileMap(csvFile);
    }

    private void loadTileMap(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            map = br.lines()
                    .map(line -> line.split(","))
                    .map(arr -> {
                        Tile[] row = new Tile[arr.length];
                        for (int i = 0; i < arr.length; i++) {
                            int tileId = Integer.parseInt(arr[i].trim());
                            if(tileId == 0) continue;
                            row[i] = new Tile("/tileImage/tile" + tileId + ".png", this.layer, tileId);
                        }
                        return row;
                    })
                    .toArray(Tile[][]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(GraphicsContext gc) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                Tile tile = map[y][x];
                if (tile != null) {
                	tile.render(gc, x * Constants.TILE_SIZE, y * Constants.TILE_SIZE);
                }
            }
        }
    }

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}
    
	public Tile getTileAt(int x, int y) {
	    if (x < 0 || y < 0 || x >= map[0].length || y >= map.length) {
	        return null; // ถ้าอยู่นอกขอบเขตของแมพ
	    }
	    return map[y][x];
	}

}
