package aleksandrpolkin.ru.lesson5dlc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class BaseNavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private Toolbar toolbar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.design_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        //createNavigationView(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.nav_main_activity:
                if (getClass() != MainActivity.class) {
                    intent = MainActivity.createStartActivity(this);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                } else {
                    drawer.closeDrawer(GravityCompat.START);
                }
                //finish();
                break;
            case R.id.nav_activity2:
                if (getClass() != Activity2.class) {
                    intent = Activity2.createStartActivity(this);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                } else {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;
            case R.id.nav_activity3:
                if (getClass() != Activity3.class) {
                    intent = Activity3.createStartActivity(this);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                } else {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;
        }

        return true;
    }

   /* public void choiceItemMenu(final DrawerLayout drawer, final Intent intent){
        final Handler handler = new Handler();
        new Thread() {
            public void run() {
                //drawer.closeDrawer(GravityCompat.START);
                drawer.closeDrawer(GravityCompat.START);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    public void run() {
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                });
            }
        }.start();
    }*/
}

