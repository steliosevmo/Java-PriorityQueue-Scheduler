import java.io.File;
import java.util.Scanner;
/*
AM:3230048,3220051
*/
public class Sort {

    //swaps two items
    private static <T extends Comparable<T>> void swap(T[] array, int firstIndex, int secondIndex) {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    // dixotomi ton pinika me basi to stoixeio pou pairnei apo to pivotIndex
    public static <T extends Comparable<T>> int pivot(T[] array, int pivotIndex, int endIndex) {
        int swapIndex = pivotIndex;
        for (int i = pivotIndex + 1; i <= endIndex; i++) {
            if (array[i].compareTo(array[pivotIndex]) > 0) { //sigkrinoume to pivotndex me to i
                swapIndex++;                                 //kai an einai megalitero to allazei me to swapindex
                swap(array, swapIndex, i);
            }
        }
        swap(array, pivotIndex, swapIndex);        //topothetoume stin sosti thesi to stoixeio kai epistrefoume tin thesi tou oste na mporespume na taxinomisoume ta kommatia pou apomenoun
        return swapIndex;
    }

    // boithitiki methodos gia anadromiki klisi 
    private static <T extends Comparable<T>> void quickSortHelper(T[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = pivot(array, left, right);
            quickSortHelper(array, left, pivotIndex - 1);
            quickSortHelper(array, pivotIndex + 1, right);
        }
    }

    // public methodos diathesimi gia ton xristi
    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            MaxPQ pq = new MaxPQ();
            String line=scanner.nextLine();//plithos epexergaston
            int number_of_processors = Integer.parseInt(line);

            for (int i = 0; i < number_of_processors; i++) {
                pq.insert(new Processor());
            }
            line = scanner.nextLine();                            //plithos diergasion

            int number_of_jobs = Integer.parseInt(line);
            Job[] jobs= greedy_decreasing_read_save(number_of_jobs,scanner);
            quickSort(jobs);
            for(Job x:jobs){
                pq.max().addJob(x);
                pq.update();
            }
            while (!pq.isEmpty()){
                Processor currentProcessor = pq.getmax();

                if (currentProcessor != null) {
                    if(pq.size()==0){
                        System.out.println("id "+currentProcessor.getId() + ", load=" + currentProcessor.getTotalProcessingTime()+" "+currentProcessor.printoutput());
                        System.out.println("Makespan = "+currentProcessor.getTotalProcessingTime());
                    }else {
                        System.out.println("id " + currentProcessor.getId() + ", load=" + currentProcessor.getTotalProcessingTime()+" " + currentProcessor.printoutput());
                    }
                }
            }


        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static Job[] greedy_decreasing_read_save(int number_of_jobs,Scanner scanner){
        Job[] jobs= new Job[number_of_jobs];
        try{
            for (int i = 0; i < number_of_jobs; i++) { //ftiakse job gia kathe epomeni grammi kai balto ston sosto epexergasti
                String line =scanner.nextLine();
                String[] words = line.split(" ");
                Job nextJob = new Job(Integer.parseInt(words[0]), Integer.parseInt(words[1]));
                jobs[i]=nextJob;
            }
            if (scanner.hasNextLine()) {//exoume kai alli grammi meta tp for loop ara lathos arithmos
                throw new Exception();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return jobs;
    }
}
