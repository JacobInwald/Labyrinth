package rpgsavegame.entities.statics;

import java.awt.Graphics;

import rpgsavegame.Handler;
import rpgsavegame.gfx.Assets;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y);
	}

	public void render(Graphics g) {
		g.drawImage(Assets.tree,(int) (x),(int) (y), width, height,  null);
	}

	public void tick() {
		
	}

}
