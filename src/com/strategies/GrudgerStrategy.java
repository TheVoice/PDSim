package com.strategies;


import com.simulation.Move;

/*
 * Cooperates until opponent defects,
 * then always defects
 */
public class GrudgerStrategy implements IStrategy {

	private boolean grudge = false;
	
	@Override
	public Move getMove() {
		if(grudge) return Move.DEFECT;
		else return Move.COOPERATE;
	}
	
	@Override
	public void addOpMove(Move move) {
		if(move == Move.DEFECT) grudge = true;
	}

	@Override
	public String getName() {
		return "Grudger";
	}

}
