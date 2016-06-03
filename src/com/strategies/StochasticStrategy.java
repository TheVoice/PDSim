package com.strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.simulation.Move;

public class StochasticStrategy implements IStrategy{

	private static final int PROBING_CYCLES = 10;
	
	private IStrategy strategy = null;
	private int turns_to_probe = 0;
	private ArrayList<Move> myMoves = new ArrayList<Move>(10);
	private ArrayList<Move> opMoves = new ArrayList<Move>(10);
	private Map<String,Integer> evalMap = new HashMap<String,Integer>();
	
	@Override
	public Move getMove() {
		turns_to_probe++;
		if (strategy == null) {
			if (turns_to_probe <= PROBING_CYCLES / 2) {
				if (turns_to_probe % 2 == 0) {
					myMoves.add(turns_to_probe - 1, Move.COOPERATE);
					return Move.COOPERATE;
				} else {
					myMoves.add(turns_to_probe - 1, Move.DEFECT);
					return Move.DEFECT;
				}
			}else{
				Move myMove = (turns_to_probe<=(PROBING_CYCLES/4*3)) ? Move.COOPERATE : Move.DEFECT;
				myMoves.add(turns_to_probe - 1, myMove);
				return myMove;
			}
		}
		else return strategy.getMove();
	}

	@Override
	public void addOpMove(Move move) {
		if(turns_to_probe == PROBING_CYCLES){
			opMoves.add(turns_to_probe-1, move);
			analyzeStrategy();
			turns_to_probe = 0;
		}else{
			opMoves.add(turns_to_probe-1, move);
		}
	}

	private void analyzeStrategy(){
		
		evalMap.put("Grudger", 0);
		evalMap.put("TitForTat", 0);
		evalMap.put("Pavlov", 0);
		evalMap.put("SoftGrudger", 0);
		evalMap.put("SuspiciousTit, value)
		int grudger = 0;
		boolean grudger_trigger = false;
		int titfortat = 0;
		int gradual = 0;
		int pavlov = 0;
		
		for(int i=0;i<PROBING_CYCLES;i++){
			//grudger
			if(grudger_trigger){
				if(opMoves.get(i)==Move.DEFECT) grudger++;
				if(opMoves.get(i)==Move.DEFECT) evalMap.put("Grudger", value);
			}else{
				if(myMoves.get(i)==Move.DEFECT) grudger_trigger=true;
			}
			//titfortat
			if(i!=0){
				if(opMoves.get(i)==myMoves.get(i-1)) titfortat++;
			}
			//pavlov
			if(i!=0){
				if(opMoves.get(i-1)==opMoves.get(i) && myMoves.get(i-1)==Move.COOPERATE) pavlov++;
			}
		}
		if(grudger>titfortat){
			System.err.println("Grudger detected");
			strategy = new AlwaysDefectStrategy();
		}else{
			System.err.println("Titfortat detected");
			strategy = new AlwaysCooperateStrategy();
		}
	}
	
	private IStrategy getBestStrategy(){
		String topStrategy;
		int topScore = 0;
		for(Entry<String,Integer> p : evalMap.entrySet()){
			if(p.getValue()>topScore) topStrategy = p.getKey();
		}
		IStrategy proposedStrategy;
		switch(topStrategy){
		case("TitForTat"):
			proposedStrategy = new AlwaysCooperateStrategy();
		}
		return proposedStrategy;
	}
}



//pomyœleæ nad analiz¹ klas strategii -> tworzenie optymalnej strategii jako odpowiedŸ na nieznane jeszcze strategie
//alternatywnie dokoñczyæ rozpoznawanie deterministyczne