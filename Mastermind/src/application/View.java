package application;

import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Is an utility to modify and handle function which are belonging to the view
 * @author Philippe Krüttli
 * @author Frithjof Hoppe
 *
 */
public class View
{
	/**
	 * set the background-color of the input button
	 * 
	 * @param b
	 * @param toTake
	 * @param color
	 * @return
	 */
	public static boolean setButtonColor(Button b, Button toTake, ArrayList<String> color)
	{
		boolean success = false;
		String back = "";

		switch (toTake.getText())
		{
		case "Rot":
			back = color.get(0);
			break;
		case "Grün":
			back = color.get(1);
			break;
		case "Blau":
			back = color.get(2);
			break;
		case "Gelb":
			back = color.get(3);
			break;
		case "Violett":
			back = color.get(4);
			break;
		case "Orange":
			back = color.get(5);
			break;
		default:
			break;
		}

		b.setBackground(new Background(new BackgroundFill(Paint.valueOf(back), null, null)));

		if (
			back != ""
		) {
			success = true;
		}

		return success;
	}

	/**
	 * set the result of a Label
	 * @param white
	 * @param black
	 * @param label
	 */
	public static void setLabelResult(String white, String black, Label label)
	{
		String text = "W:" + white + " B:" + black;
		label.setText(text);
	}

	/**
	 * get a hexadecimal string
	 * 
	 * @param b
	 * @return
	 */
	public static String getHex(Button b)
	{
		String back = "";
		// 0xef4f1fff
		Color color = (Color)b.getBackground().getFills().get(0).getFill();
		back = color.toString();
		back = back.substring(2, 8);
		back = "#" + back;
		return back;
	}
	
	/**
	 * checks if every button of a hbox is setted
	 * @param box
	 * @return boolean
	 */
	public static boolean isColorsSetted(HBox box)
	{
		boolean back = true;
		for (Node n : box.getChildren()) {
			if (
					n instanceof Button
				) {
				String color = ((Button) n).getBackground().getFills().get(0).getFill().toString();
				System.out.println(color.toString());
				if (
					color.equals("0xd8d8d8ff")
				) {
					back = false;
				}
			} //color.toString().equals("0xffffffba")
		}

		return back;
	}

	/**
	 * return the parent HBox from the input button
	 * 
	 * @param b
	 * @return HBox
	 */
	public static HBox getHBoxOfButton(Button b)
	{
		HBox hparent = (HBox) b.getParent();

		return hparent;
	}

	/**
	 * get the right sibling of a button into a hbox
	 * @param b
	 * @param box
	 * @return Button
	 */
	public static Button getNextSibling(Button b,HBox box)
	{
		Button back = null;
		int count = 0;
		
		for(Node n: box.getChildren())
		{
			if(n instanceof Button)
			{
				if(((Button)n).equals(b))
				{
					if(count < box.getChildren().size())
					{
						if (box.getChildren().get(count+1) instanceof Button)
						{
							back = (Button)box.getChildren().get(count+1);
						}
					}
				}
			}
			
			count++;
		}
		
		if(back == null)
		{
			back = b;
		}
		
		return back;
	}
	
	/**
	 * returns the first button of a hbox
	 * @param box
	 * @return Button
	 */
	public static Button getFirstHBoxButton(HBox box)
	{
		Button back = new Button();
		
		for(Node n: box.getChildren())
		{
			if(n instanceof Button)
			{
				back = (Button)n;
				break;
			}
		}
		
		return back;
	}
	
	/**
	 * opens a messeage box, which shows an exit request
	 * @return boolean
	 */
	public static boolean msgExitRequest()
	{
		boolean back = false;

		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setTitle("Closing request");
		a.setHeaderText("Bye Bye..");
		a.setContentText("You really want to exit");

		Optional<ButtonType> result = a.showAndWait();

		if (
			result.get() == ButtonType.OK
		) {
			back = true;
		}

		return back;
	}
	
	/**
	 * opens a message box, which shows an information that not every color has be set
	 */
	public static void msgColorNotSet()
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText("Color not set");
		alert.setContentText("Not every buttoncolor of the actual move has been set");

		alert.showAndWait();
	}
	
	/**
	 * opens a message box, which asks if the player would play again
	 * @return boolean
	 */
	public static boolean msgPlayAgain()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Question Dialog");
		alert.setHeaderText("Game Finished");
		alert.setContentText("Do you want to play new game. If not the programm will close");
		
		boolean back = false;
		
		ButtonType buttonTypeOne = new ButtonType("Yes");
		ButtonType buttonTypeTwo = new ButtonType("No");
		
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == buttonTypeOne)
		{
			back = true;
		}
		
		return back;
	}
	
	/**
	 * opens a message box, that the game has been finished
	 */
	public static void msgGameFinnished()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Play status");
		alert.setHeaderText("Finish");
		alert.setContentText("You lost the game!!");
		alert.showAndWait();
	}
	
	/**
	 * opens a message box, that shows that the game has been won
	 * @param count
	 */
	public static void msgWinningInformation(int count)
	{
		String text = "You win the game with >"+count;
		
		if(count == 1)
		{
			text+="< try";
		}
		else
		{
			text+="< tries";
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Play status");
		alert.setHeaderText("Congratulations");
		alert.setContentText(text);

		alert.showAndWait();
	}
	
	/**
	 * get specific hBox object of the VBox parent 
	 *  
	 * @param i: index
	 * @param parent
	 * @return HBox
	 */
	public static HBox getHBoxOfIndex(int i, VBox parent)
	{
		return (HBox) parent.getChildren().get(i);
	}

	public static boolean isChildOfHBox(Button b, HBox box)
	{
		boolean back = false;
		for (Node n : box.getChildren()) {
			if (
					n instanceof Button
				) {
				if (
						((Button) n).equals(b)
					) {
					back = true;
				}
			}
		}
		return back;
	}

	/**
	 * get the label of a HBox
	 * @param box
	 * @return Label
	 */
	public static Label getLabel(HBox box)
	{
		Label back = new Label();

		back = (Label) box.getChildren().get(box.getChildren().size() - 1);

		return back;
	}
}
