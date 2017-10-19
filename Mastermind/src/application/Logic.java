package application;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 * Handling of the view-data
 * 
 * @author Philippe Krüttli
 * @author Frithjof Hoppe
 *
 */

public class Logic {
	/**
	 * Genrate the color-code which the user should guess right
	 * 
	 * @param colors
	 * @param size
	 * @return arrayList with a secretcode
	 */
	public static ArrayList<String> generateSecretCode(ArrayList<String> colors, int size) {
		ArrayList<String> back = new ArrayList<String>();
		Random rnd = new Random();
		int min = 0;
		int max = 6;

		for (int a = 0; a < size; a++) {
			int colorNumber = 0;
			colorNumber = rnd.nextInt(max - min) + min;
			System.out.println("Geheim: " + Integer.toString(colorNumber));
			back.add(colors.get(colorNumber));
		}

		return back;
	}

	/**
	 * examine if the entered code is equal or similar and return an array with
	 * counter of "white" and "black"
	 * 
	 * @param guess
	 * @param soloution
	 * @param colors
	 * @return arrayList with count of white and black
	 */
	public static ArrayList<String> checkCode(ArrayList<String> guess, ArrayList<String> soloution,
			ArrayList<String> colors) {
		ArrayList<String> back = new ArrayList<String>();
		int white = 0;
		int black = 0;

		
		
		for (int b = 0; b < colors.size(); b++) {

			String token = colors.get(b);
			if (howMany(soloution, token) > 0) {
				int blackActual = getBlack(guess, soloution,token);
				black += blackActual;
				white += getWhite(guess, soloution, token,blackActual);
			}
		}


		back.add(Integer.toString(white));
		back.add(Integer.toString(black));
		return back;
	}

	/**
	 * get the count of colors which are positioned at the same index
	 * 
	 * @param guess
	 * @param soloution
	 * @return int count of black
	 */
	private static int getBlack(ArrayList<String> guess, ArrayList<String> soloution,String token) {
		int back = 0;

		for (int a = 0; a < soloution.size(); a++) {
			if(guess.get(a).equals(token))
			{
			if (guess.get(a).equals(soloution.get(a))) {
				back++;
			}
			}
		}

		return back;
	}

	/**
	 * get the count of colors which are in the guess and the solution, but not
	 * at the same index place
	 * 
	 * @param guess
	 * @param soloution
	 * @param token
	 * @return int count of white
	 */
	private static int getWhite(ArrayList<String> guess, ArrayList<String> soloution, String token, int black) {
		int back = 0;
		int count = howMany(soloution, token);
		int hits = 0;
		if (black < count) {
			for (int a = 0; a < soloution.size(); a++) {
				if (guess.get(a).equals(token)) {
					hits++;
					if (!soloution.get(a).equals(token)) {
						if (hits <= count) {
							back++;
						}
					}
				}
			}
		}

		return back;
	}

	/**
	 * get the count of the appearances in the solution
	 * 
	 * @param soloution
	 * @param token
	 * @return int count of token in arrayList
	 */
	private static int howMany(ArrayList<String> soloution, String token) {
		int back = 0;

		for (String s : soloution) {
			if (s.equals(token)) {
				back++;
			}
		}
		return back;
	}

	public static void resetView(VBox box) {
		for (Node hbox : box.getChildren()) {
			for (Node element : ((HBox) hbox).getChildren()) {
				if (element instanceof Button) {
					((Button) element)
							.setBackground(new Background(new BackgroundFill(Paint.valueOf("#d8d8d8"), null, null)));
				} else if (element instanceof Label) {
					((Label) element).setText("");
				}
			}
		}
	}

	public static ArrayList<Integer> getParentIndex(Button b) {
		ArrayList<Integer> back = new ArrayList<Integer>();
		HBox hparent = (HBox) b.getParent();
		VBox vparent = (VBox) hparent.getParent();
		int buttonPos = 0;
		int hboxPos = 0;
		for (Node n : hparent.getChildren()) {
			if (n.equals(b)) {
				break;
			}
			buttonPos++;
		}
		for (Node n : vparent.getChildren()) {
			if (n.equals(hparent)) {
				break;
			}
			hboxPos++;
		}
		back.add(buttonPos);
		back.add(hboxPos);
		return back;
	}
}
