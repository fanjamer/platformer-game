package net.meme.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import net.meme.Display;
import net.meme.Game;

public class Player {
	
	public float x = 0, y = -64;
	private float curGrav = 1f;
	public boolean cameraMoving = false;
	public boolean falling = true;
	public Rectangle hitbox;
	private final float jumpSpeed = 3f;
	
	public boolean canLeft = true, canRight = true, canJump = true;
	private boolean jumping = false;
	
	public void tick(Game game){
		if(game.input.keys[KeyEvent.VK_A] && canLeft) x-=2;
		if(game.input.keys[KeyEvent.VK_D] && canRight) x+=2;
		if(game.input.keys[KeyEvent.VK_SPACE] && canJump){
			jumping = true;
			y-=jumpSpeed;
		}
		
		if(jumping && falling == true){
			y-=jumpSpeed;
		}
		
		if(x < 0) x = 0;
		
		if(x < 11.5*32){
			hitbox = new Rectangle((int)x, (int)(Math.round(y)), 64, 64);
			cameraMoving = false;
		}/*else if(){
			this is stuff that will be for right side
		}*/else{
			hitbox = new Rectangle((int)368, (int)(Math.round(y)), 64, 64);
			cameraMoving = true;
		}
				
		if(falling) gravity();
		else{
			// reset gravity
			canJump = true;
			jumping = false;
			curGrav = 1f;
		}
		
		falling = true;
	}
	
	public void render(Graphics g){
		g.setColor(Color.red);
		if(cameraMoving) g.fillRect((int)(368*Display.scale), (int)((y)*Display.scale), (int)(64*Display.scale), (int)(64*Display.scale));
		else{
			if(x < 11.5*32) g.fillRect((int)(x*Display.scale), (int)((y)*Display.scale), (int)(64*Display.scale), (int)(64*Display.scale)); 
			else{ /*stuff for right side*/ } 
		}
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.white);
		g2d.draw(hitbox);
	}
	
	private void gravity(){
		// gravity multiplier
		if(curGrav < 5.5f) curGrav*=1.02f;
		y+=curGrav;
		canJump = false;
	}

}
