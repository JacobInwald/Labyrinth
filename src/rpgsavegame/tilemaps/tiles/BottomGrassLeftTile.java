package rpgsavegame.tilemaps.tiles;

import java.awt.image.BufferedImage;

import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Tiles;

public class BottomGrassLeftTile extends Tiles{

	public BottomGrassLeftTile(int id) {
		super(Assets.quarterGrassBottomLeft, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return false;
	}
}

