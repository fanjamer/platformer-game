package net.meme.block;

import net.meme.Game;
import net.meme.resource.Art;

public class CoarseDirtBlock extends Block {

	public CoarseDirtBlock(int x, int y) {
		super(x, y, Art.coarseDirt);
	}

	public void onTouch(Game game) {}
	public void onExplode(Game game) {}

}
