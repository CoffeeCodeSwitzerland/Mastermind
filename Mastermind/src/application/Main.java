package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Main extends Application {
	
	private ArrayList<String> colors = new ArrayList<String>();
	private ArrayList<String> guess = new ArrayList<String>();
	private ArrayList<String> solution = new ArrayList<String>();
	private static Button actualButton = new Button();
	private static HBox actualHBox = new HBox();
	private static Label actualLabel = new Label();
	private static VBox actualVBox = new VBox();
	private int actualRow = 0;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			VBox gameField = createGameField();
			VBox controlField = createControlField();
			root.setCenter(gameField);
			root.setBottom(controlField);
			Scene scene = new Scene(root, 500, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setOnHiding( event -> {View.msgExitRequest();} );
			primaryStage.show();
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void initialize()
	{
		colors.clear();	
		colors.add("#ef4f1f"); //Rot
		colors.add("#22ef1f"); //Grün
		colors.add("#1f76ef"); //Blau
		colors.add("#efef1f"); //Gelb
		colors.add("#aa1fe7"); //Violett
		colors.add("#ef8e1f"); //Orange
		
		solution = Logic.generateSecretCode(colors, 4);
		actualHBox = View.getHBoxOfIndex(actualVBox.getChildren().size()-1, actualVBox);
		actualRow = actualVBox.getChildren().size()-1;
		actualButton = View.getFirstHBoxButton(actualHBox);
		actualLabel = View.getLabel(actualHBox);
		actualButton.requestFocus();
		Logic.resetView(actualVBox);
	}
	
	private VBox createGameField() {
		VBox layout = new VBox();
		actualVBox = layout;
		for (int i = 0; i < 12; i++) {
			HBox hb = new HBox();
			for (int j = 0; j < 4; j++) {
				Button b = new Button();
				b.getStyleClass().add("GameFieldButtons");
				b.setShape(new Circle(25));
				b.setOnAction(e -> {
					gameFieldButtonHanlder(b);
					//b.setBackground(new Background(new BackgroundFill(Paint.valueOf("#ff0000"), null, null)));
				});
				hb.getChildren().add(b);
			}
			Label lbl = new Label("");
			lbl.getStyleClass().add("GameLabels");
			hb.getChildren().add(lbl);
			layout.getChildren().add(hb);
		}
		return layout;
	}
	
	private VBox createControlField() {
		VBox layout = new VBox();
		HBox hbTop = new HBox();
		HBox hbBottom = new HBox();

		hbTop.getChildren().add(new Button("Rot"));
		hbTop.getChildren().add(new Button("Blau"));
		hbTop.getChildren().add(new Button("Grün"));
		hbTop.getChildren().add(new Button("Prüfen"));

		hbBottom.getChildren().add(new Button("Gelb"));
		hbBottom.getChildren().add(new Button("Violett"));
		hbBottom.getChildren().add(new Button("Orange"));
		hbBottom.getChildren().add(new Button("Beenden"));
		
		int count = 0;
		for(Node b: hbTop.getChildren())
		{
			if(count == 3)
			{
				b.getStyleClass().add("RightColorButtons");
			}
			b.getStyleClass().add("ControlFieldButtons");
			
			((Button)b).setOnAction(e -> {
				controlFieldHandler((Button)b);
			});
			
			count++;
		}
		
		count = 0;
		
		for(Node b: hbBottom.getChildren())
		{
			if(count == 3)
			{
				b.getStyleClass().add("RightColorButtons");
			}
			b.getStyleClass().add("ControlFieldButtons");
			
			((Button)b).setOnAction(e -> {
				controlFieldHandler((Button)b);
			});
			
			count++;
		}

		layout.getChildren().add(hbTop);
		layout.getChildren().add(hbBottom);
		return layout;
	}
	
	private ArrayList<String> createGuess()
	{
		ArrayList<String> back = new ArrayList<String>();
		
		int counter = 0;
		for(Node n: actualHBox.getChildren())
		{
			if(counter < 4)
			{
				//System.out.println(View.getHex(((Button)n)));
				back.add(View.getHex(((Button)n)));
			}
			counter++;
		}
		
		
		return back;
	}
	
	private void controlFieldHandler(Button b)
	{
		if(b != null)
		{
		if(b.getText() == "Beenden")
		{
			if(View.msgExitRequest())
			{
				System.exit(0);
			}
		}
		else if(b.getText() == "Prüfen")
		{
			if(View.isColorsSetted(actualHBox))
			{
				ArrayList<String> result = new ArrayList<String>();
				createGuess();
				result = Logic.checkCode(createGuess(), solution, colors);
				View.setLabelResult(result.get(0), result.get(1), actualLabel);
				actualButton = null;
				if(actualRow != 0)
				{
					if(result.get(1).equals("4"))
					{
						View.msgWinningInformation((11-actualRow)+1);
				
						if(View.msgPlayAgain())
						{
							initialize();
						}
						else
						{
							System.exit(0);
						}
					}
					else
					{
						actualRow--;
						actualHBox = View.getHBoxOfIndex(actualRow, actualVBox);
						actualButton = View.getFirstHBoxButton(actualHBox);
						actualButton.requestFocus();
						actualLabel = View.getLabel(actualHBox);
					}
				}
				else
				{
					if(result.get(1).equals("4"))
					{
						View.msgWinningInformation((11-actualRow)+1);
				
						if(View.msgPlayAgain())
						{
							System.out.println("remain");
							initialize();
						}
						else
						{
							System.exit(0);
						}
					}
					else
					{
						View.msgGameFinnished();
						if(View.msgPlayAgain())
						{
							initialize();
						}
						else
						{
							System.exit(0);
						}
					}
				}
			}
			else
			{
				View.msgColorNotSet();
			}
		}
		else
		{
			View.setButtonColor(actualButton,b,colors);
			actualButton = View.getNextSibling(actualButton, actualHBox);
			actualButton.requestFocus();
//			actualButton.setBackground(new Background(new BackgroundFill(Paint.valueOf("#ff0000"), null, null)));
		}
		}
	}
	
	
	
	private void gameFieldButtonHanlder(Button b)
	{
		System.out.println("allos");
		if(View.isChildOfHBox(b, actualHBox))
		{
			actualButton = b;
			actualHBox=(HBox)b.getParent();
			actualLabel = View.getLabel(actualHBox);
			ArrayList<Integer> out  = new ArrayList<Integer>();
			out = Logic.getParentIndex(b);
			System.out.println(out.get(0)+","+out.get(1));
		}
		else
		{
			System.out.println("Falsche reihe");
		}
	}
}
