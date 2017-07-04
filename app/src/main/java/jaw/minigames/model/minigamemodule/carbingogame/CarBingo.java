package jaw.minigames.model.minigamemodule.carbingogame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johan on 6/4/2017.
 */

public class CarBingo implements ICarBingo {
    List<ICarBingoTile> carBingoTiles;

    public CarBingo(){
        carBingoTiles = new ArrayList<>();
        initCarBingoTiles();
    }

    public void initCarBingoTiles(){
        for (int type = 0; type < 4; type++)
        carBingoTiles.add(CarBingoFactory.getInstance().createCarBingoTile(type));
    }
}
