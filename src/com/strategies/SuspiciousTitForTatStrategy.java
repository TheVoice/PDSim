package com.strategies;

import com.simulation.Move;

public class SuspiciousTitForTatStrategy implements IStrategy {

	private Move lastOpMove = null;
	@Override
	public Move getMove() {
		if(lastOpMove==null) return Move.DEFECT;
		else return lastOpMove;
	}
	@Override
	public void addOpMove(Move move) {
		lastOpMove = move;
	}
	@Override
	public String getName() {
		return "Suspicious T4T";
	}

}
