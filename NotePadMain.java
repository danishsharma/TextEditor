import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class NotePadMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//use the OS look instead of the swing look
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Using default swing look");
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JNotepad();
            }
        });

	}

}
