package com.jason;


public class Dice {

	public Dice() {

	}
	
	public int[] roll() {
		int diceRoll[] = {(int)(Math.random()*6) + 1, (int)(Math.random()*6)+1};
		return diceRoll;
	}

}
