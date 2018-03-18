import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class TextActionListener extends JNotepad implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	
	replaceButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //get the text from textarea and textfield
            String str;
            String findStr;
            String replaceStr;
            //see if case sensitive
            if (resensitive) {
                str = jta.getText();
                findStr = refindField.getText();
                replaceStr = replaceField.getText();
            } else {
                str = jta.getText().toLowerCase();
                findStr = refindField.getText().toLowerCase();
                replaceStr = replaceField.getText();
            }
            if (!str.equals("")) {
                if (str.contains(findStr)) {
                    String newStr = str.replaceAll(findStr, replaceStr);
                    jta.setText(newStr);
                } else {
                    JOptionPane.showMessageDialog(null, "Not Found");
                }
            }
        }
    });
	
	
	 public void UndoRedo() {
	        undo.setEnabled(undoRedoMan.canUndo());
	        redo.setEnabled(undoRedoMan.canRedo());
	    }

	    public void replace() {

	        re = new JDialog(this, "Replace", false);
	        re.setSize(320, 150);
	        re.getContentPane().setLayout(new BorderLayout());
	        re.setLocationRelativeTo(this);
	        //create the buttons and textfield that will be used for find and find next
	        reCloseButton = new JButton("Close");
	        replaceButton = new JButton("Replace");
	        //findNextButton = new JButton("Find Next");
	        remCheckBox = new JCheckBox("Match Case");
	        refindField = new JTextField(15);
	        replaceField = new JTextField(15);

	        JPanel findPanel = new JPanel();
	        JPanel buttonPanel = new JPanel();

	        findPanel.setLayout(new FlowLayout());
	        buttonPanel.setLayout(new GridLayout(1, 3));

	        //add the textfield and checkbox on this panel and 
	        //add it to the centered of JDialod
	        //refindField.setPreferredSize(new Dimension(400,20));
	        findPanel.add(new JLabel("Find :                 "));
	        findPanel.add(refindField);
	        findPanel.add(new JLabel("Replace :           "));
	        findPanel.add(replaceField);
	        re.getContentPane().add(findPanel, BorderLayout.CENTER);
	        //add the find next and close buttons
	        buttonPanel.add(replaceButton);
	        buttonPanel.add(reCloseButton);
	        buttonPanel.add(remCheckBox);
	        re.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

	        //sensitive = false;
	        re.setVisible(true);

	        reCloseButton.addActionListener(new ButtonListener());

	        remCheckBox.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent ae) {
	                if (remCheckBox.isSelected())
	                    resensitive = true;
	                else
	                    resensitive = false;
	            }
	        });

	        

	  //get the current index then the last word of the found string and highlight it
    public void highlights(int idx, String findStr) {
        jta.setCaretPosition(idx);
        findIdx = idx;
        int startHL = jta.getCaretPosition();
        int endHL = startHL + findStr.length();
        try {
            lastHL = hl.addHighlight(startHL, endHL, painter);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    
    findButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //get the text from textarea and textfield
            String str = jta.getText().toLowerCase();
            String findStr = findField.getText().toLowerCase();
            //see if case sensitive
            if (sensitive) {
                str = jta.getText();
                findStr = findField.getText();
            }
            if (!str.equals("")) {
                if (lastHL != null)
                    hl.removeHighlight(lastHL);
                int idx = str.indexOf(findStr, 0);
                if (idx > -1) {
                    highlights(idx, findStr);
                } else {
                    JOptionPane.showMessageDialog(null, "Not Found");
                }
            }
        }
    });
    
    findNextButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //get the text from textarea and textfield
            String str = jta.getText().toLowerCase();
            String findStr = findField.getText().toLowerCase();
            //see if case sensitive
            if (sensitive) {
                str = jta.getText();
                findStr = findField.getText();
            }
            if (!str.equals("")) {
                if (lastHL != null)
                    hl.removeHighlight(lastHL);
                int idx = str.indexOf(findStr, findIdx + 1);
                if (idx > -1) {
                    highlights(idx, findStr);
                } else {
                    JOptionPane.showMessageDialog(null, "Not Found");
                }
            }
        }
    });
    
    public void goTo() {
        int lineNumber = 0;
        try {
            lineNumber = jta.getLineOfOffset(jta.getCaretPosition()) + 1;
            String tempStr = JOptionPane.showInputDialog(null, "Enter Line Number:", "" + lineNumber);
            if (tempStr == null) {
                return;
            }
            lineNumber = Integer.parseInt(tempStr);
            jta.setCaretPosition(jta.getLineStartOffset(lineNumber - 1));
        } catch (Exception e) {}
    }
    
    
    
    public void find() {
        //creates a JDialog
        jd = new JDialog(this, "Find", false);
        jd.setSize(300, 150);
        jd.getContentPane().setLayout(new BorderLayout());
        jd.setLocationRelativeTo(this);
        //create the buttons and textfield that will be used for find and find next
        closeButton = new JButton("Close");
        findButton = new JButton("Find");
        findNextButton = new JButton("Find Next");
        mCheckBox = new JCheckBox("Match Case");
        findField = new JTextField(15);
        //create a panels needed for the dialog
        JPanel findPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        findPanel.setLayout(new FlowLayout());
        buttonPanel.setLayout(new GridLayout(1, 3));
        //add the textfield and checkbox on this panel and 
        //add it to the centered of JDialod
        findPanel.add(new JLabel("Find: "));
        findPanel.add(findField);
        findPanel.add(mCheckBox);
        jd.getContentPane().add(findPanel, BorderLayout.CENTER);
        //add the find next and close buttons
        buttonPanel.add(findButton);
        buttonPanel.add(findNextButton);
        buttonPanel.add(closeButton);
        jd.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        //add actionlisteners to the checkbox and buttons



        closeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                jd.setVisible(false);
            }
        });
        mCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (mCheckBox.isSelected())
                    sensitive = true;
                else
                    sensitive = false;
            }
        });
      
        jd.setVisible(true);
    }

}
