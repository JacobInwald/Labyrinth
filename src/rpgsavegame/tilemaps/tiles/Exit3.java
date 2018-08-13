package rpgsavegame.tilemaps.tiles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Rooms;
import rpgsavegame.tilemaps.Tiles;

public class Exit3 extends Tiles {
	private int xPos, yPos;
	private Rectangle bounds;
	
	public Exit3(int id) {
		super(Assets.Exit3, id);
		xPos = 0;
		yPos = (10 / 2 -1) * Tiles.TILEHEIGHT;
		bounds = new Rectangle(xPos, yPos, Tiles.TILEWIDTH, Tiles.TILEHEIGHT);
	}
	
	public Rectangle getCollisionBounds() {
		return new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}

	public void setSolid(boolean b) {
		this.solid = b;
		
	}
}
