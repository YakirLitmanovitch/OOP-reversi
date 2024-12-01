import java.util.Comparator;

/**
 * Comparator class for comparing two `Position` objects based on the number of flips
 * they can generate in the game logic. This is used to determine which move is more advantageous.
 */
public class compareFlips implements Comparator <Position> {
    public PlayableLogic gameLogic;
    /**
     * Assigns the current state of the game logic to this comparator.
     * @param gameStatus The game logic object used to calculate flips.
     */
    public void gamePark (PlayableLogic gameStatus){this.gameLogic=gameStatus;}
    /**
     * Compares two `Position` objects based on the number of flips they can produce.
     *
     * @param o1 The first position to compare.
     * @param o2 The second position to compare.
     * @return 1 if the flips from `o1` are greater than those from `o2`,
     *         -1 if the flips from `o1` are less than those from `o2`,
     *         or 0 if both produce the same number of flips.
     */
    @Override
    public int compare(Position o1, Position o2) {
        int com1=gameLogic.countFlips(o1);
        int com2=gameLogic.countFlips(o2);
        if (com1>com2)return 1;
        else if (com1<com2) return -1;
         else return 0;
    }


}
