package rpgsavegame.entities.items;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import rpgsavegame.gfx.Assets;
import rpgsavegame.gfx.Text;

public class ItemDescription {

	public static ItemDescription healthBookDescription = new ItemDescription("The pages are torn and ", "old.It looks like you ", "can only heal yourself ", "once using this book.", "");
	public static ItemDescription regenbookDescription = new ItemDescription("The pages are aged but","whole.It looks like","you can cast one regen","spell with this book", "");
	
	private String firstline = "null";
	private String secondline = "null";
	private String thirdline = "null";
	private String fourthline = "null";
	private String fifthline = "null";
	
	public ItemDescription(String string1, String string2, String string3, String string4, String string5){
		this.firstline = string1;
		this.secondline = string2;
		this.thirdline = string3;
		this.fourthline = string4;
		this.fifthline = string5;
	}
	
	public void render(Graphics g, int xPos, int yPos) {
		Text.drawString(g, firstline, xPos, yPos, false, Color.RED, Assets.monoFont15);
		Text.drawString(g, secondline, xPos, yPos + 15, false, Color.RED, Assets.monoFont15);
		Text.drawString(g, thirdline, xPos, yPos + 30, false, Color.RED, Assets.monoFont15);
		Text.drawString(g, fourthline, xPos, yPos + 45, false, Color.RED, Assets.monoFont15);
		Text.drawString(g, fifthline, xPos, yPos + 60, false, Color.RED, Assets.monoFont15);
	}
	
}
