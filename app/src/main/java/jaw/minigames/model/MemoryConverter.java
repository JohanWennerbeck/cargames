package jaw.minigames.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import jaw.minigames.model.minigamemodule.memory.IMemoryTile;
import jaw.minigames.model.minigamemodule.memory.MemoryTile;
import jaw.minigames.util.IListConverter;

public class MemoryConverter implements IListConverter<IMemoryTile> {

    private final static MemoryConverter INSTANCE = new MemoryConverter();

    public static MemoryConverter getInstance() {
        return INSTANCE;
    }

    private MemoryConverter(){

    }

    @Override
    public JsonArray toJson(List<IMemoryTile> memoryTiles) {
        JsonArray array = new JsonArray();

        for (IMemoryTile memoryTile : memoryTiles) {
            array.add(toJson(memoryTile));
        }
        return array;
    }

    @Override
    public JsonObject toJson(IMemoryTile memoryTile) {
        JsonObject achievementObject = new JsonObject();

        achievementObject.addProperty("Type", memoryTile.getType());
        achievementObject.addProperty("Checked", memoryTile.getChecked());

        return achievementObject;
    }
    @Override
    public List<IMemoryTile> toObject(JsonArray array) {
        List<IMemoryTile> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();

            list.add(toObject(object));
        }
        return list;
    }



    @Override
    public IMemoryTile toObject(JsonObject object) {
        IMemoryTile memoryTile = new MemoryTile();

        memoryTile.setType(object.get("Type").getAsInt());
        memoryTile.setChecked(object.get("Checked").getAsBoolean());

        return memoryTile;
    }
}
