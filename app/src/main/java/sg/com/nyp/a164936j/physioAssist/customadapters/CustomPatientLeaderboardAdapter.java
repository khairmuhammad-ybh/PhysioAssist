package sg.com.nyp.a164936j.physioAssist.customadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import sg.com.nyp.a164936j.physioAssist.R;

public class CustomPatientLeaderboardAdapter extends BaseAdapter {

    private Context context;
    private String[] posResult;
    private String[] codenameResult;
    private int[] pointResult;

    private static LayoutInflater inflater = null;

    public CustomPatientLeaderboardAdapter(Context context, String[] posResult, String[] codenameResult, int[] pointResult) {
        this.context = context;
        this.posResult = posResult;
        this.codenameResult = codenameResult;
        this.pointResult = pointResult;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return posResult.length;
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
        TextView position,codeName, points;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        final View rowView;

        rowView = inflater.inflate(R.layout.layout_patient_leaderboard, null);
        holder.position = rowView.findViewById(R.id.patient_leaderboard_position);
        holder.codeName = rowView.findViewById(R.id.patient_leaderboard_patient_codename);
        holder.points = rowView.findViewById(R.id.patient_leaderboard_points);

        holder.position.setText(posResult[position]);
        holder.codeName.setText(codenameResult[position]);
        holder.points.setText(String.valueOf(pointResult[position]));

        return rowView;
    }


}
