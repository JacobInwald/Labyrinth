package rpgsavegame;


public class Launcher {
	private final static int width = 960;
	private final static int height = 640;
	
	public static void main(String[] args){
		Game game = new Game(width, height, "The Labyrinth");
		game.start();
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
	
	
}
