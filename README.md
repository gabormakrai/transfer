Transfer - Traffic Assignment Environment
========

Transfer is a Java based traffic assignment environment. Its primary purpose is to scientifically investigate and analyse different algorithms and techniques used to solve the Traffic Assignment Problem.

This project contains classes to parse all the necessary files for the solver, classed for the solvers themselves and classes to write out the results.

There are some related github projects connected to this project:
* [transfer-data](https://github.com/gabormakrai/transfer-data): project contains a collection of publicly available dataset for the Traffic Assignment Problem
* [transfer-benchmark](https://github.com/gabormakrai/transfer-benchmark): project contains performance analysis of different TAP solver algorithms on datasets found in transfer-data project

### TAP algorithms

##### Frank-Wolfe algorithm (FW)

This algorithm was used firstly to solve the TAP. More details can be found in *Frank, M., & Wolfe, P. (1956). An algorithm for quadratic programming. Naval research logistics quarterly, 3(1‐2), 95-110.*

##### Path Equilibrium algorithm (PE)

The original solution proposed to solve the TAP was the PE algorithm however that time it was considered to inpractical because its large memory requirement. More detauls can be found in *Dafermos, S. C., & Sparrow, F. T. (1969). The traffic assignment problem for a general network. Journal of Research of the National Bureau of Standards, Series B, 73(2), 91-118.*

### Single source shortest path algorithms

All the TAP algorithms depends on single source shortest path problem. Transfer contains several approach to solve this subproblem and all the implementations are based on *Cormen, Thomas H., Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein. Introduction to algorithms. Vol. 2. Cambridge: MIT press, 2001.*. The text will refer to this book as CLRS.

##### naive Dijkstra algorithm

This is the naive implementation of Dijkstra's shortest path algorithm. Pseudocode can be found in CLRS 24.3. 

##### Dijkstra algorithm with binary heap priority queue

This implementation of the Dijkstra's algorithm is using binary heap priority queue which leads us to much better running time (the time complexity is O(VlogV) compared to the naive implementations' time complexity O(V^2)). Pseudocecode can be found in CLRS 24.3. 

##### Dijkstra algorithm with Fibonacci heap priority queue

This implementation of the Dijsktra's algorithm is using Fibonacci heap priority queue which has better running time on highly connected graphs. Pseudocode for the Fibonacci heap can be found in CLRS 17.

### IO methods

##### TNTP 
