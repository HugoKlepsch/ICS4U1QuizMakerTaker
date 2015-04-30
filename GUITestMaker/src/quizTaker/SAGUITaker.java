/*
		 Title: SAGUITaker.java
		 Programmer: graham
		 Date of creation: Apr 25, 2015
		 Description: the GUI for answering short answer questions
*/


package quizTaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import questions.ShortAnswer;
import quizMaker.QuizMaker;


/**
 * @author graham
 *
 */
public class SAGUITaker {
	//all GUI elements
	static ShortAnswer origQues;
	static JFrame qRoot;
	static JPanel mainPanel, qPanel, ansPanel, buttPanel;
	static JLabel ansHelp, question, correctAns;
	static JButton submitButt, closeButt;
	static JTextField userAns;
	static ButtonHandler onCLick = new ButtonHandler();
	//button handler class
	private static class ButtonHandler implements ActionListener{
		//setting colours for later
		Color correct = new Color(0x0ff00);
		Color incorrect = new Color(0xff0000);
		Color black = new Color(0x000000);
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			//submits question and checks answer
			if (e.getSource() == submitButt) {
				submitButt.setEnabled(false);
				closeButt.setEnabled(true);
				userAns.setEnabled(false);
				userAns.setDisabledTextColor(black);
				try {
					checkAns();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			//closes window
			} else if (e.getSource() == closeButt) {
				qRoot.dispose();
			}
		}
		//checks the user's answer and stores it and displays in window
		private void checkAns() throws IOException{
			String userAnsText = userAns.getText();
			if(userAnsText.equalsIgnoreCase("")){
				userAns.setBackground(incorrect);
				correctAns.setBackground(correct);
				correctAns.setText(origQues.getAnswer());
				QuizTaker.markWriter.setQuestionStatus(markWriter.incorrect, origQues.getQuesNum());
			} else{
				if(origQues.checkAnswer(userAnsText)){
					userAns.setBackground(correct);
					QuizTaker.markWriter.setQuestionStatus(markWriter.correct, origQues.getQuesNum());
					
				} else{
					userAns.setBackground(incorrect);
					correctAns.setBackground(correct);
					correctAns.setText(origQues.getAnswer());
					QuizTaker.markWriter.setQuestionStatus(markWriter.incorrect, origQues.getQuesNum());
					//TODO save answer
				}
			}
			QuizTaker.markWriter.writeMarks();
		}
	}
	//constructor that intializes GUI elements
	public SAGUITaker(ShortAnswer question){
		origQues = question;
		
		qRoot = new JFrame("Short Answer question");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		qRoot.setBounds(0, 0, (int) (dm.width / 1.2), (dm.height / 2));
		
		mainPanel = new JPanel(new GridLayout(0,  1));
		qPanel = new JPanel(new GridLayout(0, 1));
		ansPanel = new JPanel(new GridLayout(0, 3, 10, 10));
		buttPanel = new JPanel(new GridLayout(1, 1, 10, 10));
		
		ansHelp = new JLabel("Enter the correct answer: ");
		ansHelp.setFont(QuizMaker.smallFont);
		SAGUITaker.question = new JLabel();
		SAGUITaker.question.setFont(QuizMaker.defaultFont);
		SAGUITaker.question.setText(origQues.getQuestion());
		userAns = new JTextField();
		userAns.setFont(QuizMaker.defaultFont);
		correctAns = new JLabel("");
		correctAns.setFont(QuizMaker.defaultFont);
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
		
		qPanel.add(SAGUITaker.question);
		ansPanel.add(ansHelp);
		ansPanel.add(userAns);
		ansPanel.add(correctAns);
		buttPanel.add(submitButt);
		buttPanel.add(closeButt);
		
		submitButt.setEnabled(true);
		closeButt.setEnabled(false);
		qRoot.setVisible(true);
		
	}
		
	
			

}