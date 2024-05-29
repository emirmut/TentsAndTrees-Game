/**
 * abstract piece class for O, X and T
 */

import java.util.ArrayList;

public class Piece {
    char type; // 0, X or T
    ArrayList<String> legalTents; // legal location of tents for trees (possibleTents)

    /**
     * Constructor of the abstract Piece class
     */
    public Piece(char type) { // Constructor
        this.type = type;
        this.legalTents = new ArrayList<String>();
    }

    /**
     * @return gets type of the piece
     */
    public char getType() {  // getter
        return type;
    }

    /**
     * sets type of the piece
     * @param type to set
     */
    public void setType(char type) { // setter
        this.type = type;
    }

    /**
     * @return gets location of legal tents for trees
     */
    public ArrayList<String> getLegalTents() {
        return legalTents;
    }

    /**
     * sets legal tents for trees
     * @param legalTents tents to set
     */
    public void setLegalTents(ArrayList<String> legalTents) {
        this.legalTents = legalTents;
    }
}