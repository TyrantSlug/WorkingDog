package mjc.ftamongft.warkingdog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MatchingAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<MatchingList> data;
    private int layout;

    public MatchingAdapter(Context context, int layout, ArrayList<MatchingList> data){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getTitle();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }
       MatchingList matchingList = data.get(position);

        ImageView profile = (ImageView) convertView.findViewById(R.id.profile);
        profile.setImageResource(matchingList.getProfile());

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(matchingList.getTitle());

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(matchingList.getName());

        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText(matchingList.getDate());

        return convertView;
    }
}
