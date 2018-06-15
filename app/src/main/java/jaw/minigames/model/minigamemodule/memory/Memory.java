package jaw.minigames.model.minigamemodule.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Johan 2018-06-15
 */

public class Memory implements IMemory {

    private List<IMemoryTile> memoryTiles;
    private int turn;

    public Memory(){
        this.memoryTiles = new ArrayList<>();
        initMemory();
    }

    private void initMemory(){
        int [] type = {MemoryTile.S_AMBULANCE,
                MemoryTile.S_COW,
                MemoryTile.S_HORSE,
                MemoryTile.S_POLICE,
                MemoryTile.S_AIRPLANE,
                MemoryTile.S_BIKE,
                MemoryTile.S_BOAT,
                MemoryTile.S_CHURCH,
                MemoryTile.S_FLAG,
                MemoryTile.S_HELICOPTER,
                MemoryTile.S_MCDONALD,
                MemoryTile.S_SHEEP,
                MemoryTile.S_TRACTOR,
                MemoryTile.S_TRAIN,
                MemoryTile.S_WINDMILLER,
                MemoryTile.S_WINDTURBIN,
                MemoryTile.S_AMBULANCE,
                MemoryTile.S_COW,
                MemoryTile.S_HORSE,
                MemoryTile.S_POLICE,
                MemoryTile.S_AIRPLANE,
                MemoryTile.S_BIKE,
                MemoryTile.S_BOAT,
                MemoryTile.S_CHURCH,
                MemoryTile.S_FLAG,
                MemoryTile.S_HELICOPTER,
                MemoryTile.S_MCDONALD,
                MemoryTile.S_SHEEP,
                MemoryTile.S_TRACTOR,
                MemoryTile.S_TRAIN,
                MemoryTile.S_WINDMILLER,
                MemoryTile.S_WINDTURBIN
        };
        shuffle(type);


        memoryTiles = MemoryFactory.getInstance().createMemoryTiles(type);
    }
    void shuffle(int[] arr){
        Random rand = new Random();
        for (int i = arr.length-1; i > 0; i--){
            int j = rand.nextInt(i);
            int k = arr[i];
            arr[i] = arr[j];
            arr[j] = k;
        }
    }

    @Override
    public List<IMemoryTile> getTiles() {
        return this.memoryTiles;
    }

    public void onMemoryTileTappedEvent(int i){

    }
        /*
    public void setMemoryTiles(List<IMemoryTile> memoryTiles) {
        this.memoryTiles = memoryTiles;
    }*/

    public void switchTurn(int tileColor){

    }

    public void checkForVictory(int tile){

    }
}
