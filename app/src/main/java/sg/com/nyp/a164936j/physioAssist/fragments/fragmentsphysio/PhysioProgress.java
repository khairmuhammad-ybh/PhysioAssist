package sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.Configuration.CustomSharedPreference;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.customadapters.CustomPhysioProgressAdapter;
import sg.com.nyp.a164936j.physioAssist.customadapters.RecyclerViewPatientChallengeAdapter;
import sg.com.nyp.a164936j.physioAssist.fragments.Interfaces.CustomOnClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhysioProgress extends Fragment {

    private Context context;
    private ListView lv;
    private RadioGroup radioGroup;
    private EditText dateFrom, dateTo;
    private Button btnGraph;
    private TextView physio_progress_patient_name, physio_progress_video_label;

    private CustomPhysioProgressAdapter adapter;

    //vars - Spinner
    Spinner spinner;
    private static List<String> dayList = new ArrayList<>();

    private ItemClickListener mClickListener;


    public PhysioProgress() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_physio_progress, container, false);

        CustomSharedPreference.patientVideoSelection = null;

        //generate dummy data
        generateDummyData();

        //populate listview
        context = getContext();

        lv = rootView.findViewById(R.id.physio_progress_video_listview);
        //adapter = new CustomPhysioProgressAdapter(context, videoList);
        adapter = new CustomPhysioProgressAdapter(context, CustomSharedPreference.patientProgressions);
        lv.setAdapter(adapter);

        //populate spinner
        spinner = rootView.findViewById(R.id.physio_progress_day_spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dayList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setEnabled(false);
        spinner.setAdapter(dataAdapter);

        //instantiate EditText
        dateFrom = rootView.findViewById(R.id.physio_progress_date_from);
        dateTo = rootView.findViewById(R.id.physio_progress_date_to);

        dateFrom.setEnabled(false);
        dateTo.setEnabled(false);

        //add listener for RadioGroup
        radioGroup = rootView.findViewById(R.id.physio_progress_radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkRadio = group.findViewById(checkedId);
                boolean isChecked = checkRadio.isChecked();

                if(isChecked){
                    if(checkRadio.getText().equals("Day:")){
                        Log.d(Config.TAG_BUTTON, "(PhysioProgress) radio: "+ checkRadio.getText());
                        //enable spinner
                        spinner.setEnabled(true);

                        //disable edittext
                        dateFrom.setEnabled(false);
                        dateTo.setEnabled(false);
                    }else{
                        Log.d(Config.TAG_BUTTON, "(PhysioProgress) radio: "+ checkRadio.getText());
                        //disable spinner
                        spinner.setEnabled(false);

                        //enable edittext
                        dateFrom.setEnabled(true);
                        dateTo.setEnabled(true);
                    }
                }

            }
        });

        btnGraph = rootView.findViewById(R.id.physio_progress_btn_graph);
        physio_progress_patient_name = rootView.findViewById(R.id.physio_progress_patient_name);
        physio_progress_video_label = rootView.findViewById(R.id.physio_progress_video_label);

        btnGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: call fragment to generate grouped bar graph
                Log.d(Config.TAG_BUTTON, "(PhysioProgress) Btn Show Graph pressed");
                //mClickListener.onItemClick();
                if(CustomSharedPreference.patientVideoSelection == null)
                    Log.d(Config.TAG_BUTTON, "ArrayVideoSelected: Array is empty");
                else{
                    ArrayList<String> exercises = CustomSharedPreference.patientVideoSelection[0].getSelectedExercise();
                    ArrayList<String> petals = CustomSharedPreference.patientPetalsSelection[0].getSelectedNoOfPetals();
                    int sizeOfExercise = exercises.size();
                    int sizeOfPetals = petals.size();
                    if(sizeOfExercise == sizeOfPetals){
                        for(int i=0;i<sizeOfExercise;i++){
                            Log.d(Config.TAG_BUTTON,"EXERCISE: " + exercises.get(i) + " PETALS: " + petals.get(i));
                        }
                    }

                    mClickListener.onItemClick();
                }
            }
        });

        return rootView;
    }

    // allow clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }

    //parent activity will implement this method to respond to click events
    public interface ItemClickListener{
        //Call stacked bar graph fragment - passing to PhysioDashboard.java
        void onItemClick();
    }

    public static void generateDummyData(){

        dayList.clear();
        dayList.add("Today");
        dayList.add("Yesterday");
        dayList.add("Last 3 days");
    }

}
