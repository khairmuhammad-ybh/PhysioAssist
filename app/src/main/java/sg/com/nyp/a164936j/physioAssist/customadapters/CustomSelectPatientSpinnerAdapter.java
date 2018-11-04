package sg.com.nyp.a164936j.physioAssist.customadapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.models.PatientCodename;

public class CustomSelectPatientSpinnerAdapter extends ArrayAdapter<PatientCodename> {

    private LayoutInflater flater;

    public CustomSelectPatientSpinnerAdapter(@NonNull Activity context, int resource, @NonNull List<PatientCodename> list) {
        super(context, resource, list);
        flater = context.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        PatientCodename patientCodename = getItem(position);

        View rowView = flater.inflate(R.layout.select_patient_spinner_item, null,true);

        TextView tv = rowView.findViewById(R.id.select_patient_codename);
        tv.setText(patientCodename.getCodename());

        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = flater.inflate(R.layout.select_patient_spinner_item, parent, false);
        }
        PatientCodename patientCodename = getItem(position);
        TextView tv = convertView.findViewById(R.id.select_patient_codename);
        tv.setText(patientCodename.getCodename());

        return convertView;
    }
}
