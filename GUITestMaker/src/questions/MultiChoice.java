/*
		 Title: MultiChoice.java
		 Programmer: hugo
		 Date of creation: Apr 20, 2015
		 Description: 
*/


package questions;

/**
 * @author hugo and graham
 *
 */
public class MultiChoice {
	String type = "MC";
	String question;
	String[] options = new String[5];
	static final String[] ABCDE = {"a", "b", "c", "d", "e"};
	int correctInd;
	int quesNum;
	
	
	/**
	 * @param question
	 * @param options is an array of string options that the user can answer
	 * @param correctInd is the correct answer (a = 0, b = 1, c = 2 etc.)
	 */
	public MultiChoice(String question, String[] options, int correctInd) {
		super();
		this.question = question;
		this.options = options;
		this.correctInd = correctInd;
	}

	
	/**
	 * @param question
	 * @param options are the options for the multiple choice buttons, max five. (a - e inclusive)
	 * @param correctLetter is the letter (a, b, c, d, e) that is correct
	 */
	public MultiChoice(String question, String[] options, String correctLetter) {
		super();
		this.question = question;
		this.options = options;
		// assigns the correct index based on the letter
		if (correctLetter.equalsIgnoreCase("a")) {
			correctInd = 0;
		} else if (correctLetter.equalsIgnoreCase("b")) {
			correctInd = 1;
		} else if (correctLetter.equalsIgnoreCase("c")) {
			correctInd = 2;
		} else if (correctLetter.equalsIgnoreCase("d")) {
			correctInd = 3;
		} else if (correctLetter.equalsIgnoreCase("e")) {
			correctInd = 4;
		}
	}
	
	/**
	 * 
	 * @param question
	 * @param options is an array of string options that the user can answer
	 * @param correctInd is the correct answer (a = 0, b = 1, c = 2 etc.)
	 * @param quesNum is the number identifier for the question
	 */
	public MultiChoice(String question, String[] options, int correctInd, int quesNum) {
		super();
		this.question = question;
		this.options = options;
		this.correctInd = correctInd;
		this.quesNum = quesNum;
	}

	
	/**
	 * @param question
	 * @param options are the options for the multiple choice buttons, max five. (a - e inclusive)
	 * @param correctLetter is the letter (a, b, c, d, e) that is correct
	 * @param quesNum is the number identifier for the question
	 */
	public MultiChoice(String question, String[] options, String correctLetter, int quesNum) {
		super();
		this.question = question;
		this.options = options;
		this.quesNum = quesNum;
		// assigns the correct index based on the letter
		if (correctLetter.equalsIgnoreCase("a")) {
			correctInd = 0;
		} else if (correctLetter.equalsIgnoreCase("b")) {
			correctInd = 1;
		} else if (correctLetter.equalsIgnoreCase("c")) {
			correctInd = 2;
		} else if (correctLetter.equalsIgnoreCase("d")) {
			correctInd = 3;
		} else if (correctLetter.equalsIgnoreCase("e")) {
			correctInd = 4;
		}
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
	 * @return the options
	 */
	public String[] getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(String[] options) {
		this.options = options;
	}

	/**
	 * @return the correctInd
	 */
	public int getCorrectInd() {
		return correctInd;
	}

	/**
	 * @param correctInd the correctInd to set
	 */
	public void setCorrectInd(int correctInd) {
		this.correctInd = correctInd;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
		
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
	 * @param quesNum the question number of the question
	 */
	public void setQuesNum(int quesNum) {
		this.quesNum = quesNum;
	}


	/**
	 * @author Graham
	 * @param answer the user's answer to check
	 * @return true if correct, false if incorrect
	 * @Description accepts answer as an integer (a = 0, b = 1, c = 2 etc.)
	 */
	public boolean checkAnswer(int answer){
		if(answer == this.correctInd)
			return true;
		else
			return false;
	}
	/**
	 * @author Graham
	 * @param answer the user's answer to check
	 * @return true if correct, false if incorrect
	 * @Description accepts answer as a string (a, b, c, d, e)
	 */
	public boolean checkAnswer(String answer){
		if(answer.equalsIgnoreCase(ABCDE[this.correctInd]))
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
		String answer = "Answer: " + ABCDE[this.correctInd] + "\n";
		
		return question + answer;
	}
	
	
}