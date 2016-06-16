package com.strategies;

import com.simulation.Move;

public class TitForTwoTatsStrategy implements IStrategy {

	private Move lastOpMove = null;
	private Move response = null;
	
	@Override
	public Move getMove() {
		Move rsp = null;
		if(response==null) rsp = Move.COOPERATE;
		else{
			rsp = response;
			response = null;
		}
		return rsp;
	}
	
	@Override
	public void addOpMove(Move move) {
		if(move == lastOpMove) response = move;
		lastOpMove = move;
	}

	@Override
	public String getName() {
		return "T4twoT";
	}

}
