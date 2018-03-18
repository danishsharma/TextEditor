import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class JNotepad extends JFrame implements Window,ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int count = 1;
    JMenuBar bar = new JMenuBar();
    JTextArea jta = new JTextArea();
    JLabel wordCount = new JLabel("");
    Font defFont;
    UndoManager undoRedoMan = new UndoManager();
    boolean saved = false;
    File currentFile;
    JCheckBoxMenuItem wordWrap;
    JFontChooser fontChooser;
    File viewHelp;

    JButton reCloseButton = new JButton();
    JCheckBox remCheckBox = new JCheckBox();
    JTextField refindField = new JTextField();
    boolean resensitive = false;

    //Need to set global
    //used for find functionality
    JDialog jd;
    static JDialog re;
    JTextField findField;
    JTextField replaceField;
    JCheckBox mCheckBox;
    JButton closeButton;
    JButton findNextButton;
    JButton replaceButton;
    JButton findButton;
    boolean sensitive;
    int findIdx;
    //highlights the find word(s)
    Object lastHL;
    Highlighter hl = jta.getHighlighter();
    HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GRAY);


    //used for redo and undo functionality
    public JMenuItem undo;
    public JMenuItem redo;

    JNotepad() {

        setTitle("Notepad" + count);
        setLayout(new BorderLayout());
        add(wordCount, BorderLayout.SOUTH);
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setIconImage(new ImageIcon("JNotepad.png").getImage());
        setLocationRelativeTo(null);

        

        /*---------------------------------
            DEFAULT TEXT AREA
        ----------------------------------*/
        fontChooser = new JFontChooser(this);
        jta.setFont(new Font("Courier", Font.PLAIN, 12));
        jta.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(jta);
        jta.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent ce) {
                String str = jta.getText();
                findIdx = jta.getCaretPosition();
                int lineNumber = 0, column = 0, pos = 0;
                try {
                    pos = jta.getCaretPosition();
                    lineNumber = jta.getLineOfOffset(pos);
                    column = pos - jta.getLineStartOffset(lineNumber);
                } catch (Exception excp) {}
                if (jta.getText().length() == 0) {
                    lineNumber = 0;
                    column = 0;
                }
                wordCount.setText("||       Ln " + (lineNumber + 1) + ", Col " + (column + 1));
            }
        });
        JLabel test = new JLabel("test");
        /*---------------------------------
            UNDO MANAGER FOR TEXT AREA
        ----------------------------------*/

        jta.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoRedoMan.addEdit(e.getEdit());
              //  UndoRedo();
            }
        });
        setJMenuBar(bar);
        this.add(scrollPane);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New":
             //   newFile();
                break;
            case "New Tab":
               // newTab();
                break;
            case "Open":
               // openFile();
                break;
            case "Save":
               // save();
                break;
            case "Save As":
               // saveAs();
                break;
                // case "Page Setup":
                //    print();
                //   break;
            case "Print":
               // print();
                break;
            case "Exit":
              //  newFile();
                //this.dispose();
                break;
            case "Undo":
                undoRedoMan.undo();
               // UndoRedo();
                break;
            case "Redo":
                undoRedoMan.redo();
               // UndoRedo();
                break;
            case "Cut":
                jta.cut();
                break;
            case "Copy":
                jta.copy();
                break;
            case "Paste":
                jta.paste();
                break;
                //case "Delete":
                //   jta.setText(jta.getText().replace(jta.getSelectedText(),""));
                // break;
            case "Find":
               // find();
                break;
            case "Find Next":
               // find();
                break;
            case "Go To":
               // goTo();
                break;
            case "Replace":
               // replace();
                break;
            case "Select All":
                jta.selectAll();
                break;
            case "Time/Date":
                SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a M/d/yyyy");
                jta.append(dateFormat.format(new Date(e.getWhen())));
                break;
            case "Word Wrap":
                if (wordWrap.getState())
                    jta.setLineWrap(true);
                else
                    jta.setLineWrap(false);
                break;
            case "Font":
                fontChooser.setVisible(true);
                if (fontChooser.isNewFont) {
                    jta.setFont(fontChooser.getNewFont());
                }
                break;
            case "Foreground Color":
                Color c = JColorChooser.showDialog(null, "Choose a color", jta.getForeground());
                //JColorChooser.sh
                //Color c = new Color(255,0,0);
                if (c != null)
                    jta.setForeground(c);
                break;
                // case "Line Count":
                //	showLineCount();
                //   break;
            case "Background Color":
                Color c1 = JColorChooser.showDialog(null, "Choose a color", jta.getForeground());
                if (c1 != null)
                    jta.setBackground(c1);
                break;
            case "View Help":
                viewHelp = new File("/Users/danishs/Desktop/help.txt");
                //this will open the file if it exist
                if (viewHelp.exists()) {
                    try {
                        Desktop.getDesktop().open(viewHelp);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                break;
            case "About Notepad":
                JOptionPane.showMessageDialog(this, "JAVA SWINGS ROCKS!!!!", "About Notepad", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
    @Override
	public void create() {
		// TODO Auto-generated method stub
		new JNotepad();
	}
    
}

      
	
