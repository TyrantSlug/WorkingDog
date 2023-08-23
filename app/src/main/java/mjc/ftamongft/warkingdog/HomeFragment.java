package mjc.ftamongft.warkingdog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_menu, container, false);

        final GridView gv = v.findViewById(R.id.gridView1);
        final GridView gv2 = v.findViewById(R.id.gridView2);
        HomeAdapter adapter = new HomeAdapter(getContext());
        HomeAdapter2 adapter2 = new HomeAdapter2(getContext());
        gv.setAdapter(adapter);
        gv2.setAdapter(adapter2);

        v.findViewById(R.id.tvPark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new ParkBoardFragment()).addToBackStack("parkboard").commit();
            }
        });

        return v;
    }

    public class HomeAdapter extends BaseAdapter {

        Context context;

        public HomeAdapter(Context c) {
            context = c;
        }

        Integer[] parkimg = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};

        @Override
        public int getCount() {
            return parkimg.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 400));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            imageView.setImageResource(parkimg[position]);

            return imageView;
        }
    }

    public class HomeAdapter2 extends BaseAdapter {
        Context context;

        Integer[] routeimg = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};

        public HomeAdapter2(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return routeimg.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 400));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            imageView.setImageResource(routeimg[position]);

            return imageView;
        }
    }
}
