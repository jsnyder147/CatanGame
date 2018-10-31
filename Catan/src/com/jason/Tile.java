package com.jason;

import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Tile extends Polygon{
	private Image tileImages[] = {new Image("/com/jason/resource/HexImages/desert.png"), new Image("/com/jason/resource/HexImages/field.png"),
									new Image("/com/jason/resource/HexImages/forest.png"), new Image("/com/jason/resource/HexImages/pasture.png"),
									new Image("/com/jason/resource/HexImages/mountain.png"), new Image("/com/jason/resource/HexImages/hill.png")};
	private int tileType = 0;
	private static double boardWidth;
	private static double boardHeight;
	private double width;
	private double height;
	private double origin[] = {0,0};
	private String tileTypeNames[] = {"Desert", "Field", "Forest", "Pasture", "Hill", "Mountain"};
	private double center[] = {0,0};
	private Chit chit;

	
	public Tile(Stage primaryStage, int tileType) {

		this.tileType = tileType;
		width = boardWidth / 7.0;
		height = width;
		
	}
	
	// set tile type, int value correlates with image for tile
	public int getTileType() {
		return tileType;
	}
	
	public static void setTiles(ArrayList<Tile> tiles) {
		double tileWidth = tiles.get(0).width;
		double tileHeight = tiles.get(0).width;
		double horizontalCenter = (boardWidth/2.0) - (tileWidth/2.0);
		double y = (boardHeight - (5 * tileHeight)) /4.0;
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
	}
	
	public void setOrigin(double x, double y) {
		origin[0] = x;
		origin[1] = y;
		setPoints();
	}
	
	private void setPoints(){
		
		// get origin of tile as start point
		double x = origin[0];
		double y = origin[1];
		// set center of tile
		center[0] = (x + width/2.0);
		center[1] = (y + height/2.0);
		
		// set points of hexagon
		this.getPoints().addAll(new Double[] {
				x + (width * .25), y,
				x + (width * .75), y,
				x +  width, y +(height * .5),
				x + (width * .75), y + height,
				x + (width * .25), y + height,
				x, y + (height *.5) 
		});
		
		// set image of tile
		this.setFill(new ImagePattern(tileImages[tileType], 0, 0, 1, 1, true));
		
	}
	
	public double getCenterX() {
		return center[0];
	}
	
	public double getCenterY() {
		return center[1];
	}
	
	public static void setDimensions(double width) {
		boardWidth = width;
		boardHeight = boardWidth;
		
	}
	
	public void setChit(Chit chit) {
		this.chit = chit;
	}
	
	@Override
	public String toString() {
		return tileTypeNames[getTileType()];
	}
}
