package com.simulation;


import com.strategies.IStrategy;

public class Prisoner {
	private String name;
	private IStrategy strategy;
	private int score;
	
	public Prisoner(String name,IStrategy strategy){
		this.name=name;
		this.strategy=strategy;
		this.score = 0;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public Move getMove(){
		return strategy.getMove();
	}
	
	public void alterScore(int delta){
		this.score+=delta;
	}
	
	public void addMove(Move move){
		this.strategy.addOpMove(move);
	}
	
	public String getName(){
		return this.name;
	}
}
