package jaw.minigames.controller;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import jaw.minigames.eventbus.RemovePresenterEvent;
import jaw.minigames.eventbus.RequestPresenterEvent;
import jaw.minigames.eventbus.SaveGameEvent;
import jaw.minigames.model.CarBingoConverter;
import jaw.minigames.model.FourInARowConverter;
import jaw.minigames.model.MemoryConverter;
import jaw.minigames.model.Model;
import jaw.minigames.model.minigamemodule.carbingogame.ICarBingoTile;
import jaw.minigames.model.minigamemodule.fourinarow.IFourInARowTile;
import jaw.minigames.model.minigamemodule.memory.IMemoryTile;
import jaw.minigames.util.StorageUtil;
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

        //Load all games
        loadCarBingo();
        loadMemory();
        loadFourInARow();
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
            presenter = factory.createFourInARowPresenter(model);
        } else if (event.data instanceof ICarBingoView) {
            presenter = factory.createCarBingoPresenter(model);
        } else if (event.data instanceof IMemoryView) {
            presenter = factory.createMemoryPresenter(model);
        }
        if(presenter != null) {
            presenters.add(presenter);
            System.out.println("Presenters size is " + presenters.size());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSaveGameEvent(SaveGameEvent event) {
        saveCarBingo();
        saveMemory();
        saveFourInARow();
    }


    private void saveCarBingo(){
        JsonArray array = CarBingoConverter.getInstance().toJson(model.getMiniGameModule().getCarBingo().getCarBingoTiles());
        StorageUtil.save(mContext.getApplicationContext(), "CarBingo", array);
    }

    private void loadCarBingo(){
        JsonElement element = null;
        try {
            element = StorageUtil.load(mContext.getApplicationContext(), "CarBingo");
        } catch (IllegalStateException e) {
            StorageUtil.resetData(mContext.getApplicationContext(), "CarBingo");

        } catch (FileNotFoundException ignored) {
        }

        if (element == null || !element.isJsonArray()) {
            return;
        }
        JsonArray array = element.getAsJsonArray();

        List<ICarBingoTile> carBingoTiles= CarBingoConverter.getInstance().toObject(array);

        model.getMiniGameModule().setCarBingo(carBingoTiles);
    }

    private void saveMemory(){
        JsonArray array = MemoryConverter.getInstance().toJson(model.getMiniGameModule().getMemory().getTiles());
        StorageUtil.save(mContext.getApplicationContext(), "Memory", array);
    }

    private void loadMemory(){
        JsonElement element = null;
        try {
            element = StorageUtil.load(mContext.getApplicationContext(), "Memory");
        } catch (IllegalStateException e) {
            StorageUtil.resetData(mContext.getApplicationContext(), "Memory");

        } catch (FileNotFoundException ignored) {
        }

        if (element == null || !element.isJsonArray()) {
            return;
        }
        JsonArray array = element.getAsJsonArray();

        List<IMemoryTile> memoryTiles= MemoryConverter.getInstance().toObject(array);

        model.getMiniGameModule().setMemory(memoryTiles);
    }

    private void saveFourInARow(){
        JsonArray array = FourInARowConverter.getInstance().toJson(model.getMiniGameModule().getFourInARow().getTiles());
        StorageUtil.save(mContext.getApplicationContext(), "FourInARow", array);
    }

    private void loadFourInARow(){
        JsonElement element = null;
        try {
            element = StorageUtil.load(mContext.getApplicationContext(), "FourInARow");
        } catch (IllegalStateException e) {
            StorageUtil.resetData(mContext.getApplicationContext(), "FourInARow");

        } catch (FileNotFoundException ignored) {
        }

        if (element == null || !element.isJsonArray()) {
            return;
        }
        JsonArray array = element.getAsJsonArray();

        List<IFourInARowTile> fourInARowTiles= FourInARowConverter.getInstance().toObject(array);

        model.getMiniGameModule().setFourInARow(fourInARowTiles );
    }
}
