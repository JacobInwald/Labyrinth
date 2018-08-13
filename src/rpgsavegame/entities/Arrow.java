package rpgsavegame.entities;

import java.awt.Graphics;

import rpgsavegame.Handler;
import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Tiles;

public class Arrow extends Creatures{

	protected int type;
	
	public Arrow(Handler handler, float x, float y, int type) {
		super(handler, x, y, 16, 16);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		if(xMove > 0)
			g.drawImage(Assets.rArrow, (int) (x), (int)(y), width, height, null);
		else if(xMove < 0)
			g.drawImage(Assets.lArrow, (int) (x), (int)(y), width, height, null);
		if(yMove > 0)
			g.drawImage(Assets.dArrow, (int) (x), (int)(y), width, height, null);
		else if(yMove < 0)
			g.drawImage(Assets.uArrow, (int) (x), (int)(y), width, height, null);
	}

	@Override
	public void tick() {
		if(type == 1)
			checkHitsPlayer();
		else if(type == 0)
			checkHitsEnemy();
		x += xMove;
		y += yMove;
	}



	public void reset() {
		x = 1000;
		y = 700;
		yMove = 0;
		xMove = 0;
	}
	
	
	
	public void checkArrowRange(float xPos, float yPos) {
		if(xPos- x  >= 5 * Tiles.TILEWIDTH || xPos - x <= -(5 * Tiles.TILEWIDTH)
			|| yPos - y >= 3 * Tiles.TILEHEIGHT || yPos - y <= -(3 * Tiles.TILEHEIGHT)) {
			reset();
		}
	}	
	
	private void checkHitsEnemy() {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(Enemy.class.isAssignableFrom(e.getClass()))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(this.getCollisionBounds(0, 0))) {
				e.hurt(2);
				reset();
				return;
			}
		}
		
	}
	
	public void checkHitsPlayer(){
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e == this || e == handler.getWorld().getEntityManager().getPlayer())
				continue;
			if(e.getCollisionBounds(0, 0).intersects(this.getCollisionBounds(0, 0))) {
				e.hurt(4);
				reset();
				System.out.println("ranged attack");
				return;
			}
		}
		
	}
	
	public void setXMove(float tX) {
		xMove = tX;
	}
	public void setYMove(float tY) {
		yMove = tY;
	}
}
