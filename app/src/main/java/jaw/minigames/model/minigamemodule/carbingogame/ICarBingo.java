package jaw.minigames.model.minigamemodule.carbingogame;

import java.util.List;

/**
 * Created by johan on 6/4/2017.
 */

public interface ICarBingo {
    List<ICarBingoTile> getCarBingoTiles();
    void setCarBingoTiles(List<ICarBingoTile> carBingoTiles);

    boolean checkBingoStatus();

    void onTileCheckedEvent(int type);

    void newGame();
}
