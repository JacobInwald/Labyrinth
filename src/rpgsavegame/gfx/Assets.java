package rpgsavegame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 64, height = 64;
	private static final int uiObjectWidth = 64, uiObjectHeight = 64;
	
	public static Font monoFont27, monoFont15;
	
	public static BufferedImage medKit, player, blankTile, blackTile, sandTile, Exit0, Exit1, Exit2, Exit3, tree, uArrow, dArrow, lArrow, rArrow, knight_forward_standing_neutral, hpBar;
	public static BufferedImage grassFull, dirt, halfGrassUp, halfGrassLeft, halfGrassRight, halfGrassDown, quarterGrassTopRight, quarterGrassTopLeft, quarterGrassBottomLeft, quarterGrassBottomRight;
	public static BufferedImage[] knightLeft, knightRight, knightUp, knightDown;
	public static BufferedImage inventoryScreen;
	
	
	public static void init() {
		

		monoFont27 = FontLoader.loadFont("res/fonts/VCR_OSD_MONO_1.001.ttf", 27);
		monoFont15 = FontLoader.loadFont("res/fonts/VCR_OSD_MONO_1.001.ttf", 15);
		
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_models.png"));
		SpriteSheet	placeHolderTileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/placeholdertiles.png"));
		SpriteSheet staticsSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static_entities.png"));
		SpriteSheet arrow_Sprites = new SpriteSheet(ImageLoader.loadImage("/textures/arrow_spritesheet.png"));
		SpriteSheet Knight_sprite = new SpriteSheet(ImageLoader.loadImage("/textures/Knight_Sprites.png"));
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Tile_Textures.png"));
		SpriteSheet itemSheet = new SpriteSheet(ImageLoader.loadImage("/textures/HealthBook.png"));
		
		player = playerSheet.crop(0, 0, width, height);
		inventoryScreen = ImageLoader.loadImage("/textures/inventory.png");	
		
		
		knight_forward_standing_neutral = Knight_sprite.crop(0, 0 , width, height);
		
		knightDown = new BufferedImage[4];
		knightDown[0] = Knight_sprite.crop(0, 0, width, height);
		knightDown[1] = Knight_sprite.crop(0, height * 2, width, height);
		knightDown[2] = Knight_sprite.crop(width, 0, width, height);
		knightDown[3] = Knight_sprite.crop(width, height * 2, width, height);
	
		knightUp = new BufferedImage[2];
		knightUp[0] = Knight_sprite.crop(width * 2, 0, width, height);
		knightUp[1] = Knight_sprite.crop(width * 3, 0, width, height);
		
		knightLeft = new BufferedImage[2];
		knightLeft[0] = Knight_sprite.crop(0, height, width, height);
		knightLeft[1] = Knight_sprite.crop(width, height, width, height);
		
		knightRight = new BufferedImage[2];	
		knightRight[0] = Knight_sprite.crop(width * 2, height, width, height);
		knightRight[1] = Knight_sprite.crop(width * 3, height, width, height);
		
		grassFull =  tileSheet.crop(0, 0, width, height);
		dirt =  tileSheet.crop(width, 0, width, height);
		halfGrassDown =  tileSheet.crop(width * 2, 0, width, height);
		halfGrassLeft =  tileSheet.crop(width * 3, 0, width, height);
		halfGrassUp =  tileSheet.crop(width * 4, 0, width, height);
		halfGrassRight =  tileSheet.crop(width * 5, 0, width, height);
		quarterGrassBottomLeft =  tileSheet.crop(width * 6, 0, width, height);
		quarterGrassTopLeft  =  tileSheet.crop(width * 7, 0, width, height);
		quarterGrassTopRight =  tileSheet.crop(width * 8, 0, width, height);
		quarterGrassBottomRight =  tileSheet.crop(width * 9, 0, width, height);
		
		blackTile = placeHolderTileSheet.crop(0, 0, width, height);	
		blankTile = placeHolderTileSheet.crop(width, 0, width, height);
		sandTile = placeHolderTileSheet.crop(width * 2, 0 , width, height);
		Exit0 = placeHolderTileSheet.crop(width*3, 0, width, height);
		Exit1 = placeHolderTileSheet.crop(width*4, 0, width, height);
		Exit2 = placeHolderTileSheet.crop( 0, height, width, height);
		Exit3 = placeHolderTileSheet.crop(width, height, width, height);
		
		uArrow = arrow_Sprites.crop(0, 0, 16, 16);
		dArrow = arrow_Sprites.crop(16, 0, 16, 16);
		lArrow = arrow_Sprites.crop(16 * 2, 0, 16, 16);
		rArrow = arrow_Sprites.crop(16 * 3, 0, 16, 16);
		tree = staticsSheet.crop(0, 0, width, height);
		hpBar = placeHolderTileSheet.crop(width * 2, height, width, height);
		
		medKit = itemSheet.crop(0, 0, width , height);
	}
}
