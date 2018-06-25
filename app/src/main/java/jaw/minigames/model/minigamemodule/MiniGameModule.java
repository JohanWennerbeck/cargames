package jaw.minigames.model.minigamemodule;

import java.util.List;

import jaw.minigames.model.minigamemodule.carbingogame.CarBingoFactory;
import jaw.minigames.model.minigamemodule.carbingogame.ICarBingo;
import jaw.minigames.model.minigamemodule.carbingogame.ICarBingoTile;
import jaw.minigames.model.minigamemodule.fourinarow.FourInARowFactory;
import jaw.minigames.model.minigamemodule.fourinarow.IFourInARow;
import jaw.minigames.model.minigamemodule.memory.IMemory;
import jaw.minigames.model.minigamemodule.memory.Memory;
import jaw.minigames.model.minigamemodule.memory.MemoryFactory;

/**
 * Created by johan on 6/4/2017.
 */

public class MiniGameModule {
    private ICarBingo carBingo;
    private IFourInARow fourInARow;
    private IMemory memory;

    public MiniGameModule(){
        carBingo = CarBingoFactory.getInstance().createCarBingo();
        fourInARow = FourInARowFactory.getInstance().createFourInARow();
        memory = MemoryFactory.getInstance().createMemory();
    }

    public IMemory getMemory() {
        return memory;
    }

    public void setMemory(IMemory memory) {
        this.memory = memory;
    }

    public ICarBingo getCarBingo() {
        return carBingo;
    }

    public void setCarBingo(List<ICarBingoTile> carBingoTiles) {
        this.carBingo.setCarBingoTiles(carBingoTiles);
    }

    public IFourInARow getFourInARow(){
        return fourInARow;
    }

    public void setFourInARow(IFourInARow fourInARow) {
        this.fourInARow = fourInARow;
    }
}
