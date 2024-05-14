package com.example.sdf;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    FragmentManager fragmentManager; //Fragment 관리자
    LoginFragment loginFragment; //Fragment
    MainFragment mainFragment; //Fragment

    LoginFragment3 loginFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //DrawerLayout
        drawer=(DrawerLayout)findViewById(R.id.main_drawer);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //navigation toggle home
        toggle=new ActionBarDrawerToggle(this, drawer, R.string.d_open, R.string.d_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_home) {
                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.container,loginFragment3, null);
                    fragmentTransaction.commit();

                    Toast.makeText(getApplicationContext(), "NavigationDrawer home", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.menu_menu1) {
                    Toast.makeText(getApplicationContext(), "NavigationDrawer menu1", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.menu_menu2) {
                    Toast.makeText(getApplicationContext(), "NavigationDrawer menu2", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){  //navigation toggle
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

}