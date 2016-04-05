package com.simulation;

import java.util.LinkedList;

import com.strategies.IStrategy;

public class Prisoner {
	private String name;
	private IStrategy strategy;
	private LinkedList<Move> moves; //opponent's moves history
	private int score;
	
	public Prisoner(String name,IStrategy strategy){
		this.name=name;
		this.strategy=strategy;
		this.moves = new LinkedList<Move>();
		this.score = 0;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public Move getMove(){
		return strategy.getMove(moves);
	}
	
	public void alterScore(int delta){
		this.score+=delta;
	}
	
	public void addMove(Move move){
		this.moves.add(move);
	}
	
	public String getName(){
		return this.name;
	}
}
