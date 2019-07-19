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

```java
javac -cp C:\Users\Yueleng\Coursera\algs4\Algorithm-I\algs4.jar C:\Users\Yueleng\Coursera\algs4\Algorithm-I\Week1\ExampleCode\CollidingDisks.java
```

for compiling multiple java files under current folder 

```java
javac -cp C:\Users\Yueleng\Coursera\algs4\Algorithm-I\algs4.jar Percolation.java PercolationStats.java
```

For running the compiled file:

```
java -cp "C:\Users\Yueleng\Coursera\algs4\Algorithm-I\algs4.jar"; CollidingDisks 8
```

## Week1

* Example Code
  * Hello.java
  * CollidingDisks.java
  * DynamicConnectivityClient.java
  * QuickFindUF.java
  * QuickUnionUF.java
  * QuickUnionUFWeighted.java

