import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;


public class BoyerMooreHorspool{
	/*
	Class that extends the standard BoyerMooreHorspool algorithm to include wildcareds.

	Method: main, @param String[] args
		main method, called when the program runs. Inputs are 2 mandatory and 1 optional arguments:
			- arg1: Name of file containing needles to be searched, or a string representing a needle
			- arg2: Name of file containing haystacks to be searched, or a string representing a haystack
			- (arg3): T/F parameter, deciding whether or not to print haystack after matches are found

		Returns:
			void, but prints the matches and position in haystack

		BoyerMooreHorspool method:
			The algorithm itself.
			- arg1: Character array consisting of the characters in the needle
			- arg2: Character array consisting of the characters in the haystack
			- arg3: T/F parameter, deciding whether or not to print haystack after matches are found

		Returns:
			void, but calls the printWords method

		printWords method:
			Method to print the matches found in the haystack, given a list of integers where the match ends, the haystack and the needle
			- arg1: ArrayList of integers, describing the indices of the last letter in the matches found
			- arg2: Character array consisting of the characters in the needle
			- arg2: Character array consisting of the characters in the haystack
			- arg4: T/F parameter, deciding whether or not to print haystack after matches are found

		Returns:
			void, but prints to terminal
	*/

	public static void main(String[] args){
		if(args.length < 2){
			System.out.println("Correct usage:");
			System.out.println("java BoyerMoore arg1: Needle or filename of needles arg2: Haystack or filename of haystacks (arg3): Print (T/F)");
		}
		else{
			ArrayList<String> needles = new ArrayList(); 
			ArrayList<String> haystacks = new ArrayList(); 
			char[] needle;
			char[] haystack;
			boolean print = true;
			try{
				File needleFile = new File(args[0]);
				Scanner scan = new Scanner(needleFile);
				while(scan.hasNextLine()){
					needles.add(scan.nextLine());
				}
				scan.close();
			}
			catch(FileNotFoundException e){
				needles.add(args[0]);
			}

			try{
				File needleFile = new File(args[1]);
				Scanner scan = new Scanner(needleFile);
				while(scan.hasNextLine()){
					haystacks.add(scan.nextLine());
				}
				scan.close();
			}
			catch(FileNotFoundException e){
				haystacks.add(args[1]);
			}
			if(args.length==3){
				if(args[2].compareTo("F")==0 || args[2].compareTo("False")==0){
					print=false;
				}
			}
			if(haystacks.size() != needles.size()){
				System.out.println("ERROR: Must have the same number of needles as haystacks!");
			}
			else{
				for(int i=0;i<haystacks.size();i++){
					needle=needles.get(i).toCharArray();
					haystack=haystacks.get(i).toCharArray();
					System.out.println("#################################");
					BoyerMooreHorspoolSolve(needle, haystack,print);
				}
				System.out.println("#################################");
			}
		}
	}

	public static void BoyerMooreHorspoolSolve(char[] needle, char[] haystack, boolean print) throws IllegalArgumentException{

		int[] badCharacterShift = new int[256];
		ArrayList<Integer> matchIndices= new ArrayList();


		if(needle.length==0){
			System.out.println("ERROR: Needle must have some length!");
		}

		else if(haystack.length==0){
			System.out.println("ERROR: Haystack must have some length!");
		}

		else if(needle.length>haystack.length){
			System.out.println("WARNING: Needle length is larger than haystack length, cannot find any matches!");
		}
		else{

			int maxShift=needle.length;
			int numberOfDigits;

			for(int i=needle.length-2;i>0; i--){
				if(needle[i]=='_'){
					maxShift=needle.length-i-1;
					break;
				}
			}

			for(int i=0;i<badCharacterShift.length;i++){
	    			badCharacterShift[i]=maxShift;
			}

			int last=needle.length-1;
			int offset=0, scan=0;
			int maxoffset=haystack.length-needle.length;

			for(int i=0;i<last;i++){
				if(last-i>=maxShift){
					continue;
				}
				else{
					badCharacterShift[(int) needle[i] ] = last-i;
				}
			}

			while(needle.length+offset<=haystack.length){
				for(scan=last;(needle[scan]==haystack[scan+offset]||needle[scan]=='_'); scan--){
					if(scan==0){
						matchIndices.add(last+offset);
						break;
					}
				}
				offset+=badCharacterShift[(int) haystack[offset+last]];
			}
	
			printWords(matchIndices, needle, haystack,print);
		}

	}

	public static void printWords(ArrayList<Integer> matchIndices, char[] needle, char[] haystack,boolean print){
		int wordLength=needle.length;
		System.out.print("Needle: ");
		System.out.println(needle);
		System.out.println();
		if(print){
			System.out.print("Haystack: ");
			System.out.println(haystack);
			System.out.println();
		}
		if(matchIndices.isEmpty()){
			System.out.println("No matches found");
		}
		else{
			for(int i : matchIndices){
				System.out.println("-----------------------");
				System.out.print("Match begins at index (starting from 1): ");
				System.out.println(i-wordLength+2);
				System.out.print("Match: ");
				for(int k=wordLength-1;k>=0;k--){
					System.out.print(haystack[i-k]);
				}
				System.out.println();
			}
		}

	}

}
