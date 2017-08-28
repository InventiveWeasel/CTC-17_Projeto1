package tictactoe;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	private int HUMAN = 1;
	private int CPU = 2;
	private int DRAW = 0;
	private int ROWS = 3;
	private int COLS = 3;
	private String HUMAN_SGN = "X";
	private String CPU_SGN = "O";
	private String EMPTY_SGN = " ";
	
	private int firstPlayer, winner = 0;
	private String[][] table;
	Scanner sc;
	int hx, hy, cx, cy;
	private String playerSym = "X", cpuSym = "O";
	
	public Game(int first){
		table = new String[ROWS][COLS];
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				table[i][j] = " ";
			}
		}
		
		sc = new Scanner(System.in);
	}
	
	public void start(){
		boolean finish = false;
		int round = 9;
		whoStarts();
		printTable();
		while(!finish && round > 0){
			if(firstPlayer == HUMAN){
				getPlayerComm();
				round--;
				finish = hasWon(HUMAN);
				if(!finish && round > 0){
					getCPUComm();
					round--;
					finish = hasWon(CPU);
				}
				printTable();
				
			}
			else{
				getCPUComm();
				printTable();
				round--;
				finish = hasWon(CPU);
				if(!finish && round > 0){
					getPlayerComm();
					round--;
					finish = hasWon(HUMAN);
				}
			}
		}
		endGame(winner);
	}
	
	private void printTable(){
		System.out.println("     1     2     3   ");
		System.out.println("  ===================");
		System.out.println("1 =  "+table[0][0]+"  =  "+table[0][1]+"  =  "+table[0][2]+"  =");
		System.out.println("  ===================");
		System.out.println("2 =  "+table[1][0]+"  =  "+table[1][1]+"  =  "+table[1][2]+"  =");
		System.out.println("  ===================");
		System.out.println("3 =  "+table[2][0]+"  =  "+table[2][1]+"  =  "+table[2][2]+"  =");
		System.out.println("  ===================");
	}
	
	private void getPlayerComm(){
		ArrayList<int[]> moves = getAvailableMoves();
		boolean firstTry = true;
		do{
			if(!firstTry){
				System.out.println("Celula ja preenchida");
			}
			System.out.println("Linha: ");
			hx = sc.nextInt()-1;
			System.out.println("Coluna: ");
			hy = sc.nextInt()-1;
			firstTry = false;
		} while(!table[hx][hy].equals(" "));
		table[hx][hy]="X";
	}
	
	public void whoStarts(){
		System.out.println("Primeiro a jogar (1:Humano / 2:CPU): ");
		firstPlayer = sc.nextInt();
		System.out.println(firstPlayer);
		while(firstPlayer != HUMAN && firstPlayer != CPU){
			System.out.println("Por favor digite 1 ou 2.");
			System.out.println("Primeiro a jogar (1:Humano / 2:CPU): ");
			firstPlayer = sc.nextInt();
		} 
		
	}
	
	private void getCPUComm(){
		//ArrayList<int[]> moves = getAvailableMoves();
		//int randomMove = ThreadLocalRandom.current().nextInt(0,moves.size());
		int[] moves = minimax(2,CPU);
		table[moves[1]][moves[2]] = "O";
		
	}
	
	private ArrayList<int[]> getAvailableMoves(){
		ArrayList<int[]> moves = new ArrayList<int[]>();
		
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				if(table[i][j].equals(" ")){
					moves.add(new int[] {i,j});
				}
			}
		}
		
		return moves;
	}
	
	private boolean hasWon(int player){
		String sign;
		boolean haswon = false;
		if(player == HUMAN)
			sign = HUMAN_SGN;
		else
			sign = CPU_SGN;
		
		//Verificando configuracoes ganhadoras
		//Primeira linha
		if(table[0][0].equals(sign) && table[0][1].equals(sign) && table[0][2].equals(sign))
			haswon = true;
		//Segunda Linha
		else if(table[1][0].equals(sign) && table[1][1].equals(sign) && table[1][2].equals(sign))
			haswon = true;
		//Terceira Linha
		else if(table[2][0].equals(sign) && table[2][1].equals(sign) && table[2][2].equals(sign))
			haswon = true;
		//Primeira coluna
		else if(table[0][0].equals(sign) && table[1][0].equals(sign) && table[2][0].equals(sign))
			haswon = true;
		//Segunda coluna
		else if(table[0][1].equals(sign) && table[1][1].equals(sign) && table[2][1].equals(sign))
			haswon = true;
		//Segunda coluna
		else if(table[0][2].equals(sign) && table[1][2].equals(sign) && table[2][2].equals(sign))
			haswon = true;
		//Diagonal Principal
		else if(table[0][0].equals(sign) && table[1][1].equals(sign) && table[2][2].equals(sign))
			haswon = true;
		//Diagonal Secundaria
		else if(table[0][2].equals(sign) && table[1][1].equals(sign) && table[2][0].equals(sign))
			haswon = true;
		if(haswon)
			winner = player;
		return haswon;
	}
	
	private void endGame(int winner){
		if(winner == DRAW)
			System.out.println("Jogo EMPATADO!");
		if(winner == HUMAN)
			System.out.println("Parabéns! Voce venceu!!");
		if(winner == CPU)
			System.out.println("Que pena... Voce perdeu :(");
	}
	
	private int[] minimax(int depth, int player){
		ArrayList<int[]> moves = getAvailableMoves();
		
		int bestScore = (player == HUMAN) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		int bestRow = -1, bestCol = -1;
		
		if(moves.isEmpty() || depth == 0){
			//Fim de jogo
			bestScore = avaliar();
		}
		else{
			for(int[] move : moves){
				if(player == HUMAN){
					table[move[0]][move[1]] = HUMAN_SGN;
					currentScore = minimax(depth-1,CPU)[0];
					if(currentScore > bestScore){
						bestScore = currentScore;
						bestRow = move[0];
						bestCol = move[1];
					}
				}else{
					table[move[0]][move[1]] = CPU_SGN;
					currentScore = minimax(depth-1,HUMAN)[0];
					if(currentScore<bestScore){
						bestScore = currentScore;
						bestRow = move[0];
						bestCol = move[1];
					}
				}
				//Desfazer movimento
				table[move[0]][move[1]] = EMPTY_SGN;
			}
		}
		return new int[] {bestScore, bestRow,bestCol};
	}
	
	//Definicao da heuristica utilizada
	private int avaliar(){
		int score = 0;
	      // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
	      score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
	      score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
	      score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
	      score += evaluateLine(0, 0, 1, 0, 2, 0);  // col 0
	      score += evaluateLine(0, 1, 1, 1, 2, 1);  // col 1
	      score += evaluateLine(0, 2, 1, 2, 2, 2);  // col 2
	      score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal
	      score += evaluateLine(0, 2, 1, 1, 2, 0);  // alternate diagonal
	      return score;
	}
	
	private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
	      int score = 0;
	      
	      //Primeira celula
	      if(table[row1][col1] == HUMAN_SGN){
	    	  score = 1;
	      }else if(table[row1][col1] == CPU_SGN){
	    	  score = -1;
	      }
	      
	      //Segunda celula
	      if(table[row2][col2] == HUMAN_SGN){
	    	  if(score==1){
	    		  score = 10;
	    	  } else if(score == -1){
	    		  return 0;
	    	  } else{ //Celula 1 vazia
	    		  score = 1;
	    	  }
	    	  
	      } else if(table[row2][col2] == CPU_SGN){
	    	  if(score == 1){
	    		  return 0;
	    	  } else if(score == -1){
	    		  score = -10;
	    	  } else {
	    		  score = -1;
	    	  }
	      }
	      
	      //Terceira celula
	      if(table[row3][col3] == HUMAN_SGN){
	    	  if(score > 0){
	    		  score = score*10;
	    	  } else if (score < 0){
	    		  return 0;
	    	  } else { //Ambas as celulas anteriores estao vazias
	    		  score = 1;
	    	  }
	      } else  if(table[row3][col3] == CPU_SGN){
	    	  if(score < 10){
	    		  score = score*10;
	    	  } else if(score > 1){
	    		  return 0;
	    	  } else{
	    		  score = -1;
	    	  }
	      }
	      
	      return score;
	}
}
