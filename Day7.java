package AdventOfCode2021;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.lang.Math;
/*
tried to use some big brain math to give my poor
compter less calculations, probably not very usefull
but i believe better than a straight brute force
*/
public class Day7{
	public static void main(String[] args) {
		File fp = new File("/home/keydou/AdventOfCode2021/Day7_input.txt");
		ArrayList<Integer> inputList = new ArrayList<Integer>();
		try {
			Scanner fileScanner = new Scanner(fp);
			String s;
			s=fileScanner.nextLine();
			String[] strarray = s.split(",");
			for(String elm: strarray){
				inputList.add(Integer.parseInt(elm));
			}
			fileScanner.close();
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.print(e.getMessage());
		}
		int v=getLowest(inputList)+calculateVarience(inputList);
		int finalCost=fuelCost(inputList,v),y;
		y=v;
		while(finalCost>=fuelCost(inputList,v+1)){
			v++;
			finalCost=fuelCost(inputList,v);
		}
		v=y;
		while(finalCost>=fuelCost(inputList,v-1)){
			v--;
			finalCost=fuelCost(inputList,v);
		}
		System.out.println("final result :"+finalCost);
	}
	static int bigMinus(int diff){
		int a=0;
		for (int i=1;i<=diff;i++) {
			a+=i;			
		}
		return a;
	}
	static int fuelCost(ArrayList<Integer> myList,int convergencePt){
		int a=0;
		for(int i=0;i<myList.size();i++){
			if(convergencePt>myList.get(i))
				a+=bigMinus(convergencePt-myList.get(i));
			else
				a+=bigMinus(myList.get(i)-convergencePt);
		}
		return a;
	}
	static int calculateVarience(ArrayList<Integer> myList){
		int a=0,m=getM(myList);
		for(int i=0;i<myList.size();i++){
			a+=((myList.get(i)-m)*(myList.get(i)-m));
		}
		double v;
		v=Math.sqrt(a/(myList.size()-1));
		a=(int) Math.round(v)+1;
		//System.out.println("varience "+a);
		return a;
	}
	static int getLowest(ArrayList<Integer> myList){
		int smolPP=9999;
		for(int i=1;i<myList.size();i++){
			if(myList.get(i)<smolPP)
				smolPP=myList.get(i);
		}
		//System.out.println("lowest "+smolPP);
		return smolPP;
	}
	static int getM(ArrayList<Integer> myList){
		int average=0;
		for(int i=0;i<myList.size();i++){
			average+=myList.get(i);
		}
		if(myList.size()>0)
			average=(int) Math.round(average/myList.size());
		//System.out.println("average "+average);
		return average;
	}
}