package jaw.minigames.eventbus;

/**
 * Created by johan on 7/11/2017.
 */

public class RequestPresenterEvent {
    public final Object data;

    /**
     * Constructor
     * @param data is the current data
     */
    public RequestPresenterEvent(Object data){
        this.data = data;
    }

}
