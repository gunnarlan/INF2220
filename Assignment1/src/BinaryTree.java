import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class BinaryTree{

	private static Node root;
	private static ArrayList<Integer> nodesAtDepth = new ArrayList<Integer>();
	private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public BinaryTree(String [] list_of_string){
		for(int i=0; i<list_of_string.length; i++){
			insert(list_of_string[i]);
		}
	}

	private static class Node{
		private String element;
		private Node left=null;
		private Node right=null;
		private int depth;

		Node(String elem, int depth){
			this.element=elem;
			this.left = null;
			this.right = null;
			this.depth=depth;
			if(nodesAtDepth.size() <= depth){
				nodesAtDepth.add(1);
			}
			else{
				nodesAtDepth.set(depth, nodesAtDepth.get(depth)+1);
			}
		}

		private String element(){
			return this.element;
		}

		private Node left(){
			return this.left;
		}

		private Node right(){
			return this.right;
		}
		private void insert(String word){
			if(word.compareTo(this.element)<0){
				if(this.left==null){
					this.left=new Node(word, this.depth+1);
				}
				else{
					this.left.insert(word);
				}
			}
			else if(word.compareTo(this.element)>0){
				if(this.right==null){
					this.right= new Node(word, this.depth+1);
				}
				else{
					this.right.insert(word);
				}
			}
		}

		private String findSmallest(){
			if(this.left==null){
				return this.element;
			}
			else{
				return this.left.findSmallest();
			}
		}

		private String findLargest(){
			if(this.right==null){
				return this.element;
			}
			else{
				return this.right.findLargest();
			}
		}

		private boolean search(String word){
			if (this.element.compareTo(word)==0){
				return true;
			}
			else if(this.element.compareTo(word)>0){
				if(this.left==null){
					return false;
				}
				else{
					return this.left.search(word);
				}
			}
			else{
				if(this.right==null){
					return false;
				}
				else{
					return this.right.search(word);
				}
			}
		}
	}

	public int depthOfTree(){
		return this.nodesAtDepth.size()-1;
	}
				
	public ArrayList<Integer> nodesAtDepth(){
		return this.nodesAtDepth;
	}

	public float averageDepth(){
		int num=0;
		int denom=0;
		for(int i=0;i<nodesAtDepth.size();i++){
			num+=nodesAtDepth.get(i)*i;
			denom+=nodesAtDepth.get(i);
		}
		return num/denom;
	}
	

	public String findLargest(){
		return this.root.findLargest();
	}

	public String findSmallest(){
		return this.root.findSmallest();
	}

	public void insert(String word){
		if (root==null){
			root=new Node(word,0);
		}

		else{
			root.insert(word);
		}
	}

	private void switchLetters(String word, Set<String> similar_words){
		char [] word_array = word.toCharArray();
		char [] temp_array=word_array.clone();
		String word_string;
		for(int i=0; i<word_array.length-1; i++){
			temp_array[i]=word_array[i+1];
			temp_array[i+1]=word_array[i];
			word_string=new String(temp_array);
			if(root.search(word_string)){
				similar_words.add(word_string);
			}
			temp_array[i]=word_array[i];
			temp_array[i+1]=word_array[i+1];
		}	
	}
		
	private void replaceLetters(String word, Set<String> similar_words){
		char [] word_array = word.toCharArray();
		char temp;
		String word_string;
		for(int i=0;i<word_array.length;i++){
			temp=word_array[i];
			for(int j=0; j<alphabet.length;j++){
				word_array[i]=alphabet[j];
				word_string=new String(word_array);
				if(root.search(word_string)){
					similar_words.add(word_string);
				}
			word_array[i]=temp;
			}
		}
	}	

	private void addLetters(String word, Set<String> similar_words){
		char [] word_array = word.toCharArray();
		for(int i=0;i<alphabet.length;i++){
			String first=alphabet[i]+word;
			for(int j=0; j<word_array.length+1;j++){
				String first_part=new String(Arrays.copyOfRange(word_array, 0,j));
				String last_part=new String(Arrays.copyOfRange(word_array, j,word_array.length));
				String word_string = first_part+alphabet[i]+last_part;
				if(root.search(word_string)){
					similar_words.add(word_string);
				}
			}
		}
	}

	private void removeLetters(String word, Set<String> similar_words){
		char [] word_array = word.toCharArray();
		for(int j=0; j<word_array.length;j++){
			String first_part=new String(Arrays.copyOfRange(word_array, 0,j));
			String last_part=new String(Arrays.copyOfRange(word_array, j+1,word_array.length));
			String word_string = first_part+last_part;
			if(root.search(word_string)){
				similar_words.add(word_string);
			}
		}
	}	
	

	public Set<String> search(String word){
		if(root==null){
			return null;
		}
		else{
			Set<String> words = new HashSet<String>();
			if(root.search(word)){
				words.add("0");
				return words;
			}
			else{
				this.switchLetters(word, words);
				this.replaceLetters(word, words);
				this.addLetters(word, words);
				this.removeLetters(word, words);
				return words;
			}
		}
	}		
	
}
				
	
