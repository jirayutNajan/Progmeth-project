package logic.map;

public class FarmMap {
	public TileMap[] tileMapLayers;
    private static String mapFilePath = "res/tilemap/maptest.csv";
    private static String mapFilePath2 = "res/tilemap/maptest2.csv";

    public FarmMap() {
    	this.tileMapLayers = new TileMap[3];
        tileMapLayers[0] = new TileMap(mapFilePath, 0);
        tileMapLayers[1] = new TileMap(mapFilePath2, 1);
        tileMapLayers[2] = new TileMap(2);
    }
}
