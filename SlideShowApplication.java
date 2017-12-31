import javax.swing.JFrame;
import java.awt.Color;

/**
 * SlideShowApplication is the class to test out the slide show program.
 * 
 * @author Xueying Xu (Shirley)
 */
public class SlideShowApplication {

	public static void main(String[] args) {
		
		JFrame guiFrame = new JFrame("This is a slideshow!");
		guiFrame.setSize(1000, 600);
		//guiFrame.getContentPane().setBackground( new Color(216, 235, 255) );
		guiFrame.add(new SlideShowPanel());
		// Exit normally on closing the window
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Show frame
		guiFrame.setVisible(true);
	
	}
}
