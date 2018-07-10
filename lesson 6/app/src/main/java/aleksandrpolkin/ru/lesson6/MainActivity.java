package aleksandrpolkin.ru.lesson6;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMyGetTextForActivity{

    private Toolbar toolbar;
    private FragmentTransaction fragmentTransaction;
    private Fragment1 fragment1;
    private Fragment2 fragment2;

    private final BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationView;
    {
        bottomNavigationView = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_item_one:
                        toolbar.setTitle(R.string.item_one);
                        fragment1 = Fragment1.createInstance(getResources().getString(R.string.text_fragment1));
                        fragmentTransaction = getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment1, Fragment1.FRAGMENT_TAG);
                        fragmentTransaction
                                .commit();
                        break;
                    case R.id.bottom_item_two:
                        toolbar.setTitle(R.string.item_two);
                        fragment1 = Fragment1.createInstance(getResources().getString(R.string.text_fragment2));
                        fragmentTransaction = getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment1, Fragment1.FRAGMENT_TAG);
                        fragmentTransaction
                                .commit();
                        break;
                    case R.id.bottom_item_three:
                        toolbar.setTitle(R.string.item_three);
                        fragment2 = Fragment2.createInstance();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment2, Fragment2.FRAGMENT_TAG);
                        fragmentTransaction.commit();
                        break;
                }
                return true;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Фрагменты
        fragment1 = Fragment1.createInstance(getResources().getString(R.string.text_fragment1));
        fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment1, Fragment1.FRAGMENT_TAG);
        fragmentTransaction
                .commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(bottomNavigationView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_search:
                Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.g1_item1:
                Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.g1_item2:
                Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.g2_item1:
                Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.g2_item2:
                Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_item1) {
            Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_item2) {
            Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_item3) {
            Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setTextForActivity(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
