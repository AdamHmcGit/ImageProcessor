import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TestMain extends JFrame implements ActionListener{
	
	final JFileChooser fc = new JFileChooser();
	JButton openButton;
	JLabel picLabel;
	JLabel picLabel1;
	File file;
	JPanel panel;
	JLabel picLabel2;
	ImageProcessor imgProcessor;
	
	public TestMain() throws Exception{
		imgProcessor = new ImageProcessor();
		imgProcessor.setOffSet(1800000);
		initUi();
	}
	
	public void initUi() throws Exception{
		panel = new JPanel();
		this.add(panel);
		this.setSize(1050, 900);
		setVisible(true);
		
		openButton = new JButton("Open a File...");
		openButton.addActionListener(this);
		panel.add(openButton);
	}
	
	public static void main(String args [] ) throws Exception{
        TestMain test = new TestMain();
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(TestMain.this);
            if (returnVal == JFileChooser.APPROVE_OPTION){ 
            	try {
            		File file = fc.getSelectedFile();
					reload(file);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
		}
	}
	
	public void reload(File file) throws IOException{
		BufferedImage myPicture = ImageIO.read(file);
		if(picLabel!=null)
			panel.remove(picLabel);
		picLabel = new JLabel(new ImageIcon(imgProcessor.processImage(myPicture)));
		panel.add(picLabel);
			panel.revalidate();
		panel.repaint();
	}
}
