package com.jason;

import javafx.scene.shape.Polygon;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
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

	
	public Tile(double width, Stage primaryStage, int tileType) {
		this.tileType = tileType;
		this.width = width / 10.0;
		height = this.width;
		
		/*
		double point1[] = {topCenter - (width * .25), y};
		double point2[] = {topCenter + (width * .25), y};
		double point3[] = {topCenter + (width * .5), y + (height * .5)};
		double point4[] = {topCenter + (width * .25), y + height};
		double point5[] = {topCenter - (width * .25), y + height};
		double point6[] = {topCenter - (width * .5), y + (height *.5)};
		
		
		Polygon hexagon = new Polygon();
		hexagon.getPoints().addAll(new Double[] {
				point1[0], point1[1],
				point2[0], point2[1],
				point3[0], point3[1],
				point4[0], point4[1],
				point5[0], point5[1],
				point6[0], point6[1],
		}); 
		
		
		
		Group root = new Group(hexagon);
		Scene scene = new Scene(root, 1000, 1000);
		primaryStage.setScene(scene); */
	}
	
	public int getTileType() {
		return tileType;
	}
	
	public void setOrigin(double x, double y) {
		origin[0] = x;
		origin[1] = y;
		setPoints();
	}
	
	private void setPoints(){
		double x = origin[0];
		double y = origin[1];
		this.getPoints().addAll(new Double[] {
				x + (width * .25), y,
				x + (width * .75), y,
				x +  width, y +(height * .5),
				x + (width * .75), y + height,
				x + (width * .25), y + height,
				x, y + (height *.5) 
		});
		this.setFill(new ImagePattern(tileImages[tileType], 0, 0, 1, 1, true));
	}
	
	
	@Override
	public String toString() {
		return tileTypeNames[getTileType()];
	}
}
