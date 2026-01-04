public class MaxPQ implements PQInterface {
    private Processor[] pq;   // Max priority queue holding Processors
    private int size;

    public MaxPQ() {
        this.pq = new Processor[11];
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    } 

    // Return the number of active elements in the queue
    public int size() {
        return size;
    }

    public void insert(Processor x) {
        if (size >= pq.length * 0.75)
            resize();
        pq[++size] = x;
        swim(size);
    }

    // Return root without removing
    public Processor max() {
        if (size == 0)
            return null;
        return pq[1];
    }

    // Remove and return the processor with the highest priority (lowest load)
    public Processor getmax() {
        if (size == 0)
            return null;

        Processor temp = pq[1];
        pq[1] = pq[size];
        size--;
        sink(1);
        return temp;
    }

    // Updates the position of the root element
    public void update() {
        sink(1);
    }

    // Helper: Move element up to restore heap condition
    private void swim(int i) {
        if (i == 1) return;

        int parent = i / 2;

        while (i != 1 && (pq[i].compareTo(pq[parent])) > 0) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }
    }

    // Helper: Move element down to restore heap condition
    private void sink(int i) {
        int left = 2 * i;
        int right = left + 1;

        if (left > size) return;

        while (left <= size) {
            int max = left;
            if (right <= size) {
                if (pq[left].compareTo(pq[right]) < 0)
                    max = right;
            }

            if (pq[i].compareTo(pq[max]) >= 0)
                return;
            else {
                swap(i, max);
                i = max;
                left = i * 2;
                right = left + 1;
            }
        }
    }

    private void swap(int i, int j) {
        Processor tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    // Grow the array size when capacity is nearly reached
    private void resize() {
        Processor[] newHeap = new Processor[pq.length * 2];
        for (int i = 1; i <= size; i++) {
            newHeap[i] = pq[i];
        }
        pq = newHeap;
    }
}