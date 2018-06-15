package jaw.minigames.model.minigamemodule.memory;

/**
 * Created by Johan 2018-06-15
 */


public interface IMemoryTile {
    public int getType();
    public void setType(int type);
    public boolean getChecked();
    public void toggleChecked();
    public void setChecked(boolean bool);
}
