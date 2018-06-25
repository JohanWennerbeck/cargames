package jaw.minigames.eventbus;

public class OnStartEvent {
    public final Object object;

    /**
     *
     * @param o is the current object
     */
    public OnStartEvent(Object o) {
        this.object = o;
    }
}
