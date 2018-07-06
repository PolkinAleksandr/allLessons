package aleksandrpolkin.ru.lesson5dlc;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Activity2 extends BaseNavigationActivity {

    static Intent createStartActivity(Context context) {
        Intent intent = new Intent(context, Activity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    private Button buttonProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        buttonProduct = findViewById(R.id.button_product);
        buttonProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Activity4.createStartActivity(Activity2.this));
            }
        });
    }

}
