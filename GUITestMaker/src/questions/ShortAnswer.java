/*
		 Title: ShortAnswer.java
		 Programmer: hugo
		 Date of creation: Apr 20, 2015
		 Description: class to hold the all the information required to hold a short answer question
 */

package questions;

/**
 * @author hugo and graham
 *
 */
public class ShortAnswer {
	String type = "SA"; // short identifier for easy identification in the quiz.quiz file
	String question;
	String answer;
	int quesNum; // used to help order the questions when taking the test.

	/**
	 * @param question is the question
	 * @param answer is the answer
	 * ^^^^^ Graham please I think that is obvious. 
	 */

	public ShortAnswer(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

	/**
	 * 
	 * @param question is the question
	 * @param answer is the answer
	 * @param quesNum is the question number identifier, used to take the questions in the same order that they are made. 
	 */
	public ShortAnswer(String question, String answer, int quesNum) {
		super();
		this.question = question;
		this.answer = answer;
		this.quesNum = quesNum;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param set the question
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param set the answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
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
	 * @param answer the user's answer to check
	 * @return true if correct, false if incorrect
	 */
	public boolean checkAnswer(String answer) {
		if (answer.equalsIgnoreCase(this.answer)) // use ignore case to ensure that it doesn't matter the case of the
													// answer.
			return true;

		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		/*
		 * this method is used to give an attractive output on the screen after the user has added the question. 
		 */
		String question = "Question: " + this.question + "\n";
		String answer = "Answer: " + this.answer + "\n";
		return question + answer;
	}

}