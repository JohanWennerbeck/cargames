package jaw.minigames.model.minigamemodule.fourinarow;

import java.util.List;

/**
 * Created by johan on 7/8/2017.
 */

public class FourInARow implements IFourInARow {
    private List<IFourInARowTile> tiles;
    private int turn;

    public FourInARow(List<IFourInARowTile> tiles){
        this.tiles = tiles;
        this.turn = FourInARowTile.BLUE;
    }

    @Override
    public List<IFourInARowTile> getTiles() {
        return this.tiles;
    }

    public void onTileTappedEvent(int i){
        this.tiles.get(i).setColor(turn);
        this.switchTurn(turn);
    }

    public void switchTurn(int tileColor){
        if (tileColor == FourInARowTile.BLUE){
            this.turn = FourInARowTile.RED;
        } else {
            this.turn = FourInARowTile.BLUE;
        }
    }
}
