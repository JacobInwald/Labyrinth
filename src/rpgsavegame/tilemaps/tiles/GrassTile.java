package rpgsavegame.tilemaps.tiles;

import java.awt.image.BufferedImage;

import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Tiles;

public class GrassTile extends Tiles{

	public GrassTile(int id) {
		super(Assets.grassFull, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return false;
	}
}

