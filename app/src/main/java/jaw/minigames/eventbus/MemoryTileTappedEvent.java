package jaw.minigames.eventbus;

public class MemoryTileTappedEvent {
    int i;

    public MemoryTileTappedEvent(int i){
        this.i = i;
    }

    public int getNumber(){
        return this.i;
    }
}
