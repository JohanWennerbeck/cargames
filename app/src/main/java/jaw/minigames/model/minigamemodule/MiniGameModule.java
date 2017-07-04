package jaw.minigames.model.minigamemodule;

import jaw.minigames.model.minigamemodule.carbingogame.CarBingo;
import jaw.minigames.model.minigamemodule.carbingogame.ICarBingo;

/**
 * Created by johan on 6/4/2017.
 */

public class MiniGameModule {
    private ICarBingo carBingo;

    public MiniGameModule(){
        carBingo = new CarBingo();
    }

    public ICarBingo getCarBingo() {
        return carBingo;
    }

    public void setCarBingo(ICarBingo carBingo) {
        this.carBingo = carBingo;
    }
}
