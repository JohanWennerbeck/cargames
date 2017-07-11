package jaw.minigames.eventbus;

import jaw.minigames.controller.IPresenter;

/**
 * Created by johan on 7/11/2017.
 */

public class RemovePresenterEvent {
    public final IPresenter presenter;

    /**
     * Constructor
     * @param presenter is the presenter that will be removed
     */
    public RemovePresenterEvent(IPresenter presenter){
        this.presenter = presenter;
    }
}
