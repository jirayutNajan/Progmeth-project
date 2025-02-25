package logic.components.player;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.components.Constants;
import logic.components.Direction;
import logic.components.Inventory;
import logic.components.gameScene.GameCanvas;
import logic.game.GameController;
import logic.map.Farmland;
import logic.map.SelectTile;
import logic.map.Tile;
import logic.map.TileMap;
import logic.map.vegetable.BaseVegetable;
import logic.map.vegetable.Potato;
import logic.map.vegetable.Tomato;

public class Player {
	private double x;
    private double y;
    private int corX, corY;
    private double speed;
    private double width;
    private double height;
    public SelectTile selectTile;
    private Direction direction;
    private double previousX;
    private double previousY;
    private Image image;
	private int frameIndex = 0;
	private long lastFrameTime = 0;
	private static final long FRAME_DURATION = 150_000_000; // 150ms
	private GameCanvas gameCanvas;
	private Inventory inventory;
    
    public Player(double startX, double startY, double speed, double width, double height) {
        setX(startX);
        setY(startY);
        setSpeed(speed);
        setWidth(width);
        setHeight(height);
        setDirection(Direction.DOWN);
        setSelectTile(new SelectTile());
        setPreviousX(startX);
        setPreviousY(startY);
        setImage(new Image("player/idleDown1.png"));
    }

    public void updatePlayer() {
    	animate();
    	
    	this.selectTile.setX();
    	this.selectTile.setY();
    	this.corX = (int) (this.x/Constants.TILE_SIZE);
		this.corY = (int) (this.y/Constants.TILE_SIZE);
    	
        double newX = this.getX();
        double newY = this.getY();
                
//        if (this.gameCanvas.getActiveKeys().size() > 1) return;
        
        previousX = newX;
        previousY = newY;

        if (this.gameCanvas.getActiveKeys().contains(KeyCode.W)) {
            setDirection(Direction.UP);
            if (!checkCollision(newX, newY - this.getSpeed(), this.gameCanvas.getTileMapsLayers())) {
                this.moveUp();
            }
        }
        if (this.gameCanvas.getActiveKeys().contains(KeyCode.S)) {
            setDirection(Direction.DOWN);
            if (!checkCollision(newX, newY + this.getSpeed(), this.gameCanvas.getTileMapsLayers())) {
                this.moveDown();
            }
        }
        if (this.gameCanvas.getActiveKeys().contains(KeyCode.A)) {
            setDirection(Direction.LEFT);
            if (!checkCollision(newX - this.getSpeed(), newY, this.gameCanvas.getTileMapsLayers())) {
                this.moveLeft();
            }
        }
        if (this.gameCanvas.getActiveKeys().contains(KeyCode.D)) {
            setDirection(Direction.RIGHT);
            if (!checkCollision(newX + this.getSpeed(), newY, this.gameCanvas.getTileMapsLayers())) {
                this.moveRight();
            }
        }
        
        if(this.gameCanvas.getActiveKeys().contains(KeyCode.SPACE)) {
        	if(inventory.getCurrentImageIndex() == 1) {
            	Tile selected = selectTile.getTile();
            	if(selected != null) {
            		if(selected instanceof BaseVegetable) {
            			((BaseVegetable) selected).harvest();
            			// add score
            		}
                }
        	}
        	else if(inventory.getCurrentImageIndex() == 2) {
        		if(selectTile.getTile() instanceof Farmland) {
        			new Potato(selectTile.getX(), selectTile.getY());
        		}
        	}
        	else if(inventory.getCurrentImageIndex() == 3) {
        		if(selectTile.getTile() instanceof Farmland) {
            		new Tomato(selectTile.getX(), selectTile.getY());
            	}
        	}
        	// ผัก 3 @saint
        }
        
        if(this.gameCanvas.getActiveKeys().contains(KeyCode.P)) {
        	gameCanvas.loadCityScene();
        }
        
        if(gameCanvas.getTileMapsLayers().get(Constants.LAYER_DOOR).getTileAt(corX, corY) != null) {
        	if(gameCanvas.getMapName() == "farm") {
        		gameCanvas.loadCityScene();
        	}
        	else if(gameCanvas.getMapName() == "city") {
        		gameCanvas.loadFarmScene();
        	}
        }
        
        snapToTile();
    }

