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
import jaw.minigames.view.activity.IMemoryView;
import jaw.minigames.view.adapter.IMemoryAdapter;
import jaw.minigames.view.adapter.MemoryAdapter;

public class MemoryPresenter implements IPresenter {
    private IMemoryView memoryView;
    private Model model;
    private IMemoryAdapter memoryAdapter;

    public MemoryPresenter(Model model) {
        this.model = model;
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCreate(OnCreateEvent event) {
        if (event.object instanceof IMemoryView) {
            memoryView = (IMemoryView) event.object;
            memoryView.setToolbar();
            memoryAdapter = new MemoryAdapter(model.getMiniGameModule().getMemory());
            memoryView.setMemoryAdapter((MemoryAdapter) memoryAdapter);
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

        if (event.object == this.memoryView) {
            System.out.println("Memory onDESTROY");
            EventBus.getDefault().unregister(this);
            collectDeadPresenter();
        }
    }

    private void collectDeadPresenter() {
        detachView();
        EventBus.getDefault().post(new RemovePresenterEvent(this));
    }

    private void detachView() {
        this.memoryView = null;
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
