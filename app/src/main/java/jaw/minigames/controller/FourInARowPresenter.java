package jaw.minigames.controller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import jaw.minigames.eventbus.OnCreateEvent;
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
        System.out.println("Presenter");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCreate(OnCreateEvent event) {
        if (event.object instanceof IFourInARowView) {
            System.out.println("Inne i OnCreate i FOUR");
            fourInARowView = (IFourInARowView) event.object;
            fourInARowView.setToolbar();
            fourInARowAdapter = new FourInARowAdapter(model.getMiniGameModule().getFourInARow());
            fourInARowView.setFourInARowAdapter((FourInARowAdapter) fourInARowAdapter);
        }
    }

    @Override
    public void injectModel(Model model) {
        this.model = model;
    }

}
