/*
		 Title: SAGUI.java
		 Programmer: hugo
		 Date of creation: Apr 23, 2015
		 Description: This is a class that facilitates the creation of windows to hold a short answer question. it makes a new window and all the elements required to show the data
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
	/*
	 * The following are the variable and swing components that compose the window.
	 */
	static JFrame qRoot;
	static JPanel mainPanel, questionPanel, ansPanel, buttPanel;
	static JLabel qHelp, ansHelp;
	static JTextField questionBox, ansBox;
	static JButton cancelButt, saveButt;
	static ButtonHandler onClick = new ButtonHandler();

	// our button handler class
	private static class ButtonHandler implements ActionListener {
		boolean hasEnteredQuestion = false;
		boolean hasEnteredAns = false;// these are flip-flop variables to keep track of if the user has inputed
										// their question or not.

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cancelButt) {
				qRoot.dispose(); // releases all the components used in the windows and kill it
				hasEnteredAns = hasEnteredQuestion = false; // resets variables for the next question (it doesn't reset
															// without this because buttonhandler is static)

			} else if (e.getSource() == saveButt) { // creates a short answer question and saves it in the qStorage
													// object and closes the window
				ShortAnswer tempObj = new ShortAnswer(questionBox.getText(), ansBox.getText());
				QuizMaker.qStorage.addSA(tempObj); // stores the question in the question storage class
				QuizMaker.allQ.setText(QuizMaker.allQ.getText() + tempObj.toString()); // updates the main window
																						// question feed.
				qRoot.dispose(); // kills the window, not the program.
				hasEnteredAns = hasEnteredQuestion = false; // resetting static variables.
			} else if (e.getSource() == questionBox) {
				hasEnteredQuestion = true;
				if (hasEnteredAns && hasEnteredQuestion) {
					saveButt.setEnabled(true); // only enables the save button if the user has entered something into
												// both answer and question boxes
				}
			} else if (e.getSource() == ansBox) {
				hasEnteredAns = true;
				if (hasEnteredAns && hasEnteredQuestion) {
					saveButt.setEnabled(true); // only enables the save button if the user has entered something into
												// both answer and question boxes
				}
			}
		}

	}

	/**
	 * Just like MCGUI and TFGUI, SAGUI creates a gui to help the user make a single short answer question. 
	 */
	public SAGUI() {
		// sets up the GUI
		qRoot = new JFrame("Short answer question");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		qRoot.setBounds(0, 0, (int) (dm.width / 1.2), (int) (dm.height / 1.1)); // window dimensions based off of
																				// resolution ratios.

		mainPanel = new JPanel(new GridLayout(0, 1));
		questionPanel = new JPanel(new GridLayout(1, 2));
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
		ansBox.addActionListener(onClick); // despite not being a button, a box can have a actionlistener. It activates
											// when the user hits enter with the cursor inside the box.
		cancelButt = new JButton("Cancel");
		cancelButt.setFont(QuizMaker.defaultFont);
		cancelButt.addActionListener(onClick);
		saveButt = new JButton("Save");
		saveButt.setFont(QuizMaker.defaultFont);
		saveButt.addActionListener(onClick);
		saveButt.setEnabled(false);

		qRoot.add(mainPanel); //panel gridding
		mainPanel.add(questionPanel);
		mainPanel.add(ansPanel);
		mainPanel.add(buttPanel);

		questionPanel.add(qHelp); //component gridding
		questionPanel.add(questionBox);
		ansPanel.add(ansHelp);
		ansPanel.add(ansBox);
		buttPanel.add(cancelButt);
		buttPanel.add(saveButt);

		qRoot.setVisible(true); //making it visible. 
	}
}
