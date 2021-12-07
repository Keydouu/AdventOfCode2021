package AdventOfCode2021;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Day1{
	public static void main(String[] args){
		System.out.println("Part I");
		part_1("/home/keydou/AdventOfCode2021/Day1_input.txt");
		System.out.println("Part II");
		part_2("/home/keydou/AdventOfCode2021/Day1_input.txt");
	} 
	static void part_1(String fileName){
		File fp = new File(fileName);
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
	static void part_2(String fileName){
		File fp = new File(fileName);
		try {
			Scanner fileScanner = new Scanner(fp);
			Integer input,numberOfIcreases=0;
			ArrayList<ThreeDepths> depthlist= new ArrayList<ThreeDepths>();
			int i=0,j=0;
			while(fileScanner.hasNextInt()){
				input=fileScanner.nextInt();
				depthlist.add(new ThreeDepths(input));
				if(j<3){//012 12 2
					for(int k=0;k<j;k++)
						depthlist.get(k).setDepth(input);
					j++;
				}
				else{// 012 12 2 3
					i++;
					for(int k=i;k<(i+2);k++)// 012 113 23 3
						depthlist.get(k).setDepth(input);
					if(depthlist.get(i-1).getSum()<depthlist.get(i).getSum())
						numberOfIcreases++;
				}
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
class ThreeDepths{
	private Integer depth[]=new Integer[3];
	ThreeDepths(Integer a){
		depth[0]=a;
	}
	public void setDepth(Integer a){
		if(this.depth[1]==null)
			this.depth[1]=a;
		else if(this.depth[2]==null)
			this.depth[2]=a;
	}
	public Integer getSum(){
		Integer sum=null;
		sum=depth[0]+depth[1]+depth[2];
		return sum;
	}
}
