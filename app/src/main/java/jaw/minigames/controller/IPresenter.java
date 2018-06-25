package jaw.minigames.controller;

import jaw.minigames.eventbus.OnCreateEvent;
import jaw.minigames.eventbus.OnDestroyEvent;
import jaw.minigames.eventbus.OnPauseEvent;
import jaw.minigames.eventbus.OnResumeEvent;
import jaw.minigames.eventbus.OnStartEvent;
import jaw.minigames.eventbus.OnStopEvent;
import jaw.minigames.model.Model;

/**
 * Created by johan on 7/11/2017.
 */

public interface IPresenter {
    void onCreate(OnCreateEvent event);
    void onResume(OnResumeEvent event);
    void onPause(OnPauseEvent event);
    void onStart(OnStartEvent event);
    void onDestroy(OnDestroyEvent event);
    void onStop(OnStopEvent event);
    void injectModel(Model model);


}
