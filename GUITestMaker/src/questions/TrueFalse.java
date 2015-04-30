/*
		 Title: TrueFalse.java
		 Programmer: hugo
		 Date of creation: Apr 20, 2015
		 Description: class to hold the all the information required to hold a true / false question
*/


package questions;

/**
 * @author hugo and graham
 *
 */
public class TrueFalse {
	String type = "TF"; // short identifier for easy identification in the quiz.quiz file
	String question;
	boolean correctAns;
	int quesNum; // used to help order the questions when taking the test.
	
	/**
	 * @param question is the question
	 * @param correctAns is the correct answer, true or false
	 */
	public TrueFalse(String question, boolean correctAns) {
		super();
		this.question = question;
		this.correctAns = correctAns;
	}
	/**
	 * 
	 * @param question is the question
	 * @param correctAns is the correct answer, true or false
	 * @param quesNum is the question number identifier
	 */
	public TrueFalse(String question, boolean correctAns, int quesNum) {
		super();
		this.question = question;
		this.correctAns = correctAns;
		this.quesNum = quesNum;
	}
	
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the correctAns
	 */
	public boolean getCorrectAns() {
		return correctAns;
	}
	/**
	 * @param correctAns the correctAns to set
	 */
	public void setCorrectAns(boolean correctAns) {
		this.correctAns = correctAns;
	}
	/**
	 * 
	 * @return the question number
	 */
	public int getQuesNum() {
		return quesNum;
	}
	/**
	 * 
	 * @param quesNum is the question number
	 */
	public void setQuesNum(int quesNum) {
		this.quesNum = quesNum;
	}

	/**
	 * @author Graham
	 * @param userAnswer the user's answer to check
	 * @return true if correct, false if incorrect
	 */
	public boolean checkAns(boolean userAnswer){
		if(userAnswer == this.correctAns)
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		/*
		 * this method is used to give an attractive output on the screen after the user has added the question. 
		 */
		String question = "Question: " + this.question + "\n";
		String answer = "Answer: " + this.correctAns + "\n";
		return question + answer;
	}
	
	
	

}