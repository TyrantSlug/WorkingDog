package mjc.ftamongft.warkingdog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<?> reviewList;

    public ListAdapter(Context context, ArrayList<?> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
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
        return reviewList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, R.layout.park_board_post_review_list, null);
            ListHolder parkHolder = new ListHolder(view);
            view.setTag(parkHolder);
        }
        final ListHolder reviewHolder = (ListHolder) view.getTag();
        Object obj;

        if ((obj = reviewList.get(i)) instanceof Review) { // 전달받은 리스트가 갖는 객체가 Review인 경우
            final Review review = (Review) obj;
            reviewHolder.ivUser.setImageResource(R.drawable.park);
            reviewHolder.tvTitle.setText(review.title);
            reviewHolder.tvInf.setText(review.writer + " / " + review.writeDate);
            reviewHolder.tvContent.setText(review.content);
        } else if (obj instanceof Route) { // 전달받은 리스트가 갖는 객체가 Route인 경우
            final Route route = (Route) obj;
            reviewHolder.ivUser.setImageResource(R.drawable.parkroute);
            reviewHolder.tvTitle.setText(route.title);
            reviewHolder.tvInf.setText(route.writer + " / " + route.writeDate);
            reviewHolder.tvContent.setText(route.content);

        }

        return view;
    }
}
