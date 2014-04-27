import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class ImageRotator {
	public static BufferedImage rotateMyImage(BufferedImage img, double angle) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg =new BufferedImage(w, h, img.getType());
		
		Graphics2D g = dimg.createGraphics();
		if(img.getRGB(0, 0)==-1){
			g.setColor(Color.WHITE);
		}
		else{
			g.setColor(Color.BLACK);
		}
		g.fillRect(0, 0, w, h);
		g.setColor(Color.BLACK);
		
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
//		RenderingHints.VALUE_RENDER_QUALITY);
		 
		g.rotate(Math.toRadians(angle), w/2, h/2);
		 
		g.drawImage(img, null, 0, 0);
		

		return dimg;
		}
}
