package logic.map;

public class Door extends Tile {
	private static String name = "door";
	
	public Door(String imageString, int x, int y) {
		super(imageString, x, y);
		setName(name);
	}
}
