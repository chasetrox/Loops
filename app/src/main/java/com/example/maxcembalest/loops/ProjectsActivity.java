package com.example.maxcembalest.loops;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.example.maxcembalest.loops.adapter.ProjectPagerAdapter;
import com.example.maxcembalest.loops.adapter.ProjectRecyclerAdapter;
import com.example.maxcembalest.loops.fragments.FragmentMessage;
import com.example.maxcembalest.loops.grid.LoopGrid;
import com.example.maxcembalest.loops.grid.ToneMatrix;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.concurrent.TimeUnit;

import com.example.maxcembalest.loops.usermodel.User;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.List;

public class ProjectsActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String KEY_MSG = "KEY_MSG";
    private static final String MSG_FRAGMENT = "MSG_FRAGMENT";

    private TextView tvUsername;
    private ProjectRecyclerAdapter projectRecyclerAdapter;
    private RecyclerView projectRecycler;
    int time = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        setupUI();
    }

    private void initPager() {
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new ProjectPagerAdapter(getSupportFragmentManager()));
    }

    private void setupNavUsername() {
        List<User> users = User.listAll(User.class);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        tvUsername = (TextView) header.findViewById(R.id.tvUsername);
        tvUsername.setText(users.get(0).getUsername());
    }

    private void setupUI() {
        Toolbar toolbar = setupToolbar();
        setupDrawer(toolbar);
        setupNavView();
        initPager();
    }

    private Toolbar setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void setupNavView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupDrawer(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.projects, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_about) {
            //Toast.makeText(this,"Message fragment with info and help",Toast.LENGTH_SHORT).show();
            startFragMsg("Welcome to Loops!  \n" +
                    "Interact with a tone matrix to edit, save, and share loops generated live on your phone.\n" +
                    "\n" +
                    "To get started, go to the Loop Editor and tap the grid, then hit play!  \n" +
                    "To change the dimension of the grid and the duration of tones, use the buttons to the side.\n");
        } else if (id == R.id.nav_contact) {
            //Toast.makeText(this,"Message fragment with our emails and github links",Toast.LENGTH_SHORT).show();
            startFragMsg("Loops © 2016\n" +
                    "AIT Budapest – Android Mobile Woftware Development\n" +
                    "Max Cembalest : mcembalest@wesleyan.edu\n" +
                    "Chase Troxell : chasetrox@gmail.com\n" +
                    "\n" +
                    "https://github.com/chasetrox/Loops\n" +
                    "\n" +
                    "Thank you to http://tonematrix.audiotool.com/ for the inspiration\n");
        } else if (id == R.id.nav_editor) {
            startActivity(new Intent(ProjectsActivity.this,LoopActivity.class));
        } else if (id == R.id.nav_logout) {
            //Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show();
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        SugarContext.terminate();
        SchemaGenerator schemaGenerator = new SchemaGenerator(getApplicationContext());
        schemaGenerator.deleteTables(new SugarDb(getApplicationContext()).getDB());
        SugarContext.init(getApplicationContext());
        schemaGenerator.createDatabase(new SugarDb(getApplicationContext()).getDB());
        startActivity(new Intent(ProjectsActivity.this,LoginActivity.class));
    }

    private void startFragMsg(String s) {
        FragmentMessage fragmentMessage = new FragmentMessage();
        fragmentMessage.setCancelable(true);

        Bundle bundle = new Bundle();
        bundle.putString(KEY_MSG,s);
        fragmentMessage.setArguments(bundle);

        fragmentMessage.show(getSupportFragmentManager(),MSG_FRAGMENT);
    }
}
