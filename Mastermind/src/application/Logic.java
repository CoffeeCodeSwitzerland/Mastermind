package application;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Logic
{
	public static ArrayList<String> generateSecretCode(ArrayList<String> colors,int size)
	{
		ArrayList<String> back = new ArrayList<String>();
		Random rnd = new Random();
		int min = 0;
		int max = 3; 

		for (int a = 0; a < size; a++) {
			int colorNumber = 0;
			colorNumber = rnd.nextInt(max - min) + min;
			back.add(colors.get(colorNumber));
		}

		return back;
	}

	public static ArrayList<String> checkCode(ArrayList<String> guess, ArrayList<String> soloution,
			ArrayList<String> colors)
	{
		ArrayList<String> back = new ArrayList<String>(); 
		int white = 0;
		int black = 0;

		for (int b = 0; b < colors.size(); b++) {

			String token = colors.get(b);
				if (
					howMany(soloution, token) > 0
				) {
					white += getWhite(guess, soloution, token);
			}
		}

		black += getBlack(guess, soloution);
		
		back.add("white:"+Integer.toString(white));
		back.add("black:"+Integer.toString(black));
		return back;
	}

	private static int getBlack(ArrayList<String> guess, ArrayList<String> soloution)
	{
		int back = 0;

		for (int a = 0; a < soloution.size(); a++) {
			if (
				guess.get(a).equals(soloution.get(a))
			) {
				back++;
			}
		}

		return back;
	}

	private static int getWhite(ArrayList<String> guess, ArrayList<String> soloution, String token)
	{
		int back = 0;
		int count = howMany(soloution, token);

		for (int a = 0; a < soloution.size(); a++) {
			if (
				guess.get(a).equals(token)
			) {
				if (
					!soloution.get(a).equals(token)
				) {
					if (
						back <= count
					) {
						back++;
					}
				}
			}
		}

		return back;
	}

	private static int howMany(ArrayList<String> soloution, String token)
	{
		int back = 0;

		for (String s : soloution) {
			if (
				s.equals(token)
			) {
				back++;
			}
		}

		//System.out.println("how: "+back);
		return back;
	}

	// public static ArrayList<String> checkCode(ArrayList<String> input,
	// ArrayList<String> solution,
	// ArrayList<String> colors)
	// {
	// ArrayList<String> back = new ArrayList<String>();
	//
	// int whiteTotal = 0;
	// int blackTotal = 0;
	//
	// for (int a = 0; a < colors.size(); a++) {
	// if (
	// contains(solution, colors.get(a))
	// ) {
	// String tempColor = "";
	// ArrayList<String> index = include(solution, colors.get(a));
	// for (String s : index) {
	// System.out.println(s);
	// int pos = Integer.parseInt(s);
	// if (solution.get(pos).equals(input.get(pos)))
	// {
	// blackTotal++;
	// }
	// }
	//
	// whiteTotal += getWhite(input, a);
	// }
	//
	// }
	//
	// back.add("white :" + Integer.toString(whiteTotal));
	// back.add("black :" + Integer.toString(blackTotal));
	//
	// return back;
	// }
	//
	// private static boolean contains(ArrayList<String> input, String actual)
	// {
	// boolean back = false;
	//
	// for (int a = 0; a < input.size(); a++) {
	// if (
	// input.get(a).equals(actual)
	// ) {
	// back = true;
	// break;
	// }
	// }
	//
	// return back;
	// }
	//
	// private static int getWhite(ArrayList<String> input, int actualPos)
	// {
	// int back = 0;
	// String color = input.get(actualPos);
	//
	// for (int a = 0; a < input.size(); a++) {
	// if (
	// a != actualPos
	// ) {
	// if (
	// input.get(a).equals(color)
	// ) {
	// back++;
	// }
	// }
	// }
	// System.out.println(back + "|value");
	// return back;
	// }
	//
	// private static ArrayList<String> include(ArrayList<String> input, String
	// toMatch)
	// {
	// boolean back = false;
	// ArrayList<String> index = new ArrayList<String>();
	//
	// for(int a = 0; a < input.size(); a++)
	// {
	//
	// if(input.get(a).equals(toMatch))
	// {
	// index.add(Integer.toString(a));
	// }
	// }
	//
	//
	// return index;
	// }
}
