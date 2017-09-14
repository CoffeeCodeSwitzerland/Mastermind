package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.getChildren().add(createGameField());
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private VBox createGameField() {
		VBox layout = new VBox();
		for(int i = 0; i < 12; i++)
		{
			HBox hb = new HBox();
			for(int j = 0; j < 4; j++)
			{
				hb.getChildren().add(new Button());
			}
			hb.getChildren().add(new Label());
			layout.getChildren().add(hb);
		}
		return layout;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
