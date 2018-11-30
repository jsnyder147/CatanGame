package com.jason;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;




public class Game {
	//TEST COUNTER
	private static Player currentPlayer;
	private static Label lblPlayerName = new Label();
	private static int firstTurnIndex = 3;
	private static boolean reround = false;
	
	private int counter = 0;
	private final Image BACKGROUND_IMAGE = new Image("/com/jason/resource/water.png");
	private final int NUMBER_OF_PLAYERS = 4;
	private boolean nextPlayer = false;
	private int playerNum;
	private boolean isWinner = false;
	private Player winner;
	private static ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Label> lblPlayerNames = new ArrayList<>();
	private ArrayList<TextField> tfPlayerNames = new ArrayList<>();
	private static Stage stage;
	private static Scene gameScene;
	private static Scene mainMenuScene;
	private Board board;
	private static BorderPane gamePane;
	private VBox playerPane;
	private HBox dicePane;
	private Button btnContinue;
	private ImageView dieOne;
	private ImageView dieTwo;
	private final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE};
	private Image diceImages[] = {new Image("/com/jason/resource/dice/one.png"), new Image("/com/jason/resource/dice/two.png"),
			new Image("/com/jason/resource/dice/three.png"), new Image("/com/jason/resource/dice/four.png"),
			new Image("/com/jason/resource/dice/five.png"), new Image("/com/jason/resource/dice/six.png")};
	
	
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
		playerPane = new VBox();
		playerPane.setPadding(new Insets(5,5,5,5));
		playerPane.getStyleClass().add("playerNamePane");
		
		
		// Test if tiles align by placing in pane and displaying
		gamePane.setBackground(new Background(new BackgroundImage(BACKGROUND_IMAGE, BackgroundRepeat.NO_REPEAT,
			BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

		btnContinue = new Button("Continue");
		
		// Create 4 labels and 4 text fields for player name entry
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			Label label = new Label("Player " + (i + 1) + " Name: ");
			label.setTextFill(colors[i]);
			lblPlayerNames.add(label);
			//lblPlayerNames.add(new Label("Player " + (i + 1) + " Name: "));
			tfPlayerNames.add(new TextField());
			playerPane.getChildren().addAll(lblPlayerNames.get(i), tfPlayerNames.get(i));
		}
		
		// Add Button to flow pane
		playerPane.getChildren().add(btnContinue);
		gamePane.setLeft(playerPane);
		
		// Display Player pane
		BorderPane.setAlignment(playerPane, Pos.CENTER);
		gameScene = new Scene(gamePane, 1200, 1100);
		System.out.println("GAME SCENE WIDTH: " + gameScene.getWidth());
		stage.setScene(gameScene);
		stage.show();
		
		// Center Stage On Screen
		stage.centerOnScreen();
		
		// Set PlayerPane Width
		playerPane.setPrefWidth(gameScene.getWidth() / 4);
		
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
				
				// Start First Turn
				doRolls(players);
			}
			
		});

	}
	
	// Method to Check if First Roll Has Duplicates
	private ArrayList<Player> checkMultiples(ArrayList<Player> players) {
		int highestRoll = 0;
		for(Player player : players) {
			int roll = player.getRollSum();
			if(roll > highestRoll) {
				highestRoll = roll;
			}
		}
		// Declare Array List to Hold Players that Have Duplicate Rolls
		ArrayList<Player> reRollPlayers = new ArrayList<>();
		
		// Iterate through all players
		for(int i = 0; i < players.size(); i++) {
			// Get Each Player Reference
			Player player = players.get(i);
			
			// Iterate through all players and compare to player
			for(int j = 0; j < players.size(); j++) {
				
				// Get another player to compare to  player
				Player nextPlayer = players.get(j);
				
				// If player is equal to player skip
				if(player == nextPlayer) {
					continue;
				}
				
				// If two players rolled same amount AND they have the highest roll, add that player to reRoll arrayList
				if(nextPlayer.getRollSum() == player.getRollSum() && player.getRollSum() == highestRoll) {
					if(!reRollPlayers.contains(player)) {
						reRollPlayers.add(player);
					}
				
				// FOR SECOND ITERATION
				// If player's roll is distinct and previously was not,
				// Re-add player to players list
				} else if(!this.players.contains(player)) {
					this.players.add(player);
				}
			}
		}
		
		// Set first player as player with highest roll
		Player highRoller = players.get(0);
		
		// If there aren't any duplicate rolls assign turn order
		if(reRollPlayers.size() == 0) {
			
			// Iterate through players and get highest roller;
			for(Player player: players) {
				if(player.getRollSum() > highRoller.getRollSum()) {
					highRoller = player;
				}
			}
			
			// Reorder player list for final turn order
			int highIndex = this.players.indexOf(highRoller);
			int distance = this.players.size() - highIndex;
			Collections.rotate(this.players, distance);
			
			for(Player player : this.players) {
				System.out.println("Player " + player.getPlayerNum());
			}
		}
		
		/*****      FOR TESTING    ******
		System.out.println("RE-ROLL PLAYERS: ");
		for(Player player : reRollPlayers) {
			System.out.print(player.getPlayerNum() + " ");
		}
		******					   ******/

		
		// Return List of players who rolled the same number
		return reRollPlayers;
	}
	
	// Method to do first Turn Rolls
	private void doRolls(ArrayList<Player> players) {
		// Set Up Dice Pane
		dicePane = new HBox();
		dicePane.setPrefWidth(playerPane.getPrefWidth() - (playerPane.getPrefWidth() / 10));
		dicePane.setSpacing(playerPane.getPrefWidth() / 20);
		dicePane.setAlignment(Pos.CENTER);
		dicePane.setPadding(new Insets(10,0,10,0));
		
		// Set up Player Number Label
		playerNum = 0;
		Label lblPlayer = new Label();
		lblPlayer.setText("Player " + players.get(0).getPlayerNum());
		lblPlayer.setPadding(new Insets(10,0,10,0));
		
		
		// Set up Roll and Next buttons
		Button btnRoll = new Button("Roll");
		Button btnNext = new Button("Next");
		btnRoll.setPrefWidth(dicePane.getPrefWidth() / 2);
		btnNext.setPrefWidth(dicePane.getPrefWidth() / 2);
		
		
		// Add player label and roll button to playerPane and setup
		playerPane.getChildren().addAll(lblPlayer, btnRoll);
		playerPane.setAlignment(Pos.TOP_CENTER);
		
		/******* 	FOR TESTING	   ******
		int count = 0;
		int rolls[][][] = {{{6,6}, {1,4}, {6,6}, {5,1}}, {{1,4}, {1,5}, {1,4}},{{1,5}, {1,6}}};
		for(Player player : players) {
			player.setRoll(rolls[counter][count]);
			System.out.println("Player " + player.getPlayerNum() + " Roll: Die One: " + player.getRoll()[0] + ", Die Two: " + player.getRoll()[1]);
			count++;
		}
		counter ++;
		********				   ******/
		
		// Get roll for each player
		for(Player player : players) {
			player.roll();
			System.out.println("Player " + player.getPlayerNum() + " Roll: Die One: " + player.getRoll()[0] + ", Die Two: " + player.getRoll()[1]);
		} 


		// Roll Button Event Listener
		btnRoll.setOnMouseClicked(e -> {
			
			Dice dice = new Dice();
			// Disable Roll Button and Next Button
			btnRoll.setDisable(true);
			btnNext.setDisable(true);
			
			// Create Dice Instances
			dieOne = new ImageView();
			dieTwo = new ImageView();
			
			// Set up Dice
			dieOne.setFitWidth((dicePane.getPrefWidth() / 2) - 10);
			dieOne.setFitHeight(dieOne.getFitWidth());
			dieTwo.setFitWidth(dieOne.getFitWidth());
			dieTwo.setFitHeight(dieOne.getFitWidth());
			
			// Add Dice to Dicepane
			dicePane.getChildren().addAll(dieOne, dieTwo);
			
			// Add Next Button and Dicepane to playerPane
			playerPane.getChildren().addAll(dicePane, btnNext);
			
			// Animation
			EventHandler<ActionEvent> eventHandler = ev -> {
				// Get New Roll
				int roll[] = dice.roll();
				
				/*****			TEST DISPLAY ROLL				******
				System.out.println("Roll : " + roll[0] + ", " + roll[1]);
				******			END TEST DISPLAY ROLL			*****/
				
				// Set Dice Images to random roll
				dieOne.setImage(diceImages[roll[0] -1]);
				dieTwo.setImage(diceImages[roll[1] -1]);
				
			};
			
			// Set Up Animation
			Timeline animation = new Timeline(new KeyFrame(Duration.millis(250), eventHandler));
			animation.setCycleCount(9);
			
			// On finish Animation
			animation.setOnFinished( event -> {
				dieOne.setImage(diceImages[players.get(playerNum).getRoll()[0] - 1]);
				dieTwo.setImage(diceImages[players.get(playerNum).getRoll()[1] -1 ]);
				btnNext.setDisable(false);
				playerNum++;

			});
			
			// Start Dice Animation
			animation.play();
			
		});
		
		// Next Button Event Listener
		btnNext.setOnMouseClicked(e -> {
			// If PlayerNum is <= 4 set up next player
			if(playerNum < players.size()) {
				btnRoll.setDisable(false);
				playerPane.getChildren().removeAll(dicePane, btnNext);
				dicePane.getChildren().removeAll(dieOne, dieTwo);
				lblPlayer.setText("Player " + players.get(playerNum).getPlayerNum());
			
			// If PlayerNum is > 4 remove roll info and start game
			} else {
				//Remove Dice, Player Label, Roll Button, and Next Button
				dicePane.getChildren().removeAll(dieOne, dieTwo);
				playerPane.getChildren().removeAll(lblPlayer, btnRoll, btnNext, dicePane);
				

				// Check If there are any ties and assign tied players
				ArrayList<Player> reRollPlayers = checkMultiples(players);
				
				// If Ties than roll again for tied players
				if(reRollPlayers.size() > 0) {
					// Add Tied label and players that are tied
					ArrayList<Label> tie = new ArrayList<>();
					tie.add(new Label("There is a\nTie Between:"));
					tie.get(0).setTextAlignment(TextAlignment.CENTER);
					tie.get(0).setPadding(new Insets(10,0,0,0));
					for(Player player : reRollPlayers) {
						tie.add(new Label("Player " + player.getPlayerNum()));
					}
					
					// Setup roll again button and padding for label
					Button btnContinue = new Button("Roll Again");
					btnContinue.setPrefWidth(dicePane.getPrefWidth() / 2);
					tie.get(tie.size() - 1).setPadding(new Insets(0,0,10,0));
					
					// Add Tied Labels and Roll again button
					playerPane.getChildren().addAll(tie);
					playerPane.getChildren().add(btnContinue);
					
					// Roll Again Event Listener
					btnContinue.setOnMouseClicked(ev -> {
						// Remove Tied Labels, and then Roll again
						playerPane.getChildren().removeAll(tie);
						playerPane.getChildren().remove(btnContinue);
						doRolls(reRollPlayers);
					});
					
				// If No ties Display Player Order
				} else {
					
					/*****		FOR TESTING		******/
					for(Player player : this.players) {
						System.out.println(player.getPlayerNum());
					}
					/*****		END TESTING		******/
					
					// Remove Dice, Player label, Next Button, and Roll Button
					dicePane.getChildren().removeAll(dieOne, dieTwo);
					playerPane.getChildren().removeAll(lblPlayer, btnRoll, btnNext, dicePane);
					
					// Call to Display player order
					showPlayerOrder();
				}
			}
		});
	}
	
	// Method to display final Player Order
	private void showPlayerOrder() {
		
		// List of Labels for Player Names
		ArrayList<Label> turnOrder = new ArrayList<>();
		playerNum = 1;
		turnOrder.add(new Label("Turn Order"));
		
		// Iterate through players and add Labels to turnOrder list
		for(Player player: this.players) {
			turnOrder.add(new Label("Player " + playerNum + " "  + player.getName()));
			System.out.println("Player " + player.getPlayerNum() + ": Roll: " + player.getRollSum());
			player.setPlayerNum(playerNum);
			playerNum++;
		}
		
		// Setup up label padding and Start button
		turnOrder.get(turnOrder.size() -1).setPadding(new Insets(0,0,10,0));
		Button btnStart = new Button("Start");
		btnStart.setPrefWidth(dicePane.getPrefWidth() / 2);
		
		// Add Labels and start button to playerPane
		playerPane.getChildren().addAll(turnOrder);
		playerPane.getChildren().add(btnStart);
		
		// Start Game event listener
		btnStart.setOnMouseClicked(e -> {
			// Remove turn order lables and start button
			playerPane.getChildren().removeAll(turnOrder);
			playerPane.getChildren().removeAll(btnStart);
			
			// Finish setting up board for play
			board.setIntersections();
			board.setConnections();
			board.finishBoard();
			System.out.println("\n\nBOARD CREATED\n\n");
			
			playTurn();
		});
	}
	
	private void playTurn() {
		currentPlayer = players.get(0);
		//Label lblPlayerName = new Label(players.get(0).getName());
		lblPlayerName.setText(players.get(0).getName());
		lblPlayerName.setTextFill(players.get(0).getColor());
		Board.setPlayerColorIndex(players.get(0).getColor());
		Board.setPlayerPane(playerPane);
		
		playerPane.getChildren().add(lblPlayerName);
	}
	
	public static void nextTurn() {
		if(players.indexOf(currentPlayer) == players.size() -1 || reround) {
			reround = true;
			currentPlayer = players.get(firstTurnIndex);
			lblPlayerName.setText(currentPlayer.getName());
			lblPlayerName.setTextFill(currentPlayer.getColor());
			Board.setPlayerColorIndex(currentPlayer.getColor());
			if(firstTurnIndex != 0) {
				firstTurnIndex--;
			}
			else {
				
			}
			
		} else {
			currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
			Board.setPlayerColorIndex(currentPlayer.getColor());
			lblPlayerName.setText(currentPlayer.getName());
			lblPlayerName.setTextFill(currentPlayer.getColor());
			
		}
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
	
}
