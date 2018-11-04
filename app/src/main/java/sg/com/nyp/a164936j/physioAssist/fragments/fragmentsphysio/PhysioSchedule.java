package sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.customadapters.CustomPhysioScheduleAdapter;
import sg.com.nyp.a164936j.physioAssist.models.PhysioScheduler;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhysioSchedule extends Fragment implements View.OnClickListener {

    Context context;
    ListView lv;

    CustomPhysioScheduleAdapter adapter;

    //vars - Listview
    private static String[] timeList;
    private static String[] videoList1, videoList2, videoList3;

    //Vars - Spinner
    Spinner spinner;
    private static List<String> frequentList = new ArrayList<>();

    Button btnAdd;

    //new generated dummy data from model
    private static PhysioScheduler[] physioScheduler;

    public PhysioSchedule() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_physio_schedule, container, false);

        //generate dummy data for listview & spinner
        generateDummyData();

        //populate listview
        context = getContext();
        lv = rootView.findViewById(R.id.physio_schedule_listview);
        //adapter = new CustomPhysioScheduleAdapter(context, timeList, videoList);
        adapter = new CustomPhysioScheduleAdapter(context, physioScheduler);
        lv.setAdapter(adapter);

        //populate spinner
        spinner = rootView.findViewById(R.id.physio_schedule_frequency_spinner);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, frequentList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        //add button listener
        btnAdd = rootView.findViewById(R.id.physio_schedule_btn_add);
        btnAdd.setOnClickListener(this);

        return rootView;
    }

    public static void generateDummyData(){
        timeList = new String[]{"11:00 AM","2:00 PM","5:00 PM"};
        videoList1 = new String[]{"Long Arc Quad (left)"};
        videoList2 = new String[]{"Upper Limb Strengthen","Ankle Pump (sitting)", "Long Arc Quad (left)"};
        videoList3 = new String[]{"Deep Breathing", "Long Arc Quad (left)"};

        physioScheduler = new PhysioScheduler[timeList.length];

        for(int i=0;i<timeList.length;i++){
            physioScheduler[i] = new PhysioScheduler();
            physioScheduler[i].setTimeList(timeList[i]);
            if(i==0)
                physioScheduler[i].setVideoList(videoList1);
            else if(i==1)
                physioScheduler[i].setVideoList(videoList2);
            else if(i==2)
                physioScheduler[i].setVideoList(videoList3);
        }

        frequentList.add("One Session");
        frequentList.add("Two Session");
        frequentList.add("Three Session");
    }

    @Override
    public void onClick(View view) {
        /**
         * Add video to selected session
         */
        String selectedSession = String.valueOf(spinner.getSelectedItem());
        Log.d(Config.TAG_BUTTON, "(PhysioSchedule) add button pressed");
    }
}
