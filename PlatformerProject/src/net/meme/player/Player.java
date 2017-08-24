package net.meme.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import net.meme.Display;
import net.meme.Game;

public class Player {
	
	public float x = 0, y = 0;
	public boolean cameraMoving = false;
	
	public void tick(Game game){
		if(game.input.keys[KeyEvent.VK_A]) x-=1;
		if(game.input.keys[KeyEvent.VK_D]) x+=1;
		
		if(x < 11.5*32 /*|| stuff for right side, will be dynamic because different maps are different lengths*/) cameraMoving = false;
		else cameraMoving = true;
	}
	
	public void render(Graphics g){
		System.out.println(Display.scale);
		g.setColor(Color.red);
		if(cameraMoving) g.fillRect((int)(368*Display.scale), (int)(504*Display.scale), (int)(64*Display.scale), (int)(64*Display.scale));
		else g.fillRect((int)(x*Display.scale), (int)(504*Display.scale), (int)(64*Display.scale), (int)(64*Display.scale)); 
	}

}
