package logic.map;

import javafx.scene.canvas.GraphicsContext;
import logic.components.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TileMap {
    private Tile[][] map;
    private int layer;

    //tile layer 1 new tile ปกติไม่ต้องมี static
    //tile ที่ต้องมี static คืออันพิเศษ
    
    public TileMap(String csvFile, int layer) {
        setLayer(layer);
        loadTileMap(csvFile);
    }
    
    public TileMap(int layer) {
    	setMap(new Tile[32][24]);
    	setLayer(layer);
    }

    private void loadTileMap(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<Tile[]> tempMap = new ArrayList<>();
            int y = 0; // ใช้เป็นตัวแปรแถว

            for (String line; (line = br.readLine()) != null; y++) {
                String[] arr = line.split(",");
                Tile[] row = new Tile[arr.length];

                for (int x = 0; x < arr.length; x++) { // ใช้ x เป็นตัวระบุคอลัมน์
                    int tileId = Integer.parseInt(arr[x].trim());
                    if (tileId == 0) continue;
                    
                    // สร้าง Tile พร้อมพิกัด (x, y)
                    if(this.layer == Constants.LAYER_FARMLAND) {
                    	row[x] = new Farmland(x, y);
                    }
                    else if(this.layer == Constants.LAYER_DOOR) {
                    	row[x] = new Door("/tileImage/tile" + tileId + ".png", x, y);
                    }
                    else {
                    	row[x] = new Tile("/tileImage/tile" + tileId + ".png", this.layer, tileId, x, y);
                    }
                }
                tempMap.add(row);
            }

            map = tempMap.toArray(new Tile[0][]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(GraphicsContext gc) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                Tile tile = map[y][x];
                if (tile != null) {
                	tile.render(gc);
                }
            }
        }
    }
    
    public void setTile(Tile tile, int x, int y) {
    	this.map[y][x] = tile;
    }
    
	public Tile[][] getMap() {
		return map;
	}

	public void setMap(Tile[][] map) {
		this.map = map;
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
