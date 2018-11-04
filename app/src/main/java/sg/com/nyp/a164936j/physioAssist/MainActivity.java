package sg.com.nyp.a164936j.physioAssist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.fragments.UserSelection;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class MainActivity extends AppCompatActivity{

    //PERMISSION
    //private static final int PERMS_REQUEST_CODE = 200;
    //private static final String TAG = "Permissions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserSelection userSelection = new UserSelection();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(
                R.id.fragment_container,
                userSelection,
                userSelection.getTag())
                .commit();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Toast.makeText(MainActivity.this, "In onRequestPermission", Toast.LENGTH_SHORT).show();
        Log.d(Config.TAG_PERMISSION,"(MainAcitivity.java) In onRequestPermission");
        switch (requestCode){
            case Config.PERMS_REQUEST_CODE:
                if(grantResults.length > 0){
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        Log.d(Config.TAG_PERMISSION,"(MainAcitivity.java) Permission Granted, Able to play video now.");
                    }else if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                        //Toast.makeText(MainActivity.this, "Permission Denied Cannot play video", Toast.LENGTH_SHORT).show();
                        Log.d(Config.TAG_PERMISSION, "(MainAcitivity.java) Permission Denied, Unable to play video");

                        //Show explanation
                        if(shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)){
                            showMessageOkCancel(
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE}, Config.PERMS_REQUEST_CODE);
                                        }
                                    });
                            return;
                        }
                    }
                }
                break;
        }

    }

    private void showMessageOkCancel(DialogInterface.OnClickListener okListener){
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("You need to allow access to 'WRITE_EXTERNAL_STORAGE' permission")
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
