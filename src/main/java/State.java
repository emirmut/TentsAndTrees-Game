/**
 * Current situation of the result table
 */

public class State {

    protected Piece[][] pieces; // n x n matrix of pieces

    /**
    * Constructor of State class
    */
	public State(Piece[][] pieces) { // Constructor
       this.pieces = pieces;
    }

    /**
    * gets array of piece arrays
    * @return array of piece arrays
    */
    public Piece[][] getPieces() {
        return this.pieces;
    }

    /**
    * sets array of piece arrays
    * @param pieces array of piece arrays
    */
    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }
}

