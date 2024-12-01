import java.util.Comparator;
/**
 * Comparator class for comparing two `Position` objects based on their column values.
 * This class is used to define a custom sorting order for positions.
 */

public class compareCol implements Comparator <Position> {
    /**
     * Compares two `Position` objects based on their column values.
     * @param o1 The first position to compare.
     * @param o2 The second position to compare.
     * @return 1 if the column of `o1` is greater than the column of `o2`,
     *         -1 if the column of `o1` is less than the column of `o2`,
     *         or 0 if both columns are equal.
     */
    @Override
    public int compare (Position o1, Position o2) {
        if (o1.col() > o2.col()) return 1;
        else if (o1.col() < o2.col()) return -1;
        else return 0;
    }
}
