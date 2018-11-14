package com.jason;

import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group; 
import javafx.scene.PerspectiveCamera; 
import javafx.animation.TranslateTransition;
import javafx.animation.RotateTransition;
import javafx.util.Duration;
import javafx.scene.transform.Rotate;
import javafx.animation.*;
import org.fxyz3d.shapes.primitives.CuboidMesh;

public class RollDice {
	
	private double width = 500;
	private double height = 500;

	private ParallelTransition pt;
	private CuboidMesh dieOne;
	private CuboidMesh dieTwo;
	private Group root;
	private Group root2;
	private Group medium;
	private Group medium2;
	private Group everything;
	private Group everything2;
	private Pane pane;
	private Image BACKGROUND_IMAGE = new Image("com/jason/resource/roll/rollBoard.png");
	
	public RollDice(double width, double height) {
		
		this.width = width;
		this.height = height;
		
		pane = Game.getDicePane();

	}
	
	public void setDice(int dieOneNum, int dieTwoNum) {
		double diceWidth = width/10;
		double diceHeight = width/10;
		double diceDepth = diceWidth;
		
		// Set Rotate Axis
		Rotate rxDie = new Rotate(0,0,0,0, Rotate.X_AXIS);
		Rotate ryDie = new Rotate(0,0,0,0, Rotate.Y_AXIS);
		Rotate rzDie = new Rotate(0,0,0,0, Rotate.Z_AXIS);
		Rotate rxDie2 = new Rotate(0,0,0,0, Rotate.X_AXIS);
		Rotate ryDie2 = new Rotate(0,0,0,0, Rotate.Y_AXIS);
		Rotate rzDie2 = new Rotate(0,0,0,0, Rotate.Z_AXIS);
		
		// Die One Rolls
		if(dieOneNum == 1) {
			rxDie.setAngle(0);
			ryDie.setAngle(0);
			rzDie.setAngle(0);
		}
		if(dieOneNum == 2) {
			rxDie.setAngle(320);
			ryDie.setAngle(20);
			rzDie.setAngle(0);
		}
		if(dieOneNum == 3) {
			rxDie.setAngle(0);
			ryDie.setAngle(40);
			rzDie.setAngle(0);
		}
		if(dieOneNum == 4) {
			rxDie.setAngle(-5);
			ryDie.setAngle(-45);
			rzDie.setAngle(0);
		}
		if(dieOneNum == 5) {
			rxDie.setAngle(40);
			ryDie.setAngle(40);
			rzDie.setAngle(20);
		}
		if(dieOneNum == 6) {
			rxDie.setAngle(10);
			ryDie.setAngle(90);
			rzDie.setAngle(0);
		}
		
		// Die Two Rolls
		if(dieTwoNum == 1) {
			rxDie2.setAngle(0);
			ryDie2.setAngle(0);
			rzDie2.setAngle(0);
		}
		if(dieTwoNum == 2) {
			rxDie2.setAngle(280);
			ryDie2.setAngle(20);
			rzDie2.setAngle(0);
		}
		if(dieTwoNum == 3) {
			rxDie2.setAngle(0);
			ryDie2.setAngle(90);
			rzDie2.setAngle(0);
		}
		if(dieTwoNum == 4) {
			rxDie2.setAngle(10);
			ryDie2.setAngle(-70);
			rzDie2.setAngle(0);
		}
		if(dieTwoNum == 5) {
			rxDie2.setAngle(100);
			ryDie2.setAngle(0);
			rzDie2.setAngle(0);
		}
		if(dieTwoNum == 6) {
			rxDie2.setAngle(190);
			ryDie2.setAngle(0);
			rzDie2.setAngle(0);
		}

		// Create Dice
		dieOne = new CuboidMesh(diceWidth,diceHeight,diceDepth);
		dieOne.getTransforms().addAll(rxDie, ryDie, rzDie);
		dieTwo = new CuboidMesh(diceWidth,diceHeight,diceDepth);
		
		// Set Textures
		dieOne.setTextureModeImage("com/jason/resource/roll/diceImage.jpg");
		dieTwo.setTextureModeImage("com/jason/resource/roll/diceImage.jpg");

		// Set Transform
        dieOne.getTransforms().addAll(rxDie, ryDie, rzDie);
        dieTwo.getTransforms().addAll(rxDie2, ryDie2, rzDie2); 
        
        // Set Groups for Dice
        root = new Group();
        root2 = new Group();
        root.getChildren().add(dieOne);
        root2.getChildren().add(dieTwo);
        
        // Set Containing Groups for dice
        medium = new Group();
        medium2 = new Group();
		medium.getChildren().add(root);
		medium2.getChildren().add(root2);
		
		// Add Everything to group
		everything = new Group();
		everything.getChildren().addAll(medium, medium2);

	}

