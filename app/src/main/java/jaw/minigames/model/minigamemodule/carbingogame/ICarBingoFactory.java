package jaw.minigames.model.minigamemodule.carbingogame;

import java.util.List;

/**
 * Created by johan on 6/4/2017.
 */

public interface ICarBingoFactory {
    List<ICarBingoTile> createCarBingoTiles(int [] type);
    ICarBingo createCarBingo();
}
