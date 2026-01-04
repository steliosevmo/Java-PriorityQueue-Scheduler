import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class Comparisons {
    public static void main(String[] args) {
        // From here I can create new files if I want
        //create_random_100(); 
        //create_random_250();
        //create_random_500();

        greedy_comparisons(100);
        greedy_decreasing_comparisons(100);

        greedy_comparisons(250);
        greedy_decreasing_comparisons(250);

        greedy_comparisons(500);
        greedy_decreasing_comparisons(500);
    }

    public static void greedy_comparisons(int number_N) {
        int[] make_span = new int[11];
        make_span[0] = 0;
        for (int i = 1; i <= 10; i++) { // Calculate makespan for each file
            try {
                Scanner scanner = new Scanner(new File("data/" + "text" + number_N + "_" + i + ".txt"));
                MaxPQ pq = new MaxPQ();
                String line = scanner.nextLine(); // Number of processors

                int number_of_processors = Integer.parseInt(line);
                for (int k = 0; k < number_of_processors; k++) {
                    pq.insert(new Processor());
                }
                line = scanner.nextLine();
                Job[] jobs_array = Greedy.greedy_read_save(number_N, scanner);

                for (Job j : jobs_array) {
                    pq.max().addJob(j);
                    pq.update();
                }
                while (!pq.isEmpty()) {
                    Processor currentProcessor = pq.getmax();
                    if (pq.size() == 0) {
                        make_span[i] = currentProcessor.getTotalProcessingTime();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            System.out.println(make_span[i]);
            sum += make_span[i];
        }
        System.out.println("Greedy" + number_N + ":" + sum / 10); // Average greedy makespan
    }

    public static void greedy_decreasing_comparisons(int number_N) {
        int[] make_span2 = new int[11];
        make_span2[0] = 0;

        MaxPQ pq2 = new MaxPQ();
        for (int i = 1; i <= 10; i++) {
            try {
                Scanner scanner = new Scanner(new File("data/text" + number_N + "_" + i + ".txt"));

                String line = scanner.nextLine(); // Number of processors

                int number_of_processors = Integer.parseInt(line);
                for (int k = 0; k < number_of_processors; k++) {
                    pq2.insert(new Processor());
                }
                line = scanner.nextLine();
                Job[] jobs_array = Greedy.greedy_read_save(number_N, scanner);
                Sort.quickSort(jobs_array);
                for (Job j : jobs_array) {
                    pq2.max().addJob(j);
                    pq2.update();
                }
                while (!pq2.isEmpty()) {
                    Processor currentProcessor = pq2.getmax();
                    if (pq2.size() == 0) {
                        make_span2[i] = currentProcessor.getTotalProcessingTime();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        int sum2 = 0;
        for (int i = 1; i <= 10; i++) {
            System.out.println(make_span2[i]);
            sum2 += make_span2[i];
        }
        System.out.println("Greedy_Reduction" + number_N + " :" + sum2 / 10); // Average greedy decreasing makespan
    }

    public static void create_random_100() {
        for (int i = 1; i <= 10; i++) {
            try {
                FileWriter newFile = new FileWriter("data/text100_" + i + ".txt");
                Random rand = new Random();
                newFile.write((int) Math.sqrt(100) + "\n"); // Writes the number of processors
                newFile.write("100" + "\n"); // Writes the number of jobs
                for (int j = 1; j <= 100; j++) { // Creates the jobs
                    newFile.write(j + " " + rand.nextInt(1, 1000) + "\n");
                }
                newFile.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void create_random_250() {
        for (int i = 1; i <= 10; i++) {
            try {
                FileWriter newFile = new FileWriter("data/text250_" + i + ".txt");
                Random rand = new Random();
                newFile.write((int) Math.sqrt(250) + "\n"); // Writes the number of processors
                newFile.write("250\n"); // Writes the number of jobs
                for (int j = 1; j <= 250; j++) { // Creates the jobs
                    newFile.write(j + " " + rand.nextInt(1, 1000) + "\n");
                }
                newFile.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void create_random_500() {
        for (int i = 1; i <= 10; i++) {
            try {
                FileWriter newFile = new FileWriter("data/text500_" + i + ".txt");
                Random rand = new Random();
                newFile.write((int) Math.sqrt(500) + "\n"); // Writes the number of processors
                newFile.write("500\n"); // Writes the number of jobs
                for (int j = 1; j <= 500; j++) { // Creates the jobs 
                    newFile.write(j + " " + rand.nextInt(1, 1000) + "\n");
                }
                newFile.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}