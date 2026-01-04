/*
AM:3230048,3220051
*/

public class Processor implements Comparable<Processor> {
    private int id;
    private int makespan;
    private static int counter=1;
    protected LinkedList processedJobs=new LinkedList();//linked list me ta jobs pou exei ektelesei o processor

    public Processor() {
        this.id = counter;
        counter++;
    }
    //prosthetei job sto telos tis LL
    public void addJob(Job i){
        processedJobs.insertAtBack(i);
        getTotalProcessingTime();
    }
    //emfanizei to output pou xreiazomaste gia ta jobs dipla apo to load
    public String printoutput(){
        Node temp=processedJobs.head;
        StringBuilder return_string=new StringBuilder();
        while (temp != null) {
            return_string.append(temp.getJob().getTime());
            if (temp.next != null) {  
                return_string.append(" ");
            }
            temp = temp.next;
        }
        return return_string.toString();
    }
    //ypologizi ton sinoliko xrono pou exei trexei o epexergastis
    public int getTotalProcessingTime(){
        if (processedJobs.isEmpty()){
            return 0;
        }
        else {
            int totalProcessedTime=0;
            Node temp=processedJobs.head;
            while(temp!=null){
                totalProcessedTime+= temp.getJob().getTime();
                temp=temp.next;
            }
            makespan=totalProcessedTime;
            return makespan;
        }
    }

    //sygkrinei to makespan ton processor kai epistrefei int analoga me to poios exei megaliteri proteraiotita(auton me to mikrotero makespan)
    @Override
    public int compareTo(Processor proccesor2){
        if(makespan==proccesor2.makespan){
            if (id<proccesor2.id){
                return 1;
            }else {
                return -1;
            }
        }
        else if(makespan>proccesor2.makespan){
            return -1;
        }
        else{
            return 1;
        }
    }
    
    public int getId() {
        return id;
    }

    //Nodes gia Jobs
    class Node{
        protected Node next;
        protected Job job;
        public Node(Job data) {
            job=data;
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

    class LinkedList{
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

                // while not at end of list, output current node's data
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