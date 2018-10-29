package com.jason;

import javafx.scene.shape.Polygon;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Tile {
	
	private double y = 50;
	private double width = 100;
	private double height = 100;
	private double points[] = {0,0,0,0,0,0};
	private int number;
	private double origin[] = {0,0};
	Image image = new Image("/com/jason/resource/HexImages/hill.png");
	
	public Tile(double topCenter, Stage primaryStage) {
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
		
		hexagon.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
		
		Group root = new Group(hexagon);
		Scene scene = new Scene(root, 1000, 1000);
		primaryStage.setScene(scene);
	}
}
