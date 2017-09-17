package application;

import java.util.ArrayList;

public class TestLogic {

	public static void main(String[] args) {
		
		ArrayList<String> testColor = new ArrayList<String>();
		
		testColor.add("1");
		testColor.add("2");
		testColor.add("3");
		testColor.add("4");
		testColor.add("5");
		testColor.add("6");
		
		ArrayList<String> testGuess = new ArrayList<String>();
		testGuess.add("1");
		testGuess.add("2");
		testGuess.add("3");
		testGuess.add("4");
//		testGuess.add("3");
//		testGuess.add("1");
		
		ArrayList<String> testSolution = new ArrayList<String>();
//		testSolution.add("2");
//		testSolution.add("1");
//		testSolution.add("3");
//		testSolution.add("3");
		testSolution = Logic.generateSecretCode(testColor,4);
		
		
		String guess = "";
		String solution = "";
		
		for(String s: testSolution)
		{
			solution+=s;
		}
		
		for(String s: testGuess)
		{
			guess+=s;
		}
		
		System.out.println("lösung :"+solution);
		System.out.println("geraten:"+guess);
		
		System.out.println("Lösung");
		
		for(String s: Logic.checkCode(testGuess, testSolution,testColor))
		{
			System.out.println(s);
		}
	}

}
