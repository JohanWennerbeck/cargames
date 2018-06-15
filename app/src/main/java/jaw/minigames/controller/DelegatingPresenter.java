package jaw.minigames.controller;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import jaw.minigames.eventbus.RemovePresenterEvent;
import jaw.minigames.eventbus.RequestPresenterEvent;
import jaw.minigames.model.Model;
import jaw.minigames.view.activity.ICarBingoView;
import jaw.minigames.view.activity.IFourInARowView;
import jaw.minigames.view.activity.IMainView;
import jaw.minigames.view.activity.IMemoryView;

/**
 * Created by johan on 7/11/2017.
 */

public class DelegatingPresenter {
    private Model model;
    private List<IPresenter> presenters;
    private Context mContext;
    private IPresenterFactory factory = PresenterFactory.getInstance();

    DelegatingPresenter(Context context){
        this.model = new Model();
        presenters = new ArrayList<>();
        this.mContext = context;

        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void RemoveDeadPresenter(RemovePresenterEvent event) {
        presenters.remove(event.presenter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPresenterRequest(RequestPresenterEvent event) {
        IPresenter presenter = null;
        if (event.data == mContext) {
            presenter = factory.createMainPresenter((IMainView) mContext, model);

        }else if (event.data instanceof IFourInARowView) {
            System.out.println("Delegating");
            presenter = factory.createFourInARowPresenter(model);
        } else if (event.data instanceof ICarBingoView) {
            System.out.println("DelCAR");
            presenter = factory.createCarBingoPresenter(model);
        } else if (event.data instanceof IMemoryView) {
            presenter = factory.createMemoryPresenter(model);
        }
        presenters.add(presenter);
    }
}
