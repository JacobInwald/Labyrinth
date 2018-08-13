package rpgsavegame.states;

import java.awt.Graphics;

import rpgsavegame.Handler;
import rpgsavegame.entities.EntityManager;
import rpgsavegame.entities.Player;
import rpgsavegame.tilemaps.Rooms;
import rpgsavegame.tilemaps.World;
import rpgsavegame.tilemaps.rooms.Room1;

public class GameState extends State{

	EntityManager entityManager;
	Player player;
	Rooms testRoom;
	World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler);
		handler.setWorld(world);
	}

	public void tick() {
		world.tick();
	}

	public void render(Graphics g) {
		world.render(g);
	}

}
