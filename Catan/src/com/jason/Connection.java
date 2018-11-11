package com.jason;

import java.util.ArrayList;

import com.jason.Board;
import com.jason.Board.Intersection;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Line;

public class Connection {
	
	private Intersection intersectionOne;
	private Intersection intersectionTwo;
	private ArrayList<Intersection> intersections = new ArrayList<>();
	private boolean hasIntersections = false;
	private static ArrayList<Connection> connections = new ArrayList<>();
	
	public Connection(Intersection interOne, Intersection interTwo) {
		intersections.add(interOne);
		intersections.add(interTwo);
		connections.add(this);
	}
	
	public static int getNumConnections() {
		return connections.size();
	}
	
	
	public void setIntersections(Intersection interOne, Intersection interTwo) {
		hasIntersections = true;
		intersections.add(interOne);
		intersections.add(interTwo);
	}
	
	public ArrayList<Intersection> getIntersections() {
		return intersections;
	}
	
	private boolean hasIntersectionOne(Intersection intersection) {
		System.out.println("Connection intersection One: " + this.intersectionOne);
		System.out.println("hasIntersectionOne intersection parameter: " + intersection);
		if(this.intersectionOne.equals(null)) {
			return false;
		} else if(this.intersectionOne.getIntersectionNumber() == intersection.getIntersectionNumber()) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean hasIntersectionTwo(Intersection intersection) {
		System.out.println("Connection intersection Two: " + this.intersectionOne);
		System.out.println("hasIntersectionTwo intersection parameter: " + intersection);
		if(this.intersectionOne.equals(null)) {
			return false;
		} else if(this.intersectionTwo.getIntersectionNumber() == intersection.getIntersectionNumber()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getIsConnection(Intersection interOne, Intersection interTwo) {
	
		for(Connection connection : connections) {
			if(connection.hasIntersectionOne(interOne) && connection.hasIntersectionTwo(interTwo)) {
				return true;
			}
		}
		return false;
	}

}
