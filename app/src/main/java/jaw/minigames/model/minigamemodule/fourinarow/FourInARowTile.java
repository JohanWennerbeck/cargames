package jaw.minigames.model.minigamemodule.fourinarow;

/**
 * Created by johan on 7/19/2017.
 */

public class FourInARowTile implements IFourInARowTile {
    public static final int BLUE = 2;
    public static final int RED = 1;
    public static final int BLANK = 0;

    private int tileColor;

    public FourInARowTile(int type){
        this.tileColor = type;
    }


    @Override
    public void setColor(int type) {
        this.tileColor = type;
    }

    @Override
    public int getColor() {
        return this.tileColor;
    }
}
