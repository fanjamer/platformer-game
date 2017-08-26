package net.meme;

import java.awt.Graphics;

import net.meme.block.GrassBlock;
import net.meme.block.map.Map;
import net.meme.player.Player;
import net.meme.resource.Art;

public class Game {
	
	public Input input = new Input();
	public Player player = new Player();
	public Map map;
	
	public Game(){
		map = new Map(Art.load("/map/test.png"), null, this);
	}
	
	public void tick(){
		input.tick();
		player.tick(this);
		map.tick(this);
	}
	
	public void render(Graphics g){
		map.render(this, g);
		player.render(this, g);
	}

}
