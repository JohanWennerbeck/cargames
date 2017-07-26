package jaw.minigames.eventbus;

import jaw.minigames.view.adapter.IFourInARowAdapter;

/**
 * Created by johan on 7/24/2017.
 */

public class RequestFourInARowEvent {
    public final IFourInARowAdapter adapter;

    public RequestFourInARowEvent(IFourInARowAdapter adapter){
        this.adapter = adapter;
    }
}
