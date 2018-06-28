package jaw.minigames.eventbus;

public class MemoryVictoryEvent {
    int playerOneScore, playerTwoScore;

    public MemoryVictoryEvent ( int playerOneScore, int playerTwoScore){
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }
}
