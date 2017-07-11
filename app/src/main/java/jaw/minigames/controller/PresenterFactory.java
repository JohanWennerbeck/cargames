package jaw.minigames.controller;

import android.content.Context;

import jaw.minigames.model.Model;
import jaw.minigames.view.activity.IMainView;

/**
 * Created by johan on 7/11/2017.
 */

public class PresenterFactory implements IPresenterFactory {
    private final static IPresenterFactory INSTANCE = new PresenterFactory();

    /**
     * @return singleton instance of the factory
     */
    public static IPresenterFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public DelegatingPresenter initializeDelegatingPresenter(Context context) {
        return new DelegatingPresenter(context);
    }

    @Override
    public MainPresenter createMainPresenter(IMainView mainView, Model model) {
        return new MainPresenter(mainView, model);
    }

    @Override
    public CarBingoPresenter createCarBingoPresenter(Model model) {
        return new CarBingoPresenter(model);
    }
}
