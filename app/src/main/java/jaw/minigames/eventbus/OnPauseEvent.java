package jaw.minigames.eventbus;

public class OnPauseEvent {
    public final Object object;

    /**
     *
     * @param o is the current object
     */
    public OnPauseEvent(Object o) {
        this.object = o;
    }
}
