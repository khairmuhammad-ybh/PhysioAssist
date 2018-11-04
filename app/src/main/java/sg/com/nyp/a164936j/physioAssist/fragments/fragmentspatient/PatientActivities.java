package sg.com.nyp.a164936j.physioAssist.fragments.fragmentspatient;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sg.com.nyp.a164936j.physioAssist.BlankCanvas;
import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.tutorials.Tutorials;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientActivities extends Fragment implements View.OnClickListener{

    //vars
    Button btnExercise, btnProgress, btnChallenges, btnLeaderboard;

    public PatientActivities() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_patient_activities, container, false);

        Button btnHome = rootView.findViewById(R.id.patient_activity_btnBack);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Config.TAG_BUTTON, "(PatientActivities) btnHome");
                getActivity().onBackPressed();
            }
        });

        Button btnTutorial = rootView.findViewById(R.id.patient_activity_btnTutorial);
        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Config.TAG_BUTTON, "(PatientActivities) btnTutorial");
                //TODO: Create Guide Step Tutorial
                startActivity(new Intent(getContext(), Tutorials.class));
            }
        });

        btnExercise = rootView.findViewById(R.id.patient_activity_btnExercise);
        btnChallenges = rootView.findViewById(R.id.patient_activity_btnChallenges);
        btnLeaderboard = rootView.findViewById(R.id.patient_activity_btnLeaderboard);
        btnProgress = rootView.findViewById(R.id.patient_activity_btnProgress);

        btnExercise.setOnClickListener(this);
        btnProgress.setOnClickListener(this);
        btnLeaderboard.setOnClickListener(this);
        btnChallenges.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), BlankCanvas.class);
        switch(view.getId()){
            case R.id.patient_activity_btnExercise:
                intent.putExtra("BTNTYPE", "exercise");
                startActivity(intent);
                break;
            case R.id.patient_activity_btnChallenges:
                intent.putExtra("BTNTYPE", "challenge");
                startActivity(intent);
                break;
            case R.id.patient_activity_btnLeaderboard:
                intent.putExtra("BTNTYPE", "leaderboard");
                startActivity(intent);
                break;
            case R.id.patient_activity_btnProgress:
                intent.putExtra("BTNTYPE", "progress");
                startActivity(intent);
                break;
        }
    }
}
