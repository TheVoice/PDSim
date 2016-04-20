package com.simulation;

public class Round {

	private Move myMove;
	private Move opMove;
	
	public Round(Move myMove,Move opMove){
		this.myMove=myMove;
		this.opMove=opMove;
	}
	
	public Move getMyMove(){
		return this.myMove;
	}
	
	public Move getOpMove(){
		return this.opMove;
	}
}
