package tictactoe;

import java.util.Scanner;

public class Game {
	private int firstPlayer;
	private String[][] table;
	Scanner sc;
	int hx, hy, cx, cy;
	
	public Game(int first){
		firstPlayer = first;
		table = new String[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				table[i][j] = " ";
			}
		}
		
		sc = new Scanner(System.in);
	}
	
	public void start(){
		boolean finish = false;
		printTable();
		while(!finish){
			getPlayerComm();
			printTable();
		}
	}
	
	private void printTable(){
		System.out.println("     1     2     3   ");
		System.out.println("  ===================");
		System.out.println("1 =  "+table[0][0]+"  =  "+table[1][0]+"  =  "+table[2][0]+"  =");
		System.out.println("  ===================");
		System.out.println("2 =  "+table[1][0]+"  =  "+table[1][1]+"  =  "+table[1][2]+"  =");
		System.out.println("  ===================");
		System.out.println("3 =  "+table[2][0]+"  =  "+table[2][1]+"  =  "+table[2][2]+"  =");
		System.out.println("  ===================");
	}
	
	private void getPlayerComm(){
		System.out.println("Linha: ");
		hx = sc.nextInt()-1;
		System.out.println("Coluna: ");
		hy = sc.nextInt()-1;
		table[hx][hy]="X";
	}
}
