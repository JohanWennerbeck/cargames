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
import jaw.minigames.eventbus.SaveGameEvent;
import jaw.minigames.model.Model;
import jaw.minigames.view.activity.ICarBingoView;
import jaw.minigames.view.adapter.CarBingoAdapter;
import jaw.minigames.view.adapter.ICarBingoAdapter;

/**
 * Created by johan on 7/11/2017.
 */

public class CarBingoPresenter implements IPresenter {
    private ICarBingoView carBingoView;
    private Model model;
    private ICarBingoAdapter carBingoAdapter;

    public CarBingoPresenter (Model model){
        this.model = model;
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCreate(OnCreateEvent event) {
        if (event.object instanceof ICarBingoView) {
            carBingoView = (ICarBingoView) event.object;
            carBingoView.setToolbar();
            carBingoAdapter = new CarBingoAdapter(model.getMiniGameModule().getCarBingo());
            carBingoView.setCarBingoAdapter((CarBingoAdapter) carBingoAdapter);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResume(OnResumeEvent event) {

    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onPause(OnPauseEvent event) {
        if(event.object instanceof ICarBingoView)
            EventBus.getDefault().post(new SaveGameEvent());
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStart(OnStartEvent event) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDestroy(OnDestroyEvent event) {

        if (event.object == this.carBingoView) {
            System.out.println("CarBIngo Ondestroy");
            EventBus.getDefault().unregister(this);
            collectDeadPresenter();
        }
        }

    private void collectDeadPresenter() {
        detachView();
        EventBus.getDefault().post(new RemovePresenterEvent(this));
    }

    private void detachView() {
        this.carBingoView = null;
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStop(OnStopEvent event) {

    }

    @Override
    public void injectModel(Model model) {
        this.model = model;
    }

}
