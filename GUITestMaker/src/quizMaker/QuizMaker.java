/*
		 Title: QuizMaker.java
		 Programmer: hugo
		 Date of creation: Apr 20, 2015
		 Description: 
*/


package quizMaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * @author hugo
 *
 */
public class QuizMaker {

	public static JFrame frame;
	private static JPanel mainPanel;
	private static Color puce = new Color(0xbbbbbb);
	private static JButton trueFalseButt, multiCButt, shortAButt, saveButt;
	public static Font defaultFont = new Font("Ubuntu", 1, 27);
	public static Font smallFont = new Font("Ubuntu", 1, 13);
	public static ButtonHandler onClick = new ButtonHandler();
	public static QStorage qStorage = new QStorage("quiz.quiz");
	public static JTextArea allQ;
	

	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new QuizMaker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static class ButtonHandler implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			
			if (e.getSource() == trueFalseButt) {
				addTF();
			} else if (e.getSource() == multiCButt) {
				addMC();
			} else if (e.getSource() == shortAButt) {
				addSA();
			} else if (e.getSource() == saveButt) {
				try {
					qStorage.writeToFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frame.dispose();
			}
			
			
		}
		
	}
	
	/**
	 * Create the application.
	 */
	public QuizMaker() {
		initialize();
		//topkkek
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		frame.setBounds(0, 0, dm.width, dm.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);

		
		mainPanel = new JPanel(new GridLayout(0, 3, 10, 10));
		mainPanel.setBackground(puce);
		
		trueFalseButt = new JButton("New True/False question");
		trueFalseButt.setFont(defaultFont);
		trueFalseButt.addActionListener(onClick);
		multiCButt = new JButton("New multiple-Choice question");
		multiCButt.setFont(defaultFont);
		multiCButt.addActionListener(onClick);
		shortAButt = new JButton("New short answer question");
		shortAButt.setFont(defaultFont);
		shortAButt.addActionListener(onClick);
		saveButt = new JButton("Save to quiz then file");
		saveButt.setFont(defaultFont);
		saveButt.addActionListener(onClick);
		allQ = new JTextArea();
		allQ.setFont(new Font("Ubuntu", 1, 14));
		allQ.setEditable(false);
		
		
		
		
		
		frame.add(mainPanel);
		mainPanel.add(trueFalseButt);
		mainPanel.add(multiCButt);
		mainPanel.add(shortAButt);
		mainPanel.add(saveButt);
		mainPanel.add(allQ);
		
	}
	
	
	/**
		 * @author hugo
		 * Date of creation: Apr 10, 2015 
		 * @param: None
		 * @return: None
		 * @Description: ( ͡° ͜ʖ ͡°)
		 */
	private static void addTF() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TFGUI();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
		 * @author hugo
		 * Date of creation: Apr 10, 2015 
		 * @param: None
		 * @return: None
		 * @Description: ( ͡° ͜ʖ ͡°)
		 */
	private static void addMC() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MCGUI();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
		 * @author hugo
		 * Date of creation: Apr 10, 2015 
		 * @param: None
		 * @return: None
		 * @Description: ( ͡° ͜ʖ ͡°)
		 */
	private static void addSA() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SAGUI();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
