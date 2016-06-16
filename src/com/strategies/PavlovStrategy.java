package com.strategies;

import com.simulation.Move;

public class PavlovStrategy implements IStrategy{

	private Move myLast = null;
	private Move opLast = null;
	
	@Override
	public Move getMove() {
		if(myLast == null){
			myLast = Move.COOPERATE;
			return Move.COOPERATE;
		}else{
			if(opLast == Move.COOPERATE) return myLast;
			else return reverseMove(myLast);
		}
	}

	@Override
	public void addOpMove(Move move) {
		opLast = move;
	}

	private Move reverseMove(Move move){
		if(move == Move.COOPERATE) return Move.DEFECT;
		else return Move.COOPERATE;
	}

	@Override
	public String getName() {
		return "Pavlov";
	}
}
