package com.jason;

import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Tile extends Polygon{
	private Image tileImages[] = {new Image("/com/jason/resource/HexImages/desert.png"), new Image("/com/jason/resource/HexImages/field.png"),
									new Image("/com/jason/resource/HexImages/forest.png"), new Image("/com/jason/resource/HexImages/pasture.png"),
									new Image("/com/jason/resource/HexImages/mountain.png"), new Image("/com/jason/resource/HexImages/hill.png")};
	private int tileType = 0;
	private double width;
	private double height;
	private double points[] = {0,0,0,0,0,0};
	private int number;
	private double origin[] = {0,0};
	private String tileTypeNames[] = {"Desert", "Field", "Forest", "Pasture", "Hill", "Mountain"};
	private double center[] = {0,0};

	
	public Tile(double width, Stage primaryStage, int tileType) {
		this.tileType = tileType;
		this.width = width / 10.0;
		height = this.width;
		
	}
	
	// set tile type, int value correlates with image for tile
	public int getTileType() {
		return tileType;
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
	
	@Override
	public String toString() {
		return tileTypeNames[getTileType()];
	}
}
