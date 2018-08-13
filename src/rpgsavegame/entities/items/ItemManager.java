package rpgsavegame.entities.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import rpgsavegame.Handler;
import rpgsavegame.gfx.NotificationText;

public class ItemManager {

	
	private int popupType = -1;
	private Handler handler;
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public ItemManager(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item i = it.next();
			i.tick();
			if(i.pickedUp) {
				popupType = i.getId();
				it.remove();
				}
		}
	}
	
	public void render(Graphics g) {
		for(Item i : items)
			i.render(g);
		if(popupType == 0) {
			NotificationText.healthBookPickUp.setActive(true);
			NotificationText.healthBookPickUp.render(g);
			if(NotificationText.healthBookPickUp.getRenderTick() >= 181) {
				NotificationText.healthBookPickUp.setActive(false);
				popupType = -1;
			}
		}
		else if(popupType == 1) {
			NotificationText.regenBookPickUp.setActive(true);
			NotificationText.regenBookPickUp.render(g);
			if(NotificationText.regenBookPickUp.getRenderTick() >= 181) {
				NotificationText.regenBookPickUp.setActive(false);
				popupType = -1;
			}
		}
	}
	
	public void addItem(Item i) {
		i.setHandler(handler);
		items.add(i);
		}

	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	
	
}
