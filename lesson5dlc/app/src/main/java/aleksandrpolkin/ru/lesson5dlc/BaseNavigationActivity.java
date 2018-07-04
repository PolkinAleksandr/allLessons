package aleksandrpolkin.ru.lesson5dlc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseNavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_view);
        //createNavigationView(this);
    }

    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    protected void createNavigationView(NavigationView.OnNavigationItemSelectedListener view) {
        NavigationView navigationView = findViewById(R.id.design_navigation_view);
        navigationView.setNavigationItemSelectedListener(view);
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
                    overridePendingTransition(0,0);
                } else {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;
            case R.id.nav_activity3:
                if (getClass() != Activity3.class) {
                    intent = Activity3.createStartActivity(this);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                } else {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;
        }

        return true;
    }
}

