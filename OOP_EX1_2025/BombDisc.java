import java.util.Objects;

/**
 * Represents a special type of disc, a BombDisc, which has unique properties.
 * When placed, it can cause adjacent discs to flip or trigger chain reactions with other BombDiscs.
 */
public class BombDisc extends Disc {

    /**
     * Constructs a BombDisc and assigns it to a specific player (owner).
     * @param o The player who owns this BombDisc.
     */
    public BombDisc(Player o) {
        this.owner = o;
    }

    /**
     * Returns the type of this disc as a bomb symbol ("💣").
     * @return A string representing the type of this disc.
     */
    @Override
    public String getType() {
        return "💣";
    }

    /**
     * Sets the explode state of this BombDisc to true, indicating it is in an active explosion state.
     */
    @Override
    public void setExplodeTrue() {
        this.explode = true;
    }

    /**
     * Sets the explode state of this BombDisc to false, indicating it is not in an active explosion state.
     */
    @Override
    public void setExplodeFalse() {
        this.explode = false;
    }

    /**
     * Checks whether this BombDisc is in an active explosion state.
     * @return True if the disc is currently exploding, otherwise false.
     */
    @Override
    public boolean getExplode() {
        return this.explode;
    }
}
