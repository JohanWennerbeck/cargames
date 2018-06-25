package jaw.minigames.util;

import com.google.gson.JsonObject;

public interface IConverter<T> {

    JsonObject toJson(T object);

    T toObject(JsonObject object);
}
