package jaw.minigames.model.minigamemodule.carbingogame;

/**
 * Created by johan on 6/4/2017.
 */

public class CarBingoTile implements ICarBingoTile {

    public static final int S_POLICE = 0;
    public static final int S_HORSE = 1;
    public static final int S_COW = 2;
    public static final int S_AMBULANCE = 3;

    private int type;

    public CarBingoTile(int type){
        this.type = type;
    }


    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }
}
