package jaw.minigames.model;

import jaw.minigames.model.minigamemodule.MiniGameModule;

/**
 * Created by johan on 6/4/2017.
 */

public class Model {
    MiniGameModule miniGameModule;

    public Model(){
        miniGameModule = new MiniGameModule();
    }

    public MiniGameModule getMiniGameModule() {
        return miniGameModule;
    }

    public void setMiniGameModule(MiniGameModule miniGameModule) {
        this.miniGameModule = miniGameModule;
    }
}
