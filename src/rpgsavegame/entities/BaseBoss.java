package rpgsavegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import rpgsavegame.Handler;
import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Tiles;

public  class BaseBoss extends Enemy{

	private Arrow arrow1, arrow2, arrow3, arrow4;
	private long meeleeTimer, lastMeeleeTimer = 0, meeleeAttackCoolDown = 1000;
	private long rangedTimer, lastRangedTimer = 0, rangedAttackCooldown = 1600;
	
	public BaseBoss(Handler handler, float x, float y) {
		super(handler, x, y);
		arrow1 = new Arrow(handler, 750, 1000, 0);
		arrow2 = new Arrow(handler, 800, 1000, 0);
		arrow3 = new Arrow(handler, 725, 1000, 0);
		arrow4 = new Arrow(handler, 775, 1000, 0);
		health = 20; 
		
	}
	
	public void meeleeAttack(int dmg) {
		meeleeTimer += System.currentTimeMillis() - lastMeeleeTimer;
		lastMeeleeTimer = System.currentTimeMillis();
		if(meeleeTimer <  meeleeAttackCooldown)
			return;
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 64;
		ar.width = arSize;
		ar.height = arSize;
		
		if(yMove < 0){
			ar.height = 10;
			ar.width = arSize;
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}
		else if(yMove > 0) {
			ar.height = 10;
			ar.width = arSize;
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}
		else if(xMove < 0) {
			ar.height = arSize;
			ar.width = 10;
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else if(xMove > 0) {
			ar.height = arSize;
			ar.width = 10;
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		meeleeTimer = 0;
	
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(Enemy.class.isAssignableFrom(e.getClass()))
				continue;
			if(ar.intersects(e.getCollisionBounds(0, 0))) {
				e.hurt(dmg);
				System.out.println("You were Hit!");
			}
		}
	
	}


	public void rangedAttack(int range, int dmg) {
		rangedTimer += System.currentTimeMillis() - lastRangedTimer;
		lastRangedTimer = System.currentTimeMillis();
		if(rangedTimer <  rangedAttackCooldown)
			return;
		arrow1.reset();
		arrow1.setX(x + 69); 
		arrow1.setY(y + 32);
		
		arrow2.reset();
		arrow2.setX(x - 5); 
		arrow2.setY(y + 32);
		
		arrow3.reset();
		arrow3.setX(x + 32); 
		arrow3.setY(y + 69);
		
		arrow4.reset();
		arrow4.setX(x + 32); 
		arrow4.setY(y - 5);
		
		arrow1.setXMove(4);
		arrow2.setXMove(-4);
		arrow3.setYMove(4);
		arrow4.setYMove(-4);
		
		rangedTimer = 0;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)x, (int)y, width, height, null);
		arrow1.render(g);
		arrow2.render(g);
		arrow3.render(g);
		arrow4.render(g);
	}

	@Override
	public void tick() {
		seekPlayer();
		move();
		rangedAttack(10, 1);
		meeleeAttack(2);
		arrow1.tick();
		arrow2.tick();
		arrow3.tick();
		arrow4.tick();
	}

	@Override
	public void seekPlayer() {
		double hyp = Math.sqrt(Math.pow(handler.getWorld().getEntityManager().getPlayer().getX() - this.x, 2) + Math.pow(handler.getWorld().getEntityManager().getPlayer().getY() - this.y, 2) );
		double time = hyp / 2;
		xMove = (float)((handler.getWorld().getEntityManager().getPlayer().getX() - this.x) / time);
		yMove = (float)((handler.getWorld().getEntityManager().getPlayer().getY() - this.y) / time);
		
		
	}
} 
