package net.meme.block;

import net.meme.Game;
import net.meme.resource.Art;

public class CoarseDirtBlock extends Block {

	public CoarseDirtBlock(int x, int y) {
		super(x, y, Art.coarseDirt);
	}

	public void onTouch(Game game) {}
	
	public void onExplode(Game game) {
		int mapX = (int) (x/32);
		int mapY = (int) (y/32);
		
		game.map.blocks.get(mapX).set(mapY, null);
	}

}
