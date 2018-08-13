package rpgsavegame.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	 private int index, speed;
	 private long lastTime, timer;
	 private BufferedImage[] frames;
	 
	public Animation(int speed, BufferedImage[] frames) {
	  this.speed = speed;
	  this.frames = frames;
	  index = 0;
	  timer = 0;
	  lastTime = System.currentTimeMillis();
	 }

	 public void tick() {
	  //Gives the time in milli that has passed since the update has been called, and the previous update.
	  timer += System.currentTimeMillis() - lastTime;
	  lastTime = System.currentTimeMillis();

	  if (timer > speed) {
	   index++;
	   timer = 0;
	   if (index >= frames.length) // Restart the animation.
	    index = 0;
	  }
	 }

	 public BufferedImage getCurrentImage() {
	  return frames[index];
	 }


}