package com.strategies;

import java.util.LinkedList;

import com.simulation.Move;

public class RandomStrategy implements IStrategy {

	@Override
	public Move getMove(LinkedList<Move> history) {
		if(Math.random()<0.5) return Move.COOPERATE;
		else return Move.DEFECT;
	}

}
