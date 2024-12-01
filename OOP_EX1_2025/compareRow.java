import java.util.Comparator;
/**
 * Compares two `Position` objects based on their row values.
 * Used for sorting or ordering positions by rows.
 */
public class compareRow implements Comparator <Position> {
    /**
     * Compares two positions by their row values.
     *
     * @param o1 The first position to compare.
     * @param o2 The second position to compare.
     * @return 1 if `o1`'s row value is greater than `o2`'s row value,
     *         -1 if `o1`'s row value is less than `o2`'s row value,
     *         or 0 if both have the same row value.
     */
    @Override
    public int compare (Position o1, Position o2) {
        if (o1.row() > o2.row()) return 1;
        else if (o1.row() < o2.row()) return -1;
        else return 0;
    }
}

