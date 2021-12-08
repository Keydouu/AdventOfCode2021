package AdventOfCode2021;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Day8{
	public static void main(String[] args) {
		File fp = new File("/home/keydou/AdventOfCode2021/Day8_input.txt");
		int ans=0;
		try {
			Scanner fileScanner = new Scanner(fp);
			String myBigPPString;
			int n=0;int j=0;
			while(fileScanner.hasNextLine()){
				myBigPPString=fileScanner.nextLine();
				String[] strarray = myBigPPString.split(" | ");
				ArrayList<String> inputList = new ArrayList<String>();
				ArrayList<String> outputList = new ArrayList<String>();
				for(String elm: strarray){
					if(j==1){//reading line output
						outputList.add(elm);
					}
					else if(elm.equals("|"))
						j++;
					else{//reading line input
						inputList.add(elm);
					}
				}
				j=0;
				ans+=sevenSegDisplayResult(inputList,outputList);
			}
			fileScanner.close();
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.print(e.getMessage());
		}
		System.out.println("the answers is : "+ans);
	}
	static int sevenSegDisplayResult(ArrayList<String> input,ArrayList<String> output){
		OneNumber[] digit=new OneNumber[10];//I ended up using only 4 of the 10 but fuck you i'm lazy to edit my code
		boolean found = false;
		int finalResult=0;
		digit[1]=new OneNumber(findByNbrOfSegments(input,output,2),1);
		digit[4]=new OneNumber(findByNbrOfSegments(input,output,4),4);
		digit[7]=new OneNumber(findByNbrOfSegments(input,output,3),7);
		digit[8]=new OneNumber(findByNbrOfSegments(input,output,7),8);
		for(int i=0;(i<output.size());i++){
			switch(output.get(i).length()){
				case 2: finalResult=finalResult*10+1;System.out.print("1");break;
				case 3: finalResult=finalResult*10+7;System.out.print("7");break;
				case 4: finalResult=finalResult*10+4;System.out.print("4");break;
				case 7: finalResult=finalResult*10+8;System.out.print("8");break;
				case 5:{
					if(digit[1].isContained(output.get(i))){
						System.out.print("3");
						finalResult=finalResult*10+3;
					}
					else if(digit[4].numberInCommon(output.get(i))==3){
						System.out.print("5");
						finalResult=finalResult*10+5;
					}
					else if(digit[4].numberInCommon(output.get(i))==2){
						System.out.print("2");
						finalResult=finalResult*10+2;
					}
					else
						System.out.println("ERROR CANNOT UNDERSTNAD "+output.get(i));
					break;
				}
				case 6:{
					if(digit[4].isContained(output.get(i))){
						System.out.print("9");
						finalResult=finalResult*10+9;
					}
					else if(digit[1].numberInCommon(output.get(i))==1){
						System.out.print("6");
						finalResult=finalResult*10+6;
					}
					else if(digit[4].numberInCommon(output.get(i))==3){
						System.out.print("0");
						finalResult=finalResult*10;
					}
					else
						System.out.println("ERROR CANNOT UNDERSTNAD "+output.get(i));
					break;
				}
				default:
					System.out.println("\nERROR INPUT = "+output.get(i));
			}
		}
		System.out.println("");
		return finalResult;
		/*
		069 six letters
		9 = contain 4
		6 = one in common with 1
		0 = three in common with 4

		235 five letters
		3 = contain 1
		5 = 3 in common with 4
		2 = 2 in commin with 4
		*/
	}
	static String findByNbrOfSegments(ArrayList<String> input,ArrayList<String> output,int nbrOfSegs){
		boolean notFound = true;
		String answer="";
		for(int i=0;(i<input.size())&&notFound;i++){
			if(input.get(i).length()==nbrOfSegs){
				answer=input.get(i);
				notFound=false;
			}
		}
		for(int i=0;(i<output.size())&&notFound;i++){
			if(output.get(i).length()==nbrOfSegs){
				answer=output.get(i);
				notFound=false;
			}
		}
		return answer;
	}
}
class OneNumber{//sorry that my ability to name variables fucking suck
	private int value;
	private String letters;
	OneNumber(){
		value=-1;
		letters="";
	}
	OneNumber(String s, int v){
		this.value=v;
		this.letters=s;
	}
	public boolean isContained(String s){//does the string contain the letters in this number ? Y/N
		boolean b=true;
		for (int j=0;(j<this.letters.length())&&b;j++){
			b=false;
			for (int i=0;(i<s.length())&&!b;i++){
				if(this.letters.charAt(j)==s.charAt(i))
					b=true;
			}
		}
		return b;
	}
	public int numberInCommon(String s){
		int ans=0;
		for (int i=0;(i<s.length());i++){
			for (int j=0;(j<this.letters.length());j++){
				if(this.letters.charAt(j)==s.charAt(i))
					ans++;
			}
		}
		return ans;
	}
}