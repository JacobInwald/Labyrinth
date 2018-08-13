package rpgsavegame.entities;

import rpgsavegame.Handler;
import rpgsavegame.tilemaps.Tiles;

public abstract class Creatures extends Entity{


	public static final float DEFAULT_CREATURE_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;
	
	protected float speed;
	protected float xMove, yMove;
	
	public Creatures(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_CREATURE_SPEED;
		xMove = yMove = 0;
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f))
			moveX();
		if(!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	public void moveX() {
		if(xMove > 0) {
			int tx = (int) (x + xMove + bounds.width) / Tiles.TILEWIDTH;
			
			if(!getTileCollision(tx, (int)(y + bounds.y) / Tiles.TILEHEIGHT) &&
				!getTileCollision(tx , (int)(y + bounds.y) / Tiles.TILEHEIGHT)	) 
				x += xMove;
			else 
				x = tx * Tiles.TILEWIDTH - bounds.width - bounds.x - 1;
			}
		else if(xMove < 0) {
			int tx = (int) (x + xMove) / Tiles.TILEWIDTH;
			
			if(!getTileCollision(tx, (int)(y + bounds.y) / Tiles.TILEHEIGHT) &&
					!getTileCollision(tx, (int)(y + bounds.y + bounds.height) / Tiles.TILEHEIGHT))
				x += xMove;
			else
				x = tx * Tiles.TILEWIDTH + Tiles.TILEWIDTH - bounds.x;
			
		}
	}
	
	public void moveY() {
		if(yMove > 0) {
			int ty = (int) (y + yMove + bounds.height) / Tiles.TILEHEIGHT;
			
			if(!getTileCollision((int) (x + bounds.x + bounds.width) / Tiles.TILEWIDTH, ty) &&
					!getTileCollision((int)(x + bounds.x) / Tiles.TILEWIDTH, ty))
				y += yMove;
			else
				y = ty * Tiles.TILEHEIGHT - bounds.width - bounds.y - 1;
		}
		else if(yMove < 0) {
			int ty = (int) (y + yMove) / Tiles.TILEHEIGHT;
			
			if(!getTileCollision((int)(x + bounds.x) / Tiles.TILEWIDTH, ty) &&
					!getTileCollision((int)(x + bounds.x + bounds.width)/ Tiles.TILEWIDTH, ty))
				y += yMove;
			else
				y = ty * Tiles.TILEHEIGHT + Tiles.TILEHEIGHT - bounds.y;
				
		}
		
	}
	
	public boolean getTileCollision(int x, int y) {
		return handler.getWorld().getCurrentRoom().getTile(x, y).isSolid();
	}
}
