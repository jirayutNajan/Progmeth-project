package logic.components.gameScene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameClock implements Runnable {
	private int gameHours;
    private int gameMinutes;
    private boolean running;
    private Thread clockThread;

    public GameClock(int startHours) {
    	this.gameHours = startHours; // เริ่มต้นที่ 06:00 AM
        this.gameMinutes = 0;
        this.running = true;
        clockThread = new Thread(this);
        clockThread.start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(10); // 5 วินาทีจริง
                updateTime();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private synchronized void updateTime() {
    	if(gameMinutes == 59) {
    		gameHours = (gameHours + 1) % 24;
    	}
        gameMinutes = (gameMinutes + 1) % 60;
    }

    public synchronized void render(GraphicsContext gc, double x, double y) {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 20));
        String timeText = String.format("%02d:%02d", gameHours, gameMinutes);
        gc.fillText("Time: " + timeText, x, y);
    }

    public synchronized int getGameHours() {
        return gameHours;
    }

    public void stopClock() {
        running = false;
    }
}

