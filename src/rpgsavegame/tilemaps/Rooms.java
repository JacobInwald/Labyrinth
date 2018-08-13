package rpgsavegame.tilemaps;

import java.awt.Graphics;

import rpgsavegame.Handler;
import rpgsavegame.Launcher;
import rpgsavegame.tilemaps.rooms.Room1;
import rpgsavegame.utils.Utils;

public class Rooms {
	
	protected static final int width = 16;
	protected static final int height = 11;
	protected int id;
	protected int[][] tiles;
	protected static Handler handler;
	
	public static Rooms[] rooms = new Rooms[10];
	public static Rooms room1 = new Room1(0);
	
	public Rooms( String path, int id) {
		rooms[0] = room1;
		this.id = id;
		loadRoom(path);
	}
	
	public void render(Graphics g) {
		int xStart = 0;
		int xEnd = (int) Math.min(width, Launcher.getWidth() / Tiles.TILEWIDTH );
		int yStart = 0;
		int yEnd = (int) Math.min(height, Launcher.getHeight() / Tiles.TILEHEIGHT );
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x,y).render(g, x * Tiles.TILEWIDTH, y * Tiles.TILEHEIGHT);
			}
		}
	}
	
	
	
	public Tiles getTile(int x, int y) {
		if(x < 0 || y < 0 || x> width || y > height) {
			return Tiles.grass;
		}
		
		Tiles t = Tiles.tiles[tiles[x][y]];
		if(t == null) {
			return Tiles.grass;
		}
		return t;
		
	}
	
	private void loadRoom(String path) {
		String file = Utils.loadFileAsString(path);
		String[]tokens = file.split("\\s+");
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width)]);
			}	
		}
	}
		
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
	
}
