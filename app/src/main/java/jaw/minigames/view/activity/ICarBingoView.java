package jaw.minigames.view.activity;

import jaw.minigames.view.adapter.CarBingoAdapter;

/**
 * Created by johan on 7/11/2017.
 */

public interface ICarBingoView extends IView {
    void setCarBingoAdapter(CarBingoAdapter adapter);

    void setToolbar();
}
