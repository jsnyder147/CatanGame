package com.jason;

import java.util.ArrayList;

import com.jason.Board;
import com.jason.Board.Intersection;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.control.Label;

public class Connection {
	
	private Intersection intersectionOne;
	private Intersection intersectionTwo;
	private ArrayList<Intersection> intersections = new ArrayList<>();
	private boolean hasIntersections = false;
	private static ArrayList<Connection> connections = new ArrayList<>();
	private ArrayList<Connection> relatedConnections = new ArrayList<>();
	private boolean hasRoad = false;
	private static int connectionNumbers = 1;
	private int connectionNumber = 0;
	private int related[][] = {{0,0},{2,3},{1,8,10},{1,4,5},{3,5,6},{3,4,17},{4,7,8,9},{4,6,20,22},};
	
	public static void setConnectionsList(ArrayList<Connection> connections) {
		Connection.connections = connections;
	}
	
	public static ArrayList<Connection> getConnectionsList() {
		return connections;
	}
	
	public String getRelated() {
		String connectionInfo = "Connection Number " + connectionNumber + "\nRelated Connections: ";
		for(Connection connection : relatedConnections) {
			connectionInfo += connection.connectionNumber + ", ";
		}
		return connectionInfo;
	}
	public Connection(Intersection interOne, Intersection interTwo) {
		intersections.add(interOne);
		intersections.add(interTwo);
		connectionNumber = connectionNumbers;
		connectionNumbers++;
	}
	
	public static int getNumConnections() {
		return connections.size();
	}
	
	public void setRelated() {
		for(int i = 0; i<related[connectionNumber].length; i++) {
			relatedConnections.add(connections.get(related[connectionNumber][i] -1));
		}
	}
	
	
	public void setIntersections(Intersection interOne, Intersection interTwo) {
		hasIntersections = true;
		intersections.add(interOne);
		intersections.add(interTwo);
	}
	
	public ArrayList<Intersection> getIntersections() {
		return intersections;
	}
	
	public double getX() {
		Intersection interOne = intersections.get(0);
		Intersection interTwo = intersections.get(1);
		return(((interOne.getX() + interTwo.getX())/2));
	}
	
	public double getY() {
		Intersection interOne = intersections.get(0);
		Intersection interTwo = intersections.get(1);
		return(((interOne.getY() + interTwo.getY())/2));
	}
	
	public Label displayLabel() {
		Intersection interOne = intersections.get(0);
		Intersection interTwo = intersections.get(1);
		double midPointX = (((interOne.getX() + interTwo.getX())/2));
		double midPointY = ((interOne.getY() + interTwo.getY())/2);
		System.out.println("Midpoint X: " + midPointX + " Y: " + midPointY);
		Label label = new Label(Integer.toString(connectionNumber));
		return label;
	}
	
	public Line displayConnection() {
		Intersection interOne = intersections.get(0);
		Intersection interTwo = intersections.get(1);
		double midPointX = (((interOne.getX() + interTwo.getX())/2));
		double midPointY = ((interOne.getY() + interTwo.getY())/2);
		Label label = new Label(Integer.toString(connectionNumber));
		label.setLayoutX(midPointX);
		label.setLayoutY(midPointY);
		Line line = new Line(interOne.getX(), interOne.getY(),
								interTwo.getX(), interTwo.getY());
		line.setStrokeWidth(10);
		line.setStroke(Color.BLACK);
		line.setOnMouseClicked(e ->{
			System.out.println("\nCONNECTION CLICKED\n");
			// If Connection doesn't have a road and one of the intersections has a settlement or city
			if(!hasRoad && (interOne.getHasSettlement() || interTwo.getHasSettlement() ||  checkOtherConnection())) {
				hasRoad = true;
				line.setStyle("-fx-stroke:#7E7EFF;");
			} else {
				System.out.println("Can't Build Here!");
			}
		});
		return line;
	}
	
	public boolean getHasRoad() {
		return hasRoad;
	}
	
	public void setHasRoad(boolean hasRoad) {
		this.hasRoad = hasRoad;
	}
	
	public static void trySetting() {
		System.out.println("\n");
		for(Connection connection : connections) {
			System.out.println("Connection " + connection.connectionNumber);
			for(Intersection intersection : connection.intersections) {
				System.out.println("Intersection: " + intersection.getIntersectionNumber());
				
				for(Connection intersectionsConnection: intersection.getRelatedConnections()) {
					System.out.println(intersectionsConnection.connectionNumber);
					if(intersectionsConnection.connectionNumber == connection.connectionNumber || connection.relatedConnections.contains(intersectionsConnection)) {
						// don't set
					} else {
						connection.relatedConnections.add(intersectionsConnection);
					}
				}
				
				
			}
			
		System.out.println(connection.getRelated());	
		}

	}
	
	private boolean checkOtherConnection() {

		for(Connection connection : relatedConnections) {
			
			if(connection.hasRoad) {
				return true;
			}
		}
		return false;
	}


}
