import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class ImageProcessorMain  {

	static ImageProcessor imageProcessor;
	
	public static void main(String args [] ) throws Exception{
		imageProcessor = new ImageProcessor();
		imageProcessor.setOffSet(2000000);
		String path="/Users/mhuang/Desktop/rotate";
		File file = new File(path);
		for(File f: file.listFiles()){
			if(shouldProcess(f.getName())){
				String name = f.getName();
				BufferedImage outPutImage = imageProcessor.getProcessedImage(f.getAbsolutePath());
				File outputfile = new File(path+"/roated_"+name);
				ImageIO.write(outPutImage, "jpg", outputfile);
			}
		}
	}

	public static boolean shouldProcess(String name){
		if(name.contains("png"))
			return true;
		else if(name.contains("jpg"))
			return true;
		else if(name.contains("bmp"))
			return true;
		else
			return false;
		
	}

}
