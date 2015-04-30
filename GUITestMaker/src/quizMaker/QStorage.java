/*
		 Title: QStorage.java
		 Programmer: hugo
		 Date of creation: Apr 22, 2015
		 Description: 
*/


package quizMaker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import questions.MultiChoice;
import questions.ShortAnswer;
import questions.TrueFalse;

/**
 * @author hugo
 *
 */
//class that stores all the data of all the question and handles file in and out for the questions
public class QStorage {
	//counts that handle indices of various lists
	private int tfCount = 0;
	private int mcCount = 0;
	private int saCount = 0;
	private int orderCount = 0;
	public static int totalQ = 0;
	//lists if all question types to store up to 100 of each question type
	private TrueFalse[] tfArray = new TrueFalse[100];
	private MultiChoice[] mcArray = new MultiChoice[100];
	private ShortAnswer[] saArray = new ShortAnswer[100];
	//holds the order of the questions and which type they are
	private int[] orderArray = new int[300];
	//constant ints that represent each question type, used with orderArray above
	public static final int tf = 1;
	public static final int mc = 2;
	public static final int sa = 3;
	private String filename;
	public boolean isDoneReading;
	 
	
	/**
	 * 
	 */
	//constructor with param of the filename to read from and write to 
	public QStorage(String filename) {
		this.filename = filename;
	}
	/*adds a true false question the its array and saves a true false identifier into orderArray
	 * incrementing both index counts
	 */
	public void addTF(TrueFalse ques){
		tfArray[tfCount] = ques;
		tfCount++;
		orderArray[orderCount] = tf;
		orderCount++;
	}
	/*adds a multiple choice question the its array and saves a multiple choice identifier into orderArray
	 * incrementing both index counts
	 */
	public void addMC(MultiChoice ques){
		mcArray[mcCount] = ques;
		mcCount++;
		orderArray[orderCount] = mc;
		orderCount++;
	}
	/*adds a short answer question the its array and saves a short answer identifier into orderArray
	 * incrementing both index counts
	 */
	public void addSA(ShortAnswer ques){
		saArray[saCount] = ques;
		saCount++;
		orderArray[orderCount] = sa;
		orderCount++;
	}
	
	//gets the total number of questions
	public int getNumQues(){
//		System.out.println("totalQ" + totalQ);
		return totalQ;
		
	}
	//gets index in the true false array
	public int getNumTF(){
		return tfCount;
	}
	//gets index in the multiple choice array
	public int getNumMC(){
		return mcCount;
	}
	//gets index in the short answer array
	public int getNumSA(){
		return saCount;
	}
	//writes all the question data to the file
	public void writeToFile() throws IOException{
		PrintWriter fileout = new PrintWriter(new FileWriter(this.filename));
		int tfTempCount = 0;
		int mcTempCount = 0;
		int saTempCount = 0;
		//creates json object for converting to json
		Gson json = new Gson();
		//converts orderArray to json and then writes to file
		fileout.println(json.toJson(orderArray));
		/*figures out what type of question to write next in the file based on orderArray
		 * then writes to file that question type and increments that counter
		 */
		for(int i = 0; i<orderCount; i++){
			String jsoned;
			if(orderArray[i] == tf){
				jsoned = json.toJson(tfArray[tfTempCount]);
				tfTempCount++;
			} else if (orderArray[i] == mc) {
				jsoned = json.toJson(mcArray[mcTempCount]);
				mcTempCount++;
			} else if (orderArray[i] == sa) {
				jsoned = json.toJson(saArray[saTempCount]);
				saTempCount++;
			} else {
				jsoned = "Error";
			}
			fileout.println(jsoned);
		}
		fileout.close();
	}
	//reads all the question data back out of the file
	public void readFile() throws IOException{
		isDoneReading = false;
		BufferedReader fileIn = new BufferedReader(new FileReader(this.filename));
		//creates the json object to convert back from json
		Gson json = new Gson();
		//reads the orderArray back from the file first
		this.orderArray = json.fromJson(fileIn.readLine(), int[].class);
		int orderElementKind = orderArray[0];
		//unused variables
//		int orderReadCount = 0;
//		int tfReadCount = 0;
//		int mcReadCount = 0;
//		int saReadCount = 0;
		/*this loop increments through orderArray figuring out what type of question to read in next
		 * then it converts from json and saves to the array for that question type
		 */
		while(orderElementKind != 0){
			
			orderElementKind = this.orderArray[orderCount];
			if (orderElementKind != 0) {
				totalQ++;
				//bug testing sysout statements
//				System.out.println("Increased totalq " + totalQ);
//				System.out.println("qStorage orderElementKind" + orderElementKind);
				if (orderElementKind == tf) {
					this.tfArray[tfCount] = json.fromJson(fileIn.readLine(), TrueFalse.class);
					tfCount++;
				} else if (orderElementKind == mc) {
					this.mcArray[mcCount] = json.fromJson(fileIn.readLine(), MultiChoice.class);
					mcCount++;
				} else if (orderElementKind == sa) {
					this.saArray[saCount] = json.fromJson(fileIn.readLine(), ShortAnswer.class);
					saCount++;
				} else {
					System.err.println("Not any question type");
				}
				orderCount++;
			}
		}
		isDoneReading = true;
		fileIn.close();
		orderCount = 0;
		tfCount = 0;
		mcCount = 0;
		saCount = 0;
	}

	/**
		 * @author hugo
		 * Date of creation: Apr 10, 2015 
		 * @param: None
		 * @return: None
		 * @Description: gets the current question type
		 */
	public int getCurrentQType() {
		return orderArray[orderCount];

	}
	
	/**
	 * @author hugo
	 * Date of creation: Apr 10, 2015 
	 * @param: None
	 * @return: None
	 * @Description: gets the next true false question
	 */
	public TrueFalse getNextQTF(){
		tfCount++;
		orderCount++;
//		System.out.println("getNextQTF");
//		System.out.println(tfArray[tfCount].getQuestion());
		return tfArray[tfCount - 1];	//note post increment. returns the index tfcount, THEN increments tfcount. 
	}
	
	/**
	 * @author hugo
	 * Date of creation: Apr 10, 2015 
	 * @param: None
	 * @return: None
	 * @Description: gets the next multiple choice question
	 */
	public MultiChoice getNextQMC(){
		mcCount++;
		orderCount++;
		return mcArray[mcCount - 1]; //again POST increment is important
	}
	
	/**
	 * @author hugo
	 * Date of creation: Apr 10, 2015 
	 * @param: None
	 * @return: None
	 * @Description: gets the next short answer question
	 */
	public ShortAnswer getNextQSA(){
		saCount++;
		orderCount++;
		return saArray[saCount - 1]; //again POST increment is important
	}
}









