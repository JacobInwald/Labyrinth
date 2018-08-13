package rpgsavegame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import rpgsavegame.Handler;
import rpgsavegame.entities.items.HealthBook;
import rpgsavegame.entities.items.Item;
import rpgsavegame.gfx.Assets;
import rpgsavegame.gfx.NotificationText;
import rpgsavegame.gfx.Text;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	private int notificationType = -1;
	
	private int invWidth = 960, invHeight = 640;
	private	int	invListCentreX = 225,
				invListCentreY = 150;
	private int invListSpacingX = 310,
				invListSpacingY = 160;
	private int invDescriptionBoxCentreX = 712,
				invDescriptionBoxCentreY = 292;
	private int invItemBoxX = 755,
				invItemBoxY = 55;
	
	private int selectedItem = 0;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>(); 
		for(Item i: inventoryItems)
			i.setHandler(handler);
	}

	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C))
			active = !active;
		if(!active) 
			return;
		
	
			
		
		int len = inventoryItems.size();
		if(len == 0)
			return;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_A))
			selectedItem --;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_D))
			selectedItem++;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			selectedItem -= 2;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			selectedItem += 2;
	
		if(selectedItem < 0)
			selectedItem = 0;
		if(selectedItem >= len)
			selectedItem = len -1;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
			inventoryItems.get(selectedItem).activate(selectedItem);
			inventoryItems.remove(selectedItem);
			notificationType = selectedItem;
		}
		
	}
	
	public void addItem(Item item) {
	if(inventoryItems.size() >= 6)
			return;
		inventoryItems.add(item);
		}
	
	public void render(Graphics g) {
		if(notificationType == 0) {
			NotificationText.healthBookUsed.setActive(true);
			NotificationText.healthBookUsed.render(g);
			if(NotificationText.healthBookUsed.getRenderTick() >= 181) {
				NotificationText.healthBookUsed.setActive(false);
				notificationType = -1;
			}
		}
		if(notificationType == 1) {
			NotificationText.regenBookUsed.setActive(true);
			NotificationText.regenBookUsed.render(g);
			if(NotificationText.regenBookUsed.getRenderTick() >= 181) {
				NotificationText.regenBookUsed.setActive(false);
				notificationType = -1;
			}
		}
				if(!active)
			return;
		g.drawImage(Assets.inventoryScreen, 0, 0, 960, 640, null);
	
		Color nameColor = Color.WHITE;
		
		int len = inventoryItems.size();
		if(len == 0)
			return;


		if(selectedItem < 0)
			selectedItem = 0;
		if(selectedItem >= len)
			selectedItem = len -1;
		
		for(Item i : inventoryItems) {
			i.setDoneRender(false);
		}
		


		inventoryItems.get(selectedItem).renderInInventory(g, invItemBoxX, invItemBoxY);
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 2; x++) {
				for(int i = 0; i < len; i++) {	
					if(inventoryItems.get(i).isDoneRender()) 
						continue;
					if(i == selectedItem)
						nameColor = Color.RED;
					Text.drawString(g, inventoryItems.get(i).getName(), invListCentreX + x * invListSpacingX, invListCentreY + y * invListSpacingY,  true,  nameColor, Assets.monoFont27);	
					inventoryItems.get(i).getItemDescription(selectedItem).render(g, invDescriptionBoxCentreX, invDescriptionBoxCentreY);
					inventoryItems.get(i).setDoneRender(true);
					nameColor = Color.WHITE;
					i = len - 1;
				}
			}
		}

	}
	

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Item> getInventoryItems() {
		return inventoryItems;
	}
	
}
