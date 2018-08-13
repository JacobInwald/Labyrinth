package rpgsavegame.tilemaps.tiles;

import java.awt.image.BufferedImage;

import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Tiles;

public class TopGrassRightTile extends Tiles{

	public TopGrassRightTile(int id) {
		super(Assets.quarterGrassTopRight, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return false;
	}
}

