package com.jason;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.Image;

import javafx.scene.control.Button;


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
		VBox mainPane = new VBox();

		// Set mainPane Background
		mainPane.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		// Place help and start buttons
		mainPane.getChildren().addAll(btnStart, btnHelp);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setSpacing(20);

		// Display start screen
		mainScene = new Scene(mainPane, 600, 600);
		
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
		btnHelp.setOnMouseClicked(e ->{
			getHostServices().showDocument("https://www.catan.com/en/download/?SoC_rv_Rules_091907.pdf");
			
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
