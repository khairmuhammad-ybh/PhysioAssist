package sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.customadapters.CustomSelectPatientSpinnerAdapter;
import sg.com.nyp.a164936j.physioAssist.models.PatientCodename;


public class PhysioRegisterPatient extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    List<PatientCodename> codename = new ArrayList<>();

    private Spinner customSpinner;
    private Button btn_register;

    private  String selected;

    public PhysioRegisterPatient(){
        //Empty Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_physio_register_patient, container, false);

        // Populate dummy data
        generateDummyData();

        // Attach views
        customSpinner = rootView.findViewById(R.id.patient_codename);
        btn_register = rootView.findViewById(R.id.btnPatientRegister);


        CustomSelectPatientSpinnerAdapter customSelectPatientSpinnerAdapter = new CustomSelectPatientSpinnerAdapter(getActivity(), R.layout.select_patient_spinner_item, codename);
        customSpinner.setAdapter(customSelectPatientSpinnerAdapter);

        customSpinner.setOnItemSelectedListener(this);
        btn_register.setOnClickListener(this);

        return rootView;
    }

    // Spinner Drop down elements
    private void generateDummyData(){
        PatientCodename selector = new PatientCodename();

        selector.setCodename("Please select patient codename");
        selector.setName(null);

        codename.add(selector);

        for(int i=0;i<10;i++){

            PatientCodename patientCodename = new PatientCodename();

            patientCodename.setCodename("Patient #"+i);
            patientCodename.setName("Item_Name "+i);

            codename.add(patientCodename);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(!TextUtils.isEmpty(codename.get(i).getName()))
            selected = codename.get(i).getName();
        else
            selected = null;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if(!TextUtils.isEmpty(selected))
            Log.d(Config.TAG_BUTTON, "(PhysioRegisterPatient) Register patient: " + selected);
        else
            Log.d(Config.TAG_BUTTON, "(PhysioRegisterPatient) Register patient: No patient selected");
    }
}
