# Sorting and k-ary Heapsort
Analyzing k-ary heapsorts, finding the optimal k and comparing it with quicksort and mergesort.

## Project Structure

- `*Sort.java`: are different sorting algorithms implemented for the project.
- `Sorter.java`: is an abstract class defining a reusable interface for different sorting algorithms implemented in this project.
- `Heap.java`: is the generalized version of k-ary heap structure _(unfinished atm)_.
- `MaxHeap.java`: is a k-ary max heap datastructure.
- `Driver.java`: a driver that uses the other classe to perform the tests. (includes the main program entry point)
- `CommandLnieUtilities.java`: provides easy parsing of cli arguments. It also generates a help message for a better cli usage experience.

## Installation
Requirements:
- Java SDK v8
- Apache commons library: to support parsing of the cli arguments (provided in the repo)

## Running
In the `./src` directory run `make` to compile the java classes and then call the driver using `java Driver`. You can pass different arguments to generate different sorts of arrays to test and time the sorting algorithms.
