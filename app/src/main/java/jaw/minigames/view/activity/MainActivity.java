package jaw.minigames.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import jaw.minigames.R;
import jaw.minigames.controller.IPresenterFactory;
import jaw.minigames.controller.PresenterFactory;
import jaw.minigames.eventbus.MemoryNewGameEvent;
import jaw.minigames.eventbus.OnCreateEvent;
import jaw.minigames.eventbus.OnDestroyEvent;
import jaw.minigames.eventbus.OnPauseEvent;
import jaw.minigames.eventbus.OnResumeEvent;
import jaw.minigames.eventbus.OnStartEvent;
import jaw.minigames.eventbus.OnStopEvent;
import jaw.minigames.eventbus.RequestPresenterEvent;
import jaw.minigames.eventbus.ShowCarBingoEvent;
import jaw.minigames.eventbus.ShowFourInARowEvent;
import jaw.minigames.eventbus.ShowMemoryEvent;
import jaw.minigames.eventbus.UpdateContextReferenceEvent;

public class MainActivity extends AppCompatActivity implements IMainView, NavigationView.OnNavigationItemSelectedListener {

    private static boolean initialized = false;
    private final IPresenterFactory presenterFactory  = PresenterFactory.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("On Create MAin");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(!initialized) {
            presenterFactory.initializeDelegatingPresenter(this);
            initialized = true;
        } else {
            EventBus.getDefault().post(new UpdateContextReferenceEvent(this));
        }
        attachPresenter();
        EventBus.getDefault().post(new OnCreateEvent(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("On Start Main");
        EventBus.getDefault().post(new OnStartEvent(this));
    }


    @Override
    protected void onStop() {
        System.out.println("ON STOP MAIN");
        EventBus.getDefault().post(new OnStopEvent(this));
        super.onStop();
    }


    @Override
    protected void onPause() {
        System.out.println("ON PAUSE MAIN");
        EventBus.getDefault().post(new OnPauseEvent(this));
        super.onPause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("ON REsUME MAIN");
        EventBus.getDefault().post(new OnResumeEvent(this));
    }


    @Override
    protected void onDestroy() {
        System.out.println("MAIN ACTIVITY DESTROY");
        //EventBus.getDefault().post(new OnDestroyEvent(this));
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        System.out.println("OnBackPressed");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        System.out.println("OnCreateOptionMenu");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        System.out.println("OnOptionsItemSelected");
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Car_Bingo) {
            openNextCarBingo();
        } else if (id == R.id.Four_In_A_Row) {
            openNextFourInARow();
        } else if (id == R.id.Memory) {
            openNextMemory();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openNextCarBingo(){
        EventBus.getDefault().post(new ShowCarBingoEvent());
    }

    public void openNextFourInARow(){
        EventBus.getDefault().post(new ShowFourInARowEvent());
    }

    public void openNextMemory() { EventBus.getDefault().post(new ShowMemoryEvent());}

    private void attachPresenter() {
        EventBus.getDefault().post(new RequestPresenterEvent(this));
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        System.out.println("getAppCombatActivity");
        return this;
    }

    @Override
    public void setToolbar() {
        System.out.println("setToolbar");
    }

    @Override
    public void setNavDrawer() {
        System.out.println("SetNavDrawer");
    }
}
