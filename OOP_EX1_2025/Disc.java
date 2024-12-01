import java.util.Objects;

/**
 * The Disc interface defines the characteristics of a game in a chess-like game.
 * Implementing classes should provide information about the player who owns the Disc.
 */
public abstract class Disc {
    protected Player owner;
    protected String type;
    protected boolean explode=false;



    /**
     * Get the player who owns the Disc.
     *
     * @return The player who is the owner of this game disc.
     */
   public Player getOwner()
   {
        return this.owner;
    }

    /**
     * Set the player who owns the Disc.
     *
     */
   public void setOwner(Player player){
       this.owner=player;
   }

    /**
     * Get the type of the disc.
     * use the:
     *          "⬤",         "⭕"                "💣"
     *      Simple Disc | Unflippedable Disc | Bomb Disc |
     * respectively.
     */

    abstract String getType();

    /**
     * Marks the disc as "exploded."
     * This method is used for bomb discs to indicate that they have exploded.
     */
    abstract void  setExplodeTrue();
    /**
     * Marks the disc as "not exploded."
     * This method is used to reset the explosion state of a bomb disc.
     */
    abstract void  setExplodeFalse();
    /**
     * Checks whether the disc is currently in an "exploded" state.
     * @return `true` if the disc has exploded, `false` otherwise.
     */
    abstract boolean  getExplode();

}