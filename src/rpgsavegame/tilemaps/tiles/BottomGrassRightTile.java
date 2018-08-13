package rpgsavegame.tilemaps.tiles;

import java.awt.image.BufferedImage;

import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Tiles;

public class BottomGrassRightTile extends Tiles{

	public BottomGrassRightTile(int id) {
		super(Assets.quarterGrassBottomRight, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return false;
	}
}

