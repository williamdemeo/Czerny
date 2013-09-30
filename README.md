Czerny
======

This repository contains some Java classes for testing the Czerny Conjecture using UACalc's FreeAlgebra class.

The main program in Czerny.java takes an input n (an integer) and, for each pair of
unary operations f and g on the set {0, 1, ..., n-1}, constructs an algebra
consisting of an n element universe and two unary operations, f and g (i.e.,
f and g are functions of one variable mapping the set {0,1,...,n-1} into itself). 

(This is equivalent to an automaton on n "states" and two "letters.")

For each of these unary algebras, the program then constructs the free algebra on 
one generator using Ralph Freese's FreeAlgebra Java class. The generator used
is the element (0, 1, 2, ..., n-1).)   (See http://uacalc.org to learn more about
the UACalc.) 

The set of terms of the resulting free algebra will consist of all the words
produced by an automaton on n states using the letters f and g. 

The Czerny Conjecture states that if the automaton has a "reset word"
(i.e., is "synchronizing", i.e. the free algebra has a constant term), 
then it must have a reset word of length no larger than (n-1)^2.  


(The word length is the number of letters in the word, counting repetitions.
For example, the word f(g(g(g(f(x))))) has length 5.)

To search for counter-examples, the program looks in the free algebra for
constant terms of shortest length and if it finds an example where the shortest
length constant term is greater than (n-1)^2, this is a counter-example.

So far I have run the program for n=3 up to n=6 states and, as expected, 
found no violation of the Czerny conjecture.  I am currently running it on larger 
automaton.

The program also outputs the reset words for interesting (i.e. synchronizing) automata.

Other Notes
-----------
The Czerny Conjecture is a statement about all finite automata, even those
with more than two letters.  So, while this program might be able to find a
counter-example, it will not verify the Czerny Conjecture for a given n (since
we're only checking automata with two letters---of course, the code could be
generalized to use more letters). 


Possible Improvements
---------------------
It's possible we could use Freese's thinning method to eliminate some
coordinates and compute the free algebra much faster, and yet still be able to 
test for counterexamples.  On the other hand, I suspect that speeding up the
free algebra computation will not result in major gains, since the real problem
is that the number of pairs of unary maps on n grows exponentially, so even if the
algorithm does nothing inside the loop, it will take a long time for large n.  

Probably a better way to improve the algorithm is to think of smart ways to thin
this set of candidate pairs of unary maps.


Sample Output
-------------
If you run the main() function with input n=5, this is what a small segment of
the output looks like: 

    ======== Automaton 24899 ========
    Sep 30, 2013 6:38:56 PM org.uacalc.alg.FreeAlgebra makeUniverse
    INFO: free algebra size = 1417
    |F(1)| = 1417
    letters: (3, 0, 2, 4, 0),  (3, 2, 0, 1, 4)

    Reset words:  g(f(f(g(f(g(f(g(x)))))))),  f(f(g(f(f(g(f(g(f(g(x)))))))))),  f(f(f(g(f(f(g(f(g(f(g(x))))))))))),  f(g(f(f(g(f(g(f(g(x))))))))),  g(f(g(f(f(g(f(g(f(g(x)))))))))),  

    Min reset length: 8      MaxMin reset length: 16

    ======== Automaton 24900 ========
    Sep 30, 2013 6:38:56 PM org.uacalc.alg.FreeAlgebra makeUniverse
    INFO: free algebra size = 44
    |F(1)| = 44
    letters: (3, 0, 2, 4, 0),  (3, 2, 1, 0, 4)
    not synchronizing

    ======== Automaton 24901 ========
    Sep 30, 2013 6:38:56 PM org.uacalc.alg.FreeAlgebra makeUniverse
    INFO: free algebra size = 69
    |F(1)| = 69
    letters: (3, 0, 2, 4, 0),  (3, 2, 1, 4, 0)
    not synchronizing

    ======== Automaton 24902 ========
    Sep 30, 2013 6:38:56 PM org.uacalc.alg.FreeAlgebra makeUniverse
    INFO: free algebra size = 2110
    |F(1)| = 2110
    letters: (3, 0, 2, 4, 0),  (3, 2, 4, 1, 0)

    Reset words:  g(f(g(g(f(f(f(g(f(g(x)))))))))),  f(f(g(f(g(g(f(f(f(g(f(g(x)))))))))))),  f(f(f(g(f(g(g(f(f(f(g(f(g(x))))))))))))),  f(g(f(g(g(f(f(f(g(f(g(x))))))))))),  g(f(g(f(g(g(f(f(f(g(f(g(x)))))))))))),  

    Min reset length: 10      MaxMin reset length: 16

    ======== Automaton 24903 ========
    Sep 30, 2013 6:38:56 PM org.uacalc.alg.FreeAlgebra makeUniverse
    INFO: free algebra size = 128
    |F(1)| = 128
    letters: (3, 0, 2, 4, 0),  (3, 4, 2, 1, 0)
    not synchronizing
