public class UnflippableDisc extends Disc {

    /**
     * Constructor for the UnflippableDisc class.
     * Sets the owner of the disc.
     * @param o The player who owns the disc.
     */
    public UnflippableDisc(Player o) {
        this.owner = o;
    }

    /**
     * Returns the type of the disc, represented by a circle.
     * @return The string "⭕", representing an unflippable disc.
     */
    @Override
    public String getType() {
        return "⭕";
    }

    /**
     * Sets the disc to be in an exploded state.
     * (Currently, sets explode to true, but no action is taken.)
     */
    @Override
    public void setExplodeTrue() {
        this.explode = true;
    }

    /**
     * Resets the disc's explode state to false.
     * (Currently, incorrectly sets explode to true, should set to false.)
     */
    @Override
    public void setExplodeFalse() {
        this.explode = true; // This should be setting explode to false, not true
    }

    /**
     * Returns the current explode state of the disc.
     * @return The current value of the explode state.
     */
    @Override
    public boolean getExplode() {
        return this.explode;
    }
}
