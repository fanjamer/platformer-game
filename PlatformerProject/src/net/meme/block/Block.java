package net.meme.block;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.meme.Display;
import net.meme.Game;
import net.meme.player.Player;

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
		else hitbox = new Rectangle((int)(x-game.player.offset), (int)(dy), (int)(32*Display.scale), (int)(32*Display.scale));
		
		if((game.player.y+64 <= dy+Player.maxGrav*2 && game.player.y+64 >= dy-Player.maxGrav*2) && (Math.abs(game.player.x+32-(x+16)) < 32) && hitbox.intersects(game.player.hitbox)){ // player is on top of block
			// dont fall, player can jump now
			game.player.falling = false;
			game.player.canJump = true;
			
			// if player is in block correct his position
			if(game.player.y+64>dy) game.player.y = dy-64;
		}if(game.player.x+64 <= x+Player.spd*2 && (Math.abs(game.player.y+32-(dy+16)) < 64) && hitbox.intersects(game.player.hitbox)){ // player moves right and collides
			game.player.x-=Player.spd;
		}if(game.player.x >= x-Player.spd*2 && (Math.abs(game.player.y+32-(dy+16)) < 64) && hitbox.intersects(game.player.hitbox)){ // player moves left and collides
			game.player.x+=Player.spd;
		}if(game.player.y >= dy+Player.maxGrav*2 && (Math.abs(game.player.x+32-(x+16)) < 32) && hitbox.intersects(game.player.hitbox)){ // player hits bottom of block
			if(game.player.y < dy+32) game.player.y = dy+32;
			game.player.jumping = false;
			game.player.curGrav = Player.startGrav;
		}
	}
	
	public void render(Game game, Graphics g){
		float dy = 18.75f*32-y-32;
		if(game.player.cameraMoving){
			g.drawImage(tex, (int)(Display.scale*(x-game.player.x+11.5*32)), (int)(Display.scale*(dy)), (int)(32*Display.scale), (int)(32*Display.scale), null);
		}
		else g.drawImage(tex, (int)(Display.scale*(x-game.player.offset)), (int)(Display.scale*(dy)), (int)(32*Display.scale), (int)(32*Display.scale), null);
	}
	
	public abstract void onTouch(Game game);
	public abstract void onExplode(Game game);

}
