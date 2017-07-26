package jaw.minigames.view.activity;

import android.support.v7.widget.RecyclerView;

import jaw.minigames.model.minigamemodule.fourinarow.IFourInARow;
import jaw.minigames.view.adapter.FourInARowAdapter;

/**
 * Created by johan on 7/19/2017.
 */

public interface IFourInARowView extends IView {
    void setFourInARowAdapter(FourInARowAdapter adapter);
    void setFourInARow(IFourInARow fourInARow);
    RecyclerView getGridView();
    void setToolbar();
}
