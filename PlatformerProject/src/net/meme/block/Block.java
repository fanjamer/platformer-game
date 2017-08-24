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
		this.y = 18.75f*32-y*32-32;
		this.tex = tex;
	}
	
	public void tick(Game game){
		if(game.player.cameraMoving) hitbox = new Rectangle((int)(x-game.player.x+11.5*32), (int)(y-game.player.y), 32, 32);
		else hitbox = new Rectangle((int)x, (int)(y-game.player.y), 32, 32);
		
		if(game.player.y-64 <= y && hitbox.intersects(game.player.hitbox)){
			game.player.falling = false;
			System.out.println(game.player.hitbox.y);
		}
	}
	
	public void render(Game game, Graphics g){
		if(game.player.cameraMoving) g.drawImage(tex, (int)(Display.scale*(x-game.player.x+11.5*32)), (int)(Display.scale*(y-game.player.y)), (int)(32*Display.scale), (int)(32*Display.scale), null);
		else g.drawImage(tex, (int)(Display.scale*x), (int)(Display.scale*(y-game.player.y)), (int)(32*Display.scale), (int)(32*Display.scale), null);
	}
	
	public abstract void onTouch(Game game);

}
