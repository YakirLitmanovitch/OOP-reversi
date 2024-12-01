import java.util.ArrayList;
import java.util.Comparator;
/**
 * Represents a greedy AI player in the game.
 * This AI makes moves based on maximizing immediate gain, such as flipping the maximum number of discs.
 */
public class GreedyAI extends AIPlayer{
    ArrayList <Position> posList = new ArrayList<>();
    /**
     * Constructs a GreedyAI player.
     * @param isPlayerOne Indicates if this player is Player 1 (true) or Player 2 (false).
     */
    public GreedyAI(boolean isPlayerOne) {
        super(isPlayerOne);
        this.isPlayerOne=isPlayerOne;
    }
    /**
     * Makes a move using the greedy AI strategy.
     * The AI sorts valid moves based on the number of flips they cause,
     * breaking ties by column, and then row, and selects the best move.
     *
     * @param gameStatus The current state of the game, providing logic to assess valid moves.
     * @return A {@link Move} object representing the chosen position and disc type.
     */
    @Override
    public Move makeMove(PlayableLogic gameStatus) {
        compareFlips flipComp = new compareFlips();
        compareCol colComp = new compareCol();
        compareRow rowComp = new compareRow();
        Comparator <Position> theComp = flipComp.thenComparing(colComp).thenComparing(rowComp);
        flipComp.gamePark(gameStatus);
        posList.clear();
        posList.addAll(gameStatus.ValidMoves());
        posList.sort(theComp);
        Disc d= new SimpleDisc(this);
        Position p = new Position(posList.getLast().row(),posList.getLast().col());
        Move m = new Move(p, d);
        return m;
    }
}
