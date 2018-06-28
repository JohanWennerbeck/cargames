package jaw.minigames.model.minigamemodule.carbingogame;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jaw.minigames.eventbus.BingoVictoryEvent;

/**
 * Created by johan on 6/4/2017.
 */

public class CarBingo implements ICarBingo {
    List<ICarBingoTile> carBingoTiles;

    public CarBingo(){
        carBingoTiles = new ArrayList<>();
        initCarBingoTiles();
    }

    public void initCarBingoTiles() {
        int [] type = {CarBingoTile.S_AMBULANCE,
                CarBingoTile.S_COW,
                CarBingoTile.S_HORSE,
                CarBingoTile.S_POLICE,
                CarBingoTile.S_AIRPLANE,
                CarBingoTile.S_BIKE,
                CarBingoTile.S_BOAT,
                CarBingoTile.S_CHURCH,
                CarBingoTile.S_FLAG,
                CarBingoTile.S_HELICOPTER,
                CarBingoTile.S_MCDONALD,
                CarBingoTile.S_SHEEP,
                CarBingoTile.S_TRACTOR,
                CarBingoTile.S_TRAIN,
                CarBingoTile.S_WINDMILL,
                CarBingoTile.S_WINDTURBIN
        };
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
            return true;
        } else if (checkVertical()){
            return true;
        } else {
            return false;
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


    public void onTileCheckedEvent(int type){
        this.carBingoTiles.get(type).toggleChecked();
        if (this.checkBingoStatus()){
            EventBus.getDefault().post(new BingoVictoryEvent());
        };
    }

    @Override
    public void newGame() {
        this.initCarBingoTiles();
    }
}
