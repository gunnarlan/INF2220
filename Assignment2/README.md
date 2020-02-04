# OBLIGATORY ASSIGNMENT 2

## Introduction
This assignment implements various graph algorithms to analyze feasibility time requirements for a project planning tool. The full task description is available in the PDF.

## Compiling
Compilation is done by:

    javac *.java

The program is then run as:

    java Main

## Main Method
The main method is contained in the file Main.java

## Assumptions
* I have not checked extensively if the user provides a valid filename. I thus assume that the user does provide a valid filename.

* I have also not checked that the user calls `findOptimalTime` before `printOptimalTimeSchedule`. The program will crash if this is not the case.

* I have also not implemented any significant flexibility/error handling when reading the input file.

## Peculiarities
I did not manage to solve the assignment just with the task class presented in the assignment. I had to add a few variables. `cntPredecessor2` is not strictly speaking needed, as I could iterate over
the vertices and reset this variable after changing it in the topological sort. I added it for convenience.

I did not manage to solve the latest start time problem without having doubly linked vertices, which
I therefore also added to the task class.

## Runtime analysis
My code consists of different parts, which I discuss individually.

### Topological Sort
I first iterate over every vertex, V, once, giving a time complexity of O(|V|)
I then iterate over every vertex again (as a vertex can only appear in the queue once), 
and iterate over every edge, E, associated with the vertex. Note that each edge
can only appear once. Thus the complexity of this part is O(|V|+|E|).
The total complexity is then O(|V|+|E|)+O(|V|)=O(|V|+|E|)

### Find Loop
To find a loop, I begin by iterating over all vertices, V, for a (worst-case) time complexity of O(|V|).
I then iterate over all edges, E, which give a worst-case time complexity of O(|E|).
This again gives a total complexity of O(|E|+|V|).

### Find Optimal Time
I again begin by iterating over every vertex, V, giving O(|V|),
and then iterate over every edge, E, to give a time complexity of O(|V|+|E|).
I do this again to compute the latest starting time.
Finally, I iterate over every vertex to compute the slack.
The total complexity is therefore still O(|E|+|V|).

### Print Optimal Time Schedule
Here I iterate over every timestep, and in every timestep over all vertices, V. This gives a complexity
of O(T*|V|), where T is the earliest completion time of the project.

### Print Task Information
Here I only iterate over every vertex, V, giving
a time complexity of O(|V|).



## Status
Everything works, to the best of my knowledge.

## References
The `Task` class is taken directly from the exercise text.

The code for topological sort is heavily inspired by the code found in [1]

The algorithmic idea behind the `findOptimalTime` method is also inspired by [1].

[1] Mark Allen Weiss: Data Structures and Algorithm Analysis in Java, third (international) edition. ISBN-10: 0-273-75211-1, ISBN-13: 978-0-273-75211-0.


