package jaw.minigames.model.minigamemodule.fourinarow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johan on 7/8/2017.
 */

public class FourInARow implements IFourInARow {
    private List<IFourInARowTile> tiles;
    private int turn;

    public FourInARow(){
        this.tiles = new ArrayList<>();
        this.turn = FourInARowTile.BLUE;
        initFourInARow();
    }

    private void initFourInARow(){
        tiles = FourInARowFactory.getInstance().createFourInARowTiles();
    }

    @Override
    public List<IFourInARowTile> getTiles() {
        return this.tiles;
    }

    public void onTileTappedEvent(int i){
        int rightTile = i;
        while (rightTile < 35){
            rightTile = rightTile +7;
        }
        while (tiles.get(rightTile).getColor() != FourInARowTile.BLANK){
            rightTile = rightTile - 7;
            if(rightTile < 0){
                return;
            }
        }
        this.tiles.get(rightTile).setColor(turn);
        this.switchTurn(turn);
        for (int k = 0; k < 7; k++)
        System.out.print(tiles.get(k).getColor());

        System.out.println(" ");

        for (int k = 7; k < 14; k++)
            System.out.print(tiles.get(k).getColor());

        System.out.println(" ");

        for (int k = 14; k < 21; k++)
            System.out.print(tiles.get(k).getColor());

        System.out.println(" ");

        for (int k = 21; k < 28; k++)
            System.out.print(tiles.get(k).getColor());

        System.out.println(" ");

        for (int k = 28; k < 35; k++)
            System.out.print(tiles.get(k).getColor());

        System.out.println(" ");

        for (int k = 35; k < 42; k++)
            System.out.print(tiles.get(k).getColor());

        System.out.println(" ");

    }

    public void switchTurn(int tileColor){
        if (tileColor == FourInARowTile.BLUE){
            this.turn = FourInARowTile.RED;
        } else {
            this.turn = FourInARowTile.BLUE;
        }
    }
}
