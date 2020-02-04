import java.util.List;
import java.util.ArrayList;

class Task{
	int id, time, manpower;
	String name;
	int earliestStart,latestStart;
	List<Task> outEdges=new ArrayList();
	List<Task> inEdges = new ArrayList();
	int cntPredecessors;
	int cntPredecessors2;
	int dependencies;
	int slack;
	int numberOfSteps;
}
