package com.strategies;

import com.simulation.Move;

public class GradualStrategy implements IStrategy {
	
	private int defectCount = 0;
	private int defecting = 0;
	private int cooperating = 0;

	@Override
	public Move getMove() {
		Move response = null;
		if(defectCount==0) response = Move.COOPERATE;
		else if(defecting!=0){
			response = Move.DEFECT;
			defecting--;
		}
		else if(cooperating<1){
			response = Move.COOPERATE;
			cooperating++;
		}else{
			defecting = defectCount;
			cooperating = 0;
			response = Move.COOPERATE;
		}
		return response;
	}

	@Override
	public void addOpMove(Move move) {
		if(move == Move.DEFECT) defectCount++;
	}

	@Override
	public String getName() {
		return "Gradual";
	}

}
