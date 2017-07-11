package jaw.minigames.controller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import jaw.minigames.eventbus.OnCreateEvent;
import jaw.minigames.eventbus.TileCheckedEvent;
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
    public void injectModel(Model model) {
        this.model = model;
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onTileCheckedEvent(TileCheckedEvent event){
        this.model.getMiniGameModule().getCarBingo().onTileCheckedEvent(event.getType());
    }

}
