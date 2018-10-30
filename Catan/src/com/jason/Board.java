package com.jason;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.Group;
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
		for(int i = 0; i < 9; i++) {
			switch(i) {
			case 0:
			case 8:
				x = horizontalCenter;
				repeat = 1;
				break;
			case 1:
			case 3:
			case 5:
			case 7:
				x = horizontalCenter - (tileWidth *.75);
				repeat = 2;
				break;
			case 2:
			case 4:
			case 6:
				x = horizontalCenter - ((tileWidth * 2) * .75);
				repeat = 3;
				break;
				
			}
			
			for(int j = 0; j < repeat; j++) {
				tiles.get(tilesMade).setOrigin(x, y);
				tilesMade++;
				x+= tileWidth * 1.5;
			}
			y += tileHeight *.5;

		}
		
		
		for(Tile tile:tiles) {
			System.out.println(tile);
			System.out.println("\n" + tile.getPoints());
		}
		
		Pane pane = new Pane();
		pane.setBackground(new Background(new BackgroundImage(BACKGROUND_IMAGE, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		for(Tile tile : tiles) {
			pane.getChildren().add(tile);
		}
		Scene scene = new Scene(pane, width, height);
		primaryStage.setScene(scene);

	}
	private void setChits() {
		
	}
}
