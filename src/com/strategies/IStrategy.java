package com.strategies;


import com.simulation.Move;

public interface IStrategy {
	public String getName();
	
	public Move getMove();
	
	public void addOpMove(Move move);
}
