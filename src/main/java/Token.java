/**
 * Token class represents X, O and T.
 */

public class Token {

    private State data; // can be tent, tree, or empty

    /**
     * Constructor of Token class
     * @param data argument which can be tent, tree, or empty
     */
    public Token(State data) { // Constructor
        this.data = data;
    }

    /**
     * gets data of the state
     * @return data of the state
     */
    public State getData() { // getter
        return data;
    }
}