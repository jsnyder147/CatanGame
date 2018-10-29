package com.jason;

import java.util.ArrayList;
import javafx.stage.Stage;

public class Board {
	private final int NUMBER_OF_HEXES = 19;
	public int intersections[];
	private Stage primaryStage;
	private double width;
	private double height;
	private double middle;
	
	
	private ArrayList<Tile> tiles = new ArrayList<>();
	
	public Board(Stage primaryStage, double width, double height) {
		this.primaryStage = primaryStage;
		this.width = width;
		this.height = height;
		middle = width/2.0;
		setTiles();
		setChits();
	}
	
	private void setTiles() {
		for(int i = 0; i < NUMBER_OF_HEXES; i++) {
			
			Tile tile = new Tile(middle, primaryStage);
		}

	}
	
	private void setChits() {
		
	}
}
