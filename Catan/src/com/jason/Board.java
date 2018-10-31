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

public class Board {
	
	private final Image BACKGROUND_IMAGE = new Image("/com/jason/resource/water.png");
	
	// Amount of each type of tile and chit
	private final int[] TILE_TYPE_AMOUNTS = {1, 4, 4, 4, 3, 3};
	private final int[] CHIT_TYPE_AMOUNTS = {1,2,2,2,2,2,2,2,2,1};
	
	public int intersections[];

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
			System.out.println(tile.getPoints() + "\n");
			pane.getChildren().add(tile);
		}
		
	
		Scene scene = new Scene(pane, width, height);
		primaryStage.setScene(scene);
	}
	
	public void createChits() {
		// Create Chits
		int count = 0;
		for(int chitType: CHIT_TYPE_AMOUNTS) {
			for(int i = 0; i < chitType; i++) {
				System.out.print(count + "    ");
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
		// Make interactive intersections
		
	}

}
