package jaw.minigames.eventbus;

public class OnResumeEvent {
    public final Object object;

    /**
     *
     * @param o is the current object
     */
    public OnResumeEvent(Object o) {
        this.object = o;
    }
}
