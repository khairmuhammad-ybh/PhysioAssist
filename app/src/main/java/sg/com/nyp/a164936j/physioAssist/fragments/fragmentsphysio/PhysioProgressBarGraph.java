package sg.com.nyp.a164936j.physioAssist.fragments.fragmentsphysio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.Configuration.CustomSharedPreference;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.customcomponents.MyValueFormatter;
import sg.com.nyp.a164936j.physioAssist.models.DynamicFloatArray;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhysioProgressBarGraph extends Fragment {

    private BarChart mChart1, mChart2, mChart3;
    private ImageView btnBack;

    public PhysioProgressBarGraph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_physio_progress_bar_graph, container, false);

        mChart1 = rootView.findViewById(R.id.physio_progress_bar_chart_one);
        mChart1.setMaxVisibleValueCount(40);

        mChart2 = rootView.findViewById(R.id.physio_progress_bar_chart_two);
        mChart2.setMaxVisibleValueCount(40);

        mChart3 = rootView.findViewById(R.id.physio_progress_bar_chart_three);
        mChart3.setMaxVisibleValueCount(40);

        //set to No of Session
        setData(CustomSharedPreference.patientVideoSelection[0].getSelectedExercise().size());

        btnBack = rootView.findViewById(R.id.physio_progress_bar_graph_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Config.TAG_BUTTON, "(PhysioProgressBarGraph) btn back pressed");
                getActivity().onBackPressed();
            }
        });

        return rootView;
    }

    public void setData(int count){

        ArrayList<String> exercises = CustomSharedPreference.patientVideoSelection[0].getSelectedExercise();
        ArrayList<String> petals = CustomSharedPreference.patientPetalsSelection[0].getSelectedNoOfPetals();
        //DynamicFloatArray reUpdateStackedBar = new DynamicFloatArray();

        ArrayList<BarEntry> yValues = new ArrayList<>();

        float val[] = new float[exercises.size()];

        for(int i=0, k=exercises.size();i<count; i++){


            for(int j=0;j<petals.size();j++){
                val[j] = Float.parseFloat(petals.get(j));
            }

            /*float val1 = (float)(Math.random() * count) + 20;
            float val2 = (float)(Math.random() * count) + 20;
            float val3 = (float)(Math.random() * count) + 20;*/



            /*for(int h=0;h<k;h++){
                reUpdateStackedBar.put(k, val[h]);
            }*/

            //Represent 1 2D stacked bar
            switch (k){
                case 1:
                    yValues.add(new BarEntry(i, new float[]{val[0]}));
                    break;
                case 2:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1]}));
                    break;
                case 3:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2]}));
                    break;
                case 4:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2],val[3]}));
                    break;
                case 5:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4]}));
                    break;
                case 6:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5]}));
                    break;
                case 7:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6]}));
                    break;
                case 8:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7]}));
                    break;
                case 9:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8]}));
                    break;
                case 10:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], val[9]}));
                    break;
                case 11:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], val[9], val[10]}));
                    break;
                case 12:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], val[9], val[10], val[11]}));
                    break;
                case 13:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], val[9], val[10], val[11], val[12]}));
                    break;
                case 14:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], val[9], val[10], val[11], val[12], val[13]}));
                    break;
                case 15:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], val[9], val[10], val[11], val[12], val[13], val[14]}));
                    break;
                case 16:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], val[9], val[10], val[11], val[12], val[13], val[14], val[15]}));
                    break;
                case 17:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], val[9], val[10], val[11], val[12], val[13], val[14], val[15], val[16]}));
                    break;
                case 18:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], val[9], val[10], val[11], val[12], val[13], val[14], val[15], val[16], val[17]}));
                    break;
                case 19:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], val[9], val[10], val[11], val[12], val[13], val[14], val[15], val[16], val[17], val[18]}));
                    break;
                case 20:
                    yValues.add(new BarEntry(i, new float[]{val[0], val[1], val[2], val[3], val[4], val[5], val[6], val[7], val[8], val[9], val[10], val[11], val[12], val[13], val[14], val[15], val[16], val[17], val[18], val[19]}));
                    break;
            }


            //yValues.add(new BarEntry(i, reUpdateStackedBar.getStackedBar(k)));

        }

        BarDataSet set1;

        set1 = new BarDataSet(yValues, "Statistics of Patient Exercises");
        set1.setDrawIcons(false);

        //set colors
        set1.setColors(getColors(exercises.size()));

        //set setstackLabels
        switch (exercises.size()){
            case 1:
                set1.setStackLabels(new String[]{exercises.get(0)});
                break;
            case 2:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1)});
                break;
            case 3:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2)});
                break;
            case 4:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3)});
                break;
            case 5:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4)});
                break;
            case 6:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5)});
                break;
            case 7:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6)});
                break;
            case 8:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7)});
                break;
            case 9:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8)});
                break;
            case 10:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8), exercises.get(9)});
                break;
            case 11:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8), exercises.get(9), exercises.get(10)});
                break;
            case 12:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8), exercises.get(9), exercises.get(10), exercises.get(11)});
                break;
            case 13:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8), exercises.get(9), exercises.get(10), exercises.get(11), exercises.get(12)});
                break;
            case 14:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8), exercises.get(9), exercises.get(10), exercises.get(11), exercises.get(12), exercises.get(13)});
                break;
            case 15:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8), exercises.get(9), exercises.get(10), exercises.get(11), exercises.get(12), exercises.get(13), exercises.get(14)});
                break;
            case 16:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8), exercises.get(9), exercises.get(10), exercises.get(11), exercises.get(12), exercises.get(13), exercises.get(14), exercises.get(15)});
                break;
            case 17:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8), exercises.get(9), exercises.get(10), exercises.get(11), exercises.get(12), exercises.get(13), exercises.get(14), exercises.get(15), exercises.get(16)});
                break;
            case 18:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8), exercises.get(9), exercises.get(10), exercises.get(11), exercises.get(12), exercises.get(13), exercises.get(14), exercises.get(15), exercises.get(16), exercises.get(17)});
                break;
            case 19:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8), exercises.get(9), exercises.get(10), exercises.get(11), exercises.get(12), exercises.get(13), exercises.get(14), exercises.get(15), exercises.get(16), exercises.get(17), exercises.get(18)});
                break;
            case 20:
                set1.setStackLabels(new String[]{exercises.get(0), exercises.get(1), exercises.get(2), exercises.get(3), exercises.get(4), exercises.get(5), exercises.get(6), exercises.get(7), exercises.get(8), exercises.get(9), exercises.get(10), exercises.get(11), exercises.get(12), exercises.get(13), exercises.get(14), exercises.get(15), exercises.get(16), exercises.get(17), exercises.get(18), exercises.get(19)});
                break;
        }

        BarData data = new BarData(set1);
        data.setValueFormatter(new MyValueFormatter());

        mChart1.setData(data);
        mChart1.setFitBars(true);
        mChart1.invalidate();

        mChart2.setData(data);
        mChart2.setFitBars(true);
        mChart2.invalidate();

        mChart3.setData(data);
        mChart3.setFitBars(true);
        mChart3.invalidate();

    }

    public int[] getColors(int size){

        //have as many colors as stack-values per entry

        int[] colors;
        colors = new int[size];

        for(int i=0;i<colors.length;i++){
            colors[i] = ColorTemplate.MATERIAL_COLORS[i];
        }
        return colors;
    }

}
