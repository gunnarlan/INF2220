import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class GraphAlgorithms{
	private ArrayList<Task> vertices=new ArrayList();
	private Queue<Task> vertexQueue=new LinkedList();
	private int numberOfTasks;
	private int shortestPossibleTime=0;


	public GraphAlgorithms(ArrayList<Task> vertices){
		this.vertices=vertices;
		this.numberOfTasks=vertices.size();
	}

	public boolean topologicalSort(){
		if(topoSort()){
			System.out.println("No cycles found!");
			System.out.println("------------------");
			return true;
		}
		else{	
			System.out.println(findLoop());
			return false;
		}
			
	}

	private boolean topoSort(){
		int numberOfSteps=0;
		for(Task task:this.vertices){
			if(task.cntPredecessors==0){
				this.vertexQueue.add(task);
			}
		}
		while(!vertexQueue.isEmpty()){
			numberOfSteps++;
			Task task = this.vertexQueue.remove();
			task.numberOfSteps=numberOfSteps;
			for(Task adjacentTask:task.outEdges){
				adjacentTask.cntPredecessors--;
				if(adjacentTask.cntPredecessors==0){
					this.vertexQueue.add(adjacentTask);
				}
			}
		}
		this.vertexQueue=new LinkedList();
		if(numberOfSteps != this.numberOfTasks){
			return false;
		}
		else{
			return true;
		}
	}

	private String findLoop(){
		ArrayList<String> vertexPath=new ArrayList<String>();
		Task startTask=null;
		Task currentTask=null;
		boolean loopFound=false;
		for(Task task:vertices){
			if(task.cntPredecessors>0){
				startTask=task;
				vertexPath.add(startTask.name);
				currentTask=startTask;
				break;
			}
		}
		while(loopFound==false){
			for(Task adjacentTask:currentTask.outEdges){
				if(adjacentTask.cntPredecessors > 0){
					currentTask=adjacentTask;
					vertexPath.add(currentTask.name);
					break;
				}
			}
			if(currentTask == startTask){
				loopFound=true;
			}
		}
		String out = "Cycle found! One such cycle is:\n";
		for(int j=0;j<vertexPath.size();j++){
			if(j==0){
				out=out+vertexPath.get(j);
			}
			else{
				out=out+" -> "+vertexPath.get(j);
			}
		}
		return out;
	}
	

	void findOptimalTime(){
		for(Task currentTask: this.vertices){
			currentTask.earliestStart=0;
			if(currentTask.cntPredecessors2==0){
				currentTask.earliestStart=0;
				vertexQueue.add(currentTask);
			}
		}
		while(!vertexQueue.isEmpty()){
			Task currentTask = vertexQueue.remove();
			for(Task adjacentTask:currentTask.outEdges){
				if(adjacentTask.earliestStart < (currentTask.earliestStart+currentTask.time)){
					adjacentTask.earliestStart=currentTask.earliestStart+currentTask.time;
				}
				if(this.shortestPossibleTime<adjacentTask.earliestStart+adjacentTask.time){
					this.shortestPossibleTime=adjacentTask.earliestStart+adjacentTask.time;
				}
				adjacentTask.cntPredecessors2--;
				if(adjacentTask.cntPredecessors2 == 0){
					vertexQueue.add(adjacentTask);
				}
			}

		}
		vertexQueue=new LinkedList();
		for(Task currentTask: this.vertices){
			currentTask.latestStart=Integer.MAX_VALUE;
			if(currentTask.dependencies==0){
				currentTask.latestStart=this.shortestPossibleTime-currentTask.time;
				vertexQueue.add(currentTask);
			}
		}

		while(!vertexQueue.isEmpty()){
			Task currentTask = vertexQueue.remove();
			for(Task adjacentTask:currentTask.inEdges){
				if(adjacentTask.latestStart > (currentTask.latestStart-adjacentTask.time)){
					adjacentTask.latestStart=currentTask.latestStart-adjacentTask.time;
				}
				adjacentTask.dependencies--;
				if(adjacentTask.dependencies == 0){
					vertexQueue.add(adjacentTask);
				}
			}

		}

		for(Task currentTask:this.vertices){
			currentTask.slack=currentTask.latestStart-currentTask.earliestStart;
		}

	}

	public void printOptimalTimeSchedule(){
		int manpower=0;
		for(int time=0;time<this.shortestPossibleTime;time++){
			boolean printed=false;
			for(Task task : this.vertices){
				if(task.earliestStart==time){
					if(printed ==false){
						System.out.print("Time: ");
						System.out.print(time);
						System.out.print("      ");
						printed=true;
					}
					System.out.print("Starting: ");
					System.out.println(task.name);
					manpower=manpower+task.manpower;
				}
				else if(task.earliestStart+task.time==time){
					if(printed ==false){
						System.out.print("Time: ");
						System.out.print(time);
						System.out.print("      ");
						printed=true;
					}
					System.out.print("Finished: ");
					System.out.println(task.name);
					manpower=manpower-task.manpower;
				}
			}
			if(printed){
				System.out.print("Current Staff: ");
				System.out.println(manpower);
				System.out.println();
			}
		}

		System.out.println("**** Shortest possible project execution is "+Integer.toString(shortestPossibleTime)+" ****");
				
	}

	public void printTaskInformation(){
		for(Task currentTask:this.vertices){
			System.out.println("------------------");
			System.out.print("Task: ");
			System.out.println(currentTask.name);
			System.out.print("ID: ");
			System.out.println(currentTask.id);
			System.out.print("Time needed to finish: ");
			System.out.println(currentTask.time);
			System.out.print("Manpower needed: ");
			System.out.println(currentTask.manpower);
			System.out.print("Earliest starting time: ");
			System.out.println(currentTask.earliestStart);
			System.out.print("Latest starting time: ");
			System.out.println(currentTask.latestStart);
			System.out.print("Slack: ");
			System.out.println(currentTask.slack);
			System.out.print("Task that depend on this task (ID): ");
			for(Task dependentTask : currentTask.outEdges){
				System.out.print(dependentTask.id);
				System.out.print(" ");
			}
			System.out.println("");
		}
		
	}
}
