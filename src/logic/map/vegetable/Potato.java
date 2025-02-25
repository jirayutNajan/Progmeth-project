package logic.map.vegetable;

public class Potato extends BaseVegetable {
	private static int growTime = 2000;
	private static String vegetableImageURL[] = {
		"tileImage/tile473.png",
		"tileImage/tile474.png",
		"tileImage/tile475.png",
		"tileImage/tile476.png",
		"tileImage/tile477.png",
		"tileImage/tile478.png",
		"tileImage/tile479.png",
	};
	
	public Potato(int x, int y) {
		super(x, y, vegetableImageURL, growTime, "potato");
	}
}
