package com.jason;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Player implements Comparable<Player>{
	
	private Color color;
	private final Color colors[] = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE};
	private static int num = 0;
	private String name;
	private int number;
	private ArrayList<ResourceCard> resourceCards;
	private ArrayList<DevelopmentCard> developmentCards;
	private int victoryPoints;
	private ArrayList<Settlement> settlements;
	private ArrayList<Road> roads;
	private ArrayList<City> cities;
	private Dice dice = new Dice();
	private int roll[] = {0,0};
	private int playerNum;
	
	
	public Player() {
		
	}
	
	public Player(String name) {
		this.name = name;
		this.color = colors[num];
		num++;
		playerNum = num;
	}
	
	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public ArrayList<ResourceCard> getResourceCards() {
		return resourceCards;
	}


	public void setResourceCard(ResourceCard resourceCard) {
		this.resourceCards.add(resourceCard);
	}


	public ArrayList<DevelopmentCard> getDevelopmentCards() {
		return developmentCards;
	}


	public void setDevelopmentCards(ArrayList<DevelopmentCard> developmentCards) {
		this.developmentCards = developmentCards;
	}


	public int getVictoryPoints() {
		return victoryPoints;
	}


	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}


	public ArrayList<Settlement> getSettlements() {
		return settlements;
	}


	public void setSettlements(ArrayList<Settlement> settlements) {
		this.settlements = settlements;
	}


	public ArrayList<Road> getRoads() {
		return roads;
	}


	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}


	public ArrayList<City> getCities() {
		return cities;
	}


	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}
	
	public void roll() {
		roll = dice.roll();
	}
	
	// TEST METHOD
	public void setRoll(int[] roll) {
		this.roll = roll;
	}
	
	public int[] getRoll() {
		return roll;
	}
	
	public int getRollSum() {
		return roll[0] + roll[1];
	}
	
	public int getPlayerNum() {
		return playerNum;
	}
	
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	@Override
	public int compareTo(Player o) {
		if(this.getRollSum() > o.getRollSum()) {
			return -1;
		} else {
			return 0;
		}

	}
	
	

}
