package sg.com.nyp.a164936j.physioAssist;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.customadapters.CustomPatientLeaderboardSendGiftAdapter;
import sg.com.nyp.a164936j.physioAssist.fragments.Interfaces.CustomOnClickListener;
import sg.com.nyp.a164936j.physioAssist.fragments.fragmentspatient.PatientChallenge;
import sg.com.nyp.a164936j.physioAssist.fragments.fragmentspatient.PatientExercise;
import sg.com.nyp.a164936j.physioAssist.fragments.fragmentspatient.PatientLeaderboard;
import sg.com.nyp.a164936j.physioAssist.fragments.fragmentspatient.PatientProgress;

public class BlankCanvas extends AppCompatActivity implements CustomOnClickListener {

    //Widget
    String btnType;

    RelativeLayout frameLayout;

    //var
    Button btn_back, btn_send_gift;

    //dialog + custom grid adapter
    Dialog dialog;
    GridView gridView;
    private static int[] achievementImages;
    CustomPatientLeaderboardSendGiftAdapter adapter;
    private int imgSelected;
    Button btnSendGift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank_canvas);

        Intent intent = getIntent();
        btnType = intent.getExtras().getString("BTNTYPE");

        FragmentManager manager = getSupportFragmentManager();

        frameLayout = findViewById(R.id.blank_canvas);
        btn_back = findViewById(R.id.blank_canvas_btn_back);
        btn_send_gift = findViewById(R.id.patient_btn_send_gift);

        btn_send_gift.setVisibility(View.GONE);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Config.TAG_BUTTON, "(BlankCanvas) btnBack");
                onBackPressed();
            }
        });

        switch (btnType){
            case "exercise":
                Log.d(Config.TAG_BUTTON, "(BlankCanvas) btnExercise");

                PatientExercise patientExercise = new PatientExercise();

                manager.beginTransaction()
                        .replace(R.id.fragment_container_blankcanvas, patientExercise)
                        .addToBackStack("tag")
                        .commit();

                break;
            case "challenge":
                Log.d(Config.TAG_BUTTON, "(BlankCanvas) btnChallenge");

                PatientChallenge patientChallenge = new PatientChallenge();

                manager.beginTransaction()
                        .replace(R.id.fragment_container_blankcanvas, patientChallenge)
                        .addToBackStack("tag")
                        .commit();

                break;
            case "leaderboard":
                Log.d(Config.TAG_BUTTON, "(BlankCanvas) btnLeaderboard");

                PatientLeaderboard patientLeaderboard = new PatientLeaderboard();

                frameLayout.setBackground(getDrawable(R.drawable.background2));

                btn_send_gift.setVisibility(View.VISIBLE);

                btn_send_gift.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO: Create Gift dialog to be send

                        //Call send_gift dialog
                        generateDummyData();
                        displayDialog(R.layout.dialog_send_gift, achievementImages, "Send Gift");
                        imgSelected = -1;
                        Log.d(Config.TAG_BUTTON,"(BlankCanvas) Send Gift Btn clicked");
                    }

                });

                manager.beginTransaction()
                        .replace(R.id.fragment_container_blankcanvas, patientLeaderboard)
                        .addToBackStack("tag")
                        .commit();

                break;
            case "progress":
                Log.d(Config.TAG_BUTTON, "(BlankCanvas) btnProgress");

                PatientProgress patientProgress = new PatientProgress();

                frameLayout.setBackground(getDrawable(R.drawable.background2));

                manager.beginTransaction()
                        .replace(R.id.fragment_container_blankcanvas, patientProgress)
                        .addToBackStack("tag")
                        .commit();

                break;
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void sendGiftDialog() {
        //generate dummy data
        generateDummyData();

        //Create custom dialog object
        dialog = new Dialog(BlankCanvas.this);

        //Include dialog.xml
        dialog.setContentView(R.layout.dialog_send_gift);
        //Set dialog title
        dialog.setTitle("Send Gift");

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Set values for custom dialog components
        gridView = dialog.findViewById(R.id.send_gift_grid_item_image);
        adapter = new CustomPatientLeaderboardSendGiftAdapter(BlankCanvas.this, achievementImages);
        adapter.setBtnSendGiftClick(this);
        gridView.setAdapter(adapter);

        //TODO: set onClickListener for imageView in gridView in parent class
        btnSendGift = dialog.findViewById(R.id.btn_send_gift);

        btnSendGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Send data to server
                switch (imgSelected){
                    case 0:
                        Log.d(Config.TAG_BUTTON, "(BlankCanvas) send 'Well Done Achievement' to server");
                        break;
                    case 1:
                        Log.d(Config.TAG_BUTTON, "(BlankCanvas) send 'Good Job Achievement' to server");
                        break;
                    case 2:
                        Log.d(Config.TAG_BUTTON, "(BlankCanvas) send 'Nice Achievement' to server");
                        break;
                    case 3:
                        Log.d(Config.TAG_BUTTON, "(BlankCanvas) send 'Great Achievement' to server");
                        break;
                }

            }
        });

        dialog.show();


    }

    public static void generateDummyData(){
        achievementImages = new int[]{
                R.drawable.achievement_well_done,
                R.drawable.achievement_good_job,
                R.drawable.achievement_nice,
                R.drawable.achievement_great,
        };
    }

    @Override
    public void onThumbnailClick(int parentId, int position) {
        //Not used
    }

    @Override
    public void onAchievementImgClick(int position) {
        imgSelected = position;
    }

    @Override
    public void onTrophyImgClick(int position) {
        Log.d(Config.TAG_BUTTON, "(BlankCanvas) onTrophyImgClick");

    }

    private void displayDialog(int layoutResId, int[] data,String dTitle){

        //Create custom dialog object
        dialog = new Dialog(BlankCanvas.this);

        switch (layoutResId){
            case R.layout.dialog_send_gift:
                //Include dialog.xml
                dialog.setContentView(layoutResId);
                //Set dialog title
                dialog.setTitle(dTitle);

                adapter = new CustomPatientLeaderboardSendGiftAdapter(BlankCanvas.this, data);
                adapter.setBtnSendGiftClick(this);
                /**
                 * onClickListener for CustomPatientLeaderboardSendGiftAdapater
                 */
                //TODO: set onClickListener for imageView in gridView in parent class
                btnSendGift = dialog.findViewById(R.id.btn_send_gift);

                //Set values for custom dialog components
                gridView = dialog.findViewById(R.id.send_gift_grid_item_image);

                btnSendGift.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO: Send data to server
                        switch (imgSelected){
                            case 0:
                                Log.d(Config.TAG_BUTTON, "(BlankCanvas) send 'Well Done Achievement' to server");
                                break;
                            case 1:
                                Log.d(Config.TAG_BUTTON, "(BlankCanvas) send 'Good Job Achievement' to server");
                                break;
                            case 2:
                                Log.d(Config.TAG_BUTTON, "(BlankCanvas) send 'Nice Achievement' to server");
                                break;
                            case 3:
                                Log.d(Config.TAG_BUTTON, "(BlankCanvas) send 'Great Achievement' to server");
                                break;
                        }

                    }
                });
                break;
        }

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        gridView.setAdapter(adapter);

        dialog.show();
    }
}
