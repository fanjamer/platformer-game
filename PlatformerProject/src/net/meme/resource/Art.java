package net.meme.resource;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Art {
	
	public static final BufferedImage grass = load("/block/grass.png");

	public static BufferedImage load(String path){
		InputStream stream = Art.class.getClass().getResourceAsStream(path);
		try {
			return ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
