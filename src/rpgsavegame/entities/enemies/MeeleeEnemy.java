package rpgsavegame.entities.enemies;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import rpgsavegame.Handler;
import rpgsavegame.entities.Enemy;
import rpgsavegame.entities.Entity;
import rpgsavegame.entities.items.Item;
import rpgsavegame.gfx.Assets;

public class MeeleeEnemy extends Enemy{

	protected Random randint = new Random();
	
	public MeeleeEnemy(Handler handler, float x, float y) {
		super(handler, x, y);
		}

	public void render(Graphics g) {
		g.drawImage(Assets.player,(int) (x),(int) (y), null);
	}
	
	public void tick() {
		handler.getWorld().areEnemiesActive = true;
		if(health <= 0)
			die();
		move();
		meeleeAttack();
		seekPlayer();
		
	}		
	public void meeleeAttack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer <  meeleeAttackCooldown)
			return;
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 64;
		ar.width = arSize;
		ar.height = arSize;
		
		if(yMove < 0){
			ar.height = arSize / 2;
			ar.width = arSize;
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}
		else if(yMove > 0) {
			ar.height = arSize / 2;
			ar.width = arSize;
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}
		else if(xMove < 0) {
			ar.height = arSize;
			ar.width = arSize / 2;
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else if(xMove > 0) {
			ar.height = arSize;
			ar.width = arSize / 2;
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		attackTimer = 0;
	
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(Enemy.class.isAssignableFrom(e.getClass()))
				continue;
			if(ar.intersects(e.getCollisionBounds(0, 0))) {
				e.hurt(1);
				System.out.println("You were Hit!");
			}
		}
	}
	
	public  MeeleeEnemy spawnNew(float x, float y){
		MeeleeEnemy m = new MeeleeEnemy(handler, x, y);
		return m;
	}

	public void seekPlayer() {
		if(handler.getWorld().getEntityManager().getPlayer().getY() != this.y)
			seekPlayerY();
		else if(handler.getWorld().getEntityManager().getPlayer().getX() != this.x)
			seekPlayerX();
		else {
			seekPlayerX();
		}
	}
	
	


	public void die() {
		int i = randint.nextInt(10);
		handler.getWorld().areEnemiesActive = false;
		if(i <= 3 && active)
			handler.getWorld().getItemManager().addItem(Item.healthBox.createNew((int)x, (int)y));
		active = false;
	}
	}

