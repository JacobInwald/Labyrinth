package rpgsavegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, left, right , z , x;
	public int lastArrowKeyPress;
	
	
	public KeyManager() {
		keys = new boolean[256];
		cantPress = new boolean[keys.length];
		justPressed = new boolean[keys.length];
		lastArrowKeyPress = KeyEvent.VK_DOWN;
	}
	
	public void tick() {
		for(int i = 0; i < keys.length; i++) {
			if(cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			}else if(justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}
		
		
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		z = keys[KeyEvent.VK_Z];
		x = keys[KeyEvent.VK_X];
		}
	
	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
				return false;
		return justPressed[keyCode];
		}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() > 256)
			return;
		keys[e.getKeyCode()] = true;
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		
	}

}
