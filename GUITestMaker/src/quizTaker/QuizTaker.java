
package quizTaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import questions.MultiChoice;
import questions.ShortAnswer;
import questions.TrueFalse;
import quizMaker.QStorage;
import quizMaker.QuizMaker;
import quizMaker.TFGUI;



public class QuizTaker {
	public static JFrame frame;
	private static JPanel mainPanel, userNamePanel, userNameHelpPanel;
	private static Color puce = new Color(0xcc8899);
	public static Font defaultFont = QuizMaker.defaultFont;
	public static Font smallFont = QuizMaker.smallFont;
	public static ButtonHandler onClick = new ButtonHandler();
	public static QStorage qStorage = new QStorage("quiz.quiz");
	public static JButton nextButt;
	public static JButton quitButt;
	public static JTextArea allA, userName;
	public static JButton saveNameButt;
	public static JLabel userNameHelp;
	public static int qCount = 0;
	public static markWriter markWriter;
	

	//added a comment to commit lel
	
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizTaker window = new QuizTaker();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private static class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == nextButt) {
				QuizTaker.openNextQ();
			} else if (e.getSource() == quitButt) {
				frame.dispose();
			} else if (e.getSource() == saveNameButt) {
				markWriter.setUserName(userName.getText());
				try {
					markWriter.writeMarks();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	
	public QuizTaker() throws IOException{
		intialize();
	}
	private void intialize() throws IOException{
		qStorage.readFile();
		int a = 9;
		int b = 4;
		while (!qStorage.isDoneReading) {
			//swap for lels
			a = a + b;
			b = a - b;
			a = a - b;
			System.out.println(a);
			System.out.println(b);
		}
		markWriter = new markWriter(qStorage, "QuizResult.quiz");
		frame = new JFrame();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		frame.setBounds(0, 0, dm.width, dm.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);

		
		mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		mainPanel.setBackground(puce);
		
		nextButt = new JButton("Next Question");
		nextButt.addActionListener(onClick);
		quitButt = new JButton("Quit Quiz");
		quitButt.addActionListener(onClick);
		allA = new JTextArea();
		allA.setFont(new Font("Ubuntu", 1, 14));
		allA.setEditable(false);
		userNamePanel = new JPanel(new GridLayout(0, 1, 10, 10));
		userNameHelpPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		userNameHelp = new JLabel("Enter your name");
		userNameHelp.setFont(QuizMaker.defaultFont);
		userName = new JTextArea();
		userName.setEditable(true);
		userName.setFont(defaultFont);
		saveNameButt = new JButton("Save name");
		saveNameButt.setFont(defaultFont);
		saveNameButt.addActionListener(onClick);

		
		frame.add(mainPanel);
		mainPanel.add(allA);
		mainPanel.add(quitButt);
		mainPanel.add(nextButt);
		mainPanel.add(userNamePanel);
		userNamePanel.add(userNameHelpPanel);
		userNameHelpPanel.add(userNameHelp);
		userNameHelpPanel.add(saveNameButt);
		userNamePanel.add(userName);
	}
	/**
		 * @author hugo
		 * Date of creation: Apr 10, 2015 
		 * @param: None
		 * @return: None
		 * @Description: ( ͡° ͜ʖ ͡°)
		 */
	public static void openNextQ() {
		qCount++;
		int result = qStorage.getCurrentQType();
//		System.out.println("openNextQ result" + result);
		switch (result) {
		case QStorage.tf:
			TrueFalse tempTFQ = qStorage.getNextQTF();
			tempTFQ.setQuesNum(qCount);
//			System.out.println("openNextQ tempQ.getQuestion()" + tempQ.getQuestion());
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new TFGUITaker(tempTFQ);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			if (qStorage.getCurrentQType() == 0) {
				nextButt.setEnabled(false);
			}
			break;
		case QStorage.mc:
			MultiChoice tempMCQ = qStorage.getNextQMC();
			tempMCQ.setQuesNum(qCount);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new MCGUITaker(tempMCQ);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			if (qStorage.getCurrentQType() == 0) {
				nextButt.setEnabled(false);
			}
			break;
		case QStorage.sa:
			ShortAnswer tempSAQ = qStorage.getNextQSA();
			tempSAQ.setQuesNum(qCount);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new SAGUITaker(tempSAQ);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			if (qStorage.getCurrentQType() == 0) {
				nextButt.setEnabled(false);
			}
			break;
			
		default:
			System.err.println("Unknown question type. ");
			new Exception().printStackTrace(System.out);
			break;
		}
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	


}
