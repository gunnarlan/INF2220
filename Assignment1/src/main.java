import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;


class main{
	public static void main(String [] args) throws FileNotFoundException{
			try{
				Scanner scan = new Scanner(new File("Dictionary.txt"));
				List<String> words = new ArrayList<String>();
				Set<String> words_out = new HashSet<String>();
				while(scan.hasNextLine()){
	  				words.add(scan.nextLine());
				}
				String[] all_words = words.toArray(new String[0]);
				BinaryTree tree= new BinaryTree(all_words);
			
				String current_word="";
				Scanner user_interface = new Scanner(System.in);
				while(current_word.compareTo("q")!=0){
					System.out.println("###############################");
					System.out.println("Please enter a word (q to quit)");
					System.out.println("----------------------------------");
					current_word=user_interface.next();
					if(current_word.compareTo("q")!=0){
						System.out.println("----------------------------------");
						words_out=tree.search(current_word.toLowerCase());
						if(words_out.size()==0){
							System.out.println("Word not found. No similar words");
						}
						else{
							if(words_out.contains("0")){
								System.out.println("Word found in dictionary!");
							}
							else{
								System.out.println("Word not found! Similar words are:");
								System.out.println("----------------------------------");
								for(String element: words_out){
									System.out.println(element);
								}
							}
						}
					}
				}
				System.out.println("###############################");
				System.out.print("Depth of tree: ");
				System.out.println(tree.depthOfTree());
				System.out.println("----------------------------------");
				System.out.print("First word: ");
				System.out.println(tree.findSmallest());
				System.out.println("----------------------------------");
				System.out.print("Last word: ");
				System.out.println(tree.findLargest());
				System.out.println("----------------------------------");
				System.out.print("Average depth: ");
				System.out.println(tree.averageDepth());
				System.out.println("----------------------------------");
				System.out.println("Nodes at different depths:");
				System.out.println("----------------------------------");
				for(int i=0; i<(tree.nodesAtDepth()).size();i++){
					int node=tree.nodesAtDepth().get(i);
					System.out.println("Depth "+Integer.toString(i)+":"+Integer.toString(node));
				}
				System.out.println("----------------------------------");
				
			}
			catch (FileNotFoundException ex){
				System.out.println("Could not find file");
			}
	}
}
