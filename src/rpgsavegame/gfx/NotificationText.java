package rpgsavegame.gfx;

import java.awt.Color;
import java.awt.Graphics;

public class NotificationText {

	private String text = "null";
	private boolean active = false;
	public static NotificationText healthBookPickUp = new NotificationText("HEALTH BOOK PICKED UP");
	public static NotificationText regenBookPickUp = new NotificationText("REGEN BOOK PICKED UP");
	public static NotificationText healthBookUsed = new NotificationText("HEALTH BOOK USED");
	public static NotificationText regenBookUsed = new NotificationText("REGEN BOOK USED");
	private int renderTick = 0;
	
	public NotificationText(String string) {
		this.text = string;
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
		
		if(renderTick <= 180) {
			Text.drawString(g, text, 20, 620, false, Color.WHITE, Assets.monoFont15);
			renderTick++;
			}
		else {	
			renderTick = 0;
			}
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public int getRenderTick() {
		return renderTick;
	}
	
	
	
}
