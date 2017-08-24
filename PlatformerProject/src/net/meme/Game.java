package net.meme;

import java.awt.Graphics;

import net.meme.block.GrassBlock;
import net.meme.player.Player;

public class Game {
	
	public Input input = new Input();
	public Player player = new Player();
	private GrassBlock testblocks[] = new GrassBlock[20];
	
	public Game(){
		for(int i = 0; i < testblocks.length; i++){
			testblocks[i] = new GrassBlock(i, 0);
		}
	}
	
	public void tick(){
		input.tick();
		player.tick(this);
	}
	
	public void render(Graphics g){
		for(int i = 0; i < testblocks.length; i++) testblocks[i].render(this, g);
		player.render(g);
	}

}
