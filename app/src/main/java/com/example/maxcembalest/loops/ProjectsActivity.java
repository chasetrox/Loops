package com.example.maxcembalest.loops;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxcembalest.loops.adapter.ProjectRecyclerAdapter;
import com.example.maxcembalest.loops.grid.ToneMatrix;
import com.example.maxcembalest.loops.usermodel.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProjectsActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String MSG_FRAGMENT = "MSG_FRAGMENT";
    private TextView tvUsername;
    private ProjectRecyclerAdapter projectRecyclerAdapter;
    private RecyclerView projectRecycler;
    int time = 500;

    public static final String KEY_MSG = "KEY_MSG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        setupUI();
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query q = FirebaseDatabase.getInstance().getReference().child("users/"+user+"/loops/");

        setupRecycler(q);
        setupNavUsername();
    }

    private void setupRecycler(Query q) {

        projectRecycler = (RecyclerView) findViewById(
                R.id.projectRecycler);
        projectRecycler.setHasFixedSize(true);
        projectRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        projectRecyclerAdapter = new ProjectRecyclerAdapter(q, ToneMatrix.class);
        projectRecyclerAdapter.applyListener();

        try { //Janky way to ensure data loads.
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        projectRecycler.setAdapter(projectRecyclerAdapter);
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
            startFragMsg("About");
        } else if (id == R.id.nav_send_loop) {
            Toast.makeText(this,"Database stuff",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_contact) {
            //Toast.makeText(this,"Message fragment with our emails and github links",Toast.LENGTH_SHORT).show();
            startFragMsg("Contact");
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
