package net.meme.block.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import net.meme.Display;
import net.meme.Game;
import net.meme.block.Block;
import net.meme.block.DirtBlock;
import net.meme.block.GrassBlock;

public class Map {
	
	public BufferedImage background;
	public ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();
	
	// mapImg is the image that the tiles will come from
	public Map(BufferedImage mapImg, BufferedImage background, Game game){
		this.background = background;
		createMap(mapImg, game);
	}
		
	private void createMap(BufferedImage mapImg, Game game){
		for(int y = 0; y < mapImg.getHeight(); y++){
			int mapY = mapImg.getHeight()-y-1;
			ArrayList<Block> row = new ArrayList<Block>();
			for(int x = 0; x < mapImg.getWidth(); x++){
				Color col = new Color(mapImg.getRGB(x, y));
				
				if(col.equals(new Color(0, 255, 0))) row.add(new GrassBlock(x, mapY));
				else if(col.equals(new Color(148, 82, 0))) row.add(new DirtBlock(x, mapY));
				else if(col.equals(new Color(255, 255, 255))){
					game.player.y = y*32;
					game.player.x = x*32;
					row.add(null);
				}else{
					row.add(null);
				}
			}
			blocks.add(row);
		}
	}
	
	public void tick(Game game){
		for(int x = 0; x < blocks.size(); x++){
			ArrayList<Block> row = blocks.get(x);
			for(int y = 0; y < row.size(); y++){
				if(row.get(y) != null) row.get(y).tick(game);
			}
		}
	}
	
	public void render(Game game, Graphics g){
		if(background != null) g.drawImage(background, 0, 0, (int)(Display.width*Display.scale), (int)(Display.height*Display.scale), null);
		
		for(int x = 0; x < blocks.size(); x++){
			ArrayList<Block> row = blocks.get(x);
			for(int y = 0; y < row.size(); y++){
				if(row.get(y) != null) row.get(y).render(game, g);
			}
		}
	}
	
	public int getWidth(){
		return blocks.get(0).size();
	}
	
	public int getHeight(){
		return blocks.size();
	}

}
