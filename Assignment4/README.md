# OBLIGATORY ASSIGNMENT 4
## Introduction
This assignment implements a text searching algorithm (Boyer-More-Horspool), with the option of including wildcards. The full details of the assignment may be found in the PDF.

## Compiling
Compilation is done by:

    javac *.java

The program is then run as:

    java BoyerMooreHorspool


## Main method
The main method is in the file `BoyerMooreHorspool.java`


## Explanation of the Algorithm
The algorithm is largely similar to the Boyer-Moore-Horspool algorithm.
The only difference is the tackling of wildcards. I think as follows:

A wildcard can represent any character. Thus, once a wildcard is encountered, the 
`badCharacterShift` value of every character must be updated. Note that the characters
are updated in a certain order in the original algorithm: first, all characters 
are assigned a `badCharacterShift` equivalent to the entire word length (i.e.
if that character is found, then it cannot be correct, so just shift the entire word length).

Then, the first character is given a shift value, then the second character etc., all the way
up to the second to last character (if the last character is encountered without match, we can
shift the entire word length again).
Thus, the `badCharacterShift` of the last WildCard that is *not* the very last character
will update the `badCharacterShift` of all characters. I therefore begin
by finding the last WildCard that is not in the last position of the needle,
and then set the shift of all characters to that value (`maxShift`).

Now, no character may have a higher shift than `maxShift` (as they may
fit into one of the empty slots left by the WildCard), but some characters (those to the right
of the last WildCard that are not at the end of the needle), may get an even lower shift value.
This is because if the needle is `a_bc`, then if `a b` is encountered, we have to shift a single value
(the next character might be a `c`). Thus, I set the `badCharacterShift` values of the characters
in the needle to be either `maxShift`, or (if their shift value is lower), the standard shift
predicted by the `badCharacterShift` algorithm.

One other difference to the algorithm presented in the lecture slide is how to tackle
finding a match. I do this by simply recording the match, and then moving on as if nothing
had happened. This guarantees that every match is found, as every letter is only ever
shifted by an amount that corresponds to the maximum amount it can be shifted without losing
any possible solutions.

## Assumptions
* I have assumed that the wildcard can match any character (not just letters)

* I have assumed that having a needle longer than the haystack makes 
no sense (rather than just returning no match).

## Peculiarities
I have made it possible to read in needles and haystacks from a file, or to directly add them in the command-line. If reading from file,
each new needle/haystack must be on a new line (this would be a point of improvement for my program that I may implement in the future).

I also have an optional argument `print`, which can be disabled, (if matching very long haystacks), so that the haystack is not printed

## Test cases
I have provided some test cases in the input files `Needles.in` and `Haystack.in`. This may be run as (after compiling):

    java BoyerMooreHorspool Needles.in Haystack.in


## Status
Everything works to the best of my knowledge


## References
The main parts of the code are almost directly copied from the lecture slide from the lecture on the 26th of October 2017, prepared by [ Ingrid Chieh Yu ](https://www.mn.uio.no/ifi/personer/vit/ingridcy/index.html) from the Department of Informatics at the University of Oslo. I have really
only modified the `badCharacterShift` method, added support for multiple matches and written the `print` method.
