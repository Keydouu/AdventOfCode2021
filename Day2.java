package AdventOfCode2021;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Day2{
	public static void main(String[] args) {
		File fp = new File("/home/keydou/AdventOfCode2021/Day2_input.txt");
		try {
			Scanner fileScanner = new Scanner(fp);
			Submarine carUnderTheWater = new Submarine();
			String instruction;
			while(fileScanner.hasNext()){
				instruction=fileScanner.next();
				switch(instruction){
					case "forward":
						carUnderTheWater.forward(fileScanner.nextInt());
						break;
					case "backward":
						carUnderTheWater.backward(fileScanner.nextInt());
						break;
					case "up":
						carUnderTheWater.up(fileScanner.nextInt());
						break;
					case "down":
						carUnderTheWater.down(fileScanner.nextInt());
						break;
				}
			}
			fileScanner.close();
			System.out.println("Final depth : "+carUnderTheWater.getDepth());
			System.out.println("Final horizontal position : "+carUnderTheWater.getHorizontalPos());
			System.out.println("Multiplication : "+(carUnderTheWater.getDepth()*carUnderTheWater.getHorizontalPos()));
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.print(e.getMessage());
		}
	}
}
class Submarine{
	int depth=0, horizontalPosition=0;
	public void up(int amount){
		this.depth-=amount;
	}
	public void down(int amount){
		this.depth+=amount;
	}
	public void forward(int amount){
		this.horizontalPosition+=amount;
	}
	public void backward(int amount){
		this.horizontalPosition-=amount;
	}
	public int getDepth(){
		return this.depth;
	}
	public int getHorizontalPos(){
		return this.horizontalPosition;
	}
}