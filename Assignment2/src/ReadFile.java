import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
class ReadFile{
	public static ArrayList<Task> ReadFile(String filename) throws FileNotFoundException{
		File file = new File(filename);
		Scanner in = new Scanner(file);
		int NUMBEROFTASKS=in.nextInt();
		in.nextLine();
		ArrayList<Task> vertices  = new ArrayList();
		for(int i=0;i<NUMBEROFTASKS;i++){
			Task newTask = new Task();
			vertices.add(newTask);
		}
		while(in.hasNextLine()) {
			String line=in.nextLine();
			if(line.compareTo("") !=0){
				String [] parts=line.split("\\s+");
				Task currentTask=vertices.get(Integer.parseInt(parts[0])-1);
				currentTask.id=Integer.parseInt(parts[0]);
				currentTask.name=parts[1];
				currentTask.time=Integer.parseInt(parts[2]);
				currentTask.manpower=Integer.parseInt(parts[3]);
				for(int i=4;i<parts.length-1;i++){
					int taskDependency=Integer.parseInt(parts[i]);
					vertices.get(taskDependency-1).outEdges.add(currentTask);
					vertices.get(taskDependency-1).dependencies++;
				        currentTask.inEdges.add(vertices.get(taskDependency-1));		
				}
				currentTask.cntPredecessors=parts.length-5;
				currentTask.cntPredecessors2=parts.length-5;
			}
		}
		return vertices;
	}
}	
	

	
