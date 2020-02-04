# OBLIGATORY ASSIGNMENT 1

## Introduction
This assignment implements a dictionary lookup function, which also provides suggestions for similar words. This is done using a binary search tree. Full details can be found in the PDF. 

## Compiling
Compilation is done by:

    javac *.java

The program is then run as:

    java Main

## Main method
The main method is contained in the file Main.java

## Assumptions
I have assumed the following:
* The words are short enough that the effectiveness of `Set<List>` is not a concern. I have further assumed that the tree is not so large that
	   the `ArrayList` t that counts the different nodes at different depths becomes unwieldy.

* I have assumed that the user can work exclusively with the interface, as the communication between `main` and `binaryTree` has been done in a somewhat cursory manner.

* I have not written this with general types, so I have assumed strings (though this would not be a large change). I have also 
	   assumed that the strings have reasonable (UTF-8) letters only.
* In the runtime analysis, I have only performed a big-O estimate, and therefore not cared too much about the 
	   details of how many if statements there are, etc. This is justified by the fact that the main speed bottleneck is the
	   size of the tree, rather than the number of similar words found.

## Peculiarities
One peculiarity is that I choose to store the number of nodes in an `ArrayList` of integers. This seems to be a fairly clean solution, though I welcome suggestion
	to the opposite effect. Additionally, the communication between main and the tree is somewhat strenuous, as pointed out earlier.

## Runtime analysis
### Switch Letters

  I begin by finding the runtime time of the search method. In the worst-case scenario, we must compare a number
		of nodes equal to the depth of the tree. Optimally, the depth of the tree is O(log n). This is because, if each
		level is filled to the brink, then the number of nodes is 1+2+4+8+16+.. so the depth is O(log n). However,
		if the search tree is maximally unbalanced, then it may only have nodes on one side, giving a depth O(n).
		As a conservative estimate, I adopt O(n) as the depth of the tree. Thus I must perform at most O(n) comparisons
		to find any element, and the search method has a (worst-case) performance of O(n).

  When replacing letters, I simply loop over every letter in the word. This thus scales linearly with s, the number of
		letters in the word. If s is small, then the performance is still O(n). If s is not small, a more correct 
		description may be O(sn) (though this is non-standard notation, and thus the correct notation would depend on the
		ratio between n and s).

### Replace letters

  The runtime of the search algorithm is still O(n). Now I iterate over the length s of the word, times the 
		number of characters in the alphabet. As the number of characters in the alphabet is a constant (which is ignored
		in big-O notation), the runtime of replacing letters is the same as above - O(n) if s is fairly small, otherwise O(s).

### Add letters

  Once again, this is simply iterating over the word length and the length of the alphabet - which still gives a runtime
		of O(n), if the string is small enough. If the string is not small enough, then the `Arrays.copyOfRange` method 
		may cause problems, as I suspect this is a fairly slow method.
	
### Remove letters

  This is exactly the same as above.

## Status
To the best of my knowledge, everything works

## References
I read the relevant chapters in the course book [1]. I also got some ideas bit from Stack Exchange, but only individual lines, not whole blocks of code.

[1] Mark Allen Weiss: Data Structures and Algorithm Analysis in Java, third (international) edition. ISBN-10: 0-273-75211-1, ISBN-13: 978-0-273-75211-0.
