package jaw.minigames.controller;

import android.content.Context;

import jaw.minigames.model.Model;
import jaw.minigames.view.activity.IMainView;

/**
 * Created by johan on 7/11/2017.
 */

public interface IPresenterFactory {
    MainPresenter createMainPresenter(IMainView mainView, Model model);
    CarBingoPresenter createCarBingoPresenter(Model model);
    FourInARowPresenter createFourInARowPresenter(Model model);
    DelegatingPresenter initializeDelegatingPresenter(Context context);
}
