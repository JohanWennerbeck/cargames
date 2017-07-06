package jaw.minigames.model.minigamemodule.carbingogame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        int [] type = {1,2,3,4};
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
}
