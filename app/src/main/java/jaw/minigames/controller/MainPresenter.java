package jaw.minigames.controller;

import android.content.Intent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import jaw.minigames.eventbus.CarBingoNewGameEvent;
import jaw.minigames.eventbus.FourInARowNewGameEvent;
import jaw.minigames.eventbus.MemoryNewGameEvent;
import jaw.minigames.eventbus.OnCreateEvent;
import jaw.minigames.eventbus.OnDestroyEvent;
import jaw.minigames.eventbus.OnPauseEvent;
import jaw.minigames.eventbus.OnResumeEvent;
import jaw.minigames.eventbus.OnStartEvent;
import jaw.minigames.eventbus.OnStopEvent;
import jaw.minigames.eventbus.RemovePresenterEvent;
import jaw.minigames.eventbus.SaveGameEvent;
import jaw.minigames.eventbus.ShowCarBingoEvent;
import jaw.minigames.eventbus.ShowFourInARowEvent;
import jaw.minigames.eventbus.ShowMemoryEvent;
import jaw.minigames.eventbus.TileCheckedEvent;
import jaw.minigames.eventbus.TileTappedEvent;
import jaw.minigames.eventbus.MemoryTileTappedEvent;
import jaw.minigames.model.Model;
import jaw.minigames.view.activity.CarBingoActivity;
import jaw.minigames.view.activity.FourInARowActivity;
import jaw.minigames.view.activity.IMainView;
import jaw.minigames.view.activity.MemoryActivity;
import jaw.minigames.view.adapter.IMiniGameAdapter;
import jaw.minigames.view.adapter.MemoryAdapter;
import jaw.minigames.view.adapter.MiniGameAdapter;

/**
 * Created by johan on 7/4/2017.
 */

class MainPresenter extends BasePresenter implements IPresenter{
    private IMainView mainView;
    private IMiniGameAdapter miniGameAdapter;

    MainPresenter(IMainView mainView, Model model){
        this.mainView = mainView;
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPause(OnPauseEvent event) {
        if (event.object == mainView) {
            //taskAdapter.refreshItems(mainView.getCurrentCategory());

            EventBus.getDefault().post(new SaveGameEvent());
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResume(OnResumeEvent event) {
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDestroy(OnDestroyEvent event) {
        System.out.println("UTANFör");
        if (event.object == mainView) {
            System.out.println("Den kom in här");
            EventBus.getDefault().unregister(this);
            collectDeadPresenter();
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStop(OnStopEvent event) {
        if (event.object == mainView) {
            registerViewComponents(false);
//            System.out.println("Unregister");
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStart(OnStartEvent event) {
        if (event.object == mainView) {
            registerViewComponents(true);
//            System.out.println("Register");
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

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onTileTappedEveent(TileTappedEvent event){
        this.model.getMiniGameModule().getFourInARow().onTileTappedEvent(event.getNumber());
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onMemoryTileTappedEveent(MemoryTileTappedEvent event) {
        this.model.getMiniGameModule().getMemory().onMemoryTileTappedEvent(event.getNumber());
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onCarBingoNewGameEvent (CarBingoNewGameEvent event) {
        model.getMiniGameModule().getCarBingo().newGame();
        EventBus.getDefault().post(new ShowCarBingoEvent());
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onFourInARowGameEvent (FourInARowNewGameEvent event) {
        model.getMiniGameModule().getFourInARow().newGame();
        EventBus.getDefault().post(new ShowFourInARowEvent());
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onMemoryNewGameEvent (MemoryNewGameEvent event) {
        model.getMiniGameModule().getMemory().newGame();
        EventBus.getDefault().post(new ShowMemoryEvent());
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onShowCarBingoEvent(ShowCarBingoEvent event){
        Intent intent = new Intent(mainView.getAppCompatActivity(), CarBingoActivity.class);
        mainView.getAppCompatActivity().startActivity(intent);
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onShowFourInARowEvent(ShowFourInARowEvent event){
        Intent intent = new Intent(mainView.getAppCompatActivity(), FourInARowActivity.class);
        mainView.getAppCompatActivity().startActivity(intent);
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onShowMemoryEvent(ShowMemoryEvent event){
        Intent intent = new Intent(mainView.getAppCompatActivity(), MemoryActivity.class);
        mainView.getAppCompatActivity().startActivity(intent);
    }

    private void registerViewComponents(boolean value) {
       /* if (value) {
            EventBus.getDefault().register();
            EventBus.getDefault().register();
        } else {
            EventBus.getDefault().unregister(mainView);
            EventBus.getDefault().unregister(miniGameAdapter);
        }*/
    }

    private void collectDeadPresenter() {
        detachView();
        EventBus.getDefault().post(new RemovePresenterEvent(this));
    }

    private void detachView() {
        this.mainView = null;
    }
}
