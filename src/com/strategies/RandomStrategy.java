package com.strategies;


import com.simulation.Move;

/*
 * A basic strategy: each move is random
 */
public class RandomStrategy implements IStrategy {

	@Override
	public Move getMove() {
		if(Math.random()<0.5) return Move.COOPERATE;
		else return Move.DEFECT;
	}


	@Override
	public void addOpMove(Move move) {

	}

}
