package com.strategies;

import java.util.LinkedList;

import com.simulation.Move;

public interface IStrategy {
	public Move getMove(LinkedList<Move> history);
}
