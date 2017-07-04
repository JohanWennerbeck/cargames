package jaw.minigames.model;

import jaw.minigames.model.minigamemodule.MiniGameModule;
import jaw.minigames.model.minigamemodule.MiniGameModuleFactory;

/**
 * Created by johan on 6/4/2017.
 */

public class Model {
    private MiniGameModule miniGameModule;

    public Model(){
        miniGameModule = MiniGameModuleFactory.getInstance().createMiniGameModule() ;
    }

    public MiniGameModule getMiniGameModule() {
        return miniGameModule;
    }

    public void setMiniGameModule(MiniGameModule miniGameModule) {
        this.miniGameModule = miniGameModule;
    }
}
