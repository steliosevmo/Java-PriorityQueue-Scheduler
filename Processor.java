public class Processor implements Comparable<Processor> {
    private int id;
    private int makespan;
    private static int counter = 1;
    // Linked list containing the jobs executed by the processor
    protected LinkedList processedJobs = new LinkedList();

    public Processor() {
        this.id = counter;
        counter++;
    }

    // Adds a job to the end of the linked list
    public void addJob(Job i) {
        processedJobs.insertAtBack(i);
        getTotalProcessingTime();
    }

    // Returns a string representation of job times for output purposes
    public String printoutput() {
        Node temp = processedJobs.head;
        StringBuilder return_string = new StringBuilder();
        while (temp != null) {
            return_string.append(temp.getJob().getTime());
            if (temp.next != null) {  
                return_string.append(" ");
            }
            temp = temp.next;
        }
        return return_string.toString();
    }

    // Calculates the total processing time (load) assigned to the processor
    public int getTotalProcessingTime() {
        if (processedJobs.isEmpty()) {
            return 0;
        } else {
            int totalProcessedTime = 0;
            Node temp = processedJobs.head;
            while (temp != null) {
                totalProcessedTime += temp.getJob().getTime();
                temp = temp.next;
            }
            makespan = totalProcessedTime;
            return makespan;
        }
    }

    /**
     * Compares the makespan of two processors and returns an integer based on priority.
     * The processor with the smaller makespan has higher priority.
     */
    @Override
    public int compareTo(Processor processor2) {
        if (makespan == processor2.makespan) {
            if (id < processor2.id) {
                return 1;
            } else {
                return -1;
            }
        } else if (makespan > processor2.makespan) {
            return -1;
        } else {
            return 1;
        }
    }
    
    public int getId() {
        return id;
    }

    // Node class for the Jobs linked list
    class Node {
        protected Node next;
        protected Job job;
        public Node(Job data) {
            job = data;
        }
        public Job getJob() {
            return job;
        }
        public void setNext(Node next) {
            this.next = next;
        }
        public Node getNext() {
            return next;
        }
    }

    // Internal Linked List implementation
    class LinkedList {
        private Node head = null;
        private Node tail = null;
        
        public LinkedList() {
        }
        
        public boolean isEmpty() {
            return head == null;
        }

        public void insertAtFront(Job data) {
            Node n = new Node(data);

            if (isEmpty()) {
                head = n;
                tail = n;
            } else {
                n.setNext(head);
                head = n;
            }
        }

        public void insertAtBack(Job data) {
            Node n = new Node(data);

            if (isEmpty()) {
                head = n;
                tail = n;
            } else {
                tail.setNext(n);
                tail = n;
            }
        }

        public Job removeFromFront() throws Exception {
            if (isEmpty())
                throw new Exception();

            Job data = head.getJob();

            if (head == tail)
                head = tail = null;
            else
                head = head.getNext();

            return data;
        }

        public Job removeFromBack() throws Exception {
            if (isEmpty())
                throw new Exception();

            Job data = tail.getJob();

            if (head == tail)
                head = tail = null;
            else {
                Node iterator = head;
                while (iterator.getNext() != tail)
                    iterator = iterator.getNext();

                iterator.setNext(null);
                tail = iterator;
            }

            return data;
        }
        
        public String toString() {
            if (isEmpty()) {
                return "List is empty :(";
            }

            Node current = head;
            StringBuilder ret = new StringBuilder();

            // Output current node's data until end of list
            ret.append(" HEAD -> ");

            while (current != null) {
                ret.append(current.job);

                if (current.getNext() != null)
                    ret.append(" -> ");

                current = current.next;
            }

            ret.append(" <- TAIL");

            return ret.toString();
        }
    }
}