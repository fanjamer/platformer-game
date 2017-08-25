package net.meme.block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.meme.Display;
import net.meme.Game;

public abstract class Block {
	
	public float x, y;
	public BufferedImage tex;
	public Rectangle hitbox;
	
	public Block(int x, int y, BufferedImage tex){
		this.x = x*32;
		this.y = y*32;
		this.tex = tex;
	}
	
	public void tick(Game game){
		//pixel y pos
		float dy = 18.75f*32-y-32;
		
		// set hitbox
		if(game.player.cameraMoving) hitbox = new Rectangle((int)(x-game.player.x+11.5*32), (int)(dy), (int)(32), (int)(32));
		else hitbox = new Rectangle((int)(x), (int)(dy), (int)(32*Display.scale), (int)(32*Display.scale));
		//hitbox = new Rectangle((int)x, (int)y, 32, 32);
		
		// simple check if player is on top of block
		if((game.player.y+64 <= dy+11f && game.player.y+64 >= dy-11f) && hitbox.intersects(game.player.hitbox)){
			// dont fall, player can jump now
			game.player.falling = false;
			game.player.canJump = true;
			
			// if player is in block correct his position
			if(game.player.y+64>dy) game.player.y = dy-64;
		}
	}
	
	public void render(Game game, Graphics g){
		float dy = 18.75f*32-y-32;
		if(game.player.cameraMoving) g.drawImage(tex, (int)(Display.scale*(x-game.player.x+11.5*32)), (int)(Display.scale*(dy)), (int)(32*Display.scale), (int)(32*Display.scale), null);
		else g.drawImage(tex, (int)(Display.scale*x), (int)(Display.scale*(dy)), (int)(32*Display.scale), (int)(32*Display.scale), null);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.white);
		g2d.draw(hitbox);
	}
	
	public abstract void onTouch(Game game);

}
