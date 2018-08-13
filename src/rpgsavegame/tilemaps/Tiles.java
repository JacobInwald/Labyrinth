package rpgsavegame.tilemaps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import rpgsavegame.tilemaps.tiles.BottomGrassLeftTile;
import rpgsavegame.tilemaps.tiles.BottomGrassRightTile;
import rpgsavegame.tilemaps.tiles.DirtTile;
import rpgsavegame.tilemaps.tiles.Exit0;
import rpgsavegame.tilemaps.tiles.Exit1;
import rpgsavegame.tilemaps.tiles.Exit2;
import rpgsavegame.tilemaps.tiles.Exit3;
import rpgsavegame.tilemaps.tiles.GrassHalfDownTile;
import rpgsavegame.tilemaps.tiles.GrassHalfLeftTile;
import rpgsavegame.tilemaps.tiles.GrassHalfRightTile;
import rpgsavegame.tilemaps.tiles.GrassHalfUpTile;
import rpgsavegame.tilemaps.tiles.GrassTile;
import rpgsavegame.tilemaps.tiles.TopGrassLeftTile;
import rpgsavegame.tilemaps.tiles.TopGrassRightTile;

public class Tiles {
	
	public static Tiles[] tiles = new Tiles[256];
	public static Exit0 Exit0 = new Exit0(0);
	public static Exit1 Exit1 = new Exit1(1);
	public static Exit2 Exit2 = new Exit2(2);
	public static Exit3 Exit3 = new Exit3(3);
	public static Tiles dirt = new DirtTile(4);
	public static Tiles grass = new GrassTile(5);
	public static Tiles halfGrassUp = new GrassHalfUpTile(6);
	public static Tiles halfGrassDown = new GrassHalfDownTile(7);
	public static Tiles halfGrassRight = new GrassHalfRightTile(8);
	public static Tiles halfGrassLeft = new GrassHalfLeftTile(9);
	public static Tiles topGrassRight = new TopGrassRightTile(10);
	public static Tiles topGrassLeft = new TopGrassLeftTile(11);
	public static Tiles bottomGrassRight = new BottomGrassRightTile(12);
	public static Tiles bottomGrassLeft = new BottomGrassLeftTile(13);
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected boolean solid = false;
	protected BufferedImage texture;
	protected int id;
	
	public Tiles(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, null);
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public int getId() {
		return id;
	}
}