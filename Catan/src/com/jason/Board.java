package com.jason;

import java.util.ArrayList;
import java.util.Collections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Board {
	
	private final Image BACKGROUND_IMAGE = new Image("/com/jason/resource/water.png");
	
	// Amount of each type of tile and chit
	private final int[] TILE_TYPE_AMOUNTS = {1, 4, 4, 4, 3, 3};
	private final int[] CHIT_TYPE_AMOUNTS = {1,2,2,2,2,2,2,2,2,1};
	private final int[][] related = {{0,0},{2,6},{1,3},{2,4,11},{3,5,14},{4,6,8},{1,5,7},{6,10},{5,9,20},{8,10,16},{7,9,15},
										{3,12},{11,13,21},{12,14,24},{4,13,19},{10,18},{9,17,26},{16,18,31},{15,17},{14,20,28},
										{8,19,25},{12,22},{21,23},{22,24,34},{13,23,27},{20,26,33},{16,25,29},{24,28,36},{19,27,32},
										{26,30,38},{29,31,43},{17,30},{28,33,40},{25,32,37},{23,35},{34,36,46},{27,35,39},{33,38,45},
										{29,37,41},{36,40,48},{32,39,44},{38,42,50},{41,43},{30,42},{40,45,52},{37,44,49},{35,47},
										{46,48},{39,47,51},{45,50,54},{41,49},{48,52,},{44,51,53},{52,54},{49,53}};
	
	private ArrayList<Intersection> listOfIntersections = new ArrayList<>();
	private static int intersectionNumbers = 1;
	// Board width and height
	private double width;
	private double height;
	
	// List of Tiles and Chits
	private ArrayList<Tile> tiles = new ArrayList<>();
	private ArrayList<Chit> chits = new ArrayList<>();
	
	// Pane and stage to display Tiles, chits, and intersections
	// will be moved to game after testing is complete
	private Stage primaryStage;
	private Pane pane = new Pane();
	
	public Board(Stage primaryStage, double width, double height) {
		
		this.primaryStage = primaryStage;
		this.width = width;
		this.height = height;
	}
	
	public void createTiles() {
		// Set board width for tile binding
		Tile.setDimensions(width);
		
		// Create Tiles
		int count = 0;
		for(int tileType : TILE_TYPE_AMOUNTS) {
			for(int i = 0; i < tileType; i++) {
				tiles.add(new Tile(primaryStage, count));
			}
			count++;
		}
		
		// Shuffle Tiles
		Collections.shuffle(tiles);
		
		// Place Tiles on Board
		Tile.setTiles(tiles);
		
		// Test if tiles align by placing in pane and displaying
		pane.setBackground(new Background(new BackgroundImage(BACKGROUND_IMAGE, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		for(Tile tile : tiles) {
			//System.out.println(tile.getPoints() + "\n");
			pane.getChildren().add(tile);
		}
		
	
		Scene scene = new Scene(pane, width, height);
		scene.getStylesheets().add("/com/jason/resource/catan.css");
		primaryStage.setScene(scene);
	}
	
	public void createChits() {
		// Create Chits
		int count = 0;
		for(int chitType: CHIT_TYPE_AMOUNTS) {
			for(int i = 0; i < chitType; i++) {
				//System.out.print(count + "    ");
				chits.add(new Chit(count));

			}
			count++;
		}
		
		// Shuffle Chits
		Collections.shuffle(chits);
		
		// Set Chits on Board
		int tileCount = 0;
		for(Chit chit : chits) {
			Tile currentTile = tiles.get(tileCount);
			System.out.println(chit);
			
			// If Tile is Desert skip tile by increasing tile count
			// So that a chit is not placed on tile
			if(currentTile.getTileType() == 0) {
				Chit thief = new Chit(10);
				thief.setCenterX(currentTile.getCenterX());
				thief.setCenterY(currentTile.getCenterY());
				thief.setRadius(30);
				thief.setThief();
				currentTile.setChit(thief);
				pane.getChildren().add(thief);
				tileCount++;
				currentTile = tiles.get(tileCount);

			}
			// Place chit on tile
			chit.setCenterX(currentTile.getCenterX());
			chit.setCenterY(currentTile.getCenterY());
			chit.setRadius(20);
			chit.setImage();
			pane.getChildren().add(chit);
			currentTile.setChit(chit);
			tileCount++;
		}

	}
	
	public void setIntersections() {
		boolean createIntersection = true;
		int count = 0;
		for(Tile tile: tiles) {

			int tileIntersect[][] = tile.getInersections();
			for(int i = 0; i < 6; i++) {
				createIntersection = true;
				int count2 = 0;
				for(Intersection intersection : listOfIntersections) {
					
					if( intersection.getX() == tileIntersect[i][0] && intersection.getY() == tileIntersect[i][1]) {
						intersection.addTile(tile);
						createIntersection = false;
					} 
				}
				if(createIntersection) {
					Intersection newIntersection = new Intersection(tileIntersect[i][0], tileIntersect[i][1]);
					newIntersection.addTile(tile);
					listOfIntersections.add(newIntersection);
					
					count++;
				}
			}
			/*
			System.out.println("Tile " + (tiles.indexOf(tile) + 1) + ":\n");
			for(int i = 0; i < 6; i++) {
				System.out.print("X" + (i+1) + ": " +intersect[i][0] + " Y" + (i+1) + ": " + intersect[i][1] + "\t");
			} */
		}
		System.out.println("\n\n\n" + count);
		for(Intersection intersection: listOfIntersections) {
			//intersection.setRelated();
			intersection.createCircle();
			System.out.println(intersection);
			

		}
		
		for(Intersection intersection: listOfIntersections) {
			intersection.setRelated();
		}

		
	}
	
	public class Intersection {
		
		
		private int intersectionNumber;
		private ArrayList<Tile> connectedTile = new ArrayList<>();
		private int point[] = {0,0};
		private ArrayList<Intersection> relatedIntersections = new ArrayList<>();
		private Button roundBtn;
		private boolean hasSettlement = false;
		
		public Intersection(int x, int y) {
			this.point[0] = x;
			this.point[1] = y;
			intersectionNumber = intersectionNumbers;
			intersectionNumbers++;
		}
		
		public int getX() {
			return point[0];
		}
		
		public int getY() {
			return point[1];
		}
		
		public void createCircle() {
			/*
			Label label = new Label(String.valueOf(intersectionNumber));
			label.setLayoutX(point[0]);
			label.setLayoutY(point[1]);
			pane.getChildren().add(label);
			*/
			// Create Button
			roundBtn = new Button(String.valueOf(intersectionNumber));
			
			// Set button position
			roundBtn.setLayoutX(point[0] - 10);
			roundBtn.setLayoutY(point[1] - 10);
			
			// Set button style
			roundBtn.setStyle("-fx-background-radius: 5em; " + 
								"-fx-min-width: 20px; " + 
								"-fx-min-height: 20px; " + 
								"-fx-max-width: 20px; " + 
								"-fx-max-height: 20px;");
			
			// Set button in pane
			pane.getChildren().add(roundBtn);
			
			// Set button event listener
			roundBtn.setOnMouseClicked(e -> {
				
				if(!hasSettlement) {
					hasSettlement = true;
					System.out.println(this);
					roundBtn.setLayoutX(point[0] -20);
					roundBtn.setLayoutY(point[1] - 20);
					roundBtn.setStyle("-fx-graphic: url('/com/jason/resource/pieces/BlueSettlement.png'); " +
					"-fx-background-color: transparent; " +
					"-fx-min-width: 30px; " + 
					"-fx-min-height: 30px; " + 
					"-fx-max-width: 30px; " + 
					"-fx-max-height: 30px;");
					for(Intersection aIntersection: relatedIntersections) {
		
		
						aIntersection.changeButtonColor();
					}
				} else {
					System.out.println("Can't build here");
				}
			});
		}
		
		public void addTile(Tile tile) {
			connectedTile.add(tile);
		}
		
		public void changeButtonColor() {
			roundBtn.setDisable(true);
			roundBtn.setStyle("-fx-background-color:#ff0000; " +
			"-fx-background-radius: 5em; " + 
			"-fx-min-width: 20px; " + 
			"-fx-min-height: 20px; " + 
			"-fx-max-width: 20px; " + 
			"-fx-max-height: 20px;");
		}
		
		public void setRelated() {
			System.out.println("\nRelated Intersections for intersection " + intersectionNumber + ": ");
			for(int i = 0; i<related[intersectionNumber].length; i++) {
				System.out.println(listOfIntersections.get(related[intersectionNumber][i] - 1));
				relatedIntersections.add(listOfIntersections.get(related[intersectionNumber][i] -1));
			}
		}
		
		@Override
		public String toString() {
			return intersectionNumber + " ";
			/*
			String details = "Intersection " + intersectionNumber + " Connected Tiles: \n";
			int count = 0;
			for(Tile tile: connectedTile) {
				details += "Tile " + count + ": " + tile + " Chit #: " +  tile.getChit() + "\n";
				count++;
			} */
			
			//return details;
			//return "Intersection " + intersectionNumber + ": Connected tiles: " + connectedTile + "(" + point[0] + ", " + point[1] + ")\n";
		}
	}

}
