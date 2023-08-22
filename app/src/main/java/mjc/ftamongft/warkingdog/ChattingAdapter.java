package mjc.ftamongft.warkingdog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChattingAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ChattingList> data;
    private int layout;

    public ChattingAdapter(Context context, int layout, ArrayList<ChattingList> data){
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
        ChattingList chattingList = data.get(position);

        ImageView profile = (ImageView) convertView.findViewById(R.id.profile);
        profile.setImageResource(chattingList.getProfile());

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(chattingList.getTitle());

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(chattingList.getName());

        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText(chattingList.getDate());

        return convertView;
    }
}
