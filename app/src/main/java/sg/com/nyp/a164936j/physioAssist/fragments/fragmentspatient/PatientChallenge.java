package sg.com.nyp.a164936j.physioAssist.fragments.fragmentspatient;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.customadapters.RecyclerViewPatientChallengeAdapter;
import sg.com.nyp.a164936j.physioAssist.fragments.Interfaces.CustomOnClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientChallenge extends Fragment implements CustomOnClickListener, RecyclerViewPatientChallengeAdapter.ItemClickListener {

    Context context;

    //CustomPatientChallengeAdapter adapter;
    RecyclerViewPatientChallengeAdapter adapter;

    //Dialog
    Dialog dialog;
    TextView achievementDesc;

    //vars
    private static String[][] twoDArray;


    public PatientChallenge() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.patient_challenge_gridlayout, container, false);

        //generate dummy data
        generateDummyData();

        /**
         * populate the layout
         */
        //Set up the RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.grid_recyclerView);
        int numOfColumn = 5;
        recyclerView.setLayoutManager(new GridLayoutManager(context,numOfColumn));
        adapter = new RecyclerViewPatientChallengeAdapter(getContext(), twoDArray);

        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public static void generateDummyData(){

        twoDArray = new String[][]{
                {
                        "Challenge_Desc_1",
                        "Challenge_Desc_2",
                        "Challenge_Desc_3",
                        "Challenge_Desc_4",
                        "Challenge_Desc_5",

                        "Challenge_Desc_6",
                        "Challenge_Desc_7",
                        "Challenge_Desc_8",
                        "Challenge_Desc_9",
                        "Challenge_Desc_10",

                        "Challenge_Desc_11",
                        "Challenge_Desc_12",
                        "Challenge_Desc_13",
                        "Challenge_Desc_14",
                        "Challenge_Desc_15",

                        "Challenge_Desc_16",
                        "Challenge_Desc_17",
                        "Challenge_Desc_18",
                        "Challenge_Desc_19",
                        "Challenge_Desc_20",
                },
                {
                        "checked",
                        "checked",
                        "uncheck",
                        "checked",
                        "checked",

                        "checked",
                        "uncheck",
                        "checked",
                        "checked",
                        "checked",

                        "uncheck",
                        "uncheck",
                        "uncheck",
                        "uncheck",
                        "uncheck",

                        "uncheck",
                        "uncheck",
                        "uncheck",
                        "uncheck",
                        "uncheck",
                }
        };

    }

    @Override
    public void onThumbnailClick(int parentId, int position) {

    }

    @Override
    public void onAchievementImgClick(int position) {

    }

    @Override
    public void onTrophyImgClick(int position) {
        Log.d(Config.TAG_BUTTON, "(PatientChallenge) onTrophyImgClick:" + position + " [listen from parent]");
        //Display dialog

        //Create custom dialog object
        dialog = new Dialog(getActivity());

        //Include dialog.xml (Change dialog layout)
        dialog.setContentView(R.layout.dialog_achievement_info);
        //Set dialog title
        dialog.setTitle("Achievement Info");

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        /**
         * Required to create custom layout for data to be populated in dialog
         * Set values for custom dialog components
         */
        achievementDesc = dialog.findViewById(R.id.achievement_desc);
        achievementDesc.setText(twoDArray[0][position]);

        /**
         * Set onClickedListener if any
         */

        dialog.show();
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d(Config.TAG_BUTTON,"(PatientChallenge) Recycler View pos:" + adapter.getItem(position));
        onTrophyImgClick(position);
    }
}
