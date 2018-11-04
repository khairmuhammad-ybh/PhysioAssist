package sg.com.nyp.a164936j.physioAssist.fragments.fragmentspatient;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.VideoView;

import java.io.File;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.customadapters.CustomPatientExerciseAdapter;
import sg.com.nyp.a164936j.physioAssist.fragments.Interfaces.CustomOnClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientExercise extends Fragment implements CustomOnClickListener {

    ListView lv1, lv2;
    Context context;

    CustomPatientExerciseAdapter adapter;


    //vars
    private static String[] exerciseThumbnail1, exerciseThumbnail2;
    private static String[] exerciseList1;
    private static String[] exerciseList2;

    //Video
    VideoView videoView;
    static Dialog dialog;
    Button btnQuit, btnSkip;


    public PatientExercise() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_patient_exercise, container, false);

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

        return rootView;
    }

    public static void generateDummyData(){
        exerciseThumbnail1 = new String[]
                {
                    "ankle_pump_lying",
                    "hip_side-slides_left",
                    "inner_range_quadricep_right-hold"
                };
        exerciseList1 = new String[]
                {
                    "Ankle Pump (lying)",
                    "Hip Side-Slides (left)",
                    "Inner Range Quadriceps (right - hold)"
                };

        exerciseThumbnail2 = new String[]
                {
                    "deep_breathing",
                    "upper_limb_strengthen",
                    "ankle_pump_sitting",
                    "long_arc_quad_left"
                };
        exerciseList2 = new String[]
                {
                    "Deep Breathing",
                    "Upper Limb Strengthen",
                    "Ankle Pump (sitting)",
                    "Long Arc Quad (left)"
                };
    }

    @Override
    public void onThumbnailClick(int parentId, int position) {
        //Toast.makeText(context, "Button position #"+ position + " (from parent)", Toast.LENGTH_SHORT).show();
        Log.d(Config.TAG_BUTTON, "(PatientExercise) Exercise thumbnail position #" + position);

        File sdPath = Environment.getExternalStorageDirectory();
        String videoUri;
        if(parentId == 2131296414){
            videoUri = sdPath.getAbsolutePath() + "/PhysioAssist/Video/" + exerciseList1[position]+ ".mp4";
            Log.d(Config.TAG_BUTTON,"(PatientExercise) externalSdCard: " + videoUri);
        }else{
            videoUri = sdPath.getAbsolutePath() + "/PhysioAssist/Video/" + exerciseList2[position]+ ".mp4";
            Log.d(Config.TAG_BUTTON,"(PatientExercise) externalSdCard: " + videoUri);
        }

        videoScreen(videoUri);

    }

    @Override
    public void onAchievementImgClick(int position) {

    }

    @Override
    public void onTrophyImgClick(int position) {

    }

    private void videoScreen(String videoUri){
        //Create custom dialog object
        dialog = new Dialog(context);

        //Include dialog.xml
        dialog.setContentView(R.layout.dialog_video_screen);
        //Set dialog title
        dialog.setTitle("Exercise Demo");

        //Set values for custom dialog components
        videoView = dialog.findViewById(R.id.videoView1);
        new fetchVideo().execute(videoUri);

        btnSkip = dialog.findViewById(R.id.btnSkipVideo);
        btnQuit = dialog.findViewById(R.id.btnQuitVideo);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Close dialog
                dialog.dismiss();

                //Start new fragment
                Log.d(Config.TAG_BUTTON, "(PatientExercise) Skip Exercise");
                //TODO: Start fragment to start exercise
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //close dialog
                Log.d(Config.TAG_BUTTON, "(PatientExercise) Quit Exercise");
                dialog.dismiss();
            }
        });

    }

    private class fetchVideo extends AsyncTask<String, Void, Uri> {

        ProgressDialog progressDialog = new ProgressDialog(context);

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading.. Please wait");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Uri result) {
            //dismiss loading screen
            progressDialog.dismiss();

            //Play video
            videoView.setVideoURI(result);
            videoView.setZOrderOnTop(true);
            videoView.requestFocus();
            videoView.start();

            //Adjust dialogWindow (width x Height)
            Window dialogWindow = dialog.getWindow();
            WindowManager wm = getActivity().getWindowManager();
            Display d = wm.getDefaultDisplay();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();


            lp.height = (int) (d.getHeight() * 0.8f);
            lp.width = (int) (d.getWidth() * 0.9f);
            dialogWindow.setAttributes(lp);

            dialog.show();

        }

        @Override
        protected Uri doInBackground(String... params) {

            return Uri.parse(params[0]);
        }


    }
}
