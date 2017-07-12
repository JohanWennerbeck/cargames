package jaw.minigames.controller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import jaw.minigames.eventbus.OnCreateEvent;
import jaw.minigames.eventbus.TileCheckedEvent;
import jaw.minigames.model.Model;
import jaw.minigames.view.activity.IMainView;
import jaw.minigames.view.adapter.IMiniGameAdapter;
import jaw.minigames.view.adapter.MiniGameAdapter;

/**
 * Created by johan on 7/4/2017.
 */

class MainPresenter extends BasePresenter implements IPresenter{
    private IMainView mainView;
    private IMiniGameAdapter miniGameAdapter;

    MainPresenter(IMainView mainView, Model model){
        this.mainView =mainView;
        this.model = model;
        miniGameAdapter = new MiniGameAdapter(mainView.getAppCompatActivity());
        EventBus.getDefault().register(this);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCreate(OnCreateEvent event) {
        if (event.object instanceof IMainView) {
            mainView = (IMainView) event.object;

            mainView.setNavDrawer();
            mainView.setToolbar();
        }
    }

    @Override
    public void injectModel(Model model) {
        this.model = model;
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onTileCheckedEvent(TileCheckedEvent event){
        this.model.getMiniGameModule().getCarBingo().onTileCheckedEvent(event.getType());
    }
}
