package rpgsavegame.entities.statics;

import rpgsavegame.Handler;
import rpgsavegame.entities.Entity;

public abstract class StaticEntity  extends Entity{

	protected final static int DEFAULTENTITYHEIGHT = 64;
	protected final static int DEFAULTENTITYWIDTH = 64; 
	
	public StaticEntity(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULTENTITYWIDTH, DEFAULTENTITYHEIGHT);
	}

}
