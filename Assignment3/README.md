# OBLIGATORY ASSIGNMENT 3

## Introduction
This obligatory assignment compares the quicksort algorithm with a least-significant digit (LSD) radix sort. The full exercise text can be found in the PDF document, though this is only available in Norwegian. Please feel free to message me if you require the text in English.

## Compiling
Compilation is done by:

    javac *.java

The program is then run as:

    java Main

## Main method
The main method is contained in the file Main.java

## Timing
I time my sorting as a function of input size, N. I get the following table, where each N corresponds to an average of 5 runs.


|N         | Radix (ms)     | Quicksort (ms) | SpeedUp  |
|----------|:---------------|:---------------|:---------|
|100       | 0.016583       | 0.023720       | 1.430381 | 
|1000      | 0.248773       | 0.290873       | 1.169231 | 
|10000     | 0.739373       | 1.089895       | 1.474080 | 
|100000    | 7.659827       | 23.102467      | 3.016056 | 
|1000000   | 27.502155      | 72.678201      | 2.642637 | 
|10000000  | 124.415907     | 851.375627     | 6.842981 | 

Which gives:

     Average speedup: 2.7625607055743355

So I do not quite manage a factor of 4, as suggested by the lecturer, but I come close to a factor 3.

The reason that radix sort is quick is that bitshift and logical operations can be done extremely quickly, as they operate directly on bits in memory. This enables us to quickly partition the initial array into smaller arrays, which are easier to sort. As we are sorting by largest bit, the ordering of the arrays is clear ahead of time.

Quicksort, on the other hand, uses a pivot recursively, which the values are sorted around. This sorting algorithm is (at least in its most elementary implementation) comparison based. All elements are compared to the pivot, and are placed left or right of it. Radix sort avoids these comparisons.

For larger N, the speedup increases, as more comparisons are needed for quicksort. For small N, our radix implementation also uses quicksort, so the difference is small. But this is insignificant, as only the behaviour for large N determines which algorithm is preferable.

## Stability
I believe LSD radix sort is a stable sorting algorithm. We are scanning sequentially, and copying elements to the right place in b, and opposite in the next loop. Therefore, equal elements which come early in a will also come early in b and vice versa.

## Status
To the best of my knowledge, everything works

## References
Some of the code comes from the exercise text by [Arne Maus]((http://heim.ifi.uio.no/~arnem/)) at the Department of Informatics, University of Oslo.
