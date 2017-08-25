package net.meme.block;

import net.meme.Game;
import net.meme.resource.Art;

public class DirtBlock extends Block {

	public DirtBlock(int x, int y) {
		super(x, y, Art.dirt);
	}

	public void onTouch(Game game) {}
	public void onExplode(Game game) {}

}
