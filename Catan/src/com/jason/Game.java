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
	
	private final int numPlayers = 4;
	private boolean isWinner;
	private Player winner;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Label> lblPlayerNames = new ArrayList<>();
	private ArrayList<TextField> tfPlayerNames = new ArrayList<>();
	private Stage primaryStage;
	private Scene setUpScene;
	
	public Game(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void startGame() {
		// Setup Board and Players
		setUp();
		
		// While all players VP < 10
		while(!isWinner) {
			// Do each players turn
			for(int i = 0; i < numPlayers; i++) {
				doTurn(players.get(i));
				// if player wins end game
				if(players.get(i).getVictoryPoints() >= 10) {
					isWinner = true;
					break;
				}
			}
		}
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
		
	}
	
	// Method to setup the GUI
	private void setUpGUI() {
		
		
		// Pane for Player Name Entry
		FlowPane playerPane = new FlowPane(5, 5);
		playerPane.setPadding(new Insets(5,5,5,5));
		
		Button btnContinue = new Button("Continue");
		
		// Create 4 labels and 4 text fields for player name entry
		for(int i = 0; i < numPlayers; i++) {
			lblPlayerNames.add(new Label("Player " + (i + 1) + " Name: "));
			tfPlayerNames.add(new TextField());
			playerPane.getChildren().addAll(lblPlayerNames.get(i), tfPlayerNames.get(i));
		}
		
		// Add Button to flow pane
		playerPane.getChildren().add(btnContinue);
		
		// set button event listener
		btnContinue.setOnAction(e -> {
			
			boolean allPlayersEntered = true;
			// Check for valid entry
			for(int i = 0; i < numPlayers; i++) {
				if(tfPlayerNames.get(i).getText().isEmpty()) {
					allPlayersEntered = false;

					
					
					// Need to Add: Change Text Field Border Color to Red
					
										
					
				}
			}
			
			// If all players names have been entered create new players
			if(allPlayersEntered) {
				for(int i = 0; i< numPlayers; i++) {
					players.add(new Player(tfPlayerNames.get(i).getText()));
				}	
			}
			
			// Test to display player creation
			for(Player player: players) {
				System.out.println("Player Number " + (players.indexOf(player) + 1) + "\nPlayer Name: " + player.getName() + " Color: "+player.getColor() + "\n");
			}
		});
		
		setUpScene = new Scene(playerPane, 200, 400);
		primaryStage.setScene(setUpScene);
		primaryStage.show();
		
	}
	
}
