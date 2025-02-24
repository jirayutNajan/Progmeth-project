package logic.components.player;

import javafx.scene.image.Image;

public class PlayerAnimation {
	private static Image[] idleDownFrames = {
		new Image("player/idleDown1.png"),
		new Image("player/idleDown2.png"),
		new Image("player/idleDown3.png"),
		new Image("player/idleDown4.png"),
		new Image("player/idleDown5.png"),
		new Image("player/idleDown6.png")
    };
	private static Image[] idleLeftFrames = {
		new Image("player/idleLeft1.png"),
		new Image("player/idleLeft2.png"),
		new Image("player/idleLeft3.png"),
		new Image("player/idleLeft4.png"),
		new Image("player/idleLeft5.png"),
		new Image("player/idleLeft6.png"),
	};
	private static Image[] idleRightFrames = {
		new Image("player/idleRight1.png"),
		new Image("player/idleRight2.png"),
		new Image("player/idleRight3.png"),
		new Image("player/idleRight4.png"),
		new Image("player/idleRight5.png"),
		new Image("player/idleRight6.png"),
	};
	private static Image[] idleUpFrames = {
		new Image("player/idleUp1.png"),
		new Image("player/idleUp2.png"),
		new Image("player/idleUp3.png"),
		new Image("player/idleUp4.png"),
		new Image("player/idleUp5.png"),
		new Image("player/idleUp6.png"),
	};
	private static Image[] walkDownFrames = {
		new Image("player/walkDown1.png"),
		new Image("player/walkDown2.png"),
		new Image("player/walkDown3.png"),
		new Image("player/walkDown4.png"),
		new Image("player/walkDown5.png"),
		new Image("player/walkDown6.png"),
	};
	private static Image[] walkLeftFrames = {
		new Image("player/walkLeft1.png"),
		new Image("player/walkLeft2.png"),
		new Image("player/walkLeft3.png"),
		new Image("player/walkLeft4.png"),
		new Image("player/walkLeft5.png"),
		new Image("player/walkLeft6.png"),
	};
	private static Image[] walkRightFrames = {
		new Image("player/walkRight1.png"),
		new Image("player/walkRight2.png"),
		new Image("player/walkRight3.png"),
		new Image("player/walkRight4.png"),
		new Image("player/walkRight5.png"),
		new Image("player/walkRight6.png"),
	};
	private static Image[] walkUpFrames = {
		new Image("player/walkUp1.png"),
		new Image("player/walkUp2.png"),
		new Image("player/walkUp3.png"),
		new Image("player/walkUp4.png"),
		new Image("player/walkUp5.png"),
		new Image("player/walkUp6.png"),
	};
	
	public static Image[] getIdleDownFrames() {
		return idleDownFrames;
	}
	public static Image[] getIdleRightFrames() {
		return idleRightFrames;
	}
	public static Image[] getIdleleftFrames() {
		return idleLeftFrames;
	}
	public static Image[] getIdleUpFrames() {
		return idleUpFrames;
	}
	public static Image[] getWalkDownFrames() {
		return walkDownFrames;
	}
	public static Image[] getWalkRightFrames() {
		return walkRightFrames;
	}
	public static Image[] getWalkLeftFrames() {
		return walkLeftFrames;
	}
	public static Image[] getWalkUpFrames() {
		return walkUpFrames;
	}
}
