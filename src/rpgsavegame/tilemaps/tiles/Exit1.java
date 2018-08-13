package rpgsavegame.tilemaps.tiles;

import java.awt.Rectangle;

import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Rooms;
import rpgsavegame.tilemaps.Tiles;

public class Exit1 extends Tiles {
	private int xPos, yPos;
	private Rectangle bounds;
	
	public Exit1(int id) {
		super(Assets.Exit1, id);
		xPos = (15 - 1) * Tiles.TILEWIDTH;
		yPos = (10 / 2 - 1) * Tiles.TILEHEIGHT;
		bounds = new Rectangle(xPos, yPos, Tiles.TILEWIDTH, Tiles.TILEHEIGHT * 2);
	}
	
	public Rectangle getCollisionBounds() {
		return new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	public void setSolid(boolean x) {
		this.solid = x;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
}
