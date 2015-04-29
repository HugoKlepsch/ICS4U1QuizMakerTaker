/*
		 Title: MCGUI.java
		 Programmer: hugo
		 Date of creation: Apr 22, 2015
		 Description: 
 */

package quizMaker;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import questions.MultiChoice;

/**
 * @author hugo and graham
 *
 */
public class MCGUI {
	public static JFrame qRoot;
	public static JPanel mainPanel, questionPanel, optionPanel1, optionPanel2, optionPanel3, optionPanel4, buttPanel;
	public static JLabel qhelp, oLabel1, oLabel2, oLabel3, oLabel4;
//	public static JTextField helpText;
	public static JTextField questionBox, opt1, opt2, opt3, opt4;
	public static ButtonGroup group;
	public static JRadioButton oRad1, oRad2, oRad3, oRad4;
	public static JButton cancelButt, saveButt;
	public static ButtonHandler onClick = new ButtonHandler();
	
//	public static final boolean flase = false;
	// our button handler class
	private static class ButtonHandler implements ActionListener {
		boolean hasEnteredQuestion = false;
		boolean hasSelectedCorrectAns = false;
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			//enables and disables buttons depending on what we want the user to do
			if (e.getSource() == questionBox) {
				hasEnteredQuestion = true;
				if (hasEnteredQuestion && hasSelectedCorrectAns) {
					saveButt.setEnabled(true);
				}
			} else if (e.getSource() == oRad1 || e.getSource() == oRad2 || e.getSource() == oRad3 || e.getSource() == oRad4) {
				hasSelectedCorrectAns = true;
				if (hasEnteredQuestion && hasSelectedCorrectAns) {
					saveButt.setEnabled(true);
				}
			} else if (e.getSource() == cancelButt) {
				qRoot.dispose();
				hasEnteredQuestion = hasSelectedCorrectAns = false;
			// creates a new multiple choice question and saves it in the qStorage object and closes the window
			} else if (e.getSource() == saveButt) {
				String[] options = {opt1.getText(), opt2.getText(), opt3.getText(), opt4.getText()};
				int correctInd = 0;
				if (oRad1.isSelected()) {
					correctInd = 0;
				} else if (oRad2.isSelected()) {
					correctInd = 1;
				} else if (oRad3.isSelected()){
					correctInd = 2;
				} else if (oRad4.isSelected()) {
					correctInd = 3;
				}
				
				MultiChoice tempObj = new MultiChoice(questionBox.getText(), options, correctInd);
				QuizMaker.qStorage.addMC(tempObj);
				hasEnteredQuestion = hasSelectedCorrectAns = false;
				QuizMaker.allQ.setText(QuizMaker.allQ.getText() + tempObj.toString());
				qRoot.dispose();
			}
		}

	}

	public MCGUI() {
		//sets up the GUI
		qRoot = new JFrame("Multiple choice question");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		qRoot.setBounds(0, 0, (int) (dm.width / 1.2), (int) (dm.height / 1.9));

		mainPanel = new JPanel(new GridLayout(0, 1));
		questionPanel = new JPanel(new GridLayout(1, 1));
		optionPanel1 = new JPanel(new GridLayout(1, 3));
		optionPanel2 = new JPanel(new GridLayout(1, 3));
		optionPanel3 = new JPanel(new GridLayout(1, 3));
		optionPanel4 = new JPanel(new GridLayout(1, 3));
		buttPanel = new JPanel(new GridLayout(0,  2));
		
//		helpText = new JTextField("Enter your Questoin then hit ENTER. \nThen enter each option and select the correct one. ");
//		helpText.setFont(QuizMaker.defaultFont);
		qhelp = new JLabel("Enter your question then hit ENTER");
		qhelp.setFont(QuizMaker.defaultFont);
		oLabel1 = new JLabel("Enter option then select correct one: ");
		oLabel2 = new JLabel("Enter option then select correct one: ");
		oLabel3 = new JLabel("Enter option then select correct one: ");
		oLabel4 = new JLabel("Enter option then select correct one: ");
		oLabel1.setFont(QuizMaker.smallFont);
		oLabel2.setFont(QuizMaker.smallFont);
		oLabel3.setFont(QuizMaker.smallFont);
		oLabel4.setFont(QuizMaker.smallFont);
		questionBox = new JTextField();
		questionBox.setFont(QuizMaker.smallFont);
		questionBox.addActionListener(onClick);
		opt1 = new JTextField("", 20);
		opt2 = new JTextField("", 20);
		opt3 = new JTextField("", 20);
		opt4 = new JTextField("", 20);
		opt1.setFont(QuizMaker.smallFont);
		opt2.setFont(QuizMaker.smallFont);
		opt3.setFont(QuizMaker.smallFont);
		opt4.setFont(QuizMaker.smallFont);
		group = new ButtonGroup();
		oRad1 = new JRadioButton();
		oRad2 = new JRadioButton();
		oRad3 = new JRadioButton();
		oRad4 = new JRadioButton();
		oRad1.addActionListener(onClick);
		oRad2.addActionListener(onClick);
		oRad3.addActionListener(onClick);
		oRad4.addActionListener(onClick);
		group.add(oRad1);
		group.add(oRad2);
		group.add(oRad3);
		group.add(oRad4);
		cancelButt = new JButton("Cancel");
		cancelButt.setFont(QuizMaker.defaultFont);
		cancelButt.addActionListener(onClick);
		saveButt = new JButton("Save");
		saveButt.setFont(QuizMaker.defaultFont);
		saveButt.addActionListener(onClick);
		saveButt.setEnabled(false);
		
		qRoot.add(mainPanel);
		mainPanel.add(questionPanel);
		mainPanel.add(optionPanel1);
		mainPanel.add(optionPanel2);
		mainPanel.add(optionPanel3);
		mainPanel.add(optionPanel4);
		mainPanel.add(buttPanel);
		
		questionPanel.add(qhelp);
		questionPanel.add(questionBox);
		optionPanel1.add(oLabel1);
		optionPanel1.add(oRad1);
		optionPanel1.add(opt1);
		optionPanel2.add(oLabel2);
		optionPanel2.add(oRad2);
		optionPanel2.add(opt2);
		optionPanel3.add(oLabel3);
		optionPanel3.add(oRad3);
		optionPanel3.add(opt3);
		optionPanel4.add(oLabel4);
		optionPanel4.add(oRad4);
		optionPanel4.add(opt4);
		buttPanel.add(cancelButt);
		buttPanel.add(saveButt);
		
		qRoot.setVisible(true);
	}

}







