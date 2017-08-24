package net.meme.block;

import net.meme.Game;
import net.meme.resource.Art;

public class GrassBlock extends Block {

	public GrassBlock(int x, int y) {
		super(x, y, Art.grass);
	}

	@Override
	public void onTouch(Game game) {}

}
