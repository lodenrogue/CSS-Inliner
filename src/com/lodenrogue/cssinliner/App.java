package com.lodenrogue.cssinliner;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	private static Stage stage;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		try {
			Parent root = FXMLLoader.load(App.class.getResource("app.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("CSS Inliner");
			primaryStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Stage getStage() {
		return stage;
	}
}
