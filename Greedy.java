import java.io.File;
import java.util.Scanner;

public class Greedy {

    public static void main(String[] args) {
        try {
            // Load the file provided as a command-line argument
            Scanner scanner = new Scanner(new File(args[0]));
            MaxPQ pq = new MaxPQ();
            
            try {
                // Read the number of processors from the first line
                String line = scanner.nextLine();
                System.out.println(line);
                int number_of_processors = Integer.parseInt(line);

                // Initialize the priority queue with new Processor objects
                for (int i = 0; i < number_of_processors; i++) {
                    pq.insert(new Processor());
                }

                // Read the number of jobs from the second line
                line = scanner.nextLine();
                System.out.println(line);
                int number_of_jobs = Integer.parseInt(line);
                
                // Read job data and save into an array
                Job[] jobs_array = greedy_read_save(number_of_jobs, scanner);
                
                // Assign each job to the processor with the current minimum load
                for (Job i : jobs_array) {
                    pq.max().addJob(i); // Add job to the top priority processor
                    pq.update();        // Re-heapify to maintain processor priority
                }
            } catch (Exception e) {
                System.out.println("Error: Lines and number of jobs are not equal");
            }

            // Output results for each processor until the priority queue is empty
            while (!pq.isEmpty()) {
                Processor currentProcessor = pq.getmax();

                if (currentProcessor != null) {
                    // Final processor output includes the Makespan (total completion time)
                    if (pq.size() == 0) {
                        System.out.println("id " + currentProcessor.getId() + ", load=" + currentProcessor.getTotalProcessingTime() + " " + currentProcessor.printoutput());
                        System.out.println("Makespan = " + currentProcessor.getTotalProcessingTime());
                    } else {
                        System.out.println("id " + currentProcessor.getId() + ", load=" + currentProcessor.getTotalProcessingTime() + " " + currentProcessor.printoutput());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: File not found");
        }
    }

    /**
     * Reads job data from a scanner and saves them into an array.
     * Validates that the number of jobs matches the expected count.
     */
    public static Job[] greedy_read_save(int number_of_jobs, Scanner scanner) {
        try {
            Job[] jobs_returned = new Job[number_of_jobs];
            for (int i = 0; i < number_of_jobs; i++) { 
                // Parse job ID and processing time from the file line
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                Job nextJob = new Job(Integer.parseInt(words[0]), Integer.parseInt(words[1]));
                jobs_returned[i] = nextJob;
            }
            
            // If there is another line after the loop, the job count is incorrect
            if (scanner.hasNextLine()) {
                throw new Exception();
            }
            return jobs_returned;
        } catch (Exception e) {
            System.out.println("Error: Lines and number of jobs are not equal");
        }
        return null;
    }
}