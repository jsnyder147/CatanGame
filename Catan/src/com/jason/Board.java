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

public class Board {
	private final Image BACKGROUND_IMAGE = new Image("/com/jason/resource/water.png");
	private final int[] TILE_TYPE_AMOUNTS = {1, 4, 4, 4, 3, 3};
	private final int NUMBER_OF_HEXES = 19;
	public int intersections[];
	private Stage primaryStage;
	private double width;
	private double height;
	private double origin[] = {0.0, 0.0};
	private final double TOP_PADDING = 50;
	
	
	private ArrayList<Tile> tiles = new ArrayList<>();
	
	public Board(Stage primaryStage, double width, double height) {
		
		this.primaryStage = primaryStage;
		this.width = width;
		this.height = height;
		origin[0] = width/2.0 - ((width/10.0)/2.0);
		origin[1] = height/10;
	}
	

	
	public void createTiles() {
		int count = 0;
		for(int tileType : TILE_TYPE_AMOUNTS) {
			for(int i = 0; i < tileType; i++) {
				tiles.add(new Tile(width, primaryStage, count));
			}
			count++;
		}
		
		Collections.shuffle(tiles);

		setTiles();
	}
	
	private void setTiles() {
		
		double tileWidth = width/10.0;
		double tileHeight = tileWidth;
		double horizontalCenter = (width/2.0) - (tileWidth/2.0);
		double y = TOP_PADDING;
		double x = 0;
		int tilesMade = 0;
		int repeat = 0;
		
		// calculate origin for each tile in row
		for(int i = 0; i < 9; i++) {
			switch(i) {
			// Rows with 1 tile
			case 0:
			case 8:
				x = horizontalCenter;
				repeat = 1;
				break;
			// Rows with 2 tiles
			case 1:
			case 3:
			case 5:
			case 7:
				x = horizontalCenter - (tileWidth *.75);
				repeat = 2;
				break;
			// Rows with 3 tiles
			case 2:
			case 4:
			case 6:
				x = horizontalCenter - ((tileWidth * 2) * .75);
				repeat = 3;
				break;
				
			}
			
			// Set points for row of tiles
			for(int j = 0; j < repeat; j++) {
				tiles.get(tilesMade).setOrigin(x, y);
				tilesMade++;
				x+= tileWidth * 1.5;
			}
			y += tileHeight *.5;

		}
		
		// Test if tile points are correct by displaying to console
		for(Tile tile:tiles) {
			System.out.println(tile);
			System.out.println("\n" + tile.getPoints());
		}
		
		// Test if tiles align by placing in pane and displaying
		Pane pane = new Pane();
		pane.setBackground(new Background(new BackgroundImage(BACKGROUND_IMAGE, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		for(Tile tile : tiles) {
			pane.getChildren().add(tile);
			
			pane.getChildren().add(new Circle(tile.getCenterX(), tile.getCenterY(), 10));
		}
	
		Scene scene = new Scene(pane, width, height);
		primaryStage.setScene(scene);

	}
	private void setChits() {
		
	}
}
