# Multiprocessor Job Scheduler & Makespan Optimizer

A Java-based implementation of heuristic algorithms designed to solve the **Multiprocessor Scheduling Problem** (Minimizing Makespan). This project demonstrates the efficient use of custom-built **Priority Queues** and **Sorting Algorithms** to handle optimization tasks.

## 📌 Problem Overview
The goal is to distribute $N$ jobs with varying processing times across $M$ processors so that the total completion time (the "Makespan") is minimized. Since this is an **NP-hard** problem, the application implements two heuristic approaches:
1. **Greedy Algorithm:** Assigns each job to the processor with the current minimum load.
2. **Greedy Decreasing Algorithm:** Sorts jobs in descending order of processing time before applying the greedy assignment, typically yielding a more optimal result.

---

## 🛠️ Custom Data Structures
To avoid reliance on standard libraries and demonstrate low-level architectural skills, the following were implemented from scratch:

### Max Priority Queue (Binary Heap)
* **Logic:** Efficiently manages processors based on their current load.
* **Efficiency:** Supports $O(\log M)$ insertion and removal of the processor with the highest priority (lowest load).
* **Dynamic Sizing:** Features an automatic `resize()` method to handle varying numbers of processors.


### Generic QuickSort
* **Implementation:** A standalone `Sort.java` featuring a generic QuickSort implementation.
* **Logic:** Uses a recursive partitioning strategy to sort jobs or any `Comparable` objects.

---

## 📂 Project Structure
* `Job.java`: Represents a task with a specific duration.
* `Processor.java`: Represents a processing unit, maintaining its own `LinkedList` of assigned jobs.
* `Greedy.java`: Main entry point for the basic Greedy heuristic.
* `Sort.java`: Entry point for the Greedy Decreasing heuristic.
* `Comparisons.java`: Utility to run simulations on large datasets and compare the performance of both algorithms.

---

## 💻 Usage

### Compilation
Compile all source files:
```bash
javac *.java
