package jaw.minigames.model.minigamemodule;

import jaw.minigames.model.minigamemodule.carbingogame.CarBingo;
import jaw.minigames.model.minigamemodule.carbingogame.CarBingoFactory;
import jaw.minigames.model.minigamemodule.carbingogame.ICarBingo;
import jaw.minigames.model.minigamemodule.fourinarow.FourInARow;
import jaw.minigames.model.minigamemodule.fourinarow.FourInARowFactory;
import jaw.minigames.model.minigamemodule.fourinarow.IFourInARow;

/**
 * Created by johan on 6/4/2017.
 */

public class MiniGameModule {
    private ICarBingo carBingo;
    private IFourInARow fourInARow;

    public MiniGameModule(){
        carBingo = CarBingoFactory.getInstance().createCarBingo();
        fourInARow = FourInARowFactory.getInstance().createFourInARow();

    }

    public ICarBingo getCarBingo() {
        return carBingo;
    }

    public void setCarBingo(ICarBingo carBingo) {
        this.carBingo = carBingo;
    }
}
