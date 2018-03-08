import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JFontChooser extends JDialog implements ListSelectionListener
{
	    //used by font names
	    JList fontList;
	    DefaultListModel fontListModel;
	    JScrollPane fontScrollPane;
	    //used by the style(PLAIN,BOLD,Italic)
	    JList styleList;
	    DefaultListModel styleListModel;
	    JScrollPane styleScrollPane;
	    //used by size list
	    JList sizeList;
	    DefaultListModel sizeListModel;
	    JScrollPane sizeScrollPane;

	    //will be used on main JNotepad if there is a new font
	    public static final boolean ACCEPTED = true;
	    public static final boolean REJECTED = false;
	    boolean isNewFont;

	    //this will be used to get the new font &style from dialog
	    Font newFont;
	    String currentFont;
	    int currentStyle;
	    int currentSize;

	    JButton acceptButton = new JButton("Accept");
	    JButton cancelButton = new JButton("Cancel");
	    JLabel sampleLabel = new JLabel("Sample");

	    JFontChooser(JFrame frame){
	        super(frame,"Font Select",true);
	        sampleLabel.setFont(new Font("Courier", Font.PLAIN, 72));
	        setResizable(false);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        setLocationRelativeTo(frame);
	        setSize(500,400);
	        //get the available fonts in the system
	        //put them in a list then add it to a scroll pane
	        fontListModel = new DefaultListModel();
	        String[] fontsAvailable = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	        for(String font: fontsAvailable){
	            fontListModel.addElement(font);
	        }
	        fontList = new JList(fontListModel);
	        //select the first item as default
	        fontList.setSelectedIndex(0);
	        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        //add it to the scroll pane
	        fontScrollPane = new JScrollPane(fontList);

	        //add the different styles to a list
	        styleListModel = new DefaultListModel();
	        styleListModel.addElement("Plain");
	        styleListModel.addElement("Bold");
	        styleListModel.addElement("Italic");
	        styleList = new JList(styleListModel);
	        styleList.setSelectedIndex(0);
	        styleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        styleScrollPane = new JScrollPane(styleList);

	        //add the different sizes available
	        String[] sizeNames = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72"};
	        sizeListModel = new DefaultListModel();
	        for(String size: sizeNames){
	            sizeListModel.addElement(size);
	        }
	        sizeList = new JList(sizeListModel);
	        sizeList.setSelectedIndex(0);
	        sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        sizeScrollPane = new JScrollPane(sizeList);

	        fontList.addListSelectionListener(this);
	        styleList.addListSelectionListener(this);
	        sizeList.addListSelectionListener(this);

	        //fix the way it looks
	        JPanel panel = new JPanel();
	        JPanel panel2 = new JPanel();
	        JPanel panel3 = new JPanel();

	        //add them to the panel first then to the dialog
	        panel.add(fontScrollPane);
	        panel.add(styleScrollPane);
	        panel.add(sizeScrollPane);
	        panel3.add(acceptButton);
	        panel3.add(cancelButton);
	        panel2.add(sampleLabel);

	        add(panel3,BorderLayout.CENTER);
	        add(panel,BorderLayout.NORTH);
	        add(panel2,BorderLayout.SOUTH);

	        //add listeners for the buttons
	        acceptButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println(newFont.getFontName());
	                isNewFont = ACCEPTED;
	                setVisible(false);
	            }
	        });
	        cancelButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                isNewFont = REJECTED;
	                setVisible(false);
	            }
	        });
	    }
	    public Font getNewFont() {
	        return newFont;
	    }
	    @Override
	    //save the selected font properties
	    public void valueChanged(ListSelectionEvent e) {
	        //this will set a new font
	        currentFont = (String) fontList.getSelectedValue();
	        currentStyle = styleList.getSelectedIndex();
	        currentSize = Integer.parseInt((String) sizeList.getSelectedValue());
	        newFont = new Font(currentFont, currentStyle, currentSize);
	        //change the label to see what is looks like
	        sampleLabel.setFont(newFont);
	    }
}
