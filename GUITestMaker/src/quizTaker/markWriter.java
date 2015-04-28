/*
		 Title: markWriter.java
		 Programmer: hugo
		 Date of creation: Apr 28, 2015
		 Description: 
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
	final int unanswered = 0;
	final int correct = 1;
	final int incorrect = 2;
	int totalQuestions = 0;
	double average;
	String userName;
	String filename;
	PrintWriter fileout;
	int[] questionStatus;
	
	/**
	 * @throws IOException 
	 * 
	 */
	public markWriter(QStorage qstorage, String userName, String filename) throws IOException {
		this.filename = filename;
		fileout = new PrintWriter(new FileWriter(this.filename));
		this.totalQuestions = qstorage.getNumQues();
		questionStatus = new int[qstorage.getNumQues()];
		for (int i = 0; i < questionStatus.length; i++) {
			questionStatus[i] = unanswered;
		}
	}
	/**
	 * @throws IOException 
	 * 
	 */
	public markWriter(int totalQuestions, String userName, String filename) throws IOException {
		this.filename = filename;
		fileout = new PrintWriter(new FileWriter(this.filename));
		this.totalQuestions = totalQuestions;
		questionStatus = new int[totalQuestions];
		for (int i = 0; i < questionStatus.length; i++) {
			questionStatus[i] = unanswered;
		}
		
	}
	
	

	/**
	 * @return the correctCount
	 */
	public int getCorrectCount() {
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
	 * @return the average
	 */
	public double getAverage() {
		return ((double) getCorrectCount() / totalQuestions);
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
		this.userName = userName;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
		 * @author hugo
		 * Date of creation: Apr 10, 2015 
		 * @param: None
		 * @return: None
		 * @Description: ( ͡° ͜ʖ ͡°)
		 */
	public void writeMarks() {
		fileout.println("UserName: " + userName);
		fileout.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		fileout.println("Average: " + getAverage());
		fileout.println(getCorrectCount() + "/" + getTotalQuestions());
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
		fileout.close();
		
	}
	
	
	
}