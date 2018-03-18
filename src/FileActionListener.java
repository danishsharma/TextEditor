import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTMLEditorKit;

public class FileActionListener extends JNotepadToolbar implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== newFile)
		{
			newFile();
		}
		if(e.getSource()== print)
		{
			print();
		}
		if(e.getSource()== newTab)
		{
			newTab();
		}
		if(e.getSource()== openFile)
		{
			openFile();
		}
		if(e.getSource()== saveFile)
		{
			save();
		}
		if(e.getSource()== saveAsFile)
		{
			saveAs();
		}
		}
	
    public void print() {
        JTextPane tempPane = new JTextPane();
        tempPane.setEditorKit(new HTMLEditorKit());
        tempPane.setText(jta.getText());

        try {
            boolean printed = tempPane.print();
            if (printed)
                JOptionPane.showMessageDialog(null, "Finished Printing");
            else
                JOptionPane.showMessageDialog(null, "Cancelled Printing");

        } catch (PrinterException e) {
            e.printStackTrace();
        }

    }
    
    public void newFile() {
        //when creating a new file, see if user wants to save the current document
        Object[] options = {
            "Save",
            "Discard",
            "Cancel"
        };
        JOptionPane optionsPane = new JOptionPane();
        JDialog j = optionsPane.createDialog(this, "Title");
        int choice = optionsPane.showOptionDialog(null, "Would you like to save changes to the document?", "New",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, "Save");
        //go to save method if user select save.

        if (choice == 0) {
            save();
        } else if (choice == 1) {
            jta.setText("");
            setTitle("Notepad" + count);
            saved = false;
            this.dispose();


        }

    }
    public void newTab() {

        /*JFrame frame2 = new JNotepad();
        frame2.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame2.setLocation(100, 100);
        */
        Thread t = new Thread(new NewTabThread());
        t.start();
        count++;
    }
    public void openFile() {
        JFileChooser jfc = new JFileChooser();
        //set the filter for the jfilechooser
        FileNameExtensionFilter txt = new FileNameExtensionFilter("Text Files", "txt");
        jfc.addChoosableFileFilter(txt);
        if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(this)) {
            File file = jfc.getSelectedFile();
            currentFile = file;
            try {
                //read the file and put it in the jtextarea
                Scanner sc = new Scanner(file);
                jta.setText("");
                while (sc.hasNext()) {
                    String str = sc.nextLine();
                    jta.append(str + "\n");
                    //System.out.println();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        saved = true;
    }
    public void save() {
        //it will run the saveAs function if its first time saving.
        if (!saved) {
            saveAs();
        }
        //otherwise it will update the file.
        else {
            try {
                BufferedWriter outFile = new BufferedWriter(new FileWriter(currentFile));
                jta.write(outFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        saved = true;
    }
    public void saveAs() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Save As");
        //create filter for java and txt extensions
        FileNameExtensionFilter txt = new FileNameExtensionFilter("Text Files", "txt");
        jFileChooser.addChoosableFileFilter(txt);
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            //check if the filename specified have a extension
            //if none, add to it
            if (!currentFile.getAbsolutePath().endsWith(".txt"))
                currentFile = new File(currentFile.getAbsolutePath() + ".txt");
            if (currentFile.exists()) {
                //if file already exist
                //this will check if user wants to overwrite the document
                int overwrite = JOptionPane.showConfirmDialog(null, "Do you want to overwrite the existing file?",
                    "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (overwrite != JOptionPane.YES_OPTION) {
                    saved = false;
                } else {
                    writeToFile();
                    saved = true;
                }
            } else {
                writeToFile();
            }
        }
    }
    
    

    public void writeToFile() {
        //its a new document so make the file
        try {
            FileWriter fw = new FileWriter(currentFile.getAbsolutePath());
            BufferedWriter outFile = new BufferedWriter(fw);
            outFile.write(this.jta.getText());
            outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //make sure to only update from now on
        saved = false;
        //update the title of the frame
        setTitle(currentFile.getName() + " - Notepad" + count);
    }

    //this will update the menu item to have the redo
    //and undo to be visible or not.
   

}
