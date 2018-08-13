package rpgsavegame.states;

import java.awt.Graphics;

import rpgsavegame.Handler;

public abstract class State {

	private static State currentState = null;
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public static void setCurrentState(State state) {
		currentState = state;
	}
	
	public static State getCurrentState() {
		return currentState;
	}
}
