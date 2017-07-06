package jaw.minigames.model.minigamemodule.carbingogame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johan on 6/4/2017.
 */

public class CarBingoFactory implements ICarBingoFactory {
    private static final ICarBingoFactory carBingoFactory = new CarBingoFactory();

    public static ICarBingoFactory getInstance(){
        return carBingoFactory;
    }


    @Override
    public List<ICarBingoTile> createCarBingoTiles(int [] type) {
        List<ICarBingoTile> carBingoTiles = new ArrayList<>();
        for (int i : type)
        carBingoTiles.add(new CarBingoTile(i));
        return carBingoTiles;
    }
}
