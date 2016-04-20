package com.strategies;

import java.util.ArrayList;

import com.simulation.Move;

public class AdaptiveStrategy implements IStrategy {

	private int coopSum = 0;
	private int defSum = 0;
	private int turns = 0;
	private ArrayList<Move> myMoves = new ArrayList<Move>(){{
		add(Move.COOPERATE);
		add(Move.COOPERATE);
		add(Move.COOPERATE);
		add(Move.COOPERATE);
		add(Move.COOPERATE);
		add(Move.DEFECT);
		add(Move.DEFECT);
		add(Move.DEFECT);
		add(Move.DEFECT);
		add(Move.DEFECT);
	}};
	
	@Override
	public Move getMove() {
		if(turns < 10){
			return myMoves.get(turns);
		}else{
			if(coopSum>=defSum) return Move.COOPERATE;
			else return Move.DEFECT;
		}
	}

	@Override
	public void addOpMove(Move move) {
		if(turns < 10){
			if(myMoves.get(turns)==Move.COOPERATE && move==Move.COOPERATE) coopSum+=1;
			if(myMoves.get(turns)==Move.COOPERATE && move==Move.DEFECT) {};
			if(myMoves.get(turns)==Move.DEFECT && move==Move.COOPERATE) defSum+=2;
			if(myMoves.get(turns)==Move.DEFECT && move==Move.DEFECT) {};
			turns++;
		}
	}

}
