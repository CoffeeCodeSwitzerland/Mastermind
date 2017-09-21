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
		Color color = (Color) b.getBackground().getFills().get(0).getFill();
		back = color.toString();
		back = back.substring(2, 8);
		back = "#" + back;
		return back;
	}
	
	
	public static boolean isColorsSetted(HBox box)
	{
		boolean back = true;
		for (Node n : box.getChildren()) {
			if (
					n instanceof Button
				) {
				Color color = (Color) ((Button) n).getBackground().getFills().get(0).getFill();
				System.out.println(color.toString());
				if (
					color.toString().equals("0xffffffba")
				) {
					back = false;
				}
			}
		}

		return back;
	}

	/**
	 * return the parent HBox from the input button
	 * 
	 * @param b
	 * @return
	 */
	public static HBox getHBoxOfButton(Button b)
	{
		HBox hparent = (HBox) b.getParent();

		return hparent;
	}

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
	
	public static void msgWinningInformation(int count)
	{
		String text = "You've win the game with >"+count;
		
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

	public static Label getLabel(HBox box)
	{
		Label back = new Label();

		back = (Label) box.getChildren().get(box.getChildren().size() - 1);

		return back;
	}
}
