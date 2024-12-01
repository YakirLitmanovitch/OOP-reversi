import java.util.List;

public class RandomAI extends AIPlayer{
    /**
     * Constructor to initialize a RandomAI player.
     * @param isPlayerOne Determines if the AI is player one.
     */
    public RandomAI(boolean isPlayerOne) {
        super(isPlayerOne);
    }
    /**
     * Makes a move for the AI player randomly from the valid moves.
     * The AI may choose between placing a simple disc, a bomb disc, or an unflippable disc.
     * @param gameStatus The current game logic used to determine valid moves.
     * @return The randomly selected move for the AI.
     */
    @Override
    public Move makeMove(PlayableLogic gameStatus) {
        List<Position> validMoves = gameStatus.ValidMoves();
        int randomNLocInList = (int) (Math.random() * validMoves.size());
        Player player;
        if (gameStatus.isFirstPlayerTurn())
            player = gameStatus.getFirstPlayer();
        else
            player = gameStatus.getSecondPlayer();
        int randomeNum;
        if ((player.getNumber_of_bombs() > 0) && (player.getNumber_of_bombs() > 0))
        {
            randomeNum = (int) (Math.random() * 3);
            if (randomeNum == 0)
                return new Move(validMoves.get(randomNLocInList), new SimpleDisc(player));
            if (randomeNum == 1)
                return new Move(validMoves.get(randomNLocInList), new BombDisc(player));
            return new Move(validMoves.get(randomNLocInList), new UnflippableDisc(player));
        }
            if ((player.getNumber_of_bombs() > 0))
            {
                    randomeNum = (int) (Math.random() * 2);
                    if (randomeNum == 0)
                             return new Move(validMoves.get(randomNLocInList), new SimpleDisc(player));
                    if (randomeNum == 1)
                            return new Move(validMoves.get(randomNLocInList), new BombDisc(player));
        }
        if (player.getNumber_of_unflippedable() > 0) {
            randomeNum = (int) (Math.random() * 2);
            if (randomeNum == 0)
                return new Move(validMoves.get(randomNLocInList), new SimpleDisc(player));
            if (randomeNum == 1)
                return new Move(validMoves.get(randomNLocInList), new UnflippableDisc(player));
        }
        return new Move(validMoves.get(randomNLocInList), new SimpleDisc(player));
    }

}
