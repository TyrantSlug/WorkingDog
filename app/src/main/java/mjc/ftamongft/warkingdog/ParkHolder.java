package mjc.ftamongft.warkingdog;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ParkHolder {
    ImageView ivPark;
    TextView tvTitle;
    TextView tvArea;
    TextView tvLike;

    ParkHolder(View v) {
        ivPark = v.findViewById(R.id.ivPark);
        tvTitle = v.findViewById(R.id.tvTitle);
        tvArea = v.findViewById(R.id.tvArea);
        tvLike = v.findViewById(R.id.tvLike);
    }
}
