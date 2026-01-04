import java.io.File;
import java.util.Scanner;
/*
AM:3230048,3220051
*/
public class Greedy {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            MaxPQ pq = new MaxPQ();
            try {
                String line = scanner.nextLine();//plithos epexergaston
                System.out.println(line);
                int number_of_processors = Integer.parseInt(line);

                for (int i = 0; i < number_of_processors; i++) {
                    pq.insert(new Processor());
                }

                line = scanner.nextLine();//plithos diergasion
                System.out.println(line);
                int number_of_jobs = Integer.parseInt(line);
                Job[] jobs_array=greedy_read_save(number_of_jobs,scanner);
                for (Job i:jobs_array){
                    pq.max().addJob(i);
                    pq.update();
                }
            } catch (Exception e) {
                System.out.println("Lines and number of jobs are not equal");
                
           }
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
           
            System.out.println("File not found");
        }
    }


    public static Job[] greedy_read_save(int number_of_jobs,Scanner scanner) {//diabazei kai apothikeuei se pinaka
        try {
            Job[] jobs_returned=new Job[number_of_jobs];
            for (int i = 0; i < number_of_jobs; i++) { //ftiakse job kai balto ston pinaka
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                Job nextJob = new Job(Integer.parseInt(words[0]), Integer.parseInt(words[1]));
                jobs_returned[i]=nextJob;
            }
            if (scanner.hasNextLine()) {//exoume kai alli grammi meta tp for loop ara lathos arithmos
                throw new Exception();
            }
            return jobs_returned;
        } catch (Exception e) {
            System.out.println("Lines and number of jobs are not equal");
            
        }
        return null;
    }
}
