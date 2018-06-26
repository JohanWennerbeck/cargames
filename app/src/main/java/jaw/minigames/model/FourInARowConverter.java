package jaw.minigames.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import jaw.minigames.model.minigamemodule.fourinarow.FourInARowTile;
import jaw.minigames.model.minigamemodule.fourinarow.IFourInARowTile;
import jaw.minigames.util.IListConverter;

public class FourInARowConverter implements IListConverter<IFourInARowTile> {

    private final static FourInARowConverter INSTANCE = new FourInARowConverter();

    public static FourInARowConverter getInstance() {
        return INSTANCE;
    }

    private FourInARowConverter(){

    }

    @Override
    public JsonArray toJson(List<IFourInARowTile> fourInARowTiles) {
        JsonArray array = new JsonArray();

        for (IFourInARowTile fourInARowTile : fourInARowTiles) {
            array.add(toJson(fourInARowTile));
        }
        return array;
    }
    @Override
    public JsonObject toJson(IFourInARowTile fourInARowTile) {
        JsonObject achievementObject = new JsonObject();

        achievementObject.addProperty("Color", fourInARowTile.getColor());

        return achievementObject;
    }
    @Override
    public List<IFourInARowTile> toObject(JsonArray array) {
        List<IFourInARowTile> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();

            list.add(toObject(object));
        }
        return list;
    }



    @Override
    public IFourInARowTile toObject(JsonObject object) {
        IFourInARowTile fourInARowTile = new FourInARowTile();

        fourInARowTile.setColor(object.get("Color").getAsInt());

        return fourInARowTile;
    }
}
