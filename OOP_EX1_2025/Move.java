import java.util.ArrayList;
/**
 * Represents a move made in the game, consisting of a position, a disc, and the list of flipped positions.
 */
public class Move {
    private final Position p;
    private final Disc d;
    private ArrayList<Position> flip;
    /**
     * Constructor to create a move with only the position and disc.
     * @param p The position where the disc is placed.
     * @param d The disc placed at the position.
     */
    public Move(Position p, Disc d) {
        this.p = p;
        this.d = d;
    }
    /**
     * Constructor to create a move with the position, disc, and a list of flipped positions.
     * @param p The position where the disc is placed.
     * @param d The disc placed at the position.
     * @param newFlip The list of positions flipped as a result of the move.
     */
    public Move(Position p, Disc d,ArrayList<Position> newFlip) {
        this.p = p;
        this.d = d;
        flip = new ArrayList<>();
        this.flip.addAll(newFlip);
    }
    /**
     * Retrieves the list of flipped positions caused by this move.
     * @return The list of flipped positions.
     */
    public ArrayList <Position> arrayList (){return flip;}
    /**
     * Retrieves the position where the disc is placed.
     * @return The position of the move.
     */
    public Position position() {
        return p;
    }
    /**
     * Retrieves the disc placed at the position.
     * @return The disc of the move.
     */
    public Disc disc() {
        return d;
    }
}
