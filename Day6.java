package AdventOfCode2021;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Day6{
	public static void main(String[] args) {
		File fp = new File("/home/keydou/AdventOfCode2021/Day6_input.txt");
		ArrayList<Integer> lanternFishArmy = new ArrayList<Integer>();
		String firstLanternFishArmy;
		try {
			Scanner fileScanner = new Scanner(fp);
			firstLanternFishArmy=fileScanner.nextLine();
			fileScanner.close();
			String[] strarray = firstLanternFishArmy.split(",");
			for(String elm: strarray)
				lanternFishArmy.add(Integer.parseInt(elm));
			for(int i=0;i<80;i++)
				addDay(lanternFishArmy);
			System.out.println("number of lanternfish : "+lanternFishArmy.size());
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.print(e.getMessage());
		}
	}
	static void addDay(ArrayList<Integer> lanternFishArmy){
		int newFish=0,j;
		for (int i=0;i<lanternFishArmy.size();i++) {
			if(lanternFishArmy.get(i)>0){
				j=lanternFishArmy.get(i)-1;
				lanternFishArmy.set(i,j);
			}
			else{
				newFish++;
				lanternFishArmy.set(i,6);
			}
		}
		for(int i=0;i<newFish;i++)
			lanternFishArmy.add(8);
	}
}