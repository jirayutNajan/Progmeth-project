package logic.map;

public class Farmland extends Tile {
	private static String imageString = "tileImage/tile431.png";
	private static String name = "farmland";
	
	public Farmland(int x, int y) {
		super(imageString, x, y);
		setName(name);
	}
}
