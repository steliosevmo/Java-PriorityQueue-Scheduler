public interface PQInterface
{
        boolean isEmpty(); //check if the queue is empty
        int size(); //return the number of active elements in the queue
        void insert(Processor x); // insert the object x to the queue
        Processor max(); /*return without removing the object with
the highority */
        Processor getmax();/* remove and return the object
with the highest priority*/
}
