package com.strategies;

import com.simulation.Move;

public class SoftGrudgerStrategy implements IStrategy {

	private boolean defecting = false;
	private int defCount = 0;
	@Override
	public Move getMove() {
		Move response = null;
		if(defecting){
			if(defCount<=3) response = Move.DEFECT;
			else if(defCount<=4) response = Move.COOPERATE;
			else{
				defCount = -1;
				defecting = false;
				response = Move.COOPERATE;
			}
		}else{
			return Move.COOPERATE;
		}
		defCount++;
		return response;
	}

	@Override
	public void addOpMove(Move move) {
		if(move == Move.DEFECT) defecting=true;
	}

	@Override
	public String getName() {
		return "Soft Grudger";
	}

}
