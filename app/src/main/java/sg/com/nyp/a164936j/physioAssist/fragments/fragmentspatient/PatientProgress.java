package sg.com.nyp.a164936j.physioAssist.fragments.fragmentspatient;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.Configuration.CustomSharedPreference;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.customadapters.CustomPatientGiftGridViewAdapter;
import sg.com.nyp.a164936j.physioAssist.customadapters.CustomPatientProgressHistoryAdapter;
import sg.com.nyp.a164936j.physioAssist.customadapters.CustomPatientTrophyGridViewAdapter;
import sg.com.nyp.a164936j.physioAssist.customcomponents.WrappingGridView;
import sg.com.nyp.a164936j.physioAssist.models.PatientProgression;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientProgress extends Fragment {

    //TODO: retrieve user info to be display in progress fragment
    Context context;
    Resources res;
    ListView progressHistoryListView;
    WrappingGridView progressTrophyGridView, progressGiftGridView;
    CustomPatientProgressHistoryAdapter adapterHistory;
    CustomPatientTrophyGridViewAdapter adapterTrophy;
    CustomPatientGiftGridViewAdapter adapterGift;
    String textFormat;
    TextView textPoints;
    int points;

    /**
     * data arrays
     */

    //History Vars
    /*private static String[] exerciseTitle;
    private static int[] noOfPetals;*/
    private static String[][] exerciseHistory;

    private static PatientProgression[] patientProgression;

    //Trophy Vars
    private static int trophyCollected;
    TextView totalTrophy;

    //Gifts Vars
    private static int[] giftReceived;

    public PatientProgress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_patient_progress, container, false);

        //generate dummy data
        generateDummyData();

        context = getContext();

        //instantiate resources
        res = getResources();

        //set point
        textPoints = rootView.findViewById(R.id.progress_top_score);
        points = 3140;
        textPoints.setText(String.valueOf(points));

        //set adapter for History
        progressHistoryListView = rootView.findViewById(R.id.patient_progress_history_listview);
        adapterHistory = new CustomPatientProgressHistoryAdapter
                (
                    context,
                    CustomSharedPreference.patientProgressions
                );
        progressHistoryListView.setAdapter(adapterHistory);

        //set adapter for Trophy
        progressTrophyGridView = rootView.findViewById(R.id.progress_trophy_gridview);
        adapterTrophy = new CustomPatientTrophyGridViewAdapter(context, trophyCollected);
        progressTrophyGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        progressTrophyGridView.setAdapter(adapterTrophy);

        //set text to trophy header for total trophy achieved
        totalTrophy = rootView.findViewById(R.id.progress_trophy_header);
        textFormat = String.format(res.getString(R.string.progress_trophy_header), trophyCollected);
        totalTrophy.setText(textFormat);

        //set adapter for Gifts
        progressGiftGridView = rootView.findViewById(R.id.progress_gift_gridview);
        adapterGift = new CustomPatientGiftGridViewAdapter(context, giftReceived);
        progressGiftGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        progressGiftGridView.setAdapter(adapterGift);

        return rootView;

    }

    public static void generateDummyData(){

        /**
         * patient History progression being instantiate & initialize at "UserSelection.java"
         */

        //Trophy
        trophyCollected = 6;

        //Gifts
        giftReceived = new int[]{
                R.drawable.achievement_well_done,
                R.drawable.achievement_good_job
        };
    }

}
