package sg.com.nyp.a164936j.physioAssist.tutorials.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.customadapters.CustomPatientExerciseAdapter;
import sg.com.nyp.a164936j.physioAssist.fragments.Interfaces.CustomOnClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialExerciseVideo extends Fragment implements CustomOnClickListener {

    ListView lv1, lv2;
    Context context;

    CustomPatientExerciseAdapter adapter;


    //vars
    private static String[] exerciseThumbnail1, exerciseThumbnail2;
    private static String[] exerciseList1;
    private static String[] exerciseList2;

    public TutorialExerciseVideo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_tutorial_exercise_video, container, false);

        //generate dummy data
        generateDummyData();

        context = getContext();
        lv1 = rootView.findViewById(R.id.patient_customListView1);
        adapter = new CustomPatientExerciseAdapter(context, exerciseList1, exerciseThumbnail1);
        adapter.setThumbnailClickListener(this);
        lv1.setAdapter(adapter);

        lv2 = rootView.findViewById(R.id.patient_customListView2);
        adapter = new CustomPatientExerciseAdapter(context, exerciseList2, exerciseThumbnail2);
        adapter.setThumbnailClickListener(this);
        lv2.setAdapter(adapter);

        return  rootView;
    }

    public static void generateDummyData(){
        exerciseThumbnail1 = new String[]{"ankle_pump_lying", "hip_side-slides_left"};
        exerciseList1 = new String[]{"Ankle Pump (lying)","Hip Side-Slides (left)"};

        exerciseThumbnail2 = new String[]{"deep_breathing", "upper_limb_strengthen"};
        exerciseList2 = new String[]{"Deep Breathing","Upper Limb Strengthen"};
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
}
