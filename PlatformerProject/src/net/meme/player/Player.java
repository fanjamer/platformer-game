package net.meme.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import net.meme.Display;
import net.meme.Game;

public class Player {
	
	public static final float jumpSpeed = 5f;
	public static final float maxGrav = 8f;
	public static final float startGrav = 1f;
	public static final float spd = 2f;
	
	public float x = 0, y = 0;
	public float curGrav = startGrav;
	public boolean cameraMoving = false;
	public boolean falling = true;
	public Rectangle hitbox;
	
	public boolean canLeft = true, canRight = true, canJump = true;
	public boolean jumping = false;
	
	public void tick(Game game){
		if(game.input.keys[KeyEvent.VK_A] && canLeft) x-=spd;
		if(game.input.keys[KeyEvent.VK_D] && canRight) x+=spd;
		if(game.input.keys[KeyEvent.VK_SPACE] && canJump){
			jumping = true;
			y-=jumpSpeed;
		}
		
		if(jumping && falling == true){
			y-=jumpSpeed;
		}
		
		if(x < 0) x = 0;
		else if(x+64 > 32*game.map.getWidth()) x = 32*game.map.getWidth()-64;
		
		if(x < 11.5*32){
			hitbox = new Rectangle((int)x, (int)(Math.round(y)), 64, 64);
			cameraMoving = false;
		}else if(x > 32*game.map.getWidth()-13.5*32){
			float edge = (float) (32*game.map.getWidth());
			int dx = (int) (800-(edge-x));
			hitbox = new Rectangle(dx, (int)Math.round(y), 64, 64);
			cameraMoving = false;
		}else{
			hitbox = new Rectangle((int)368, (int)(Math.round(y)), 64, 64);
			cameraMoving = true;
		}
				
		if(falling) gravity();
		else{
			// reset gravity
			canJump = true;
			jumping = false;
			curGrav = startGrav;
		}
		
		falling = true;
	}
	
	public void render(Game game, Graphics g){
		g.setColor(Color.red);
		if(cameraMoving) g.fillRect((int)(368*Display.scale), (int)((y)*Display.scale), (int)(64*Display.scale), (int)(64*Display.scale));
		else{
			float edge = (float) (32*game.map.getWidth());
			int dx = (int) (800-(edge-x));
			if(x < 11.5*32) g.fillRect((int)(x*Display.scale), (int)((y)*Display.scale), (int)(64*Display.scale), (int)(64*Display.scale)); 
			else{ 
				g.fillRect((int) (dx*Display.scale), (int)(Math.round(y)*Display.scale), (int)(64*Display.scale), (int)(64*Display.scale));
			} 
		}
	}
	
	private void gravity(){
		// gravity multiplier
		if(curGrav < maxGrav) curGrav*=1.03f;
		y+=curGrav;
		canJump = false;
	}

}
