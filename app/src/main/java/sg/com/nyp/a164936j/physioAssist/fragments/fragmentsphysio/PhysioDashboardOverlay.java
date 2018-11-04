package sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sg.com.nyp.a164936j.physioAssist.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhysioDashboardOverlay extends Fragment {


    public PhysioDashboardOverlay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_physio_dashboard_overlay, container, false);
    }

}
