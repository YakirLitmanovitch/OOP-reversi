import java.util.Objects;

public class Position {
    private final int row;
    private final int col;

    /**
     * Constructor to create a position object with specified row and column.
     *
     * @param row The row of the position.
     * @param col The column of the position.
     */
    public Position(int row, int col) {
        this.col = col;
        this.row = row;
    }

    /**
     * Retrieves the column of the position.
     *
     * @return The column of the position.
     */
    public int col() {
        return col;
    }

    /**
     * Retrieves the row of the position.
     *
     * @return The row of the position.
     */
    public int row() {
        return row;
    }

    /**
     * Checks if this position is equal to another position.
     *
     * @param p The object to compare to.
     * @return true if the positions are equal (same row and column), otherwise false.
     */
    @Override
    public boolean equals(Object p) {
        // Check if the object is the same reference
        if (this == p) return true;

        // Check if the object is of type Position and not null
        if (p == null || getClass() != p.getClass()) return false;

        // Cast the object to Position and compare row and col values
        Position pos = (Position) p;
        return this.col == pos.col() && this.row == pos.row();
    }
}
