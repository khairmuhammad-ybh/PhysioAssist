package sg.com.nyp.a164936j.physioAssist.customadapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import sg.com.nyp.a164936j.physioAssist.Configuration.Config;
import sg.com.nyp.a164936j.physioAssist.R;
import sg.com.nyp.a164936j.physioAssist.fragments.Interfaces.CustomOnClickListener;

public class CustomPatientExerciseAdapter extends BaseAdapter {

    private Context context;
    private String[] result;
    private String[] imageId;

    private CustomOnClickListener customListener;

    private static LayoutInflater inflater = null;

    public void setThumbnailClickListener(CustomOnClickListener listener){
        this.customListener = listener;
    }

    public CustomPatientExerciseAdapter(Context context, String[] result, String[] imageId) {
        this.context = context;
        this.result = result;
        this.imageId = imageId;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return result.length;
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
        TextView tv;
        ImageView img;
        Button btn;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        View rowView;

        rowView = inflater.inflate(R.layout.layout_exercise_routine, null);
        holder.tv = rowView.findViewById(R.id.video_title);
        holder.img = rowView.findViewById(R.id.exercise_thumbnail);
        holder.btn = rowView.findViewById(R.id.btnStartExercise);

        File sdPath = Environment.getExternalStorageDirectory();
        String imgURI;
        imgURI = sdPath.getAbsolutePath() + "/PhysioAssist/Thumbnail/" + imageId[position]+ ".jpg";

        if(sdPath.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(imgURI);
            holder.img.setImageBitmap(bitmap);
        }

        holder.tv.setText(result[position]);


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: handle multiple fragments depends on which button is being pressed
                int parentId = parent.getId();
                Log.d(Config.TAG_BUTTON, "(CustomPatientExerciseAdapter) getParent: "+ parentId);

                customListener.onThumbnailClick(parentId, position);

            }
        });

        return rowView;
    }


}
