package jaw.minigames.eventbus;

/**
 * Created by johan on 7/19/2017.
 */

public class TileTappedEvent {
    int i;

    public TileTappedEvent(int i){
        this.i = i;
    }

    public int getNumber(){
        return this.i;
    }
}
