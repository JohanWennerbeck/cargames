package jaw.minigames.model.minigamemodule.carbingogame;

/**
 * Created by johan on 6/4/2017.
 */

public class CarBingoFactory implements ICarBingoFactory {
    private static final ICarBingoFactory carBingoFactory = new CarBingoFactory();

    public static ICarBingoFactory getInstance(){
        return carBingoFactory;
    }


    @Override
    public CarBingoTile createCarBingoTile(int type) {
        return new CarBingoTile(type);
    }
}
