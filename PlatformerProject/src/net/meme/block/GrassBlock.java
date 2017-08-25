package net.meme.block;

import net.meme.Game;
import net.meme.resource.Art;

public class GrassBlock extends Block {

	public GrassBlock(int x, int y) {
		super(x, y, Art.grass);
	}

	public void onTouch(Game game) {}
	public void onExplode(Game game) {}

}
