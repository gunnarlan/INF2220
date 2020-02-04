import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class Main{
	public static void main(String[] args) throws FileNotFoundException {
		String filename=args[0];
		ArrayList<Task> vertices;
	
		vertices=ReadFile.ReadFile(filename);
	
		GraphAlgorithms solver = new GraphAlgorithms(vertices);	
		if(solver.topologicalSort()){
			solver.findOptimalTime();
			solver.printOptimalTimeSchedule();
			solver.printTaskInformation();
		}
	}
}
