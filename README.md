# Algorithm-I

## A Priceton University - Coursera Online Course

## Compile Code Tutorial

How to compile the java code and run with external jar files.
Reference: [Compile and Run Java in Command Line with External Jars](https://www.programcreek.com/2014/01/compile-and-run-java-in-command-line-with-external-jars/)

Compile a single file with `algs4.jar`

```java
javac -cp "C:\Users\Yueleng\Coursera\algs4\Algorithm-I\algs4.jar" C:\Users\Yueleng\Coursera\algs4\Algorithm-I\Week1\ExampleCode\CollidingDisks.java
```

or without double quotes

```bash
javac -cp C:\Users\Yueleng\Coursera\algs4\Algorithm-I\algs4.jar C:\Users\Yueleng\Coursera\algs4\Algorithm-I\Week1\ExampleCode\CollidingDisks.java
```

for compiling multiple java files under current folder

```bash
javac -cp C:\Users\Yueleng\Coursera\algs4\Algorithm-I\algs4.jar Percolation.java PercolationStats.java
```

For running the compiled file:

```bash
java -cp "C:\Users\Yueleng\Coursera\algs4\Algorithm-I\algs4.jar"; CollidingDisks 8
```

## Week1

### Example Code From Lecture Note
  
* Hello.java
* CollidingDisks.java
* DynamicConnectivityClient.java
* QuickFindUF.java
* QuickUnionUF.java
* QuickUnionUFWeighted.java

### Submission of Assignment

* Percolation.java
* PercolationStats.java

### Notes of Lectures

* We study and define the Dynamic Connectivity Model.

* Data structure to implement this model: `Quick Find`, `Quick Union`, `Weighted Quick Union`, `Weighted Quick Union with Path Compression`. The following is the worst case

    | Algorithm        | Initialize           | Union  | Connected |
    | ------------- |:-------------:| -----:|-----: |
    | Quick Find      | N | N | 1|
    | Quick Union      |  N     |   N | N |
    | Weighted QU  |   N    |  lgN  | lgN |

* Hopcroft-Ulman, Tarjan Proposition
* Fredman-Saks Proposition.

* M union-find operations on a set of N objects

    | Algorithm        | Initialize   |
    | ------------- |:-------------:|
    | Quick Find      | MN |
    | Quick Union      | MN |
    | Weighted QU  |   N + MlogN  |
    | QU + path compression  |   N + MlogN  |
    | Weighted QU + path compression  |   N + Mlg*N  |

    lg*: iterate log function

* Some algorithmic succeesses: `Discrete Fourier Transform(FFT algorithm, NlogN steps)`, `N-body simulation`, 

* Bad new for Analysis of Algorithms: Difficult to get precise measurements.

* Total running time: sum of cost(machine, compiler) * frequency(algorithm, input data) for all operations

* Tilde notation: equivalence relationship. For example: 1/6 N^3 + 20N + 16 ~ 1/6 N^3

* Page 31.