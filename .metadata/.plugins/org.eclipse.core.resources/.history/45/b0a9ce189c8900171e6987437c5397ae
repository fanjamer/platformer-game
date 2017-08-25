package net.meme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int width = 800, height = 600;
	public static float scale = 1;
	public static final String title = "";
	public static final int fpsCap = 90;
	public static Display instance;
	public JFrame frame;
			
	private Thread thread;
	public Game game;		
	
	private boolean running = false;
	
	public int fps;
	public int tps;
					
	private Display(){
		init();
		start();
	}
	
	private void init(){
		game = new Game();
		
		Dimension size = new Dimension(width, height);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		
		frame = new JFrame(title);
		frame.add(this);
		frame.addKeyListener(game.input);
		frame.setResizable(false);
		frame.setSize(size);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start(){
		if(running) return;
		running = true;
	
		if(thread == null) thread = new Thread(this);
				
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running) return;
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / fpsCap;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			boolean shouldRender = false;
			
			while(delta >= 1){
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(shouldRender){
				frames++;
				repaint();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				
				fps = frames;
				tps = ticks;
				
				frames = 0;
				ticks = 0;
			}
		}
		
		stop();
	}
	
	private void tick(){
		game.tick();
	}
	
	private void render(Graphics g){
		g.fillRect(0, 0, (int)(width*scale), (int)(height*scale));
		game.render(g);
	}
	
	public void paint(Graphics g){
		g.clearRect(0, 0, (int)(width*scale), (int)(height*scale));
		render(g);
	}
	
	public static void main(String[] args){
		instance = new Display();
	}

}
