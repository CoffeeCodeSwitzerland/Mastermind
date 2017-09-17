package application;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Handling of the view-data
 * @author Philippe Kr�ttli
 * @author Frithjof Hoppe
 *
 */

public class Logic
{
	/**
	 * Genrate the color-code which the user should guess right
	 * @param colors
	 * @param size
	 * @return arrayList with a secretcode
	 */
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

	/**
	 * examine if the entered code is equal or similar and return an array with counter of "white" and "black"
	 * @param guess
	 * @param soloution
	 * @param colors
	 * @return arrayList with count of white and black
	 */
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

	/**
	 * get the count of colors which are positioned at the same index
	 * @param guess
	 * @param soloution
	 * @return int count of black
	 */
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

	/**
	 * get the count of colors which are in the guess and the solution, but not at the same index place
	 * @param guess
	 * @param soloution
	 * @param token
	 * @return int count of white
	 */
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

	/**
	 * get the count of the appearances in the solution
	 * @param soloution
	 * @param token
	 * @return int count of token in arrayList
	 */
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
		return back;
	}
}