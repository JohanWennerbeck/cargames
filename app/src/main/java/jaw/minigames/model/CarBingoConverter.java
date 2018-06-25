package jaw.minigames.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import jaw.minigames.model.minigamemodule.carbingogame.CarBingoTile;
import jaw.minigames.model.minigamemodule.carbingogame.ICarBingoTile;
import jaw.minigames.util.IListConverter;

public class CarBingoConverter implements IListConverter<ICarBingoTile> {

    private final static CarBingoConverter INSTANCE = new CarBingoConverter();

    public static CarBingoConverter getInstance() {
        return INSTANCE;
    }

    private CarBingoConverter(){

    }

    @Override
    public JsonArray toJson(List<ICarBingoTile> bingoTiles) {
        JsonArray array = new JsonArray();

        for (ICarBingoTile bingoTile : bingoTiles) {
                array.add(toJson(bingoTile));
        }
        return array;
    }
    @Override
    public JsonObject toJson(ICarBingoTile bingoTile) {
        JsonObject achievementObject = new JsonObject();

        achievementObject.addProperty("Type", bingoTile.getType());
        achievementObject.addProperty("Checked", bingoTile.getChecked());

        return achievementObject;
    }
    @Override
    public List<ICarBingoTile> toObject(JsonArray array) {
        List<ICarBingoTile> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();

            list.add(toObject(object));
        }
        return list;
    }



    @Override
    public ICarBingoTile toObject(JsonObject object) {
        ICarBingoTile carBingoTile = new CarBingoTile();

        carBingoTile.setType(object.get("Type").getAsInt());
        carBingoTile.setChecked(object.get("Checked").getAsBoolean());

        return carBingoTile;
    }
}
