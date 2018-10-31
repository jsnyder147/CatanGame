package com.jason;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class Game {
	
	private final int NUMBER_OF_PLAYERS = 4;
	private boolean isWinner = false;
	private Player winner;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Label> lblPlayerNames = new ArrayList<>();
	private ArrayList<TextField> tfPlayerNames = new ArrayList<>();
	private Stage primaryStage;
	private Scene setUpScene;
	private Board board;
	
	
	public Game(Stage primaryStage) {
		this.primaryStage = primaryStage;
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
		board = new Board(primaryStage, 1000, 1000);
		board.createTiles();
		board.createChits();
		board.setIntersections();
		// Do first turn that has weird order
		
		
	}
	private void doTurn(Player player) {
		player.roll();
	}
	
	// Method to setup the GUI
	private void setUpGUI() {
		
		
		// Pane for Player Name Entry
		FlowPane playerPane = new FlowPane(5, 5);
		playerPane.setPadding(new Insets(5,5,5,5));
		
		Button btnContinue = new Button("Continue");
		
		// Create 4 labels and 4 text fields for player name entry
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			lblPlayerNames.add(new Label("Player " + (i + 1) + " Name: "));
			tfPlayerNames.add(new TextField());
			playerPane.getChildren().addAll(lblPlayerNames.get(i), tfPlayerNames.get(i));
		}
		
		// Add Button to flow pane
		playerPane.getChildren().add(btnContinue);
		// Display Player pane
		setUpScene = new Scene(playerPane, 200, 400);
		primaryStage.setScene(setUpScene);
		primaryStage.show();
		
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
				for(int i = 0; i< NUMBER_OF_PLAYERS; i++) {
					players.add(new Player(tfPlayerNames.get(i).getText()));
				}	
			}
			
			// Test to display player creation
			for(Player player: players) {
				int die[] = player.roll();
				System.out.println("Player Number " + (players.indexOf(player) + 1) + "\nPlayer Name: " + player.getName() +
						" Color: "+player.getColor() + "Roll Die 1: " + die[0] + " Roll Die 2: " + die[1] + "\n");

			}
			
		});
		
		
		
	}
	
}
