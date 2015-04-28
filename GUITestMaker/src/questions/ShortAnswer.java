/*
		 Title: ShortAnswer.java
		 Programmer: hugo
		 Date of creation: Apr 20, 2015
		 Description: 
*/


package questions;

/**
 * @author hugo
 *
 */
public class ShortAnswer {
	String type = "SA";
	String question;
	String answer;
	
	
	
	
	/**
	 * @param question
	 * @param answer
	 */
	public ShortAnswer(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
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
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @author Graham
	 * @param answer the user's answer to check
	 * @return true if correct, false if incorrect
	 */
	public boolean checkAnswer(String answer){
		if(answer.equalsIgnoreCase(this.answer))
			return true;
		
		else
			return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String question = "Question: " + this.question + "\n";
		String answer = "Answer: " + this.answer + "\n";
		return question + answer;
	}
	
	
}