package com.simulation;

import com.strategies.*;

public class Simulation {
	
	private static int sum_total = 0;

	public static void main(String[] args) {
		Prisoner p1 = new Prisoner("Alice",new StochasticStrategy());
		Prisoner p2 = new Prisoner("Bob",new GrudgerStrategy());
		
		int threshold = 50;
		int max_number_of_turns=100;
		int turns_count = 0;
		
		Prisoner winner = null;
		int winning_turn = 0;
		int winning_score = 0;
		int losing_score = 0;
		
		
		/*
		do{
			turns_count++;
			simulateTurn(p1,p2);
			System.out.println(p1.getName()+": "+p1.getScore()+" | "+p2.getName()+": "+p2.getScore());
			System.out.println("_____________________");
		}while(p1.getScore()<threshold && p2.getScore()<threshold);
		*/
		
		for(int i=0;i<max_number_of_turns;i++){
			simulateTurn(p1,p2);
			if(winner==null){
				if(p1.getScore()>=threshold){
					winner = p1;
					winning_turn = i+1;
					winning_score = p1.getScore();
					losing_score = p2.getScore();
				}
				if(p2.getScore()>=threshold){
					winner = p2;
					winning_turn = i+1;
					winning_score = p2.getScore();
					losing_score = p1.getScore();
				}
			}
			System.out.println(p1.getName()+": "+p1.getScore()+" | "+p2.getName()+": "+p2.getScore());
			System.out.println("_____________________");
		}
		
		System.out.println("--FINAL SCORE--");
		System.out.println(p1.getName()+"("+p1.getStrategy().getName()+") -> "+p1.getScore()+" vs "+p2.getScore()+" <- "+p2.getName()+"("+p2.getStrategy().getName()+")");
		double average = (double)sum_total/(double)max_number_of_turns;
		System.out.println("Average point delta = "+average);
		System.out.println("--WINNER--");
		if(winner!=null)
			System.out.println(winner.getName()+" in turn "+winning_turn+"("+winning_score+":"+losing_score+")");
		else
			System.out.println("NO WINNER");
	}

	private static void simulateTurn(Prisoner p1, Prisoner p2) {
		Move m1 = p1.getMove();
		Move m2 = p2.getMove();
		//COOPERATE vs COOPERATE: both players get +3
		//DEFECT vs COOPERATE: defecting player gets +5
		//DEFECT vs DEFECT: players get +1 each
		if(m1==Move.COOPERATE){
			if(m2==Move.COOPERATE){
				p1.alterScore(3);
				p2.alterScore(3);
				sum_total += 6;
				
			}else{
				p2.alterScore(5);
				sum_total += 5;
			}
		}else{
			if(m2==Move.COOPERATE){
				p1.alterScore(5);
				sum_total += 5;
			}else{
				p1.alterScore(1);
				p2.alterScore(1);
				sum_total += 2;
			}
		}
		System.out.println(m1+" vs "+m2);
		p1.addMove(m2);
		p2.addMove(m1);
	}

}
