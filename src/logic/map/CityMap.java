package logic.map;

import logic.components.Constants;

public class CityMap {
	public TileMap[] tileMapLayers;
	private static String mapFilePath[] = {
			"res/tilemap/cityscene/cityLayer1.csv",
			"res/tilemap/cityscene/cityLayer2.csv",
			"res/tilemap/cityscene/cityLayer3.csv",
			"res/tilemap/cityscene/cityLayer4.csv",
			"res/tilemap/cityscene/cityLayer5.csv",
			};

    public CityMap() {
    	this.tileMapLayers = new TileMap[6];
    	tileMapLayers[Constants.LAYER_BACKGROUND] = new TileMap(mapFilePath[Constants.LAYER_BACKGROUND], Constants.LAYER_BACKGROUND);
    	tileMapLayers[Constants.LAYER_UNPASS] = new TileMap(mapFilePath[Constants.LAYER_UNPASS], Constants.LAYER_UNPASS);
    	tileMapLayers[Constants.LAYER_PASS3D] = new TileMap(mapFilePath[Constants.LAYER_PASS3D], Constants.LAYER_PASS3D);
    	tileMapLayers[Constants.LAYER_FARMLAND] = new TileMap(mapFilePath[Constants.LAYER_FARMLAND], Constants.LAYER_FARMLAND);
    	tileMapLayers[Constants.LAYER_DOOR] = new TileMap(mapFilePath[Constants.LAYER_DOOR], Constants.LAYER_DOOR);
        tileMapLayers[Constants.LAYER_VEG] = new TileMap(Constants.LAYER_VEG);
    }
}
