package sg.com.nyp.a164936j.physioAssist.customadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import sg.com.nyp.a164936j.physioAssist.R;

public class CustomPhysioScheduleVideosAdapter extends BaseAdapter {

    private Context context;
    private String[] videos;

    private static LayoutInflater inflater = null;

    public CustomPhysioScheduleVideosAdapter(Context context, String[] videos) {
        this.context = context;
        this.videos = videos;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return videos.length;
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
        TextView videoTitle;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        View rowView;

        rowView = inflater.inflate(R.layout.layout_physio_schedule_video_item, null);
        holder.videoTitle = rowView.findViewById(R.id.physio_schedule_item_video_title);

        holder.videoTitle.setText(videos[position]);

        return rowView;
    }


}
