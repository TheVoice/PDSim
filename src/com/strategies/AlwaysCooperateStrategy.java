package com.strategies;

import com.simulation.Move;

public class AlwaysCooperateStrategy implements IStrategy {

	@Override
	public Move getMove() {
		return Move.COOPERATE;
	}

	@Override
	public void addOpMove(Move move) {
		
	}

	@Override
	public String getName() {
		return "Cooperative";
	}

}
