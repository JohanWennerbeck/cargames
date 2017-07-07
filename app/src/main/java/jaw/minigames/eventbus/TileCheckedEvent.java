package jaw.minigames.eventbus;

/**
 * Created by johan on 7/7/2017.
 */

public class TileCheckedEvent {
    private int type;

    public TileCheckedEvent(int type){
        this.type = type;
    }

    public int getType(){
        return this.type;
    }
}
