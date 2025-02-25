package logic.map;

import logic.components.Constants;

public class FarmMap {
	public TileMap[] tileMapLayers;
	private static String mapFilePath[] = {
			"res/tilemap/farmscene/farmLayer1.csv",
			"res/tilemap/farmscene/farmLayer2.csv",
			"res/tilemap/farmscene/farmLayer3.csv",
			"res/tilemap/farmscene/farmLayer4.csv",
			"res/tilemap/farmscene/farmLayer5.csv",
			};

    public FarmMap() {
    	this.tileMapLayers = new TileMap[6];
    	tileMapLayers[Constants.LAYER_BACKGROUND] = new TileMap(mapFilePath[Constants.LAYER_BACKGROUND], Constants.LAYER_BACKGROUND);
    	tileMapLayers[Constants.LAYER_UNPASS] = new TileMap(mapFilePath[Constants.LAYER_UNPASS], Constants.LAYER_UNPASS);
    	tileMapLayers[Constants.LAYER_PASS3D] = new TileMap(mapFilePath[Constants.LAYER_PASS3D], Constants.LAYER_PASS3D);
    	tileMapLayers[Constants.LAYER_FARMLAND] = new TileMap(mapFilePath[Constants.LAYER_FARMLAND], Constants.LAYER_FARMLAND);
    	tileMapLayers[Constants.LAYER_DOOR] = new TileMap(mapFilePath[Constants.LAYER_DOOR], Constants.LAYER_DOOR);
        tileMapLayers[Constants.LAYER_VEG] = new TileMap(Constants.LAYER_VEG);
    }
}
