package rpgsavegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import rpgsavegame.Handler;
import rpgsavegame.gfx.Animation;
import rpgsavegame.gfx.Assets;
import rpgsavegame.inventory.Inventory;

public class Player extends Creatures{
	private Animation animUp;
	private Animation animDown;
	private Animation animRight;
	private Animation animLeft;
	private BufferedImage hpBar;
	private boolean regenActive = false;
	private int regenTicks = 0,
				regenTimes = 0;
	
	protected Inventory inventory;
	
	protected Arrow arrow;
	private long lastAttackTimer, attackCooldown = 800,rangedAttackCooldown = 2400 ,attackTimer = attackCooldown;
	float lastArrowX, lastArrowY;
	
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creatures.DEFAULT_CREATURE_WIDTH, Creatures.DEFAULT_CREATURE_HEIGHT);
		arrow = new Arrow(handler, 0, 0, 1);

		inventory = new Inventory(handler);
		animUp = new Animation(400, Assets.knightUp);
		animRight = new Animation(400, Assets.knightRight);
		animLeft = new Animation(400, Assets.knightLeft);
		animDown = new Animation(175, Assets.knightDown);
		hpBar = Assets.hpBar;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
		g.drawImage(hpBar, 784, 608, health * 16, 32, null);
		arrow.render(g);
		inventory.render(g);
	}

	@Override
	public void tick() {
		if(regenActive && regenTicks >= 60) {
			addHealth(1);
			regenTicks = 0;
			regenTimes++;
		}
		if(regenTimes >=3) {
			regenTimes = 0;
			regenTicks = 0;
			setRegenActive(false);
		}
		regenTicks++;
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		animDown.tick();
		inventory.tick();
		getInput();
		move();
		checkAttacks();
		arrow.tick();
		arrow.checkArrowRange(lastArrowX, lastArrowY);
	}
	
	public void checkMeelee() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer <  attackCooldown)
			return;
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 64;
		ar.width = 0;
		ar.height = 0;
		if(handler.getKeyManager().z) {
			if(handler.getKeyManager().lastArrowKeyPress == KeyEvent.VK_UP) {
				ar.height = arSize / 2;
				ar.width = arSize;
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y - arSize;
			}else if(handler.getKeyManager().lastArrowKeyPress == KeyEvent.VK_DOWN) {
				ar.height = arSize / 2;
				ar.width = arSize;
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y + cb.height;
			}else if(handler.getKeyManager().lastArrowKeyPress == KeyEvent.VK_LEFT) {
				ar.height = arSize;
				ar.width = arSize / 2;
				ar.x = cb.x - arSize / 2;
				ar.y = cb.y + cb.height / 2 - arSize / 2;			
			}else if(handler.getKeyManager().lastArrowKeyPress == KeyEvent.VK_RIGHT) {
				ar.height = arSize;
				ar.width = arSize / 2;
				ar.x = cb.x + cb.width;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			}
			else {
				return;
			}
		}
		else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e == this)
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				System.out.print("Meelee Hit");
				e.hurt(5);
				return;
				}
		}
		}
				
		public void checkRanged() {
			attackTimer += System.currentTimeMillis() - lastAttackTimer;
			lastAttackTimer = System.currentTimeMillis();
			if(attackTimer <  rangedAttackCooldown)
				return;
			
			if(handler.getKeyManager().x) {
				if(handler.getKeyManager().lastArrowKeyPress == KeyEvent.VK_UP) {
					arrow.y = y - (Creatures.DEFAULT_CREATURE_HEIGHT / 2);
					arrow.x = x;
					lastArrowX = x;
					lastArrowY = y;
					if(arrow.xMove != 0)
						arrow.xMove = 0;
					if(arrow.yMove != 0)
						arrow.yMove = 0;
					arrow.yMove = -3;
				}else if(handler.getKeyManager().lastArrowKeyPress == KeyEvent.VK_DOWN) {
					arrow.y = y + (Creatures.DEFAULT_CREATURE_HEIGHT);
					arrow.x = x;
					lastArrowX = x;
					lastArrowY = y;
					if(arrow.xMove != 0)
						arrow.xMove = 0;
					if(arrow.yMove != 0)
						arrow.yMove = 0;
					arrow.yMove = 3;
					
				}else if(handler.getKeyManager().lastArrowKeyPress == KeyEvent.VK_LEFT) {
					arrow.y = y;
					arrow.x = x - (Creatures.DEFAULT_CREATURE_WIDTH / 2);
					lastArrowX = x;
					lastArrowY = y;
					if(arrow.xMove != 0)
						arrow.xMove = 0;
					if(arrow.yMove != 0)
						arrow.yMove = 0;
					arrow.xMove = -3;
				}else if(handler.getKeyManager().lastArrowKeyPress == KeyEvent.VK_RIGHT) {
					arrow.y = y;
					arrow.x = x +(Creatures.DEFAULT_CREATURE_WIDTH / 2);
					lastArrowX = x;
					lastArrowY = y;
					if(arrow.xMove != 0)
						arrow.xMove = 0;
					if(arrow.yMove != 0)
						arrow.yMove = 0;
					arrow.xMove = 3;
				}
				else {
					return;
					
				}
			}
			else {
				return;
			}
			
			attackTimer = 0;
	}
		
	private BufferedImage getCurrentAnimationFrame() {
			if(xMove < 0) {
				return animLeft.getCurrentImage();
			}else if(xMove > 0) {
				return animRight.getCurrentImage();
			}else if(yMove > 0) {
				return animDown.getCurrentImage();
			}else if(yMove < 0) {
				return animUp.getCurrentImage();
			}
			return Assets.knight_forward_standing_neutral;
		}
		
	public void checkAttacks() {
			checkMeelee();
			checkRanged();
		}
	
	public void getInput() {
		xMove = 0;
		yMove = 0;
		if(handler.getKeyManager().up) {
			yMove = -speed;
			handler.getKeyManager().lastArrowKeyPress = KeyEvent.VK_UP;
		}
		else if(handler.getKeyManager().down) {
			yMove = speed;
			handler.getKeyManager().lastArrowKeyPress = KeyEvent.VK_DOWN;
		}
		else if(handler.getKeyManager().left) {
			xMove = -speed;
			handler.getKeyManager().lastArrowKeyPress = KeyEvent.VK_LEFT;
	}
		else if(handler.getKeyManager().right) {
			xMove = speed;
			handler.getKeyManager().lastArrowKeyPress = KeyEvent.VK_RIGHT;
		}

		}
	
	public void keyLogger() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_A))
			System.out.print("a");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_B))
			System.out.print("b");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C))
			System.out.print("c");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_D))
			System.out.print("d");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			System.out.print("e");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F))
			System.out.print("f");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_G))
			System.out.print("g");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_H))
			System.out.print("h");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I))
			System.out.print("i");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_J))
			System.out.print("j");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_K))
			System.out.print("k");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_L))
			System.out.print("l");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_M))
			System.out.print("m");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N))
			System.out.print("n");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_O))
			System.out.print("o");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P))
			System.out.print("p");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q))
			System.out.print("Q");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R))
			System.out.print("R");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			System.out.print("S");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_T))
			System.out.print("t");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_U))
			System.out.print("u");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_V))
			System.out.print("v");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			System.out.print("w");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_X))
			System.out.print("x");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y))
			System.out.print("y");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Z))
			System.out.print("z");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1))
			System.out.print("1");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2))
			System.out.print("2");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3))
			System.out.print("3");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4))
			System.out.print("4");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5))
			System.out.print("5");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_6))
			System.out.print("6");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_7))
			System.out.print("7");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_8))
			System.out.print("8");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_9))
			System.out.print("9");
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_0))
			System.out.print("0");
	}
	
	
	
	public boolean isRegenActive() {
		return regenActive;
	}

	public void setRegenActive(boolean regenActive) {
		this.regenActive = regenActive;
	}

	public void addHealth(int health) {
		this.health += health;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}

}


