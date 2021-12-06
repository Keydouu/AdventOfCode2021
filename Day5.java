package AdventOfCode2021;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Day5{
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> bigusMapus = new ArrayList<ArrayList<Integer>>();
		File fp = new File("/home/keydou/AdventOfCode2021/Day5_input.txt");
		int numberOfTwosPlus=0;
		try {
			Scanner fileScanner = new Scanner(fp);
			int x1,y1,x2,y2;
			String myBigPPString;
			while(fileScanner.hasNextLine()){
				myBigPPString=fileScanner.nextLine();
				String[] strarray1 = myBigPPString.split(" -> ");
				String[] strarray2 = strarray1[0].split(",");
				String[] strarray3 = strarray1[1].split(",");
				x1=Integer.parseInt(strarray2[0]);
				y1=Integer.parseInt(strarray2[1]);
				x2=Integer.parseInt(strarray3[0]);
				y2=Integer.parseInt(strarray3[1]);
				if(x1==x2){
					if(y1<y2)
						numberOfTwosPlus+=addHorizontalLine(bigusMapus,x1,y1,y2);
					else
						numberOfTwosPlus+=addHorizontalLine(bigusMapus,x1,y2,y1);
				}
				else if(y1==y2){
					if(x1<x2)
						numberOfTwosPlus+=addVerticalLine(bigusMapus,y1,x1,x2);
					else
						numberOfTwosPlus+=addVerticalLine(bigusMapus,y1,x2,x1);
				}
			}
			fileScanner.close();
			//writeMap(bigusMapus);
			System.out.println("number of dangerous cases : "+numberOfTwosPlus);
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.print(e.getMessage());
		}
	}
	static int addHorizontalLine(ArrayList<ArrayList<Integer>> bigusMapus,int verticalPos,int lineStart, int lineEnd){
		int a;
		int numberOfTwosPlus=0;
		if(bigusMapus.size()<=lineEnd)
			expandBigList(bigusMapus,lineEnd);
		if(bigusMapus.get(0).size()<=verticalPos)
			expandAllSmallerLists(bigusMapus,verticalPos);
		for (int i=lineStart;i<=lineEnd;i++){
			a=bigusMapus.get(i).get(verticalPos);
			a++;
			bigusMapus.get(i).set(verticalPos,a);
			if(a==2)
				numberOfTwosPlus++;
		}
		return numberOfTwosPlus;
	}
	static int addVerticalLine(ArrayList<ArrayList<Integer>> bigusMapus,int horizontalPos,int lineStart, int lineEnd){
		int a;
		int numberOfTwosPlus=0;
		if(bigusMapus.size()<=horizontalPos)
			expandBigList(bigusMapus,horizontalPos);
		if(bigusMapus.get(0).size()<=lineEnd)
			expandAllSmallerLists(bigusMapus,lineEnd);
		for (int i=lineStart;i<=lineEnd;i++){
			a=bigusMapus.get(horizontalPos).get(i);
			a++;
			bigusMapus.get(horizontalPos).set(i,a);
			if(a==2)
				numberOfTwosPlus++;
		}
		return numberOfTwosPlus;
	}
	static void expandBigList(ArrayList<ArrayList<Integer>> bigusMapus,int size){
		size++;
		while(bigusMapus.size()<size)
			bigusMapus.add(new ArrayList<Integer>());
		expandAllSmallerLists(bigusMapus, bigusMapus.get(0).size());
	}
	static void expandAllSmallerLists(ArrayList<ArrayList<Integer>> bigusMapus,int size){
		size++;
		for (int i=0;i<bigusMapus.size();i++){
			while(bigusMapus.get(i).size()<size)
				bigusMapus.get(i).add(0);
		}
	}
	static void writeMap(ArrayList<ArrayList<Integer>> bigusMapus){//you better not use this if the map is too big
		for (int i=0;i<bigusMapus.size();i++){
			for(int j=0;j<bigusMapus.get(i).size();j++) {
				System.out.print(bigusMapus.get(i).get(j));
			}
			System.out.println();
		}
	}
}