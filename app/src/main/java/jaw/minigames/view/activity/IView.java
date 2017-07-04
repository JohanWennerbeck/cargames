package jaw.minigames.view.activity;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by johan on 7/4/2017.
 */

public interface IView {
    /**
     * Pretty much casts the view into a AppCompatView,
     *
     * @return Returns the AppCompatActivity of the view.
     */
    AppCompatActivity getAppCompatActivity();
}