	public void showStage() {
		
		// Set Scene and Stage
		PerspectiveCamera camera = new PerspectiveCamera(true);
		Rotate ryDie = new Rotate(0,0,0,0, Rotate.Y_AXIS);
		ryDie.setAngle(90);
		camera.getTransforms().add(ryDie);
		camera.setTranslateX(0);
		camera.setTranslateY(0);
		camera.setTranslateZ(0);
		everything2 = new Group();
		everything2.getChildren().addAll( everything);
		Group everything3 = new Group();
		
		ImagePattern pattern = new ImagePattern(BACKGROUND_IMAGE);
		Rectangle r = new Rectangle();
		r.setX(-0);
		r.setY(50);
		r.setWidth(width);
		r.setHeight((width/3) * 2);
		r.setFill(pattern);
		everything3.getChildren().add(r);
		everything3.getChildren().addAll(camera, everything2);
		pane.getChildren().add(everything3);

	}
	
	public void animate() {
		// X-Axis Rotation Animation
        RotateTransition rtX = new RotateTransition(Duration.millis(2000), dieOne);
        rtX.setAxis(Rotate.X_AXIS);
        rtX.setCycleCount(1);
        rtX.setFromAngle(0);
        rtX.setToAngle(360);
        
        RotateTransition rtX2 = new RotateTransition(Duration.millis(2000), dieTwo);
        rtX2.setAxis(Rotate.X_AXIS);
        rtX2.setCycleCount(1);
        rtX2.setFromAngle(0);
        rtX2.setToAngle(360);

        // Y-Axis Rotation Animation
        RotateTransition rtY = new RotateTransition(Duration.millis(2000), medium);
        rtY.setAxis(Rotate.Y_AXIS);
        rtY.setCycleCount(1);
        rtY.setFromAngle(0);
        rtY.setToAngle(350);
        
        RotateTransition rtY2 = new RotateTransition(Duration.millis(1000), medium2);
        rtY2.setAxis(Rotate.Y_AXIS);
        rtY2.setCycleCount(2);
        rtY2.setFromAngle(0);
        rtY2.setToAngle(350);
        
        // Movement Animation
        TranslateTransition trZ = new TranslateTransition(Duration.millis(2000), dieOne);
        trZ.setFromZ(0);
        trZ.setToZ(-100);
        trZ.setFromX(0);
        trZ.setFromY(0);
        trZ.setToY(width/3);
        trZ.setToX(width/2);
        trZ.setCycleCount(1);
        
        TranslateTransition trZ2 = new TranslateTransition(Duration.millis(2000), dieTwo);
        trZ2.setFromZ(100);
        trZ2.setToZ(-100);
        trZ2.setFromX(0);
        trZ2.setToX(250);
        trZ2.setFromY(0);
        trZ2.setToY(width/2);
        trZ2.setCycleCount(1);
        
        // Scale Animation
        ScaleTransition sc = new ScaleTransition(Duration.millis(1500),everything);
        sc.setFromX(width/200);
        sc.setToX(width/480);
        sc.setFromY(width/200);
        sc.setToY(width/480);
        sc.setFromZ(0);
        sc.setToZ(-width/150);
        sc.setCycleCount(1);
       
        pt = new ParallelTransition(rtX, rtX2, rtY, rtY2, trZ, trZ2, sc);
	}
	
	public void roll() {
		pt.setCycleCount(1);
		pt.play();
		pt.setOnFinished(e->{
		});
	}
	
	
	
	
	
	
	
}
