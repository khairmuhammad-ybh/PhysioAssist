package sg.com.nyp.a164936j.physioAssist.customadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.models.PhysioScheduler;

public class CustomPhysioScheduleAdapter extends BaseAdapter {

    private Context context;
    private String[] timeList;
    private String[] videoList;

    private static LayoutInflater inflater = null;

    private PhysioScheduler[] physioSchedulers;

    /*public CustomPhysioScheduleAdapter(Context context, String[] time, String[] videos) {
        this.context = context;
        this.timeList = time;
        this.videoList = videos;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/

    public CustomPhysioScheduleAdapter(Context context, PhysioScheduler[] physioSchedulers) {
        this.context = context;
        this.physioSchedulers = physioSchedulers;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return physioSchedulers.length;
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
        TextView time;
        ListView videoList;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        View rowView;

        rowView = inflater.inflate(R.layout.layout_physio_schedule, null);
        holder.time = rowView.findViewById(R.id.physio_schedule_item_time);
        holder.videoList = rowView.findViewById(R.id.physio_schedule_item_listview);

        //holder.time.setText(timeList[position]);
        holder.time.setText(physioSchedulers[position].getTimeList());

        //TODO: Populate listview for videos
        CustomPhysioScheduleVideosAdapter adapter = new CustomPhysioScheduleVideosAdapter(context, physioSchedulers[position].getVideoList());
        holder.videoList.setAdapter(adapter);

        return rowView;
    }


}
