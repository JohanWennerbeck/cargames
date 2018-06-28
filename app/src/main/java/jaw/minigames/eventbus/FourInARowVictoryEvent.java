package jaw.minigames.eventbus;

public class FourInARowVictoryEvent {
    int player;

    public FourInARowVictoryEvent(int player){
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }
}
