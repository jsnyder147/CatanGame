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

public class Board {
	private final Image BACKGROUND_IMAGE = new Image("/com/jason/resource/water.png");
	private final int[] TILE_TYPE_AMOUNTS = {1, 4, 4, 4, 3, 3};
	private final int[] CHIT_TYPE_AMOUNTS = {1,2,2,2,2,1,2,2,2,2,1};
	private final int NUMBER_OF_CHITS = 18;
	public int intersections[];
	private Stage primaryStage;
	private double width;
	private double height;
	private double origin[] = {0.0, 0.0};
	private ArrayList<Tile> tiles = new ArrayList<>();
	private ArrayList<Chit> chits = new ArrayList<>();
	private Pane pane = new Pane();
	
	public Board(Stage primaryStage, double width, double height) {
		
		this.primaryStage = primaryStage;
		this.width = width;
		this.height = height;
		origin[0] = width/2.0 - ((width/7.0)/2.0);
		origin[1] = height/10;
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
		int count2 = 0;
		for(Chit chit : chits) {
			System.out.println(chit);
			chit.setCenterX(tiles.get(count2).getCenterX());
			chit.setCenterY(tiles.get(count2).getCenterY());
			chit.setRadius(20);
			chit.setImage();
			pane.getChildren().add(chit);
			count2++;
		}
	}

}
