package com.jason;

import java.util.ArrayList;

public class Player {
	
	private String color;
	private final String colors[] = {"Red", "Green", "Blue", "Yellow"};
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
	
	
	public Player() {
		
	}
	
	public Player(String name) {
		this.name = name;
		this.color = colors[num];
		num++;
	}
	
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
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
	
	public int[] roll() {
		return dice.roll();
	}

}
