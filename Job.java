public class Job implements Comparable<Job> {
    private int id;
    private int time;

    // Job constructor 
    public Job(int id, int time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    // Compare the processing time of two jobs
    @Override
    public int compareTo(Job job2) {
        if (getTime() == job2.getTime()) {
            // If times are equal, job with smaller ID has higher priority
            if (id < job2.id) {
                return 1;
            } else {
                return -1;
            }
        } else if (getTime() > job2.getTime()) {
            return 1;
        } else {
            return -1;
        }
    }
}