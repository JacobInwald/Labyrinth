package rpgsavegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import rpgsavegame.Handler;
import rpgsavegame.tilemaps.Tiles;

public abstract class Entity {

	public static final int DEFAULT_HEALTH = 10;
	protected Handler handler;
	protected float y;
	protected float x;
	protected boolean active = true;
	protected int width, height;
	protected int health;
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = x;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public void hurt(int amt) {
		health -= amt;
		if(health <= 0) {
			die();
		}
	}
	
	public void die() {
		setActive(false);
	}

	///GETTERS AND SETTERS
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public boolean isActive() {
		return active;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getHealth() {
		return health;
	}
	
	
	
}
