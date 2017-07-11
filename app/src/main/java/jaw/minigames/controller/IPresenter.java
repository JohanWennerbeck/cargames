package jaw.minigames.controller;

import jaw.minigames.eventbus.OnCreateEvent;
import jaw.minigames.model.Model;

/**
 * Created by johan on 7/11/2017.
 */

public interface IPresenter {
    void onCreate(OnCreateEvent event);
    void injectModel(Model model);


}
