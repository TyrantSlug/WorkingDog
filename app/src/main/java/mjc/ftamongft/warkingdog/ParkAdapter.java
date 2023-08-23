package mjc.ftamongft.warkingdog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ParkAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Park> parkList;

    ParkAdapter(Context context, ArrayList<Park> parkList) {
        this.context = context;
        this.parkList = parkList;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        return parkList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, R.layout.park_board_list, null);
            ParkHolder parkHolder = new ParkHolder(view);
            view.setTag(parkHolder);
        }
        ParkHolder parkHolder = (ParkHolder) view.getTag();
        Park park = parkList.get(i);

        // 서버에서 검색한 결과를 화면에 적용
        parkHolder.ivPark.setImageResource(Park.imageRes[Integer.parseInt(park.id)][0]);
        parkHolder.tvTitle.setText(park.name);
        parkHolder.tvArea.setText(park.address);
        parkHolder.tvLike.setText("추천: " + park.likePoint);
        return view;
    }
}
