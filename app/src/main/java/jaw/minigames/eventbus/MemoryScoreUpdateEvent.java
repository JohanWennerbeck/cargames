package jaw.minigames.eventbus;

public class MemoryScoreUpdateEvent {
    private int playerOneScore;
    private int playerTwoScore;

    public MemoryScoreUpdateEvent(int playerOneScore, int playerTwoScore){
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
    }

    public int getPlayerOneScore(){
        return playerOneScore;
    }

    public int getPlayerTwoScore(){
        return playerTwoScore;
    }
}
