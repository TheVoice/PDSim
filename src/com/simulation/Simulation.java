package com.simulation;

import com.strategies.RandomStrategy;

public class Simulation {

	public static void main(String[] args) {
		Prisoner p1 = new Prisoner("Alice",new RandomStrategy());
		Prisoner p2 = new Prisoner("Bob",new RandomStrategy());
		
		int threshold = 10;
		//int max_number_of_turns=20;
		int turns_count = 0;
		
		do{
			turns_count++;
			simulateTurn(p1,p2);
			System.out.println(p1.getName()+": "+p1.getScore()+" | "+p2.getName()+": "+p2.getScore());
			System.out.println("_____________________");
		}while(p1.getScore()<threshold && p2.getScore()<threshold);
		
	}

	private static void simulateTurn(Prisoner p1, Prisoner p2) {
		Move m1 = p1.getMove();
		Move m2 = p2.getMove();
		//COOPERATE vs COOPERATE: both players get +1
		//DEFECT vs COOPERATE: defecting player gets +2
		//DEFECT vs DEFECT: players get no points
		if(m1==Move.COOPERATE){
			if(m2==Move.COOPERATE){
				p1.alterScore(1);
				p2.alterScore(1);
				
			}else{
				p2.alterScore(2);
			}
		}else{
			if(m2==Move.COOPERATE){
				p1.alterScore(2);
			}
		}
		System.out.println(m1+" vs "+m2);
		p1.addMove(m2);
		p2.addMove(m1);
	}

}
