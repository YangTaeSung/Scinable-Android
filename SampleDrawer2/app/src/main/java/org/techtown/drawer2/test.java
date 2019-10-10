package org.techtown.drawer2;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.core.view.GravityCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback{

    // private AppBarConfiguration mAppBarConfiguration;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    Toolbar toolbar;

    private TextView nameTextView;
    private TextView emailTextView;
    private FirebaseAuth auth; // Can bring data for Any activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        auth = FirebaseAuth.getInstance(); // Essential process before using 'auth'

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
/*

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


 */

        nameTextView = (TextView)view.findViewById(R.id.header_name_text);
        emailTextView = (TextView)view.findViewById(R.id.header_email_text);

        nameTextView.setText(auth.getCurrentUser().getDisplayName());
        emailTextView.setText(auth.getCurrentUser().getEmail());

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu1) {
            Toast.makeText(this,"Selected first menu", Toast.LENGTH_LONG).show();
            onFragmentSelected(0,null);
        } else if (id == R.id.menu2) {
            Toast.makeText(this, "Selected second menu", Toast.LENGTH_LONG).show();
            onFragmentSelected(1,null);
        } else if (id == R.id.menu3) {
            Toast.makeText(this, "Selected third menu", Toast.LENGTH_LONG).show();
            onFragmentSelected(2, null);
        } else if (id == R.id.nav_logout) {
            auth.signOut();
            finish();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle){
        Fragment curFragment = null;

        if (position == 0) {
            curFragment = fragment1;
            toolbar.setTitle("First display");
        } else if (position == 1) {
            curFragment = fragment2;
            toolbar.setTitle("Second display");
        } else if (position == 2) {
            curFragment = fragment3;
            toolbar.setTitle("Third display");
        } else if (position == 3) {
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).commit();
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

 */
}