/*
		 Title: saver.java
		 Programmer: hugo
		 Date of creation: Apr 20, 2015
		 Description: this is a program used to test how to use the google gson library. Word for the wise, this library is AWESOME bookmark and download. 
*/


package testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import questions.TrueFalse;

/**
 * @author hugo
 *
 */
// not used just for testing
public class saver {

	/**
		 * @author hugo
		 * Date of creation: Apr 10, 2015 
		 * @param: None
		 * @return: None
		 * @throws IOException 
		 * @Description: ( ͡° ͜ʖ ͡°)
		 */
	public static void main(String[] args) throws IOException {
		PrintWriter fileOut = new PrintWriter(new FileWriter("test true flase.txt"));
		
		
		TrueFalse qOne = new TrueFalse("Example true/false question, answer true. ", true);
//		TrueFalse qTwo = new TrueFalse("Example true/false question, answer false. " , false);
//		ShortAnswer qThree = new ShortAnswer("Who am I?" , "Mr. Pain");
//		String[] options = {"one", "two", "three", "four"};
//		MultiChoice qFour = new MultiChoice("Three", options, 2);
		
		Gson json = new Gson();
		
		String jsond = json.toJson(qOne);
		fileOut.println(jsond);
//		
//		jsond = json.toJson(qTwo);
//		fileOut.println(jsond);
//		
//		jsond = json.toJson(qThree);
//		fileOut.print(jsond);
		
		fileOut.close();
		
		BufferedReader fileIn = new BufferedReader(new FileReader("test true flase.txt"));
		
		TrueFalse totallyNew = json.fromJson(fileIn.readLine(), TrueFalse.class);
		
		System.out.println(totallyNew.getQuestion());
		fileIn.close();
	}

}
