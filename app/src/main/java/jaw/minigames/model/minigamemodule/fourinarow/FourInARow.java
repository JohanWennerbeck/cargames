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
        checkForVictory(rightTile);
        this.switchTurn(turn);
    }

    public void switchTurn(int tileColor){
        if (tileColor == FourInARowTile.BLUE){
            this.turn = FourInARowTile.RED;
        } else {
            this.turn = FourInARowTile.BLUE;
        }
    }

    public void checkForVictory(int tile){
        checkVertical(tile);
        checkHorizontal(tile);
        checkDownDiagonal(tile);
        checkUpDiagonal(tile);
    }

    private void checkUpDiagonal(int tile){
        int difHorizontal = tile % 7;
        int difVertical = 5- (int) Math.floor(tile/7);
        int dif;
        if ( difHorizontal < difVertical) {
            dif = difHorizontal;
        } else {
            dif = difVertical;
        }
        for (int i = 0; i < dif; i++){
            tile = tile +6;
        }
        int count = 0;
        while (tile > 6 && tile %  7 != 6) {
            if (this.tiles.get(tile).getColor() == this.tiles.get(tile-6).getColor() && this.tiles.get(tile).getColor() != FourInARowTile.BLANK) {
                count++;
                if(count==3){
                    System.out.println("VICTORY");;
                }
            } else {
                count = 0;
            }
            tile-=6;
        }
    }

    private void checkDownDiagonal(int tile) {
        int difHorizontal = tile % 7;
        int difVertical = (int) Math.floor(tile/7);
        int dif;
        if ( difHorizontal < difVertical) {
            dif = difHorizontal;
        } else {
            dif = difVertical;
        }
        for (int i = 0; i < dif; i++){
            tile = tile - 8;
        }
        int count = 0;
        while (tile < 35 && tile %  7 != 6) {
            if (this.tiles.get(tile).getColor() == this.tiles.get(tile+8).getColor() && this.tiles.get(tile).getColor() != FourInARowTile.BLANK) {
                count++;
                if(count==3){
                    System.out.println("VICTORY");;
                }
            } else {
                count = 0;
            }
            tile+=8;
        }
    }

    private void checkHorizontal(int tile){
        int dif = tile % 7;
        int tempTile = tile-dif;
        int count = 0;
        while (tempTile < tile - dif + 6) {
            if (this.tiles.get(tempTile).getColor() == this.tiles.get(tempTile+1).getColor() && this.tiles.get(tempTile).getColor() != FourInARowTile.BLANK) {
                count++;
                if(count==3){
                    System.out.println("VICTORY");;
                }
            } else {
                count = 0;
            }
            tempTile++;
        }
    }

    private void checkVertical(int tile) {
        if (tile < 21) { //Check vertical
            if (this.tiles.get(tile+7).getColor() == this.turn &&
                    this.tiles.get(tile+14).getColor() == this.turn &&
                    this.tiles.get(tile+21).getColor() == this.turn) {
                System.out.println("WIIIN");
            }
        }
    }
}
