package rpgsavegame.tilemaps;

import java.awt.Graphics;
import java.util.Random;

import rpgsavegame.Handler;
import rpgsavegame.entities.BaseBoss;
import rpgsavegame.entities.EntityManager;
import rpgsavegame.entities.Player;
import rpgsavegame.entities.enemies.MeeleeEnemy;
import rpgsavegame.entities.enemies.RangedEnemy;
import rpgsavegame.entities.items.ItemManager;
import rpgsavegame.entities.statics.Tree;

public class World {
	
	
	private EntityManager entityManager;
	private Handler handler;
	private Rooms currentRoom;
	private Rooms room;
	private Tree tree;
	private int entityLocation[]; 
	private int roomCount = 0;
	private int amtOfEnemies = 1;
	public boolean areEnemiesActive;

	private ItemManager itemManager;
	
	private Random randint;
	
	private long lastExitTimer, exitCooldown = 750, exitTimer = exitCooldown;
	
	public World(Handler handler) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		currentRoom = Rooms.room1;
		randint = new Random();
		tree = new Tree(handler, 500, 500);
		entityManager.addEntity(tree);
	    spawnEnemy(amtOfEnemies);
	
		
	}
	
	public void render(Graphics g) {
		currentRoom.render(g);
		entityManager.render(g);
		itemManager.render(g);
		}
	
	public void tick() {
		entityManager.tick();
		itemManager.tick();
		exit();
		if(roomCount >= 10)
			amtOfEnemies = 4;
			
	}

	public void spawnEnemy(int amt) {
		int type = 0;
		
		int pAmt = randint.nextInt(amt);
		if(pAmt == 0)
			pAmt++;
		for(int x = 0; x < pAmt; x++) {
			type = randint.nextInt(2);
			int xPos = randint.nextInt(7);
			xPos = (xPos + 1) * Tiles.TILEWIDTH;
			int yPos = randint.nextInt(6);
			yPos = (yPos + 1) * Tiles.TILEWIDTH;
			if(type == 1)
				entityManager.addEntity(new MeeleeEnemy(handler, xPos, yPos));
			else if(type == 0)
				entityManager.addEntity(new RangedEnemy(handler, xPos, yPos));
		}
		
	}
	
	public void exit() {
		exitTimer += System.currentTimeMillis() - lastExitTimer;
		lastExitTimer = System.currentTimeMillis();
		if(exitTimer < exitCooldown)
			return;
		Tiles.Exit0.setSolid(false);
		Tiles.Exit1.setSolid(false);
		Tiles.Exit2.setSolid(false);
		Tiles.Exit3.setSolid(false);
		if(areEnemiesActive) {
			Tiles.Exit0.setSolid(true);
			Tiles.Exit1.setSolid(true);
			Tiles.Exit2.setSolid(true);
			Tiles.Exit3.setSolid(true);
		}
		
		if(entityManager.getPlayer().getCollisionBounds(0,0).intersects(Tiles.Exit0.getCollisionBounds())) {
			entityManager.getPlayer().setX((float)(Tiles.Exit2.getXPos()));
			entityManager.getPlayer().setY((float)(Tiles.Exit2.getYPos() - Tiles.TILEHEIGHT));
			Tiles.Exit2.setSolid(true);
			changeRoom();
			roomCount++;
		}
		else if(entityManager.getPlayer().getCollisionBounds(0,0).intersects(Tiles.Exit2.getCollisionBounds())) {
			entityManager.getPlayer().setX((float)(Tiles.Exit0.getXPos()));
			entityManager.getPlayer().setY((float)(Tiles.Exit0.getYPos() + Tiles.TILEHEIGHT));
			Tiles.Exit0.setSolid(true);
			changeRoom();
			roomCount++;
		}
		else if(entityManager.getPlayer().getCollisionBounds(0,0).intersects(Tiles.Exit3.getCollisionBounds())) {
			entityManager.getPlayer().setX((float)(Tiles.Exit1.getXPos() - Tiles.TILEWIDTH));
			entityManager.getPlayer().setY((float)(Tiles.Exit1.getYPos()));
			Tiles.Exit1.setSolid(true);
			changeRoom();
			roomCount++;
		}
		else if(entityManager.getPlayer().getCollisionBounds(0,0).intersects(Tiles.Exit1.getCollisionBounds())) {
			entityManager.getPlayer().setX((float)(Tiles.Exit3.getXPos() + Tiles.TILEWIDTH));
			entityManager.getPlayer().setY((float)(Tiles.Exit3.getYPos()));
			Tiles.Exit3.setSolid(true);
			changeRoom();
			roomCount++;
		}
		
		exitTimer = 0;
	}
	
	public void changeRoom() {
		int x = randint.nextInt(2);
		if(x == Rooms.room1.id)
			currentRoom = Rooms.room1;
		if(roomCount == 10) {
			entityManager.addEntity(new BaseBoss(handler, 320, 480));
			return;
			}
		spawnEnemy(amtOfEnemies);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Rooms getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Rooms currentRoom) {
		this.currentRoom = currentRoom;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	
}
