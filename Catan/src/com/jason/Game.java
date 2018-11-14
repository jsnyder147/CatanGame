package com.jason;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Button;


public class Game {
	private final Image BACKGROUND_IMAGE = new Image("/com/jason/resource/water.png");
	private final int NUMBER_OF_PLAYERS = 4;
	private boolean isWinner = false;
	private Player winner;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Label> lblPlayerNames = new ArrayList<>();
	private ArrayList<TextField> tfPlayerNames = new ArrayList<>();
	private static Stage stage;
	private static Scene gameScene;
	private static Scene mainMenuScene;
	private Board board;
	private static BorderPane gamePane;
	private Button btnContinue;
	private static Pane dicePane = new Pane();

	
	
	public Game() {
		mainMenuScene = MainPage.getScene();
		stage = MainPage.getStage();
		

	}
	
	public void startGame() {
		// Setup Board and Players
		setUp();
		
		
		/*
		// While all players VP < 10
		while(!isWinner) {
			// Do each players turn
			for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				doTurn(players.get(i));
				// if player wins end game
				if(players.get(i).getVictoryPoints() >= 10) {
					isWinner = true;
					winner = players.get(i);
					break;
				}
			}
		}  */
	}
	
	public boolean getIsWinner() {
		return isWinner;
	}
	
	public Player getWinner() {
		return winner;
	}
	
	private void setUp() {
		// Setup GUI
		setUpGUI();
		
		// Roll dice to see who goes first
		
		
		// Set up Board
		
		// Do first turn that has weird order
		
		
	}
	private void doTurn(Player player) {
		player.roll();
	}
	
	// Method to setup the GUI
	private void setUpGUI() {
		
		gamePane = new BorderPane();
		gamePane.getStylesheets().add("com/jason/resource/catan.css");
		
		// Pane for Player Name Entry
		VBox playerPane = new VBox();
		playerPane.setPadding(new Insets(5,5,5,5));
		playerPane.getStyleClass().add("playerNamePane");
		
		
		// Test if tiles align by placing in pane and displaying
		gamePane.setBackground(new Background(new BackgroundImage(BACKGROUND_IMAGE, BackgroundRepeat.NO_REPEAT,
			BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

		btnContinue = new Button("Continue");
		
		// Create 4 labels and 4 text fields for player name entry
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			lblPlayerNames.add(new Label("Player " + (i + 1) + " Name: "));
			tfPlayerNames.add(new TextField());
			playerPane.getChildren().addAll(lblPlayerNames.get(i), tfPlayerNames.get(i));
		}
		
		// Add Button to flow pane
		playerPane.getChildren().add(btnContinue);
		gamePane.setLeft(playerPane);
		
		// Display Player pane
		BorderPane.setAlignment(playerPane, Pos.CENTER);
		gameScene = new Scene(gamePane, 900, 1200);
		stage.setScene(gameScene);
		stage.show();
		
		// set button event listener
		btnContinue.setOnAction(e -> {
			
			boolean allPlayersEntered = true;
			// Check for valid entry
			for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				if(tfPlayerNames.get(i).getText().isEmpty()) {
					allPlayersEntered = false;

					
					
					// Need to Add: Change Text Field Border Color to Red
					// If Any Player names haven't been entered
					
										
					
				}
			}
			
			// If all players names have been entered create new players
			if(allPlayersEntered) {
				System.out.println("\nPLAYERS ENTERED\n");
				for(int i = 0; i< NUMBER_OF_PLAYERS; i++) {
					players.add(new Player(tfPlayerNames.get(i).getText()));
				}
				
				// Remove Player info from sidePane
				playerPane.getChildren().removeAll(lblPlayerNames);
				playerPane.getChildren().removeAll(tfPlayerNames);
				playerPane.getChildren().remove(btnContinue);

				// Create Board
				board = new Board(1000, 1000);
				board.createTiles();
				board.createChits();
				board.setIntersections();
				board.setConnections();
				board.finishBoard();
				System.out.println("\n\nBOARD CREATED\n\n");
				
				
				// Start First Turn
				firstTurn();
			}
			
			// Test to display player creation
			for(Player player: players) {
				System.out.println("\n\nTESTING DICE\n\n");
				int die[] = player.roll();
				System.out.println("Player Number " + (players.indexOf(player) + 1) + "\nPlayer Name: " + player.getName() +
						" Color: "+player.getColor() + "Roll Die 1: " + die[0] + " Roll Die 2: " + die[1] + "\n");

			}		
			
		});
		
		
		
	}
	
	private void firstTurn() {
		Button btn = new Button("Roll");
		Button btn2 = new Button("Close");
		Pane pane = new Pane();
		gamePane.setRight(pane);
		pane.getChildren().add(btn);
		
		btn.setOnMouseClicked(ev->{
			pane.getChildren().remove(0);
			pane.getChildren().add(dicePane);
			pane.getChildren().add(btn2);
			RollDice dice = new RollDice(400,400);
			
			
			dice.setDice(4,3);
			dice.showStage();
			dice.animate();
			dice.roll();
		});
		btn2.setOnMouseClicked(e -> {
			pane.getChildren().remove(0);
			pane.getChildren().remove(0);
		});

		
	}
	
	// Getters for stage and scene
	public static Stage getStage() {
		return stage;
	}
	
	public static Scene getScene() {
		return gameScene;
	}
	
	// Getter and Setter for BorderPane
	
	public static BorderPane getPane() {
		return gamePane;
	}
	
	public static void setPane(BorderPane pane) {
		gamePane = pane;
	}
	
	public static Pane getDicePane() {
		return dicePane;
	}
	
	
}
