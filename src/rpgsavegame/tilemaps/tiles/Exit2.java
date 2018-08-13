package rpgsavegame.tilemaps.tiles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Rooms;
import rpgsavegame.tilemaps.Tiles;

public class Exit2 extends Tiles {
	private int xPos, yPos;
	private Rectangle bounds;
	
	public Exit2(int id) {
		super(Assets.Exit2, id);
		xPos = (15 / 2 - 1 ) * Tiles.TILEWIDTH;
		yPos = 9 * Tiles.TILEHEIGHT;
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
