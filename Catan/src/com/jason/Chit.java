package com.jason;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


public class Chit extends Circle{
	
	private int type = 0;
	private String chitNumbers[] = {"2","3","4","5","6","8","9","10","11","12"};
	private Image chitImages[] = {	new Image("/com/jason/resource/chits/2.png"), new Image("/com/jason/resource/chits/3.png"),
									new Image("/com/jason/resource/chits/4.png"), new Image("/com/jason/resource/chits/5.png"),
									new Image("/com/jason/resource/chits/6.png"), new Image("/com/jason/resource/chits/8.png"),
									new Image("/com/jason/resource/chits/9.png"), new Image("/com/jason/resource/chits/10.png"),
									new Image("/com/jason/resource/chits/11.png"), new Image("/com/jason/resource/chits/12.png")};
	private Image thief = new Image("/com/jason/resource/chits/robber.png");
	

	
	public Chit(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return chitNumbers[type];
	}
	
	public void setImage() {
		this.setFill(new ImagePattern(chitImages[type], 0, 0, 1, 1, true));
	}
	
	public void setThief() {
		
		this.setFill(new ImagePattern(thief, 0, 0, 1, 1, true));
	}
}
