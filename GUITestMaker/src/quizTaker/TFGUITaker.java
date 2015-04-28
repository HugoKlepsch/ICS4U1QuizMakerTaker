/*
		 Title: TFGUITaker.java
		 Programmer: hugo
		 Date of creation: Apr 25, 2015
		 Description: 
*/


package quizTaker;

import java.awt.Color;
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

import questions.TrueFalse;
import quizMaker.QuizMaker;

/**
 * @author hugo
 *
 */
public class TFGUITaker {
	static TrueFalse origQues;
	static JFrame qRoot;
	static JPanel mainPanel, qPanel, ansPanel, buttPanel;
	static JLabel ansHelp, question;
	static ButtonGroup group;
	static JRadioButton ansTrueRad, ansFalseRad;
	static JButton submitButt, closeButt;
	static ButtonHandler onCLick = new ButtonHandler();
	
	private static class ButtonHandler implements ActionListener{
		Color correct = new Color(0x0ff00);
		Color incorrect = new Color(0xff0000);
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ansTrueRad || e.getSource() == ansFalseRad) {
				submitButt.setEnabled(true);
			} else if (e.getSource() == submitButt) {
				submitButt.setEnabled(false);
				closeButt.setEnabled(true);
				ansTrueRad.setEnabled(false);
				ansFalseRad.setEnabled(false);
				checkAns();
				//TODO save answer
			} else if (e.getSource() == closeButt) {
				qRoot.dispose();
			}
		}
		
		private void checkAns() {
			if (ansTrueRad.isSelected()) { //if true is selected,
				if (origQues.checkAns(true)) { //and true is correct
					ansTrueRad.setBackground(correct);
				} else { //true is not correct
					ansTrueRad.setBackground(incorrect);
					ansFalseRad.setBackground(correct);
				}
			} else { //if false is selected
				if (origQues.checkAns(false)) { //and false is correct
					ansFalseRad.setBackground(correct);
				} else { //false is not correct
					ansFalseRad.setBackground(incorrect);
					ansTrueRad.setBackground(correct);
				}
			}
		}
		
	}
	
	public TFGUITaker(TrueFalse question){
		this.origQues = question;
		
		qRoot = new JFrame("True/False question");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		qRoot.setBounds(0, 0, (int) (dm.width / 1.2), (dm.height / 2));
		
		mainPanel = new JPanel(new GridLayout(0,  1));
		qPanel = new JPanel(new GridLayout(0, 1));
		ansPanel = new JPanel(new GridLayout(0, 3, 10, 10));
		buttPanel = new JPanel(new GridLayout(1, 1, 10, 10));
		
		ansHelp = new JLabel("Select the correct answer: ");
		ansHelp.setFont(QuizMaker.smallFont);
		this.question = new JLabel();
		this.question.setFont(QuizMaker.defaultFont);
		this.question.setText(question.getQuestion());
//		System.out.println(question.getQuestion());
		group = new ButtonGroup();
		ansTrueRad = new JRadioButton("True");
		ansTrueRad.setFont(QuizMaker.defaultFont);
		ansTrueRad.addActionListener(onCLick);
		ansFalseRad = new JRadioButton("False");
		ansFalseRad.setFont(QuizMaker.defaultFont);
		ansFalseRad.addActionListener(onCLick);
		group.add(ansFalseRad);
		group.add(ansTrueRad);
		submitButt = new JButton("Check answers and save");
		submitButt.setFont(QuizMaker.defaultFont);
		submitButt.addActionListener(onCLick);
		closeButt = new JButton("Close");
		closeButt.setFont(QuizMaker.defaultFont);
		closeButt.addActionListener(onCLick);
		
		qRoot.add(mainPanel);
		mainPanel.add(qPanel);
		mainPanel.add(ansPanel);
		mainPanel.add(buttPanel);
		
		qPanel.add(this.question);
		ansPanel.add(ansHelp);
		ansPanel.add(ansTrueRad);
		ansPanel.add(ansFalseRad);
		buttPanel.add(submitButt);
		buttPanel.add(closeButt);
				
		submitButt.setEnabled(false);
		closeButt.setEnabled(false);
		qRoot.setVisible(true);
	}
	
	
}







