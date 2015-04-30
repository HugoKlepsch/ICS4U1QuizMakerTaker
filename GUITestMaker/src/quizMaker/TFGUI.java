/*
		 Title: TFGUI.java
		 Programmer: hugo
		 Date of creation: Apr 22, 2015
		 Description: This is a class that facilitates the creation of windows to hold a true/false question. it makes a new window and all the elements required to show the data
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

import questions.TrueFalse;

public class TFGUI {
	/*
	 * The following are the variable and swing components that compose the window.
	 */
	static JFrame qRoot;
	static JPanel qMainPanel, trueFalsePanel, trueFalseArrayPanel, qBoxPanel, buttPanel;
	static JLabel tfHelpText, qHelpText;
	static JTextField questionBox;
	static ButtonGroup group;
	static JRadioButton trueCorrect;
	static JRadioButton falseCorrect;
	static JButton cancelButt;
	static JButton saveButt;
	public static ButtonHandler onClick = new ButtonHandler();

	// our butthandler class.
	private static class ButtonHandler implements ActionListener {

		public boolean selectedTF = false;
		public boolean selectedQ = false; // variables to store whether the user has enter a question and selected a
											// correct answer

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// creates a new true/false question and saves it in the qStorage object
			if (e.getSource() == saveButt) { // since the sve butt is only enabled after selecting a radiobutton,
				boolean correctAns = (trueCorrect.isSelected() ? true : false); // if the true one is selected, that
																				// means the user selected true, else
																				// the user selected false
				String question = questionBox.getText();
				TrueFalse tempQues = new TrueFalse(question, correctAns);
				QuizMaker.qStorage.addTF(tempQues); //adding the question to the question database
				selectedQ = selectedTF = false; //reseting variables
				QuizMaker.allQ.setText(QuizMaker.allQ.getText() + tempQues.toString()); //updating the main window question feed
				qRoot.dispose(); //kills the window but keeps the program running. 
			} else if (e.getSource() == cancelButt) {
				selectedQ = selectedTF = false; //reset variabels
				qRoot.dispose(); //kill window

			} else if (e.getSource() == trueCorrect || e.getSource() == falseCorrect) {
				selectedTF = true;
				if (selectedTF && selectedQ) {
					saveButt.setEnabled(true); //enable the save button if the user has entered both Q and A
				}
			} else if (e.getSource() == questionBox) {
				selectedQ = true;
				if (selectedTF && selectedQ) {
					saveButt.setEnabled(true); //enable the save button if the user has entered both Q and A
				}
			}

		}

	}

	/**
	 * init the gui
	 * Just like all the other gui classes, read MCGUI for more detailed comments. 
	 */
	public TFGUI() {
		// sets up the GUI
		qRoot = new JFrame("True/False question");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		qRoot.setBounds(0, 0, (int) (dm.width / 1.2), (dm.height / 2));
		// qRoot.setDefaultCloseOperation(JFrame.);

		tfHelpText = new JLabel("Correct answer ===>");
		tfHelpText.setFont(QuizMaker.defaultFont);
		qHelpText = new JLabel("Enter question then hit ENTER");
		qHelpText.setFont(QuizMaker.defaultFont);

		group = new ButtonGroup();

		qMainPanel = new JPanel(new GridLayout(0, 1));
		trueFalsePanel = new JPanel(new GridLayout(0, 2));
		trueFalseArrayPanel = new JPanel(new GridLayout(1, 2));
		qBoxPanel = new JPanel(new GridLayout(0, 2));
		buttPanel = new JPanel(new GridLayout(0, 2, 10, 10));

		questionBox = new JTextField();
		questionBox.setFont(QuizMaker.defaultFont);
		questionBox.addActionListener(onClick);
		trueCorrect = new JRadioButton("True");
		trueCorrect.setFont(QuizMaker.defaultFont);
		trueCorrect.addActionListener(onClick);
		falseCorrect = new JRadioButton("False");
		falseCorrect.setFont(QuizMaker.defaultFont);
		falseCorrect.addActionListener(onClick);
		cancelButt = new JButton("Cancel");
		cancelButt.setFont(QuizMaker.defaultFont);
		cancelButt.addActionListener(onClick);
		saveButt = new JButton("Save and close");
		saveButt.setFont(QuizMaker.defaultFont);
		saveButt.addActionListener(onClick);

		group.add(trueCorrect);
		group.add(falseCorrect);

		qRoot.add(qMainPanel);
		qMainPanel.add(trueFalsePanel, 0);
		qMainPanel.add(qBoxPanel, 1);
		qMainPanel.add(buttPanel, 2);
		trueFalsePanel.add(tfHelpText, 0);
		trueFalsePanel.add(trueFalseArrayPanel, 1);
		trueFalseArrayPanel.add(trueCorrect);
		trueFalseArrayPanel.add(falseCorrect);
		qBoxPanel.add(qHelpText, 0);
		qBoxPanel.add(questionBox, 1);
		buttPanel.add(cancelButt, 0);
		buttPanel.add(saveButt, 1);
		saveButt.setEnabled(false);

		qRoot.setVisible(true);
	}

}