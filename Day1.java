package AdventOfCode2021;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Day1{
	public static void main(String[] args) {
		File fp = new File("/home/keydou/AdventOfCode2021/Day1_input.txt");
		try {
			Scanner fileScanner = new Scanner(fp);
			Integer newDepth,lastDepth=null,numberOfIcreases=0;
			while(fileScanner.hasNextInt()){
				newDepth=fileScanner.nextInt();
				System.out.print(newDepth);
				if(lastDepth==null)
					System.out.println(" (N/A - no previous measurement)");
				else if(lastDepth<newDepth){
					System.out.println(" (increased)");
					numberOfIcreases++;
				}
				else if(lastDepth>newDepth)
					System.out.println(" (decreased)");
				else
					System.out.println(" constant");
				lastDepth=newDepth;
			}
			fileScanner.close();
			System.out.println("Depth increased "+numberOfIcreases+" times.");
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.print(e.getMessage());
		}
	}
}