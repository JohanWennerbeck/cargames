package jaw.minigames.eventbus;

/**
 * Created by johan on 7/11/2017.
 */

public class OnCreateEvent {
    public final Object object;

    /**
     *
     * @param o is the current object
     */
    public OnCreateEvent(Object o) {
        this.object = o;
    }
}
