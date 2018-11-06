package com.jason;

import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MainPage extends Application{
	
	private static Stage stage;
	private static Scene mainScene;
	// Background Image
	private final Image backgroundImage = new Image("com/jason/resource/mainBackground.jpg");

	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		// Create new Game Instance
		Game game = new Game();
		
		// Create Buttons
		Button btnStart = new Button("Start Game");
		Button btnHelp = new Button("Help");
		
		// Create new BorderPane
		BorderPane mainPane = new BorderPane();

		// Set mainPane Background
		mainPane.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		// Place help and start buttons
		mainPane.setBottom(btnStart);
		mainPane.setTop(btnHelp);

		// Display start screen
		mainScene = new Scene(mainPane, 600, 600);
		
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
		btnHelp.setOnMouseClicked(e ->{
	
			
		});
		btnStart.setOnMouseClicked(e -> {
			game.startGame();
		});
		
		
		
		
	}
	
	// Getters for Stage and Scene
	
	public static Stage getStage() {
		return stage;
	}
	
	
	public static Scene getScene() {
		return mainScene;
	}
}
