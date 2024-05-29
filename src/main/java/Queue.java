/**
 * Queue class of puzzle pieces
 */
public class Queue {
    private int first;
    private int last;
    private Token[] array;
    private int N;

    /**
     * Constructor of the Queue class
     */
    public Queue(int N) { // Constructor
        first = 0;
        last = 0;
        array = new Token[N];
        this.N = N;
    }

    /**
     * checks whether the queue is empty or not
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return first == last;
    }

    /**
     * checks whether the queue is full or not
     * @return true if the queue is full, false otherwise
     */
    public boolean isFull() {
        return (last + 1) % N == first;
    }

    /**
     * adds new token to the queue
     * @param token token to be added
     */
    public void enqueue(Token token) {
        if (!isFull()) {
            array[last] = token;
            last = (last + 1) % N;
        }
    }

    /**
     * deletes a token from the queue
     * @return deleted token
     */
    public Token dequeue() {
        if (!isEmpty()) {
            Token temp = array[first];
            first = (first + 1) % N;
            return temp;
        }
        return null;
    }
}