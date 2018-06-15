package jaw.minigames.model.minigamemodule.memory;

/**
 * Created by Johan 2018-06-15
 */


public class MemoryTile implements IMemoryTile {
    public static final int S_POLICE = 0;
    public static final int S_HORSE = 1;
    public static final int S_COW = 2;
    public static final int S_AMBULANCE = 3;
    public static final int S_TRACTOR = 4;
    public static final int S_WINDTURBIN = 5;
    public static final int S_WINDMILLER = 6;
    public static final int S_AIRPLANE = 7;
    public static final int S_CHURCH = 8;
    public static final int S_FLAG = 9;
    public static final int S_BOAT = 10;
    public static final int S_TRAIN = 11;
    public static final int S_HELICOPTER = 12;
    public static final int S_BIKE = 13;
    public static final int S_MCDONALD = 14;
    public static final int S_SHEEP = 15;

    private int type;
    private boolean checked;



    public MemoryTile(int type){
        this.type = type;
        checked = false;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean getChecked() {
        return this.checked;
    }

    @Override
    public void toggleChecked() {
        this.checked = !this.checked;
    }

    @Override
    public void setChecked(boolean bool) {
        this.checked = bool;
    }
}
