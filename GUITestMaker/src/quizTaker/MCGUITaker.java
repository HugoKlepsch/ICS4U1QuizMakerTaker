/*
		 Title: MCGUITaker.java
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
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import questions.MultiChoice;
import quizMaker.QuizMaker;

/**
 * @author hugo
 *
 */
public class MCGUITaker {
	public static MultiChoice origQues;
	static JFrame qRoot;
	static JPanel mainPanel, qPanel, ansPanel, ansRadPanel, buttPanel;
	static JLabel questionLabel, ansHelp;
	static ButtonGroup group;
	static JRadioButton rad1, rad2, rad3, rad4;
	static JButton closeButt, submitButt;
	static ButtonHandler onClick = new ButtonHandler();	
	
	private static class ButtonHandler implements ActionListener{
		Color correct = new Color(0x0ff00);
		Color incorrect = new Color(0xff0000);
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submitButt) {
				submitButt.setEnabled(false);
				closeButt.setEnabled(true);
				rad1.setEnabled(false);
				rad2.setEnabled(false);
				rad3.setEnabled(false);
				rad4.setEnabled(false);
				try {
					checkAns();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//TODO save
			} else if (e.getSource() == closeButt) {
				qRoot.dispose();
			}
		}
		
		private void checkAns() throws IOException {
			int correctInd = MCGUITaker.origQues.getCorrectInd();
			int selectedInd = 0;
			JRadioButton[] radArray = {rad1, rad2, rad3, rad4};
			if (rad1.isSelected()) {
				selectedInd = 0;
			} else if (rad2.isSelected()) {
				selectedInd = 1;
			} else if (rad3.isSelected()) {
				selectedInd = 2;
			} else if (rad4.isSelected()) {
				selectedInd = 3;
			}
			
			if (selectedInd == correctInd) {
				radArray[selectedInd].setBackground(correct);
				QuizTaker.markWriter.setQuestionStatus(markWriter.correct, origQues.getQuesNum());
			} else {
				radArray[selectedInd].setBackground(incorrect);
				radArray[correctInd].setBackground(correct);
				QuizTaker.markWriter.setQuestionStatus(markWriter.incorrect, origQues.getQuesNum());
			}
			QuizTaker.markWriter.writeMarks();
			
		}
		
	}
	
	public MCGUITaker(MultiChoice question){
		origQues = question;
		
		qRoot = new JFrame("True/False question");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		qRoot.setBounds(0, 0, (int) (dm.width / 1.2), (dm.height / 2));
		
		mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		qPanel = new JPanel(new GridLayout(1, 1, 10, 10));
		ansPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		ansRadPanel = new JPanel(new GridLayout(0, 4, 10, 10));
		buttPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		
		questionLabel = new JLabel(origQues.getQuestion());
		questionLabel.setFont(QuizMaker.defaultFont);
		ansHelp = new JLabel("Select the correct answer");
		ansHelp.setFont(QuizMaker.defaultFont);
		group = new ButtonGroup();
		String[] options = origQues.getOptions();
		rad1 = new JRadioButton(options[0]);
		rad2 = new JRadioButton(options[1]);
		rad3 = new JRadioButton(options[2]);
		rad4 = new JRadioButton(options[3]);
		group.add(rad1);
		group.add(rad2);
		group.add(rad3);
		group.add(rad4);
		closeButt = new JButton("Close");
		closeButt.setFont(QuizMaker.defaultFont);
		closeButt.setEnabled(false);
		closeButt.addActionListener(onClick);
		submitButt = new JButton("Check answers and save");
		submitButt.setFont(QuizMaker.defaultFont);
		submitButt.addActionListener(onClick);
		
		qRoot.add(mainPanel);
		mainPanel.add(qPanel);
		mainPanel.add(ansPanel);
		mainPanel.add(ansRadPanel);
		mainPanel.add(buttPanel);
		
		qPanel.add(questionLabel);
		ansPanel.add(ansHelp);
		ansPanel.add(ansRadPanel);
		ansRadPanel.add(rad1);
		ansRadPanel.add(rad2);
		ansRadPanel.add(rad3);
		ansRadPanel.add(rad4);
		buttPanel.add(submitButt);
		buttPanel.add(closeButt);
		
		
		
		qRoot.setEnabled(true);
		qRoot.setVisible(true);
	}
	
}











