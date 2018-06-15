package jaw.minigames.model.minigamemodule.carbingogame;

import java.util.List;

/**
 * Created by johan on 6/4/2017.
 */

public interface ICarBingo {
    public List<ICarBingoTile> getCarBingoTiles();

    public boolean checkBingoStatus();

    public void onTileCheckedEvent(int type);
}
