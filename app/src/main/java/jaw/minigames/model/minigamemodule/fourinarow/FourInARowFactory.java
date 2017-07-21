package jaw.minigames.model.minigamemodule.fourinarow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johan on 7/8/2017.
 */

public class FourInARowFactory implements IFourInARowFactory {
    private static final IFourInARowFactory fourInARowFactory = new FourInARowFactory();

    public static IFourInARowFactory getInstance() {
        return fourInARowFactory;
    }

    @Override
    public IFourInARow createFourInARow() {
        return new FourInARow();
    }

    @Override
    public List<IFourInARowTile> createFourInARowTiles(){
        List<IFourInARowTile> tiles = new ArrayList<>();
        for (int i = 0; i < 42; i++){
            tiles.add(new FourInARowTile(FourInARowTile.BLANK));
        }
        return tiles;
    }
}
