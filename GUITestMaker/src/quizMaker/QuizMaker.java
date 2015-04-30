/*
		 Title: QuizMaker.java
		 Programmer: hugo
		 Date of creation: Apr 20, 2015
		 Description: The main window that allows the user to select between different types of questions that they might want to make. 
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
	private static Color puce = new Color(0xcc8899);
	private static JButton trueFalseButt, multiCButt, shortAButt, saveButt;
	public static Font defaultFont = new Font("Ubuntu", 1, 27); // big font for titles and important text
	public static Font smallFont = new Font("Ubuntu", 1, 13); // small font for less importatn things
	public static ButtonHandler onClick = new ButtonHandler();
	public static QStorage qStorage = new QStorage("quiz.quiz"); // the file to save the quiz under, also the object
																	// that helps keep track of the already made
																	// questions.
	public static JTextArea allQ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // this spawns a new thread because gui elements are not thread safe:
												// they pause the thread until something happens.
					public void run() {
						try {
							new QuizMaker(); // no explicit refs means garbage collection happens quickly once the
												// window closes.
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}

	private static class ButtonHandler implements ActionListener {

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == trueFalseButt) {
				addTF(); //calls the method that spawns a new truefalse question maker gui. 
			} else if (e.getSource() == multiCButt) {
				addMC(); //calls the method that spawns a new multiple choice question maker gui. 
			} else if (e.getSource() == shortAButt) {
				addSA(); //calls the method that spawns a new short answer question maker gui. 
			} else if (e.getSource() == saveButt) {
				try {
					qStorage.writeToFile(); //when the user signals that they are done, write out to file. 
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frame.dispose(); //kills the window. 
			}

		}

	}

	/**
	 * Create the application.
	 */
	public QuizMaker() {
		initialize(); //why is this a default methode....
		// topkkek
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * init the gui
		 * Just like all the other gui classes, read MCGUI for more detailed comments. 
		 */
		frame = new JFrame();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		frame.setBounds(0, 0, dm.width, dm.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH); //this line ensures that the window starts fullscreen

		mainPanel = new JPanel(new GridLayout(0, 3, 10, 10)); //easy gridding mode. 
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
		mainPanel.add(shortAButt); //adding all the elements to the screen. 
		mainPanel.add(saveButt);
		mainPanel.add(allQ);

	}

	/**
		 * @author hugo
		 * Date of creation: Apr 10, 2015 
		 * @param: None
		 * @return: None
		 * @Description: This method starts a new thread with a new window of the true false gui maker. 
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
		 * @Description: This method starts a new thread with a new window of the true false gui maker. 
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
		 * @Description: This method starts a new thread with a new window of the true false gui maker. 
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
