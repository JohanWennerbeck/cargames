package jaw.minigames.eventbus;

public class OnDestroyEvent {
    public final Object object;

    /**
     *
     * @param o is the current object
     */
    public OnDestroyEvent(Object o) {
        this.object = o;
    }
}
