package net.meme;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
	
	public boolean keys[] = new boolean[KeyEvent.KEY_LAST+1];
	public boolean keysClick[] = new boolean[KeyEvent.KEY_LAST+1];
	
	private int heldFor[] = new int[KeyEvent.KEY_LAST+1];
	
	public Input(){
		for(int i = 0; i < keys.length; i++){
			keys[i] = false;
			keysClick[i] = false;
			heldFor[i] = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		keysClick[e.getKeyCode()] = false;
		heldFor[e.getKeyCode()] = 0;
	}

	public void tick(){
		for(int i = 0; i < keys.length; i++){
			if(keys[i] == true){
				if(heldFor[i] <= 0) keysClick[i] = true;
				else keysClick[i] = false;
				heldFor[i]++;
			}
		}
	}
	
}
