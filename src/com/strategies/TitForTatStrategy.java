package com.strategies;

import java.util.LinkedList;

import com.simulation.Move;

/*
 * Cooperate on first turn, then mirror opponents last move
 */
public class TitForTatStrategy implements IStrategy {

	private Move lastOpMove = null;
	@Override
	public Move getMove() {
		if(lastOpMove==null) return Move.COOPERATE;
		else return lastOpMove;
	}
	@Override
	public void addOpMove(Move move) {
		lastOpMove = move;
	}

}
