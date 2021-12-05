package AdventOfCode2021;
import java.awt.*;
import java.io.*;
import java.util.*;

import java.util.List;

public class Day4{
	public static void main(String[] args) {
		File fp = new File("/home/keydou/AdventOfCode2021/Day4_input.txt");
		List<BingoTable> bT = new ArrayList<BingoTable>();
		List<Integer> drawnNumbers = new ArrayList<Integer>();
		char c='a';
		int newNumber,i=0,numberOfTables=0,winnerScore=0;
		try {
			Scanner fileScanner = new Scanner(fp);
			String firstLine;
			firstLine=fileScanner.nextLine();
			String[] strarray = firstLine.split(",");
			for(String elm: strarray)
				drawnNumbers.add(Integer.parseInt(elm));
			while(fileScanner.hasNextInt()){//read bingo tables
				bT.add(new BingoTable());
				for (int j=0;j<25;j++) {
					newNumber=fileScanner.nextInt();
					bT.get(numberOfTables).addToTable(newNumber,j);
				}
				System.out.println();
				numberOfTables++;
			}
			fileScanner.close();
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.print(e.getMessage());
		}
		for (newNumber=0;(newNumber<drawnNumbers.size())&&(winnerScore==0);newNumber++){
			for (i=0;(i<numberOfTables)&&(winnerScore==0);i++){
				winnerScore=bT.get(i).addRevelation(drawnNumbers.get(newNumber));
			}
		}
		System.out.println("Board n°"+i+" will win first with a score of "+winnerScore);
	}
}
class BingoTable{
	private int[][] numbersTable = new int[5][5];
	private boolean[][] boolTable = new boolean[5][5];
	public void addToTable(int value,int position){
		this.numbersTable[position/5][position%5]=value;
		this.boolTable[position/5][position%5]=false;
	}
	public int addRevelation(int newNumber){
		int score =0;
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if(this.numbersTable[i][j]==newNumber){
					boolTable[i][j]=true;
					if (this.win(i,j)){
						for(i=0;i<5;i++){
							for (j=0;j<5;j++){
								if(!this.boolTable[i][j])
									score+=this.numbersTable[i][j];
							}
						}
						score*=newNumber;
					}
					i=5;j=5;
				}
			}
		}
		return score;
	}
	public boolean win(int y, int x){
		boolean verticalWin=true,horizontalWin=true;
		for(int i=0;(i<5)&&(verticalWin||horizontalWin);i++){

			if(!this.boolTable[y][i])
				horizontalWin=false;

			if(!this.boolTable[i][x])
				verticalWin=false;

		}
		if((verticalWin)||(horizontalWin)){
			return true;
		}
		else
			return false;
	}
}