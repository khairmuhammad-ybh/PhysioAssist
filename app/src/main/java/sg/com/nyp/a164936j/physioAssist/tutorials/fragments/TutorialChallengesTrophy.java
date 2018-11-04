package sg.com.nyp.a164936j.physioAssist.tutorials.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.customadapters.RecyclerViewPatientChallengeAdapter;
import sg.com.nyp.a164936j.physioAssist.fragments.Interfaces.CustomOnClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialChallengesTrophy extends Fragment implements CustomOnClickListener, RecyclerViewPatientChallengeAdapter.ItemClickListener{

    Context context;
    RecyclerViewPatientChallengeAdapter adapter;


    //vars
    private static String[][] twoDArray;

    public TutorialChallengesTrophy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_tutorial_challenges_trophy, container, false);

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

        /**
         * Close Tutorials
         */
        Button btnClose = rootView.findViewById(R.id.patient_tutorial_btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

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

    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d(Config.TAG_BUTTON,"(TutorialChallengesTrophy) Recycler View pos:" + adapter.getItem(position));
        onTrophyImgClick(position);
    }

}
