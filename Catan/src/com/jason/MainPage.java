package com.jason;

import javafx.geometry.Pos;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
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

	// Background Image
	private final Image backgroundImage = new Image("com/jason/resource/mainBackground.jpg");

	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Create new Game Instance
		Game game = new Game(primaryStage);
		
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
		Scene scene = new Scene(mainPane, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		btnHelp.setOnMouseClicked(e ->{
			Browser browser = new Browser();
			BrowserView view = new BrowserView(browser);

			 

			Stage stage = new Stage();
			Pane pane = new HBox();
			BorderPane aNewPane = new BorderPane(view);
			Scene aNewScene = new Scene(aNewPane, 600, 600);
			stage.setScene(aNewScene);
			stage.show();
			
			browser.loadURL("/com/jason/help.pdf");
			
			/*
			WebView browser = new WebView();
			WebEngine webEngine = browser.getEngine();

			webEngine.load(helpPDF);
			pane.getChildren().add(browser);
			Scene aScene = new Scene(pane);
			aScene.getStylesheets().add("webviewsample/BrowserToolbar.css");
			stage.setScene(aScene);
			stage.show();
			*/
			
		});
		btnStart.setOnMouseClicked(e -> {
			game.startGame();
		});
		
		
		
		
	}
}
