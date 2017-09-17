package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class View
{
	public static boolean setButtonColor(Button b,Button toTake, ArrayList<String> color)
	{
		boolean success = false;
		String back = "";
		
		switch(toTake.getText())
		{
		case "Rot": back=color.get(0); break;
		case "Grün":back=color.get(1);break;
		case "Blau":back=color.get(2);break;
		case "Gelb":back=color.get(3);break;
		case "Violett":back=color.get(4);break;
		case "Orange":back=color.get(5);break;
		default:break;
		}
		
		b.setBackground(new Background(new BackgroundFill(Paint.valueOf(back), null, null)));
		
		if(back != "")
		{
			success = true;
		}
		
		return success;
	}
	
//	public static String getButtonHEXColor(Button toTake, ArrayList<String> color)
//	{
//		
//		String back = "";
//		
//		switch(toTake.getText())
//		{
//		case "Rot": back=color.get(0); break;
//		case "Grün":back=color.get(1);break;
//		case "Blau":back=color.get(2);break;
//		case "Gelb":back=color.get(3);break;
//		case "Violett":back=color.get(4);break;
//		case "Orange":back=color.get(5);break;
//		default:break;
//		}
//	
//		return back;
//	}
	
	public static String getHex(Button b)
	{
		String back = "";
		//0xef4f1fff
		Color color = (Color)b.getBackground().getFills().get(0).getFill();
		back = color.toString();
		back = back.substring(2, 8);
		back = "#"+back;
		return back;
	}
	
	public static HBox getHBoxOfButton(Button b)
	{	
		HBox hparent = (HBox) b.getParent();
		
		return hparent;
	}
}
