package net.meme.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.meme.Display;
import net.meme.Game;

public abstract class Block {
	
	public float x, y;
	public BufferedImage tex;
	
	public Block(int x, int y, BufferedImage tex){
		this.x = x*32;
		this.y = 18.75f*32-y*32-32;
		System.out.println(this.y);
		this.tex = tex;
	}
	
	public abstract void tick(Game game);
	
	public void render(Game game, Graphics g){
		if(game.player.cameraMoving) g.drawImage(tex, (int)(Display.scale*(x-game.player.x+11.5*32)), (int)(Display.scale*(y-game.player.y)), (int)(32*Display.scale), (int)(32*Display.scale), null);
		else g.drawImage(tex, (int)(Display.scale*x), (int)(Display.scale*y), (int)(32*Display.scale), (int)(32*Display.scale), null);
	}

}
