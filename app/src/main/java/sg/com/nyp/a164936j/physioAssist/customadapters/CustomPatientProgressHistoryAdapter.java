package sg.com.nyp.a164936j.physioAssist.customadapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.models.PatientProgression;

public class CustomPatientProgressHistoryAdapter extends BaseAdapter {

    private Context context;
    private String[][] exerciseHistory;
    private PatientProgression[] patientProgression;

    private static LayoutInflater inflater = null;

    /*public CustomPatientProgressHistoryAdapter(Context context, String[][] titleHeader) {
        this.context = context;
        this.exerciseHistory = titleHeader;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/

    public CustomPatientProgressHistoryAdapter(Context context, PatientProgression[] patientProgression) {
        this.context = context;
        this.patientProgression = patientProgression;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ViewHolder{
        TextView exerciseHeader;
        GridView gridView;
    }

    /*@Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        View rowView;

        rowView = inflater.inflate(R.layout.layout_progress_history, null);
        holder.exerciseHeader = rowView.findViewById(R.id.progress_history_item_header);
        holder.gridView = rowView.findViewById(R.id.progress_history_gridview);

        holder.exerciseHeader.setText(exerciseHistory[0][position]);

        //TODO: populate gridview with images

        Log.d(Config.TAG_CUSTOM_ADAPTER, "(CustomPatientProgressHistoryAdapter) in getView");

        //change petals with proper array
        CustomPatientHistoryGridViewAdapter adapter = new CustomPatientHistoryGridViewAdapter(context, Integer.parseInt(exerciseHistory[1][position]));
        holder.gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        holder.gridView.setAdapter(adapter);


        return rowView;
    }*/

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        View rowView;

        rowView = inflater.inflate(R.layout.layout_progress_history, null);
        holder.exerciseHeader = rowView.findViewById(R.id.progress_history_item_header);
        holder.gridView = rowView.findViewById(R.id.progress_history_gridview);

        //holder.exerciseHeader.setText(patientProgression.length);
        holder.exerciseHeader.setText(patientProgression[position].getExerciseTitle());

        //TODO: populate gridview with images

        Log.d(Config.TAG_CUSTOM_ADAPTER, "(CustomPatientProgressHistoryAdapter) in getView");

        //change petals with proper array
        CustomPatientHistoryGridViewAdapter adapter = new CustomPatientHistoryGridViewAdapter
                (
                        context,
                        Integer.parseInt(patientProgression[position].getNoOfPetals())
                );
        holder.gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        holder.gridView.setAdapter(adapter);


        return rowView;
    }

    /*@Override
    public int getCount() {
        return exerciseHistory[0].length;
    }*/

    @Override
    public int getCount() {
        return patientProgression.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



}
