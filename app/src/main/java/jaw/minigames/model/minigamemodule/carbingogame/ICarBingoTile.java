package jaw.minigames.model.minigamemodule.carbingogame;

/**
 * Created by johan on 6/4/2017.
 */

public interface ICarBingoTile {
    public int getType();
    public void setType(int type);
    public boolean getChecked();
    public void toggleChecked();
    public void setChecked(boolean bool);
}
