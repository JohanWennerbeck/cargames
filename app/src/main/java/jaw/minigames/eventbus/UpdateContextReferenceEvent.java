package jaw.minigames.eventbus;

import android.content.Context;

/**
 * Created by johan on 7/12/2017.
 */

public class UpdateContextReferenceEvent {
    public final Context context;

    /**
     *
     * @param context is context
     */
    public UpdateContextReferenceEvent(Context context){
        this.context = context;
    }
}
