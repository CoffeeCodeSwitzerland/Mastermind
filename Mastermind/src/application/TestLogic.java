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
		testGuess.add("5");
		testGuess.add("2");
		testGuess.add("6");
		testGuess.add("4");
		testGuess.add("3");
		testGuess.add("1");
		
		ArrayList<String> testSolution = new ArrayList<String>();
		testSolution = Logic.generateSecretCode(testColor);
		
		
		for(String s: Logic.checkCode(testGuess, testSolution, testColor))
		{
			
		}
	}

}
