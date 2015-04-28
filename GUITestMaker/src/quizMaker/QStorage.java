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
public class QStorage {
	private int tfCount = 0;
	private int mcCount = 0;
	private int saCount = 0;
	private int orderCount = 0;

	private TrueFalse[] tfArray = new TrueFalse[100];
	private MultiChoice[] mcArray = new MultiChoice[100];
	private ShortAnswer[] saArray = new ShortAnswer[100];
	
	private int[] orderArray = new int[300];
	public static final int tf = 1;
	public static final int mc = 2;
	public static final int sa = 3;
	private String filename;
	
	/**
	 * 
	 */
	public QStorage(String filename) {
		this.filename = filename;
	}
	
	public void addTF(TrueFalse ques){
		tfArray[tfCount] = ques;
		tfCount++;
		orderArray[orderCount] = tf;
		orderCount++;
	}
	
	public void addMC(MultiChoice ques){
		mcArray[mcCount] = ques;
		mcCount++;
		orderArray[orderCount] = mc;
		orderCount++;
	}
	
	public void addSA(ShortAnswer ques){
		saArray[saCount] = ques;
		saCount++;
		orderArray[orderCount] = sa;
		orderCount++;
	}
	
	
	public int getNumQues(){
		return orderCount;
	}
	
	public int getNumTF(){
		return tfCount;
	}
	
	public int getNumMC(){
		return mcCount;
	}
	
	public int getNumSA(){
		return saCount;
	}
	
	public void writeToFile() throws IOException{
		PrintWriter fileout = new PrintWriter(new FileWriter(this.filename));
		int tfTempCount = 0;
		int mcTempCount = 0;
		int saTempCount = 0;
		Gson json = new Gson();
		fileout.println(json.toJson(orderArray));
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
	
	public void readFile() throws IOException{
		BufferedReader fileIn = new BufferedReader(new FileReader(this.filename));
		Gson json = new Gson();
		this.orderArray = json.fromJson(fileIn.readLine(), int[].class);
		int orderElementKind = orderArray[0];
//		int orderReadCount = 0;
//		int tfReadCount = 0;
//		int mcReadCount = 0;
//		int saReadCount = 0;
		while(orderElementKind != 0){
			
			orderElementKind = this.orderArray[orderCount];
			if (orderElementKind != 0) {
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
		 * @Description: ( ͡° ͜ʖ ͡°)
		 */
	public int getCurrentQType() {
		return orderArray[orderCount];

	}
	
	/**
	 * @author hugo
	 * Date of creation: Apr 10, 2015 
	 * @param: None
	 * @return: None
	 * @Description: ( ͡° ͜ʖ ͡°)
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
	 * @Description: ( ͡° ͜ʖ ͡°)
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
	 * @Description: ( ͡° ͜ʖ ͡°)
	 */
	public ShortAnswer getNextQSA(){
		saCount++;
		orderCount++;
		return saArray[saCount - 1]; //again POST increment is important
	}
}









