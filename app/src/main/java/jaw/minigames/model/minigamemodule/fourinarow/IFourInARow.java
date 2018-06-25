package jaw.minigames.model.minigamemodule.fourinarow;

import java.util.List;

/**
 * Created by johan on 7/8/2017.
 */

public interface IFourInARow {
    List<IFourInARowTile> getTiles();
    void onTileTappedEvent(int i);
    void newGame();
}
