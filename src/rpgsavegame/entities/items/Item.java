package rpgsavegame.entities.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import rpgsavegame.Handler;
import rpgsavegame.gfx.Assets;

public class Item{
	
	//Handler
	
	public static Item[] items = new Item[256];
	public static Item healthBox = new HealthBook(Assets.medKit, "Healing Spell", 0);
	public static Item regenBook = new Item(Assets.medKit, "Regen Spell", 1);
	
	//Class
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32; 
		
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	protected boolean doneRender= false;
	protected String description = "no description";
	protected ItemDescription itemDescription;
	
	protected Rectangle bounds;
	
	protected int x, y;
	
	protected boolean pickedUp = false;
	
	public Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id= id;
		x = 1000;
		y = 700;
		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		items[id] = this;
	}
	
	public void tick() {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(bounds)) {
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
	}
	}
	
	public void render(Graphics g) {
		render(g, x, y);
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}
	
	public void renderInInventory(Graphics g, int x, int y) {
		g.drawImage(texture, x ,y, 130, 130, null);
	}
	
	public Item createNew(int x, int y) {
		Item i = new Item(texture, name, id);
		i.setPos(x, y);
		return i;
	}
	
	public Item createNew() {
		Item i = new Item(texture, name, id);
		i.setPickedUp(true);
		return i;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	
	
	public void activate(int i) {
		if(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().get(i).id == 0) {
			handler.getWorld().getEntityManager().getPlayer().addHealth(1);
			System.out.println("activated");
		}
		else if(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().get(i).id == 1){
			handler.getWorld().getEntityManager().getPlayer().setRegenActive(true);
			System.out.println("activate regen");
		}
		
	}
	//GETTERS SETTERS
	
	public ItemDescription getItemDescription(int i) {
		if(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().get(i).id == 0)
			return ItemDescription.healthBookDescription;
		else if(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().get(i).id == 1)
			return ItemDescription.regenbookDescription;
		
		return null;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}


	public boolean isDoneRender() {
		return doneRender;
	}

	public void setDoneRender(boolean doneRender) {
		this.doneRender = doneRender;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}






		
}
