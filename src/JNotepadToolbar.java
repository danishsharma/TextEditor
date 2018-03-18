import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class JNotepadToolbar extends JNotepad{
	JMenuItem undo;
    JMenuItem redo;
    JCheckBoxMenuItem wordWrap;
    JMenuBar bar = new JMenuBar();
    JMenuItem newFile;
    JMenuItem print;
    JMenuItem newTab;
    JMenuItem openFile;
    JLabel wordCount = new JLabel("");
    JMenuItem saveFile;
    JMenuItem saveAsFile;
	public JNotepadToolbar() {
		super();
		// TODO Auto-generated constructor stub
		/*---------------------------------
	    FILE MENU
	----------------------------------*/
	JMenu file = new JMenu("File");
	 newFile= new JMenuItem("New");
	newTab = new JMenuItem("New Tab");
	//createMenuItem("New Tab",KeyEvent.VK_N,fileMenu,KeyEvent.VK_N,this);
	 openFile= new JMenuItem("Open");
	saveFile = new JMenuItem("Save");
	saveAsFile = new JMenuItem("Save As");
	//JMenuItem pageSetup = new JMenuItem("Page Setup");
	print = new JMenuItem("Print");
	JMenuItem exit = new JMenuItem("Exit");

	file.setMnemonic('F');
	newFile.setMnemonic('N');
	// pageSetup.setMnemonic('u');
	exit.setMnemonic('x');

	newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

	newFile.addActionListener(new FileActionListener());
	newTab.addActionListener(new FileActionListener());
	openFile.addActionListener(new FileActionListener());
	saveFile.addActionListener(new FileActionListener());
	saveAsFile.addActionListener(new FileActionListener());
	print.addActionListener(new FileActionListener());
	//pageSetup.addActionListener(this);
	exit.addActionListener(new FileActionListener());


	file.add(newFile);
	file.add(newTab);
	file.add(openFile);
	file.add(saveFile);
	file.add(saveAsFile);
	file.addSeparator();
	//file.add(pageSetup);
	file.add(print);
	file.addSeparator();
	file.add(exit);

	/*---------------------------------
	    EDIT MENU
	----------------------------------*/
	JMenu edit = new JMenu("Edit");
	edit.setMnemonic('E');
	undo = new JMenuItem("Undo");
	redo = new JMenuItem("Redo");
	JMenuItem cut = new JMenuItem("Cut");
	JMenuItem copy = new JMenuItem("Copy");
	JMenuItem paste = new JMenuItem("Paste");
	JMenuItem find = new JMenuItem("Find");
	JMenuItem findNext = new JMenuItem("Find Next");
	JMenuItem replace = new JMenuItem("Replace");
	JMenuItem gotoEdit = new JMenuItem("Go To");
	JMenuItem selectAll = new JMenuItem("Select All");
	JMenuItem timeDate = new JMenuItem("Time/Date");

	undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	//delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	gotoEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	timeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

	undo.setEnabled(false);
	redo.setEnabled(false);

	undo.addActionListener(new TextActionListener());
	redo.addActionListener(new TextActionListener());
	cut.addActionListener(new TextActionListener());
	copy.addActionListener(new TextActionListener());
	paste.addActionListener(new TextActionListener());
	//delete.addActionListener(this);
	find.addActionListener(new TextActionListener());
	findNext.addActionListener(new TextActionListener());
	replace.addActionListener(new TextActionListener());
	gotoEdit.addActionListener(new TextActionListener());
	selectAll.addActionListener(new TextActionListener());
	timeDate.addActionListener(new TextActionListener());

	edit.add(undo);
	edit.add(redo);
	edit.addSeparator();
	edit.add(cut);
	edit.add(copy);
	edit.add(paste);
	edit.addSeparator();
	edit.add(find);
	edit.add(findNext);
	edit.add(replace);
	edit.add(gotoEdit);
	edit.addSeparator();
	edit.add(selectAll);
	edit.add(timeDate);

	/*---------------------------------
	    FORMAT MENU
	----------------------------------*/

	JMenu format = new JMenu("Format");
	wordWrap = new JCheckBoxMenuItem("Word Wrap");
	JMenuItem font = new JMenuItem("Font");
	JMenuItem foreground = new JMenuItem("Foreground Color");
	JMenuItem background = new JMenuItem("Background Color");
	format.setMnemonic('o');
	wordWrap.setMnemonic('W');
	font.setMnemonic('F');
	wordWrap.addActionListener(new TextAreaActionListener());
	font.addActionListener(new TextAreaActionListener());
	foreground.addActionListener(new TextAreaActionListener());
	background.addActionListener(new TextAreaActionListener());
	format.add(wordWrap);
	format.add(font);
	format.add(foreground);
	format.add(background);

	/*---------------------------------
	    VIEW MENU
	----------------------------------*/
	JMenu view = new JMenu("View");
	JCheckBoxMenuItem LineCountBar = new JCheckBoxMenuItem("Line Count", true);
	view.setMnemonic('V');
	//LineCountBar.setMnemonic('S');
	//LineCountBar.addActionListener(this);
	view.add(LineCountBar);
	LineCountBar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent ev) {
	        JCheckBoxMenuItem temp = (JCheckBoxMenuItem) ev.getSource();
	        wordCount.setVisible(temp.isSelected());
	    }
	});

	/*---------------------------------
	    HELP MENU
	----------------------------------*/
	JMenu help = new JMenu("Help");
	JMenuItem viewHelp = new JMenuItem("View Help");
	JMenuItem about = new JMenuItem("About Notepad");
	help.setMnemonic('H');
	viewHelp.setMnemonic('H');
	viewHelp.addActionListener(new HelpActionListener());
	about.addActionListener(new HelpActionListener());
	view.add(LineCountBar);
	help.add(viewHelp);
	help.add(about);

	/*---------------------------------
	    ADD MENUS TO MENU BAR
	----------------------------------*/

	bar.add(file);
	bar.add(edit);
	bar.add(format);
	bar.add(view);
	bar.add(help);
	}


}
