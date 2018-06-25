package jaw.minigames.view.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;

import jaw.minigames.R;
import jaw.minigames.eventbus.CarBingoNewGameEvent;
import jaw.minigames.eventbus.OnCreateEvent;
import jaw.minigames.eventbus.OnDestroyEvent;
import jaw.minigames.eventbus.OnPauseEvent;
import jaw.minigames.eventbus.OnResumeEvent;
import jaw.minigames.eventbus.OnStartEvent;
import jaw.minigames.eventbus.OnStopEvent;
import jaw.minigames.eventbus.RequestPresenterEvent;
import jaw.minigames.view.adapter.CarBingoAdapter;
import jaw.minigames.view.adapter.ICarBingoAdapter;

/**
 * Created by johan on 6/4/2017.
 */

public class CarBingoActivity extends AppCompatActivity implements ICarBingoView{
    private RecyclerView gridview;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.carbingo_activity);

        gridview = (RecyclerView) findViewById(R.id.carBingoGrid);
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);
        gridview.setLayoutManager(new GridLayoutManager(this, 4));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        EventBus.getDefault().post(new RequestPresenterEvent(this));
        EventBus.getDefault().post(new OnCreateEvent(this));
        /*gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(CarBingoActivity.this, "Nice work! Keep going!",
                        Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new TileCheckedEvent(position));
            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().post(new OnStartEvent(this));
    }


    @Override
    protected void onStop() {
        EventBus.getDefault().post(new OnStopEvent(this));
        super.onStop();
    }


    @Override
    protected void onPause() {
        EventBus.getDefault().post(new OnPauseEvent(this));
        super.onPause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().post(new OnResumeEvent(this));
    }


    @Override
    protected void onDestroy() {
        System.out.println("CAR ACTIVITY DESTROY");
        EventBus.getDefault().post(new OnDestroyEvent(this));
        super.onDestroy();
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.new_game) {
            EventBus.getDefault().post(new CarBingoNewGameEvent());
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshItems() {
        ICarBingoAdapter adapter = (ICarBingoAdapter) gridview.getAdapter();
        adapter.refreshItems();

        // Stop refresh animation
       // refreshLayout.setRefreshing(false);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Car_Bingo) {
            // Handle the camera action
        } else if (id == R.id.Four_In_A_Row) {

        } else if (id == R.id.Memory) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    @Override
    public void setCarBingoAdapter(CarBingoAdapter adapter) {
        gridview.setAdapter(adapter);
    }

    @Override
    public void setToolbar() {

    }
}
