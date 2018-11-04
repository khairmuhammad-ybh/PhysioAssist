package sg.com.nyp.a164936j.physioAssist.tutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sg.com.nyp.a164936j.physioAssist.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialLeaderboard extends Fragment {


    public TutorialLeaderboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial_leaderboard, container, false);
    }

}
