package com.example.maxcembalest.loops;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.maxcembalest.loops.adapter.ProjectRecyclerAdapter;
import com.example.maxcembalest.loops.usermodel.User;

import java.util.List;

public class ProjectsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ProjectRecyclerAdapter projectRecyclerAdapter;
    private RecyclerView projectRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        setupUI();

        projectRecycler = (RecyclerView) findViewById(
                R.id.projectRecycler);
        projectRecycler.setHasFixedSize(true);
        projectRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        projectRecyclerAdapter = new ProjectRecyclerAdapter();
        projectRecycler.setAdapter(projectRecyclerAdapter);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Toast.makeText(this,"Message fragment with info and help",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send_loop) {
            Toast.makeText(this,"Database stuff",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_contact) {
            Toast.makeText(this,"Message fragment with our emails and github links",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_editor) {
            startActivity(new Intent(ProjectsActivity.this,LoopActivity.class));
        } else if (id == R.id.nav_logout) {
            //Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show();
            List<User> users = User.listAll(User.class);
            users.clear();
            startActivity(new Intent(ProjectsActivity.this,LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
