import java.io.File;
import java.util.Scanner;

public class Sort {

    // Swaps two items in the array
    private static <T extends Comparable<T>> void swap(T[] array, int firstIndex, int secondIndex) {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    /**
     * Partitions the array based on the element at pivotIndex.
     * This implementation sorts in descending order to support the 
     * Greedy Decreasing scheduling heuristic.
     */
    public static <T extends Comparable<T>> int pivot(T[] array, int pivotIndex, int endIndex) {
        int swapIndex = pivotIndex;
        for (int i = pivotIndex + 1; i <= endIndex; i++) {
            // Compare the pivot element with the current element
            if (array[i].compareTo(array[pivotIndex]) > 0) { 
                swapIndex++; // If the current element is larger, increment swapIndex
                swap(array, swapIndex, i); // and swap it
            }
        }
        // Place the pivot element in its correct sorted position
        swap(array, pivotIndex, swapIndex); 
        return swapIndex;
    }

    // Helper method for recursive QuickSort calls
    private static <T extends Comparable<T>> void quickSortHelper(T[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = pivot(array, left, right);
            quickSortHelper(array, left, pivotIndex - 1);
            quickSortHelper(array, pivotIndex + 1, right);
        }
    }

    // Public method available to the user to sort an array
    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            MaxPQ pq = new MaxPQ();
            
            // Read number of processors
            String line = scanner.nextLine(); 
            int number_of_processors = Integer.parseInt(line);

            for (int i = 0; i < number_of_processors; i++) {
                pq.insert(new Processor());
            }
            
            // Read number of jobs
            line = scanner.nextLine(); 

            int number_of_jobs = Integer.parseInt(line);
            Job[] jobs = greedy_decreasing_read_save(number_of_jobs, scanner);
            
            // Sort jobs in descending order (Greedy Decreasing approach)
            quickSort(jobs);
            
            for (Job x : jobs) {
                pq.max().addJob(x);
                pq.update(); // Update processor priority in the heap
            }
            
            // Output the results for each processor
            while (!pq.isEmpty()) {
                Processor currentProcessor = pq.getmax();

                if (currentProcessor != null) {
                    if (pq.size() == 0) {
                        System.out.println("id " + currentProcessor.getId() + ", load=" + currentProcessor.getTotalProcessingTime() + " " + currentProcessor.printoutput());
                        System.out.println("Makespan = " + currentProcessor.getTotalProcessingTime());
                    } else {
                        System.out.println("id " + currentProcessor.getId() + ", load=" + currentProcessor.getTotalProcessingTime() + " " + currentProcessor.printoutput());
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Reads job data from the scanner and saves them into an array.
     */
    public static Job[] greedy_decreasing_read_save(int number_of_jobs, Scanner scanner) {
        Job[] jobs = new Job[number_of_jobs];
        try {
            for (int i = 0; i < number_of_jobs; i++) { 
                // Create a job for each subsequent line and assign it temporarily
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                Job nextJob = new Job(Integer.parseInt(words[0]), Integer.parseInt(words[1]));
                jobs[i] = nextJob;
            }
            
            // Verification: If there are more lines, the job count provided was incorrect
            if (scanner.hasNextLine()) {
                throw new Exception("File contains more jobs than specified.");
            }
        } catch (Exception e) {
            System.out.println("Error reading job data: " + e.getMessage());
        }
        return jobs;
    }
}