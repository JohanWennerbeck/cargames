package jaw.minigames.eventbus;

public class OnStopEvent {
    public final Object object;

    /**
     *
     * @param o is the current object
     */
    public OnStopEvent(Object o) {
        this.object = o;
    }
}
