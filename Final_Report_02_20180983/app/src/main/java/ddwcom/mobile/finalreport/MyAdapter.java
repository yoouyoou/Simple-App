package ddwcom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<movieData> movieList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, int layout, ArrayList<movieData> movieList){
        this.context = context;
        this.layout = layout;
        this.movieList = movieList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movieList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        if(convertView == null)
            convertView = inflater.inflate(layout, parent, false);

        ImageView tv_image = convertView.findViewById(R.id.tv_image);
        TextView tv_title = convertView.findViewById(R.id.tv_title);
        TextView tv_actor = convertView.findViewById(R.id.tv_actor);
        TextView tv_rating = convertView.findViewById(R.id.tv_rating);

        tv_image.setImageResource(movieList.get(pos).getImage());
        tv_title.setText(movieList.get(pos).getTitle());
        tv_actor.setText(movieList.get(pos).getMainActor());
        tv_rating.setText(movieList.get(pos).getRating());

        tv_title.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(context, movieList.get(pos).get_id() + "번", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return convertView;
    }

}
