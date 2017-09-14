package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			VBox gameField = createGameField();
			VBox controlField = createControlField();
			root.setCenter(gameField);
			root.setBottom(controlField);
			Scene scene = new Scene(root, 500, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private VBox createGameField() {
		VBox layout = new VBox();
		for (int i = 0; i < 12; i++) {
			HBox hb = new HBox();
			for (int j = 0; j < 4; j++) {
				Button b = new Button();
				b.getStyleClass().add("GameFieldButtons");
				b.setShape(new Circle(25));
				b.setOnAction(e -> {
					b.setBackground(new Background(new BackgroundFill(Paint.valueOf("#ff0000"), null, null)));
				});
				hb.getChildren().add(b);
			}
			Label lbl = new Label("Test");
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
		hbBottom.getChildren().add(new Button("Weiss"));
		hbBottom.getChildren().add(new Button("Schwarz"));
		hbBottom.getChildren().add(new Button("Beenden"));
		
		int count = 0;
		for(Node b: hbTop.getChildren())
		{
			if(count == 3)
			{
				b.getStyleClass().add("RightColorButtons");
			}
			b.getStyleClass().add("ControlFieldButtons");
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
			count++;
		}

		layout.getChildren().add(hbTop);
		layout.getChildren().add(hbBottom);
		return layout;
	}
}
