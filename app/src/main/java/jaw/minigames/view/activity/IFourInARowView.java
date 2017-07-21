package jaw.minigames.view.activity;

import jaw.minigames.view.adapter.FourInARowAdapter;

/**
 * Created by johan on 7/19/2017.
 */

public interface IFourInARowView extends IView {
    void setFourInARowAdapter(FourInARowAdapter adapter);

    void setToolbar();
}
