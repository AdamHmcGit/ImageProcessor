import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageProcessor {
	int offset;
	int alphaMask = 16777215;
	
	public void setOffSet(int offSet){
		this.offset = offSet;
	}
	
	public BufferedImage getProcessedImage(String filePath) throws Exception{
		BufferedImage myPicture = ImageIO.read(new File(filePath));
		return processImage(myPicture);
	}

	public BufferedImage processImage(BufferedImage image){
		return cutImage(rotateImage(image));
	}
	
	public BufferedImage cutImage(BufferedImage image){
		PixelPoint top=null;
		PixelPoint bot=null;
		PixelPoint left=null;
		PixelPoint right=null;
		int height = image.getHeight();
		int width = image.getWidth();
		int defaultRGB = image.getRGB(0, 0)&alphaMask;		
		boolean pixelFound=false;
		for(int i=0;i<height;i++){
			if(pixelFound)
				break;
			for(int j=0;j<width;j++){
				if(Math.abs((image.getRGB(j, i)&alphaMask)-defaultRGB)>offset){
					top = new PixelPoint(j,i);
					pixelFound=true;
					break;
				}
			}
		}
		pixelFound=false;
		for(int i=0;i<width;i++){
			if(pixelFound)
				break;
			for(int j=0;j<height;j++){
				if(Math.abs((image.getRGB(i, j)&alphaMask)-defaultRGB)>offset){
					left=new PixelPoint(i,j);
					pixelFound=true;
					break;
				}
			}
		}		
		pixelFound=false;
		for(int i=width-1;i>0;i--){
			if(pixelFound)
				break;
			for(int j=0;j<height;j++){
				if(Math.abs((image.getRGB(i, j)&alphaMask)-defaultRGB)>offset){
					right=new PixelPoint(i,j);
					pixelFound=true;
					break;
				}
			}
		}		
		pixelFound=false;
		for(int i=height-1;i>0;i--){
			if(pixelFound)
				break;
			for(int j=width-1;j>0;j--){
				if(Math.abs((image.getRGB(j, i)&alphaMask)-defaultRGB)>offset){
					bot=new PixelPoint(j,i);
					pixelFound=true;
					break;
				}
			}
		}
		return image.getSubimage(left.x,top.y,right.x-left.x,bot.y-top.y);
	}
	
	public BufferedImage rotateImage(BufferedImage image){
		int height = image.getHeight();
		int width = image.getWidth();
		int defaultRGB = (image.getRGB(0, 0)&alphaMask);
		PixelPoint top=null;
		PixelPoint left=null;
		boolean pixelFound=false;
		for(int i=0;i<height;i++){
			if(pixelFound)
				break;
			for(int j=0;j<width;j++){
				if(Math.abs((image.getRGB(j, i)&alphaMask)-defaultRGB)>offset){
					top = new PixelPoint(j,i);
					pixelFound=true;
					break;
				}
			}
		}
		pixelFound=false;
		for(int i=0;i<width;i++){
			if(pixelFound)
				break;
			for(int j=0;j<height;j++){
				if(Math.abs((image.getRGB(i, j)&alphaMask)-defaultRGB)>offset){
					left=new PixelPoint(i,j);
					pixelFound=true;
					break;
				}
			}
		}
		return ImageRotator.rotateMyImage(image, calculateAngle(top,left));
	}
	
	public double calculateAngle(PixelPoint top, PixelPoint left){
		double angle = 0;
		int deltaX =Math.abs(top.x-left.x);
		int deltaY = Math.abs(top.y-left.y);		
		angle = Math.atan2(deltaY, deltaX) *180 / Math.PI - 90;
		return angle;
	}
	
	public class PixelPoint{
		public int x;
		public int y;
		public PixelPoint(int x, int y){
			this.x=x;
			this.y=y;
		}
		
		public String toString(){			
				return "(x: "+this.x+", y: "+this.y+")";
		}
	}
	
}
