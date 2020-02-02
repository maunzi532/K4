package gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MainGUI extends Application
{
	public static void main(String[] args)
	{
		launch(MainGUI.class);
	}

	@Override
	public void start(Stage stage)
	{
		stage.setTitle("KdE_K");
		stage.setOnCloseRequest(e -> stage.close());
		GridPane gridPane = new GridPane();
		gridPane.add(new TextField("Wugu"), 0, 0);
		Scene scene = new Scene(gridPane);
		stage.setScene(scene);
		stage.show();
	}
}