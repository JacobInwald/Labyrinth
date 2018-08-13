package rpgsavegame.tilemaps.tiles;

import java.awt.image.BufferedImage;

import rpgsavegame.gfx.Assets;
import rpgsavegame.tilemaps.Tiles;

public class TopGrassLeftTile extends Tiles{

	public TopGrassLeftTile(int id) {
		super(Assets.quarterGrassTopLeft, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSolid() {
		return false;
	}
}

