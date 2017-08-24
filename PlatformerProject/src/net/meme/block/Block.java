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
		this.y = y*32;
		this.tex = tex;
	}
	
	public abstract void tick(Game game);
	
	public void render(Game game, Graphics g){
		g.drawImage(tex, (int)(Display.scale*(x-game.player.x)), (int)(Display.scale*(y-game.player.y)), (int)(32*Display.scale), (int)(32*Display.scale), null);
	}

}