    private void snapToTile() {
    	if(!isMoving()) {
    		this.x = Math.round(this.x / Constants.TILE_SIZE) * Constants.TILE_SIZE;
    		this.y = Math.round(this.y / Constants.TILE_SIZE) * Constants.TILE_SIZE;
    	}
    }

    // ฟังก์ชันเช็คว่า Player กำลังเคลื่อนที่หรือไม่
    private boolean isMoving() {
        return (this.x != previousX || this.y != previousY);
    }

    private boolean checkCollision(double newX, double newY, ArrayList<TileMap> tileMapsLayers) {
        int playerWidth = (int) (this.getWidth());
        int playerHeight = (int) (this.getHeight());

        // ตรวจสอบ 4 มุมของ Bounding Box ของ Player
        return isTileCollidable(newX, newY, tileMapsLayers) || // มุมซ้ายบน
                isTileCollidable(newX + playerWidth, newY, tileMapsLayers) || // มุมขวาบน
                isTileCollidable(newX, newY + playerHeight, tileMapsLayers) || // มุมซ้ายล่าง
                isTileCollidable(newX + playerWidth, newY + playerHeight, tileMapsLayers); // มุมขวาล่าง
    }

    private boolean isTileCollidable(double x, double y, ArrayList<TileMap> tileMapsLayers) {
        int[] position = getTilePosition(x, y);
        int tileX = position[0];
        int tileY = position[1];

        for (TileMap tilemap : tileMapsLayers) {
            Tile tile = tilemap.getTileAt(tileX, tileY);
            if (tile != null && tile.getLayer() == Constants.LAYER_UNPASS) {
                return true;
            }
        }
        return false;
    }

    public int[] getTilePosition(double x, double y) {
        int tileX = (int) (x / Constants.TILE_SIZE);
        int tileY = (int) (y / Constants.TILE_SIZE);
        return new int[] {tileX, tileY};
    }

    private void animate() {
        long currentTime = System.nanoTime();
        Image[] currentFrame = PlayerAnimation.getIdleDownFrames();
        if(this.gameCanvas.getActiveKeys().size() == 0) {
        	switch (getDirection()) {
        		case Direction.DOWN:
        			currentFrame = PlayerAnimation.getIdleDownFrames();
        			break;
		        case Direction.LEFT:
		        	currentFrame = PlayerAnimation.getIdleleftFrames();
		        	break;
		        case Direction.RIGHT: 
		        	currentFrame = PlayerAnimation.getIdleRightFrames();
		        	break;
				case Direction.UP: 
					currentFrame = PlayerAnimation.getIdleUpFrames();
					break;
	        }
        }
        else {
        	switch (getDirection()) {
	    		case Direction.DOWN:
	    			currentFrame = PlayerAnimation.getWalkDownFrames();
	    			break;
		        case Direction.LEFT:
		        	currentFrame = PlayerAnimation.getWalkLeftFrames();
		        	break;
		        case Direction.RIGHT: 
		        	currentFrame = PlayerAnimation.getWalkRightFrames();
		        	break;
				case Direction.UP: 
					currentFrame = PlayerAnimation.getWalkUpFrames();
					break;
        	}
        }
        
        if (currentTime - lastFrameTime >= FRAME_DURATION) {
            frameIndex = (frameIndex + 1) % currentFrame.length;
            setImage(currentFrame[frameIndex]); 
            lastFrameTime = currentTime;
        }
    }

    public void render(GraphicsContext gc) {
        selectTile.render(gc);
        gc.drawImage(this.image, x - Constants.TILE_SIZE/2, y - Constants.TILE_SIZE/2, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);
    }

    public void setup() {
    	this.gameCanvas = GameController.getGameCanvas();
    	this.selectTile.loadPlayer();
    	this.inventory = GameController.getInventory();
    }
    
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void moveUp() {
        this.y -= speed;
    }

    public void moveDown() {
        this.y += speed;
    }

    public void moveLeft() {
        this.x -= speed;
    }

    public void moveRight() {
        this.x += speed;
    }

    public double getSpeed() {
        return this.speed;
    }

	public SelectTile getSelectTile() {
		return selectTile;
	}

	public void setSelectTile(SelectTile selectTile) {
		this.selectTile = selectTile;
	}

	public double getPreviousX() {
		return previousX;
	}

	public void setPreviousX(double previousX) {
		this.previousX = previousX;
	}

	public double getPreviousY() {
		return previousY;
	}

	public void setPreviousY(double previousY) {
		this.previousY = previousY;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
