package jaw.minigames.model.minigamemodule.carbingogame;

import java.util.List;

/**
 * Created by johan on 6/4/2017.
 */

public interface ICarBingo {
    List<ICarBingoTile> getCarBingoTiles();

    boolean checkBingoStatus();

    void onTileCheckedEvent(int type);

    void newGame();
}
