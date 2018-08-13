package rpgsavegame.tilemaps.tiles;

import java.awt.image.BufferedImage;

import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Tiles;

public class DirtTile extends Tiles{

	public DirtTile(int id) {
		super(Assets.dirt, id);
		// TODO Auto-generated constructor stub
	}

	public boolean isSolid() {
		return true;
	}
	
}
