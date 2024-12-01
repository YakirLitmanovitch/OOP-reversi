import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class GameLogic implements PlayableLogic {
    private Player player1, player2;
    private int turn = 0;
    private Disc[][] board = new Disc[getBoardSize()][getBoardSize()];
    private static final int[][] sides = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
            {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };
    private final ArrayList<Move> moves = new ArrayList<>();
    private ArrayList<Position> flip = new ArrayList<>();
    private final ArrayList<Position> theBompFlip = new ArrayList<>();
    private Player currentPlayer;

    /**
     * Places a disc on the board at the specified position if the move is valid.
     * Ensures that the position is empty and that the move flips at least one opponent disc.
     * Handles special logic for unflippable and bomb discs.
     * Updates the board state, flips the necessary discs, and records the move.
     * @param position The position where the disc is to be placed.
     * @param disc The disc to place.
     * @return True if the move is valid and successfully executed, otherwise false.
     */
    @Override
    public boolean locate_disc(Position position, Disc disc) {
        if (board[position.row()][position.col()] == null && (countFlips(position) > 0)) {

            if ((disc.getType().equals("⭕")) &&
                    currentPlayer.getNumber_of_unflippedable() > 0) {
                board[position.row()][position.col()] = new UnflippableDisc(currentPlayer);
                currentPlayer.reduce_unflippedable();
            } else if ((disc.getType().equals("💣")) &&
                    currentPlayer.getNumber_of_bombs() > 0) {
                board[position.row()][position.col()] = new BombDisc(currentPlayer);
                currentPlayer.reduce_bomb();
            } else {
                board[position.row()][position.col()] = new SimpleDisc(currentPlayer);

            }
            if (currentPlayer.isPlayerOne) {
                System.out.println("\nPlayer 1 placed a " + disc.getType() +
                        " in (" + position.row() + ", " + position.col() + ")");
                for (Position position1 : flip) {
                    board[position1.row()][position1.col()].setOwner(currentPlayer);
                    System.out.println("Player 1 flipped the " + board[position1.row()][position1.col()].getType() +
                            " in (" + position1.row() + ", " + position1.col() + ")");
                }
            }
            if (!currentPlayer.isPlayerOne) {
                System.out.println("\nPlayer 2 placed a " + disc.getType() +
                        " in (" + position.row() + ", " + position.col() + ")");
                for (Position position1 : flip) {
                    board[position1.row()][position1.col()].setOwner(currentPlayer);
                    System.out.println("Player 2 flipped the " + board[position1.row()][position1.col()].getType() +
                            " in (" + position1.row() + ", " + position1.col() + ")");
                }

            }
            Move y = new Move(position, disc, flip);
            moves.add(y);
            turn++;
            return true;
        }
        return false;


    }
    /**
     * Returns the disc located at the specified position on the board.
     * If the position is invalid or the cell is empty, returns null.
     * @param position The position on the board.
     * @return The disc at the given position or null if the position is empty or invalid.
     */
    @Override
    public Disc getDiscAtPosition(Position position) {
        if (isValidPosition(position.row(), position.col()))
            return board[position.row()][position.col()];
        return null;
    }
    /**
     * Returns the size of the game board, which is fixed at 8x8.
     * @return The size of the board (always 8).
     */
    @Override
    public int getBoardSize() {
        return 8;
    }
    /**
     * Computes and returns all valid moves for the current player.
     * A move is considered valid if it is on an empty cell and flips at least one opponent disc.
     * @return A list of positions representing valid moves for the current player.
     */
    @Override
    public List<Position> ValidMoves() {
        List<Position> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    if (countFlips(new Position(i, j)) > 0)
                        list.add(new Position(i, j));
                }
            }
        }
        return list;
    }
    /**
     * Checks if a given position is within the valid bounds of the board.
     * @param row The row index.
     * @param col The column index.
     * @return True if the position is within the board boundaries, otherwise false.
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
    /**
     * Determines the number of opponent discs that would be flipped if a disc is placed at the given position.
     * Populates a list with the positions of discs to be flipped.
     * @param position The position being evaluated for a move.
     * @return The number of discs that would be flipped by this move.
     */
    @Override
    public int countFlips(Position position) {
        flip.clear();
        for (int[] side : sides) {
            ArrayList<Position> tempFlip = new ArrayList<>();
            int i = position.row() + side[0];
            int j = position.col() + side[1];
            while (isValidPosition(i, j) && board[i][j] != null) {
                if (board[i][j].getOwner().isPlayerOne == isFirstPlayerTurn()) {
                    if (!tempFlip.isEmpty()) {
                        for (Position temp : tempFlip)
                            flip.add(new Position(temp.row(), temp.col()));

                    }
                    break;
                }
                if (board[i][j].getOwner().isPlayerOne != isFirstPlayerTurn()
                        && !Objects.equals(board[i][j].getType(), "⭕")) {
                    Position libi = new Position(i, j);
                    if (board[i][j].getType().equals("💣")) {
                        board[i][j].setExplodeTrue();
                        flipBomb(libi);
                        board[i][j].setExplodeFalse();
                        for (Position temp1 : theBompFlip) {
                            if (!tempFlip.contains(temp1))
                                tempFlip.add(new Position(temp1.row(), temp1.col()));
                        }
                        theBompFlip.clear();
                    }
                    if (!tempFlip.contains(libi))
                        tempFlip.add(libi);
                }
                i += side[0];
                j += side[1];
            }
        }
        flip= (ArrayList<Position>) flip.stream().distinct().collect(Collectors.toList());
        return flip.size();
    }
    /**
     * Handles the flipping of discs affected by the explosion of a bomb disc.
     * Flips all adjacent discs and manages chain reactions caused by other bombs in the vicinity.
     * @param pb The position of the exploding bomb disc.
     */
    public void flipBomb(Position pb) {
        for (int[] side : sides) {
            int i = pb.row() + side[0];
            int j = pb.col() + side[1];
            if (isValidPosition(i, j) && board[i][j] != null
                    && board[i][j].getOwner() != currentPlayer
                    && (!Objects.equals(board[i][j].getType(), "⭕"))) {
                if ((board[i][j].getType().equals("💣")) && (!board[i][j].getExplode())) {
                    board[i][j].setExplodeTrue();
                    if (!theBompFlip.contains(new Position(i, j)))
                        theBompFlip.add(new Position(i, j));
                    flipBomb(new Position(i, j));
                    board[i][j].setExplodeFalse();
                } else {
                    if (!theBompFlip.contains(new Position(i, j)))
                        theBompFlip.add(new Position(i, j));
                }
            }
        }
    }
    /**
     * Retrieves Player 1 (the first player).
     * @return The first player.
     */
    @Override
    public Player getFirstPlayer() {
        return player1;
    }
    /**
     * Retrieves Player 2 (the second player).
     * @return The second player.
     */
    @Override
    public Player getSecondPlayer() {
        return player2;
    }
    /**
     * Sets the players for the game.
     * Assigns the first and second players to their respective roles.
     * @param player1 The first player.
     * @param player2 The second player.
     */
    @Override
    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    /**
     * Determines if it is currently the first player's turn.
     * Alternates between players based on the current turn number.
     * @return True if it is Player 1's turn, otherwise false.
     */

    @Override
    public boolean isFirstPlayerTurn() {
        if (turn % 2 == 0)
            currentPlayer = player1;
        else currentPlayer = player2;
        return turn % 2 == 0;
    }
    /**
     * Checks if the game has ended by verifying if there are no valid moves left for either player.
     * Declares the winner or a draw based on the number of discs owned by each player.
     * @return True if the game is finished, otherwise false.
     */
    @Override
    public boolean isGameFinished() {
        if (ValidMoves().isEmpty()) {
            int p1NumOfDiscs = 0;
            int p2NumOfDiscs = 0;
            for (Disc[] discs : board) {
                for (Disc disc : discs) {
                    if (disc != null) {
                        if (disc.getOwner() == player1)
                            p1NumOfDiscs++;
                        if (disc.getOwner() == player2)
                            p2NumOfDiscs++;
                    }
                }
            }
            if (p1NumOfDiscs > p2NumOfDiscs) {
                System.out.println("\nPlayer 1 wins with " + p1NumOfDiscs +
                        " discs! Player 2 had " + p2NumOfDiscs + " discs.");
                player1.addWin();
            }
            if (p1NumOfDiscs < p2NumOfDiscs) {
                System.out.println("\nPlayer 2 wins with " + p2NumOfDiscs +
                        " discs! Player 1 had " + p1NumOfDiscs + " discs.");
                player2.addWin();
            }
            if (p1NumOfDiscs == p2NumOfDiscs) System.out.println("\nIts a draw");
        }
        return ValidMoves().isEmpty();
    }
    /**
     * Resets the game to its initial state.
     * Clears the board, resets the turn counter, and reinitializes player stats and the starting discs.
     */
    @Override
    public void reset() {
        //stack.isEmpty();
        this.board = new Disc[getBoardSize()][getBoardSize()];
        turn = 0;
        board[3][3] = new SimpleDisc(player1);
        board[4][4] = new SimpleDisc(player1);
        board[3][4] = new SimpleDisc(player2);
        board[4][3] = new SimpleDisc(player2);
        player1.reset_bombs_and_unflippedable();
        player2.reset_bombs_and_unflippedable();
        if (!moves.isEmpty()) {
            moves.subList(0, moves.size()).clear();
        }
    }
    /**
     * Undoes the last move made in the game.
     * Removes the last placed disc, flips affected discs back to their previous state,
     * and restores any special disc counts (bombs or unflippable discs).
     */
    @Override
    public void undoLastMove() {
        if (turn == 0)
            System.out.println("\tNo previous move available to undo .");
        if (turn > 0) {
            System.out.println("\nUndoing last move :");

            System.out.println("\tUndo: removing " +
                    board[moves.getLast().position().row()][moves.getLast().position().col()].getType() +
                    " from (" + moves.getLast().position().row() + ", " + moves.getLast().position().col() + ")");
            board[moves.getLast().position().row()][moves.getLast().position().col()] = null;
            ArrayList<Position> mofl = moves.getLast().arrayList();
            for (Position pov : mofl) {
                board[pov.row()][pov.col()].setOwner(currentPlayer);
                System.out.println("\tflipping back " + board[pov.row()][pov.col()].getType()
                        + " in (" + pov.row() + ", " + pov.col() + ")");
            }
            if (!moves.isEmpty()) {
                if (Objects.equals(moves.getLast().disc().getType(), "💣")) {
                    if (currentPlayer.isPlayerOne())
                        player2.number_of_bombs++;
                    else
                        player1.number_of_bombs++;
                }
                if (Objects.equals(moves.getLast().disc().getType(), "⭕")) {
                    if (currentPlayer.isPlayerOne())
                        player2.number_of_unflippedable++;
                    else
                        player1.number_of_unflippedable++;
                }

                moves.removeLast();
            }
            turn--;
        }

    }
}