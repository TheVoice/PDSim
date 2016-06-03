package com.strategies;

import com.simulation.Move;

public class AlwaysDefectStrategy implements IStrategy {

	@Override
	public Move getMove() {
		return Move.DEFECT;
	}

	@Override
	public void addOpMove(Move move) {
		
	}

}
