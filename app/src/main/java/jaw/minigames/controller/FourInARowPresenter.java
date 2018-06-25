package jaw.minigames.controller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import jaw.minigames.eventbus.OnCreateEvent;
import jaw.minigames.eventbus.OnDestroyEvent;
import jaw.minigames.eventbus.OnPauseEvent;
import jaw.minigames.eventbus.OnResumeEvent;
import jaw.minigames.eventbus.OnStartEvent;
import jaw.minigames.eventbus.OnStopEvent;
import jaw.minigames.eventbus.RemovePresenterEvent;
import jaw.minigames.eventbus.RequestFourInARowEvent;
import jaw.minigames.eventbus.SaveGameEvent;
import jaw.minigames.model.Model;
import jaw.minigames.view.activity.IFourInARowView;
import jaw.minigames.view.adapter.FourInARowAdapter;
import jaw.minigames.view.adapter.IFourInARowAdapter;

/**
 * Created by johan on 7/19/2017.
 */

public class FourInARowPresenter implements IPresenter {
    private IFourInARowView fourInARowView;
    private Model model;
    private IFourInARowAdapter fourInARowAdapter;

    public FourInARowPresenter (Model model){
        this.model = model;
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCreate(OnCreateEvent event) {
        if (event.object instanceof IFourInARowView) {
            fourInARowView = (IFourInARowView) event.object;
            fourInARowView.setToolbar();
            fourInARowAdapter = new FourInARowAdapter(model.getMiniGameModule().getFourInARow());
            fourInARowView.setFourInARowAdapter((FourInARowAdapter) fourInARowAdapter);
            fourInARowView.setFourInARow(model.getMiniGameModule().getFourInARow());
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResume(OnResumeEvent event) {

    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onPause(OnPauseEvent event) {
        if(event.object instanceof IFourInARowView)
            EventBus.getDefault().post(new SaveGameEvent());
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStart(OnStartEvent event) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDestroy(OnDestroyEvent event) {
        if (event.object == this.fourInARowView) {
            System.out.println("Fourinarow Destory");
            EventBus.getDefault().unregister(this);
            collectDeadPresenter();
        }
    }

    private void collectDeadPresenter() {
        detachView();
        EventBus.getDefault().post(new RemovePresenterEvent(this));
    }

    private void detachView() {
        this.fourInARowView = null;
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStop(OnStopEvent event) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void injectModel(Model model) {
        this.model = model;
    }
}
