package jaw.minigames.util;

import com.google.gson.JsonArray;

import java.util.List;

public interface IListConverter<T> extends IConverter<T> {
    JsonArray toJson(List<T> list);

    List<T> toObject(JsonArray array);
}
