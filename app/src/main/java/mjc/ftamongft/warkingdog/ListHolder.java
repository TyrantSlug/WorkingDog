package mjc.ftamongft.warkingdog;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ListHolder {
    ImageView ivUser;
    TextView tvTitle;
    TextView tvInf;
    TextView tvContent;

    public ListHolder(View v){
        ivUser = v.findViewById(R.id.ivUser);
        tvTitle = v.findViewById(R.id.tvTitle);
        tvInf = v.findViewById(R.id.tvInf);
        tvContent = v.findViewById(R.id.tvContent);
    }
}
