package sg.com.nyp.a164936j.physioAssist.customadapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.Configuration.CustomSharedPreference;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.models.PatientProgression;

public class CustomPhysioProgressAdapter extends BaseAdapter {

    private Context context;
    private String[] videoList;

    private PatientProgression[] patientProgression;

    private ArrayList<String> progressionArrayListTitle;
    private ArrayList<String> progressionArrayListNoOfPetals;
    private PatientProgression[] videoSelected;
    private PatientProgression[] petalsSelected;

    private static LayoutInflater inflater = null;

    /*public CustomPhysioProgressAdapter(Context context, String[] videos) {
        this.context = context;
        this.videoList = videos;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/

    public CustomPhysioProgressAdapter(Context context, PatientProgression[] patientProgression) {
        this.context = context;
        this.patientProgression = patientProgression;

        progressionArrayListTitle = new ArrayList<>();
        progressionArrayListNoOfPetals = new ArrayList<>();
        videoSelected = new PatientProgression[patientProgression.length];
        petalsSelected = new PatientProgression[patientProgression.length];

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

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

    public class ViewHolder{
        CheckBox ckBoxVideo;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        View rowView;

        if(convertView == null){
            rowView = inflater.inflate(R.layout.layout_physio_progress_video_check_item, null);
            holder.ckBoxVideo = rowView.findViewById(R.id.physio_progress_checkbox);

            //holder.ckBoxVideo.setText(videoList[position]);
            holder.ckBoxVideo.setText(patientProgression[position].getExerciseTitle());

            /*holder.ckBoxVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Log.d(Config.TAG_BUTTON, "(CustomPhysioProgressAdapter) ckBoxVideo: " + videoList[position]);
                    Log.d(Config.TAG_BUTTON, "(CustomPhysioProgressAdapter) ckBoxVideo: " + patientProgression[position].getExerciseTitle());
                }
            });*/



            holder.ckBoxVideo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked){
                        progressionArrayListTitle.add(patientProgression[position].getExerciseTitle());
                        progressionArrayListNoOfPetals.add(patientProgression[position].getNoOfPetals());
                        Log.d(Config.TAG_BUTTON, "(CustomPhysioProgressAdapter) ckBoxVideo(Select): " + patientProgression[position].getExerciseTitle() + " Exercise Routine: " + patientProgression[position].getNoOfPetals());
                    }else{
                        progressionArrayListTitle.remove(patientProgression[position].getExerciseTitle());
                        progressionArrayListNoOfPetals.remove(patientProgression[position].getNoOfPetals());
                        Log.d(Config.TAG_BUTTON, "(CustomPhysioProgressAdapter) ckBoxVideo(De-select): " + patientProgression[position].getExerciseTitle() + " Exercise Routine: " + patientProgression[position].getNoOfPetals());
                    }

                    videoSelected[0] = new PatientProgression();
                    videoSelected[0].setSelectedExercise(progressionArrayListTitle);

                    petalsSelected[0] = new PatientProgression();
                    petalsSelected[0].setSelectedNoOfPetals(progressionArrayListNoOfPetals);

                    CustomSharedPreference.patientVideoSelection = videoSelected;
                    CustomSharedPreference.patientPetalsSelection = petalsSelected;
                }
            });

        }else{
            rowView = convertView;
        }

        return rowView;
    }


}
