package logic.map.vegetable;

public class Tomato extends BaseVegetable {
	private static int growTime = 2000;
	private static String vegetableImageURL[] = {
		"tileImage/tile524.png",
		"tileImage/tile525.png",
		"tileImage/tile526.png",
		"tileImage/tile527.png",
		"tileImage/tile528.png",
		"tileImage/tile529.png",
		"tileImage/tile530.png",
	};
	
	public Tomato(int x, int y) {
		super(x, y, vegetableImageURL, growTime, "tomato");
	}
	
	
}
