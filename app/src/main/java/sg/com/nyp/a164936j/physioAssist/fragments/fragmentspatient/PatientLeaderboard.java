package sg.com.nyp.a164936j.physioAssist.fragments.fragmentspatient;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.customadapters.CustomPatientLeaderboardAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientLeaderboard extends Fragment{

    ListView lv1, lv2;
    Context context;

    //vars
    private static String[] positions;
    private static String[] codeNames;
    private static int[] points;


    public PatientLeaderboard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_patient_leaderboard, container, false);

        //generate dummy data
        generateDummyData();

        context = getContext();

        lv1 = rootView.findViewById(R.id.customListView1);
        lv1.setAdapter(new CustomPatientLeaderboardAdapter(context, positions, codeNames, points));

        return rootView;
    }

    public static void generateDummyData(){
        positions = new String[]
                {
                    "1st",
                    "2nd",
                    "3rd",
                    "4th",
                    "5th"
                };
        codeNames = new String[]
                {
                    "codename1",
                    "codename2",
                    "codename3",
                    "codename4",
                    "codename5"
                };
        points = new int[]
                {
                    1000,
                    2000,
                    3000,
                    4000,
                    5000
                };
    }

}
