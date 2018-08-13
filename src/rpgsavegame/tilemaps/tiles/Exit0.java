package rpgsavegame.tilemaps.tiles;

import java.awt.Rectangle;

import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Tiles;

public class Exit0 extends Tiles {
	private int xPos, yPos;
	private Rectangle bounds;
	
	public Exit0(int id) {
		super(Assets.Exit0, id);
		xPos = 6 * Tiles.TILEWIDTH;
		yPos = 0;
		bounds = new Rectangle(xPos, yPos, Tiles.TILEWIDTH * 2, Tiles.TILEHEIGHT);
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
