/*
		 Title: SAGUI.java
		 Programmer: hugo
		 Date of creation: Apr 23, 2015
		 Description: 
*/


package quizMaker;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import questions.ShortAnswer;

/**
 * @author hugo
 *
 */
public class SAGUI {
	static JFrame qRoot;
	static JPanel mainPanel, questionPanel, ansPanel, buttPanel;
	static JLabel qHelp, ansHelp;
	static JTextField questionBox, ansBox;
	static JButton cancelButt, saveButt;
	static ButtonHandler onClick = new ButtonHandler();
	
	
	private static class ButtonHandler implements ActionListener {
		boolean hasEnteredQuestion = false;
		boolean hasEnteredAns = false;
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cancelButt) {
				qRoot.dispose();
				hasEnteredAns = hasEnteredQuestion = false;
			} else if (e.getSource() == saveButt) {
				ShortAnswer tempObj = new ShortAnswer(questionBox.getText(), ansBox.getText());
				QuizMaker.qStorage.addSA(tempObj);
				QuizMaker.allQ.setText(QuizMaker.allQ.getText() + tempObj.toString());
				qRoot.dispose();
				hasEnteredAns = hasEnteredQuestion = false;
			} else if (e.getSource() == questionBox) {
				hasEnteredQuestion = true;
				if (hasEnteredAns && hasEnteredQuestion) {
					saveButt.setEnabled(true);
				}
			} else if (e.getSource() == ansBox) {
				hasEnteredAns = true;
				if (hasEnteredAns && hasEnteredQuestion) {
					saveButt.setEnabled(true);
				}
			}
		}

	}
	
	/**
	 * 
	 */
	public SAGUI() {
		qRoot = new JFrame("Short answer question");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		qRoot.setBounds(0, 0, (int) (dm.width / 1.2), (int) (dm.height / 1.1));
		
		mainPanel = new JPanel(new GridLayout(0, 1));
		questionPanel = new JPanel(new GridLayout(1,  2));
		ansPanel = new JPanel(new GridLayout(1, 2));
		buttPanel = new JPanel(new GridLayout(1, 2));
		
		qHelp = new JLabel("Enter your question then hit ENTER");
		qHelp.setFont(QuizMaker.defaultFont);
		ansHelp = new JLabel("Enter your question then hit ENTER");
		ansHelp.setFont(QuizMaker.defaultFont);
		questionBox = new JTextField();
		questionBox.setFont(QuizMaker.smallFont);
		questionBox.addActionListener(onClick);
		ansBox = new JTextField();
		ansBox.setFont(QuizMaker.smallFont);
		ansBox.addActionListener(onClick);
		cancelButt = new JButton("Cancel");
		cancelButt.setFont(QuizMaker.defaultFont);
		cancelButt.addActionListener(onClick);
		saveButt = new JButton("Save");
		saveButt.setFont(QuizMaker.defaultFont);
		saveButt.addActionListener(onClick);
		saveButt.setEnabled(false);
		
		qRoot.add(mainPanel);
		mainPanel.add(questionPanel);
		mainPanel.add(ansPanel);
		mainPanel.add(buttPanel);
		
		questionPanel.add(qHelp);
		questionPanel.add(questionBox);
		ansPanel.add(ansHelp);
		ansPanel.add(ansBox);
		buttPanel.add(cancelButt);
		buttPanel.add(saveButt);
		
		qRoot.setVisible(true);
	}
}






