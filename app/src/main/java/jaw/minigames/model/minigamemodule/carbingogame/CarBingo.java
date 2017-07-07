package jaw.minigames.model.minigamemodule.carbingogame;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jaw.minigames.eventbus.BingoEvent;
import jaw.minigames.eventbus.TileCheckedEvent;

/**
 * Created by johan on 6/4/2017.
 */

public class CarBingo implements ICarBingo {
    private boolean gotBingo;
    List<ICarBingoTile> carBingoTiles;

    public CarBingo(){
        gotBingo = false;
        carBingoTiles = new ArrayList<>();
        initCarBingoTiles();
        EventBus.getDefault().register(this);
    }

    public void initCarBingoTiles() {
        int [] type = {CarBingoTile.S_AMBULANCE,CarBingoTile.S_COW, CarBingoTile.S_HORSE, CarBingoTile.S_POLICE};
        shuffle(type);
        carBingoTiles = CarBingoFactory.getInstance().createCarBingoTiles(type);
    }

    void shuffle(int[] arr){
        Random rand = new Random();
        for (int i = arr.length-1; i > 0; i--){
            int j = rand.nextInt(i);
            int k = arr[i];
            arr[i] = arr[j];
            arr[j] = k;
        }
    }

    public List<ICarBingoTile> getCarBingoTiles() {
        return carBingoTiles;
    }

    public void setCarBingoTiles(List<ICarBingoTile> carBingoTiles) {
        this.carBingoTiles = carBingoTiles;
    }

    public boolean checkBingoStatus(){
        if (checkHorizontal()){
            setChecked(true);
            return true;
        } else if (checkVertical()){
            setChecked(true);
            return true;
        } else {
            setChecked(false);
            return false;
        }
    }

    public void setChecked(boolean bool){
        this.gotBingo = bool;
        if (gotBingo){
            EventBus.getDefault().post(new BingoEvent());
        }
    }

    private boolean checkVertical() {
        for (int i = 0; i < this.carBingoTiles.size(); i += Math.sqrt(this.carBingoTiles.size())){
            int notchecked = 0;
            for (int j = i; j < i + Math.sqrt(this.carBingoTiles.size()); j++){
                if (!this.carBingoTiles.get(j).getChecked()){
                    notchecked++;
                }
            }
            if (notchecked == 0){
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontal() {
        for (int i = 0; i < Math.sqrt(this.carBingoTiles.size()) ; i++) {
            int notChecked = 0;
            for (int j = i; j < this.carBingoTiles.size();){
                 if (!this.carBingoTiles.get(j).getChecked()){
                     notChecked++;
                 }
                 j = j + (int) Math.sqrt(this.carBingoTiles.size());
            }
            if (notChecked == 0){
                return true;
            }
        }
        return false;
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onTileCheckedEvent(TileCheckedEvent event){
        this.carBingoTiles.get(event.getType()).toggleChecked();
        this.checkBingoStatus();
    }
}
