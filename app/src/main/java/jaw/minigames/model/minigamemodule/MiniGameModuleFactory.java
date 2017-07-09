package jaw.minigames.model.minigamemodule;

/**
 * Created by johan on 7/4/2017.
 */

public class MiniGameModuleFactory {
    private static MiniGameModuleFactory miniGameModuleFactory = new MiniGameModuleFactory();

    public static MiniGameModuleFactory getInstance(){
        return miniGameModuleFactory;
    }

    public MiniGameModule createMiniGameModule (){
        return new MiniGameModule();
    }

}
