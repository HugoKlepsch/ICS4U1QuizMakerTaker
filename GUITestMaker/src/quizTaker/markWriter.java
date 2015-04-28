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

import quizMaker.QStorage;

/**
 * @author hugo
 *
 */
public class markWriter {
	int correctCount = 0;
	int totalQuestions = 0;
	double average;
	String filename;
	PrintWriter fileout;
	
	/**
	 * @throws IOException 
	 * 
	 */
	public markWriter(QStorage qstorage, String filename) throws IOException {
		this.filename = filename;
		fileout = new PrintWriter(new FileWriter(this.filename));
	}
	/**
	 * @throws IOException 
	 * 
	 */
	public markWriter(int correctCount, int totalQuestions, String filename) throws IOException {
		this.filename = filename;
		fileout = new PrintWriter(new FileWriter(this.filename));
		this.correctCount = correctCount;
		this.totalQuestions = totalQuestions;
		
	}
	

	/**
	 * @return the correctCount
	 */
	public int getCorrectCount() {
		return correctCount;
	}
	/**
	 * @param correctCount the correctCount to set
	 */
	public void setCorrectCount(int correctCount) {
		this.correctCount = correctCount;
	}
	/**
	 * @return the totalQuestions
	 */
	public int getTotalQuestions() {
		return totalQuestions;
	}
	/**
	 * @param totalQuestions the totalQuestions to set
	 */
	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	/**
	 * @return the average
	 */
	public double getAverage() {
		return ((double) correctCount / totalQuestions);
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	
	
	
	
	
}
