Czerny
======

This is a Java class for testing the Czerny Conjecture using UACalc's FreeAlgebra class.

The main program in Czerny.java takes an input n (an integer) and, for each pair of
unary operations  f and g on the set {0, 1, ..., n-1}, it constructs an algebra
consisting of an n element universe and two unary operations, f and g.

(This is equivalent to automaton on n "states" and two "letters.")

The program then constructs, for each of these unary algebras, the free algebra on 
one generator. (The generator used is the element (0, 1, 2, ..., n-1).)  

The point is that the set of terms of the resulting free algebra will consist of
all the words produced by an automaton on n states using the letters f and g.

The Czerny Conjecture states that if the automaton has a "reset word"
(i.e., is "synchronizing", i.e. the free algebra has a constant term), 
then it must have a reset word of length no larger than (n-1)^2.  

So, to search for counter-examples, the program looks in the free algebra for 
constant terms of shortest length and if it finds an example where the shortest length
constant term is greater than (n-1)^2, this is a counter-example.

So far I have run the program for n=3 up to n=6 states and, as expected, 
found no violation of the Czerny conjecture.  I am currently running it on larger 
automaton.

The program also outputs the reset words for interesting (i.e. synchronizing) automata.

