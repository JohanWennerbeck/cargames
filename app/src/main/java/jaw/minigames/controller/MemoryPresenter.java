package jaw.minigames.controller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import jaw.minigames.eventbus.OnCreateEvent;
import jaw.minigames.model.Model;
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
    public void injectModel(Model model) {
        this.model = model;
    }
}
