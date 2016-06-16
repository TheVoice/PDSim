package com.strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.simulation.Move;

public class StochasticStrategy implements IStrategy{

	private static final int PROBING_CYCLES = 20;
	
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
		else{
			Move myMove = strategy.getMove();
			myMoves.add(turns_to_probe-1,myMove);
			return strategy.getMove();
		}
	}

	@Override
	public void addOpMove(Move move) {
		if (turns_to_probe == PROBING_CYCLES) {
			opMoves.add(turns_to_probe - 1, move);
			analyzeStrategy();
			turns_to_probe = 0;
		} else {
			opMoves.add(turns_to_probe - 1, move);
		}

		if (strategy != null)
			strategy.addOpMove(move);
	}

	private void analyzeStrategy(){
		
		evalMap.put("SoftGrudger", 0);
		evalMap.put("Grudger", 0);
		evalMap.put("TitForTat", 0);
		evalMap.put("Pavlov", 0);
		evalMap.put("SuspiciousTit", 0);
		evalMap.put("TitForTwoTats",0);

		boolean grudger_trigger = false;

		if(opMoves.get(0)==Move.COOPERATE) evalMap.put("TitForTat", evalMap.get("TitForTat")+1);
		if(opMoves.get(0)==Move.DEFECT) evalMap.put("SuspiciousTit", evalMap.get("SuspiciousTit")+1);
		
		for(int i=0;i<PROBING_CYCLES;i++){
			//grudger
			if(grudger_trigger){
				if(opMoves.get(i)==Move.DEFECT) evalMap.put("Grudger", evalMap.get("Grudger")+1);
			}else{
				if(myMoves.get(i)==Move.DEFECT) grudger_trigger=true;
			}
			//titfortat
			if(i!=0){
				if(opMoves.get(i)==myMoves.get(i-1)) evalMap.put("TitForTat", evalMap.get("TitForTat")+1);
			}
			//pavlov
			if(i!=0){
				if(opMoves.get(i-1)==opMoves.get(i) && myMoves.get(i-1)==Move.COOPERATE) evalMap.put("Pavlov", evalMap.get("Pavlov")+1);
			}
			//Suspicious
			if(i!=0){
				if(opMoves.get(i)==myMoves.get(i-1)) evalMap.put("SuspiciousTit", evalMap.get("SuspiciousTit")+1);
			}
			//Soft grudger
			if(i>5){
				if(myMoves.get(i-6)==Move.DEFECT){
					if(opMoves.get(i)==Move.COOPERATE && opMoves.get(i-1)==Move.COOPERATE && opMoves.get(i-2)==Move.DEFECT && opMoves.get(i-3)==Move.DEFECT && opMoves.get(i-4)==Move.DEFECT && opMoves.get(i-5)==Move.DEFECT) evalMap.put("SoftGrudger", evalMap.get("SoftGrudger")+6);
				}
			}
			//TitForTwoTats
			if(i>1){
				if(opMoves.get(i)==myMoves.get(i-1) && myMoves.get(i-1)==myMoves.get(i-2)) evalMap.put("TitForTwoTats", evalMap.get("TitForTwoTats")+2);
			}
		}
		strategy = getBestStrategy();
	}
	
	private IStrategy getBestStrategy(){
		String topStrategy = null;
		int topScore = 0;
		for(Entry<String,Integer> p : evalMap.entrySet()){
			if(p.getValue()>topScore){
				topStrategy = p.getKey();
				topScore = p.getValue();
			}
			System.out.println(p.getKey()+"-"+p.getValue());
		}
		IStrategy proposedStrategy = null;
		switch(topStrategy){
			case "Pavlov":
				System.err.println("Pavlov detected");
				proposedStrategy = new TitForTatStrategy();
				break;
			case "TitForTat":
				System.err.println("Titfortat detected");
				proposedStrategy = new AlwaysCooperateStrategy();
				break;
			case "Grudger":
				System.err.println("Grudger detected");
				proposedStrategy = new AlwaysDefectStrategy();
				break;
			case "SuspiciousTit":
				System.err.println("Suspicious TitForTat detected");
				proposedStrategy = new AlwaysDefectStrategy();
				break;
			case "SoftGrudger":
				System.err.println("SoftGrudger detected");
				proposedStrategy = new TitForTatStrategy();
				break;
			case "TitForTwoTats":
				System.err.println("Titfor2tats detected");
				proposedStrategy = new TitForTwoTatsStrategy();
				break;
			default:
				System.err.println("No strategy detected?");
				proposedStrategy = new AlwaysDefectStrategy();
				break;
		}
		
		return proposedStrategy;
	}

	@Override
	public String getName() {
		return "Stochastic";
	}
}