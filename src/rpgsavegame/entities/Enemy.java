package rpgsavegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import rpgsavegame.Handler;
import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Tiles;

public abstract class Enemy extends Creatures{

	protected long lastAttackTimer, meeleeAttackCooldown = 800, attackTimer = meeleeAttackCooldown, rangedAttackCooldown = 1600, rangedAttackTimer = rangedAttackCooldown;
	private Random randX;
	
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y, Creatures.DEFAULT_CREATURE_WIDTH, Creatures.DEFAULT_CREATURE_HEIGHT);
		randX = new Random();

	}

	@Override
	public abstract void render(Graphics g);
	@Override
	public abstract void tick();

	
	public abstract void seekPlayer();
	
	public void seekPlayerX() {
		if(handler.getWorld().getEntityManager().getPlayer().x != this.x &&  handler.getWorld().getEntityManager().getPlayer().x != this.x - 1 &&  handler.getWorld().getEntityManager().getPlayer().x != this.x + 1) {
			yMove = 0;
			if(handler.getWorld().getEntityManager().getPlayer().x - this.x > 0)
				xMove = 2;
			else if (handler.getWorld().getEntityManager().getPlayer().x - this.x < 0)
				xMove = -2;
			else {
				xMove = 0;
				this.x = handler.getWorld().getEntityManager().getPlayer().x;
			}
		}
	}
	public void seekPlayerY() {
	if(handler.getWorld().getEntityManager().getPlayer().y != this.y + 1 &&  handler.getWorld().getEntityManager().getPlayer().y != this.y - 1 &&  handler.getWorld().getEntityManager().getPlayer().y != this.y) {
		xMove = 0;
		if(handler.getWorld().getEntityManager().getPlayer().y - this.y > 0)
			yMove = 2;
		else if(handler.getWorld().getEntityManager().getPlayer().y - this.y < 0)
			yMove = -2;
		}
		else {
			yMove = 0;
			this.y = handler.getWorld().getEntityManager().getPlayer().y;
		}
	}
	
	public void spawn() {
	 int tX;
	 tX = randX.nextInt(10 * Tiles.TILEWIDTH);
	 x = tX + 2 * Tiles.TILEWIDTH; 
	 
	 int tY;
	 tY = randX.nextInt(7 * Tiles.TILEHEIGHT);
	 y = tY + 2 * Tiles.TILEHEIGHT; 
	}
	
	public void die() {
		active = false;
		Tiles.Exit0.setSolid(false);
		Tiles.Exit1.setSolid(false);
		Tiles.Exit2.setSolid(false);
		Tiles.Exit3.setSolid(false);
		spawn();
	}
}
	
