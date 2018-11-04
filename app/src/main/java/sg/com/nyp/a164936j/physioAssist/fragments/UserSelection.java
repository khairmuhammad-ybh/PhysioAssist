package sg.com.nyp.a164936j.physioAssist.fragments;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.Configuration.CustomSharedPreference;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.fragments.Interfaces.CustomOnClickListener;
import sg.com.nyp.a164936j.physioAssist.fragments.fragmentspatient.PatientActivities;
import sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio.PhysioLogin;
import sg.com.nyp.a164936j.physioAssist.models.PatientProgression;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSelection extends Fragment implements View.OnClickListener, CustomOnClickListener {

    //Vars - patient's history progression
    private static String[][] exerciseHistory;
    private static PatientProgression[] patientProgression;


    public UserSelection() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_selection, container, false);

        //generate patient's history progression dummy data
        generatePatientHistory();

        Button btnPatient = rootView.findViewById(R.id.btnPatient);
        Button btnPhysio = rootView.findViewById(R.id.btnPhysio);

        if(!checkPermission()){
            requestPermission();
        }else{
            Log.d(Config.TAG_PERMISSION, "(UserSelection.java) Permission already granted");
        }


        btnPatient.setOnClickListener(this);
        btnPhysio.setOnClickListener(this);

        return rootView;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE}, Config.PERMS_REQUEST_CODE);
        Log.d(Config.TAG_PERMISSION,"(UserSelection.java) In requestPermission");
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE);
        Log.d(Config.TAG_PERMISSION,"(UserSelection.java) In checkPermission");

        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onClick(View v) {

        FragmentManager manager = getActivity().getSupportFragmentManager();

        switch (v.getId()){
            case R.id.btnPatient:
                Log.d(Config.TAG_BUTTON, "(UserSelection) Patient button are being pressed");

                PatientActivities patientActivities = new PatientActivities();

                manager.beginTransaction()
                        .replace(R.id.fragment_container, patientActivities)
                        .addToBackStack("tag")
                        .commit();

                break;
            case R.id.btnPhysio:
                Log.d(Config.TAG_BUTTON, "(UserSelection) Physiotherapist button are being pressed");

                PhysioLogin physioLogin = new PhysioLogin();

                manager.beginTransaction()
                        .replace(R.id.fragment_container, physioLogin).addToBackStack("tag")
                        .commit();

                break;
        }
    }

    @Override
    public void onThumbnailClick(int parentId, int position) {

    }

    @Override
    public void onAchievementImgClick(int position) {

    }

    @Override
    public void onTrophyImgClick(int position) {

    }

    public void generatePatientHistory(){
        //History
        exerciseHistory = new String[][]{
                {
                        "Deep Breathing",
                        "Single Leg Bridging",
                        "Quads",
                        "Knee Bending"
                },
                {
                        "2",
                        "1",
                        "3",
                        "5"
                }
        };

        int pCount = exerciseHistory[0].length;
        patientProgression = new PatientProgression[pCount];

        for(int i=0;i<pCount;i++){
            patientProgression[i] = new PatientProgression();
            //set exercise title
            patientProgression[i].setExerciseTitle(exerciseHistory[0][i]);
            //set no of petals
            patientProgression[i].setNoOfPetals(exerciseHistory[1][i]);
        }

        CustomSharedPreference.patientProgressions = patientProgression;
    }
}
