import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class NewTabThread implements Runnable{

	
	@Override
	public void run() {
		
	JFrame frame2 = new JNotepad();
    	frame2.setLocation(100, 100);
    	frame2.setTitle("Notepad" + (JNotepad.count));
    	int a = WindowConstants.HIDE_ON_CLOSE;
    	frame2.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    	//System.out.println("New Tread Started");
		// TODO Auto-generated method stub
		
	}
}
