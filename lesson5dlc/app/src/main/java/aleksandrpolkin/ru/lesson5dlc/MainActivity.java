package aleksandrpolkin.ru.lesson5dlc;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseNavigationActivity {

    static Intent createStartActivity(Context context) {
       Intent intent = new Intent(context, MainActivity.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
       return intent;
    }

    private Button buttonProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonProduct = findViewById(R.id.button_product);
        buttonProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Activity5.createStartActivity(MainActivity.this));
            }
        });
    }
}

