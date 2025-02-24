package logic.map;

import logic.components.Constants;

public class FarmMap {
	public TileMap[] tileMapLayers;
    private static String mapFilePath = "res/tilemap/maptest.csv";
    private static String mapFilePath2 = "res/tilemap/maptest2.csv";

    public FarmMap() {
    	this.tileMapLayers = new TileMap[2];
        tileMapLayers[0] = new TileMap(mapFilePath, Constants.TILE_SIZE, 0);
        tileMapLayers[1] = new TileMap(mapFilePath2, Constants.TILE_SIZE, 1);
    }
}
