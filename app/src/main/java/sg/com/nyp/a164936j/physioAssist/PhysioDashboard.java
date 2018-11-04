package sg.com.nyp.a164936j.physioAssist;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio.PhysioDashboardOverlay;
import sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio.PhysioProgress;
import sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio.PhysioProgressBarGraph;
import sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio.PhysioRegisterPatient;
import sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio.PhysioSchedule;

public class PhysioDashboard extends AppCompatActivity implements PhysioProgress.ItemClickListener {

    private DrawerLayout mDrawerLayout;

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physio_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        PhysioDashboardOverlay physioDashboardOverlay = new PhysioDashboardOverlay();

        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, physioDashboardOverlay, physioDashboardOverlay.getTag()).commit();

        NavigationView navigationView = findViewById(R.id.nav_view);

        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = hView.findViewById(R.id.nav_header);

        //Set User's name on navigation drawer's header
        nav_user.setText("Physiotherapy");

        nav_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Nav header text selected", Toast.LENGTH_SHORT).show();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //set item as  selected  to persist highlight
                item.setChecked(true);
                //close drawer when item is tapped
                mDrawerLayout.closeDrawers();

                manager = getSupportFragmentManager();

                //Add code here to update the UI based on the item selected
                //For example, swap UI fragments here
                int id = item.getItemId();
                if(id == R.id.nav_home){
                    callFragment("physioDashboardOverlay");

                }else if(id == R.id.nav_reg_patient){
                    callFragment("registerPatient");

                }else if(id == R.id.nav_view_progress){
                    callFragment("progress");

                }else if(id == R.id.nav_view_schedule){
                    callFragment("schedule");

                }else if(id == R.id.nav_logout){

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

                }

                return true;
            }
        });
    }

    private void callFragment(String fragment){
        switch (fragment){
            case "physioDashboardOverlay":
                PhysioDashboardOverlay physioDashboardOverlay = new PhysioDashboardOverlay();
                manager.beginTransaction()
                        .replace(R.id.fragment_container, physioDashboardOverlay)
                        .addToBackStack("tag")
                        .commit();
                break;
            case "registerPatient":
                PhysioRegisterPatient registerPatient = new PhysioRegisterPatient();
                manager.beginTransaction()
                        .replace(R.id.fragment_container, registerPatient)
                        .addToBackStack("tag")
                        .commit();
                break;
            case "progress":
                PhysioProgress progress = new PhysioProgress();
                progress.setClickListener(this);
                manager.beginTransaction()
                        .replace(R.id.fragment_container, progress)
                        .addToBackStack("tag")
                        .commit();
                break;
            case "schedule":
                PhysioSchedule schedule = new PhysioSchedule();
                manager.beginTransaction()
                        .replace(R.id.fragment_container, schedule)
                        .addToBackStack("tag")
                        .commit();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick() {

        //Call stacked bar graph fragment
        PhysioProgressBarGraph physioProgressBarGraph = new PhysioProgressBarGraph();

        manager.beginTransaction()
                .replace(R.id.fragment_container, physioProgressBarGraph)
                .addToBackStack("tag")
                .commit();
    }
}
