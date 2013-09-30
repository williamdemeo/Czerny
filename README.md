Czerny
======

Java class for testing Czerny Conjecture with UACalc's FreeAlgebra class

This class provides some functions for working with finite automata. 
In particular, we want to test the Czerny conjecture for small n using the FreeAlgebra class.

The main program in Czerny.java takes an input n (an integer) and constructs an algebra
consisting of an n element universe and two unary operations: f and g.  

(This is an automaton on n "states" and two "letters.")

It does this for every possible pair f and g and checks whether the resulting automaton 
violates the Czerny conjecture.

So far I have run the program for n=3 up to n=6 states and, as expected, found no violation of
the Czerny conjecture.  I am currently running it on larger automaton.

The program also outputs the reset words for interesting (i.e. slowly synchronizing) automata.

