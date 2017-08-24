package net.meme;

import java.awt.Graphics;

public class Game {
	
	public Input input = new Input();
	public Player player = new Player();
	
	public void tick(){
		input.tick();
		player.tick(this);
	}
	
	public void render(Graphics g){
		player.render(g);
	}

}
