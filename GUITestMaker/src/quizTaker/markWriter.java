/*
		 Title: markWriter.java
		 Programmer: hugo
		 Date of creation: Apr 28, 2015
		 Description: A class that aids the program to write the quiz details to file. It formats the results from each taken question and writes out date, time, question result, and marks. 
 */

package quizTaker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import quizMaker.QStorage;

/**
 * @author hugo
 *
 */
public class markWriter {
	public final static int unanswered = 0; // these are used to make it more obvious that 0 is unanswered, 1 is correct
											// etc.
	public final static int correct = 1;
	public final static int incorrect = 2;
	int totalQuestions = 0;
	double average;
	String userName;
	PrintWriter fileout;
	int[] questionStatus; // an array that holds an int that shows status of the question (correct, incorrect,
							// unanswered)
	String startDate; // use a single date because otherwise a new file would appear with each new question.

	/**
	 * @throws IOException 
	 * @param qstorage object that is made in quizmaker. 
	 */
	public markWriter(QStorage qstorage, String userName) throws IOException {
		this.userName = userName;

		this.totalQuestions = QStorage.totalQ;
		questionStatus = new int[this.totalQuestions];
		for (int i = 0; i < questionStatus.length; i++) {
			questionStatus[i] = unanswered; // init the array ot unanswered
		}
		startDate = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date()); // use dots because slashes don't
																					// work in filename.
	}

	/**
	 * @throws IOException 
	 * 
	 */
	public markWriter(QStorage qstorage) throws IOException {
		this.userName = "No Name Entered";
		this.totalQuestions = QStorage.totalQ;

		questionStatus = new int[this.totalQuestions];
		for (int i = 0; i < questionStatus.length; i++) {
			questionStatus[i] = unanswered; // init the array ot unanswered
		}
		startDate = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date()); // use dots because slashes don't
																					// work in filename.
	}

	/**
	 * @throws IOException 
	 * 
	 */
	public markWriter(int totalQuestions, String userName) throws IOException {
		this.userName = userName;
		
		this.totalQuestions = QStorage.totalQ;
		

		questionStatus = new int[this.totalQuestions];
		for (int i = 0; i < questionStatus.length; i++) {
			questionStatus[i] = unanswered;
		}
		startDate = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
	}

	/**
	 * @throws IOException 
	 * 
	 */
	public markWriter(int totalQuestions) throws IOException {
		this.userName = "No Name Entered";
		this.totalQuestions = QStorage.totalQ;

		questionStatus = new int[this.totalQuestions];
		for (int i = 0; i < questionStatus.length; i++) {
			questionStatus[i] = unanswered; // init the array ot unanswered
		}
		startDate = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date()); // use dots because slashes don't
																					// work in filename.
	}

	/**
	 * @param questionStatus the questionStatus to set
	 */
	public void setQuestionStatus(int status, int index) { // this is called for each question and sets the index to the
															// status.
		this.questionStatus[index - 1] = status;
	}

	/**
	 * @return the correctCount
	 */
	public int getCorrectCount() { // iterates through the question array to check how many are correct.
		int count = 0;
		for (int i = 0; i < questionStatus.length; i++) {
			switch (questionStatus[i]) {
			case correct:
				count++;
				break;

			default:
				break;
			}
		}
		return count;
	}

	/**
	 * @return the totalQuestions
	 */
	public int getTotalQuestions() {
		return totalQuestions;
	}

	/**
	 * @return the average in percent
	 */
	public double getAverage() {
		return ((double) getCorrectCount() / totalQuestions) * 100;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName; // set the username to use.
	}



	/**
		 * @author hugo
		 * Date of creation: Apr 10, 2015 
		 * @param: None
		 * @return: None
		 * @throws IOException 
		 * @Description: goes through the whole array of questions that have been answered and writes the status, as well as some other information about the quiz taken. 
		 */
	public void writeMarks() throws IOException {
		fileout = new PrintWriter(new FileWriter(userName + " - " + startDate)); // the filename is based off of the
																					// username and the time that they
																					// started.
		fileout.println("UserName: " + userName);
		fileout.println("Date taken: " + startDate);
		fileout.println("Average: %" + getAverage());
		fileout.println("Mark: " + getCorrectCount() + "/" + getTotalQuestions());
		for (int i = 0; i < questionStatus.length; i++) {
			fileout.print("Question " + i + ": ");
			switch (questionStatus[i]) {
			case correct:
				fileout.println("Correct. ");
				break;
			case incorrect:
				fileout.println("Incorrect. ");
				break;
			case unanswered:
				fileout.println("Not answered. ");
			default:
				break;
			}
		}
		fileout.close(); //closes the file so that the OS knows what to do. 

	}

}
