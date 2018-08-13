package rpgsavegame.entities.enemies;

import java.awt.Graphics;
import java.util.Random;

import rpgsavegame.Handler;
import rpgsavegame.entities.Arrow;
import rpgsavegame.entities.Enemy;
import rpgsavegame.entities.items.Item;
import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Tiles;

public class RangedEnemy extends Enemy{

	protected Arrow arrow;
	protected Random randint = new Random();
	protected long attackCooldown = 1600, attackTimer = attackCooldown, lastAttack;
	
	public RangedEnemy(Handler handler, float x, float y) {
		super(handler, x, y);
		arrow = new Arrow(handler, 700, 1000, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)x, (int)y, null);
		arrow.render(g);
	}

	@Override
	public void tick() {
		
		handler.getWorld().areEnemiesActive = true;
		if(!active) {
			spawn();
			handler.getWorld().areEnemiesActive = false;
			}
		arrow.tick();
		move();
		seekPlayer();
		rangedAttack();

	}
	
	public void rangedAttack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer <  attackCooldown)
			return;
		if(handler.getWorld().getEntityManager().getPlayer().getX() - this.x >= Tiles.TILEWIDTH * 3 || handler.getWorld().getEntityManager().getPlayer().getY() - this.y >= Tiles.TILEHEIGHT * 3)
			return;
		arrow.reset();
		arrow.setX(x);
		arrow.setY(y);
		double hyp = Math.sqrt(Math.pow(handler.getWorld().getEntityManager().getPlayer().getX() - this.x, 2) + Math.pow(handler.getWorld().getEntityManager().getPlayer().getY() - this.y, 2) );
		double time = hyp / arrow.DEFAULT_CREATURE_SPEED;
		arrow.setXMove((float)((handler.getWorld().getEntityManager().getPlayer().getX() - this.x) / time));
		arrow.setYMove((float)((handler.getWorld().getEntityManager().getPlayer().getY() - this.y) / time));
		attackTimer = 0;
		}

	@Override
	public void seekPlayer() {
		if(handler.getWorld().getEntityManager().getPlayer().getX() - this.x  <= Tiles.TILEWIDTH * 2 && handler.getWorld().getEntityManager().getPlayer().getX() - this.x >= -Tiles.TILEWIDTH * 2 ) {
			xMove = 0;
		}
		else if(handler.getWorld().getEntityManager().getPlayer().getX() - this.x  > Tiles.TILEWIDTH * 2 || handler.getWorld().getEntityManager().getPlayer().getX() - this.x < -Tiles.TILEWIDTH * 2 ) {
			seekPlayerX();
		}
		
		if(handler.getWorld().getEntityManager().getPlayer().getY() - this.y  <= Tiles.TILEHEIGHT * 2 && handler.getWorld().getEntityManager().getPlayer().getY() - this.y >= -Tiles.TILEHEIGHT * 2 ) {
			yMove = 0;
		}
		else if(handler.getWorld().getEntityManager().getPlayer().getY() - this.y > Tiles.TILEHEIGHT * 2 || handler.getWorld().getEntityManager().getPlayer().getY() - this.y  <  -Tiles.TILEHEIGHT * 2 ) {
			seekPlayerY();
		}
	}
	
	public void die() {
		int i = randint.nextInt(10);
		handler.getWorld().areEnemiesActive = false;
		if(i <= 3 && active)
			handler.getWorld().getItemManager().addItem(Item.regenBook.createNew((int)x, (int)y));
		//if(i <=2 && active)
			//handler.getWorld().getItemManager().addItem(Item.regenBook.createNew((int)x, (int)y));
		active = false;
	}
	
}
