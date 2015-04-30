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
	public final static int unanswered = 0;
	public final static int correct = 1;
	public final static int incorrect = 2;
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
		this.userName = userName;
		
		this.totalQuestions = QStorage.totalQ;
//		System.out.println("totalQuestions" + totalQuestions);
		questionStatus = new int[this.totalQuestions];
		for (int i = 0; i < questionStatus.length; i++) {
			questionStatus[i] = unanswered;
		}
	}
	/**
	 * @throws IOException 
	 * 
	 */
	public markWriter(QStorage qstorage, String filename) throws IOException {
		this.filename = filename;
		this.userName = "No Name Entered";
//		this.totalQuestions = qstorage.getNumQues();
		this.totalQuestions = QStorage.totalQ;
//		System.out.println("totalQuestions" + totalQuestions);

		System.out.println(this.totalQuestions);
		questionStatus = new int[this.totalQuestions];
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
		this.userName = userName;
//		this.totalQuestions = totalQuestions;
		this.totalQuestions = QStorage.totalQ;
//		System.out.println("totalQuestions" + totalQuestions);

		questionStatus = new int[this.totalQuestions];
		for (int i = 0; i < questionStatus.length; i++) {
			questionStatus[i] = unanswered;
		}
		
	}
	/**
	 * @throws IOException 
	 * 
	 */
	public markWriter(int totalQuestions, String filename) throws IOException {
		this.filename = filename;
		this.userName = "No Name Entered";
//		this.totalQuestions = totalQuestions;
		this.totalQuestions = QStorage.totalQ;
//		System.out.println("totalQuestions" + totalQuestions);

		questionStatus = new int[this.totalQuestions];
		for (int i = 0; i < questionStatus.length; i++) {
			questionStatus[i] = unanswered;
		}	
	}
	
	
	/**
	 * @param questionStatus the questionStatus to set
	 */
	public void setQuestionStatus(int status, int index) {
//		System.out.println("index - 1 = " + (index - 1));
		this.questionStatus[index - 1] = status;
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
	 * @throws IOException 
		 * @Description: ( ͡° ͜ʖ ͡°)
		 */
	public void writeMarks() throws IOException {
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())
		fileout = new PrintWriter(new FileWriter(this.filename));
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
