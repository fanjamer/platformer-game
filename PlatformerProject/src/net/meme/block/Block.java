package net.meme.block;

import java.awt.Graphics;
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
		float dy = 18.75f*32-y-32;
		/*if(game.player.cameraMoving) hitbox = new Rectangle((int)(x-game.player.x+11.5*32), (int)(dy-game.player.y), (int)(32), (int)(32));
		else hitbox = new Rectangle((int)(x), (int)(dy-game.player.y), (int)(32*Display.scale), (int)(32*Display.scale));*/
		hitbox = new Rectangle((int)x, (int)y, 32, 32);
		
		// top of block
		if(game.player.y >= this.y && hitbox.intersects(game.player.hitbox)){
			game.player.falling = false;
			game.player.canJump = true;
			game.player.y = y;
		}
	}
	
	public void render(Game game, Graphics g){
		float dy = 18.75f*32-y-32;
		if(game.player.cameraMoving) g.drawImage(tex, (int)(Display.scale*(x-game.player.x+11.5*32)), (int)(Display.scale*(dy-game.player.y)), (int)(32*Display.scale), (int)(32*Display.scale), null);
		else g.drawImage(tex, (int)(Display.scale*x), (int)(Display.scale*(dy-game.player.y)), (int)(32*Display.scale), (int)(32*Display.scale), null);
	}
	
	public abstract void onTouch(Game game);

}
