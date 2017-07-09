package jaw.minigames.model.minigamemodule.fourinarow;

/**
 * Created by johan on 7/8/2017.
 */

public class FourInARowFactory implements IFourInARowFactory {
    private static final IFourInARowFactory fourInARowFactory = new FourInARowFactory();

    public static IFourInARowFactory getInstance() {
        return fourInARowFactory;
    }

    @Override
    public IFourInARow createFourInARow() {
        return new FourInARow();
    }
}
