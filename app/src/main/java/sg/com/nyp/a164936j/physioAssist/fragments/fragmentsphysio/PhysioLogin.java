package sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.PhysioDashboard;
import sg.com.nyp.a164936j.physioAssist.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhysioLogin extends Fragment {

    private Button btnPhysioLogin, backClick;

    public PhysioLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_physio_login, container, false);

        btnPhysioLogin = rootView.findViewById(R.id.btnPhysioLogin);


        btnPhysioLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Config.TAG_BUTTON, "(PhysioLogin) Physio logged in");
                startActivity(new Intent(getContext(), PhysioDashboard.class));
                getActivity().finish();
            }
        });

        backClick = rootView.findViewById(R.id.backBtn);
        backClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Config.TAG_BUTTON, "(PhysioLogin) Physio back btn pressed");
                getActivity().onBackPressed();
            }
        });

        return rootView;
    }

}
