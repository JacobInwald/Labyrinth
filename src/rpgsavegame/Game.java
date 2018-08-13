package rpgsavegame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import rpgsavegame.display.Display;
import rpgsavegame.gfx.Assets;
import rpgsavegame.input.KeyManager;
import rpgsavegame.states.GameState;
import rpgsavegame.states.State;

public class Game implements Runnable {

	private int width, height;
	private Thread thread;
	private boolean running = false;
	private String title;
	
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	
	private Handler handler;
	private State gameState;
	
	private KeyManager keyManager;
	
	
	public Game(int width, int height, String title) {
		this.height = height;
		this.width = width;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		handler = new Handler(this);
		gameState = new GameState(handler);
		State.setCurrentState(gameState);
		
	}
	
	private void tick() {
		keyManager.tick();
		
		if(State.getCurrentState() != null)
			State.getCurrentState().tick();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		State.getCurrentState().render(g);
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps; 
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				delta--;
				ticks++;
				}
			if(timer >= 1000000000) {
//				System.out.println("Ticks and Frames:" + ticks);
				timer = 0L;
				ticks = 0;
			}
		}
	}
	
	public synchronized void start() {
		if (running) 
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	
}
