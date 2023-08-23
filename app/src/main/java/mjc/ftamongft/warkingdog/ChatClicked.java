package mjc.ftamongft.warkingdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatClicked extends Activity {

    private int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_clicked);

        Intent intent = getIntent();

        ImageView profile = (ImageView) findViewById(R.id.img_profile);
        TextView title = (TextView) findViewById(R.id.txt_title);
        TextView name = (TextView) findViewById(R.id.txt_name);
        TextView date = (TextView) findViewById(R.id.txt_date);

        img = Integer.parseInt(intent.getStringExtra("profile"));
        profile.setImageResource(img);
        title.setText(intent.getStringExtra("title"));
        name.setText(intent.getStringExtra("name"));
        date.setText(intent.getStringExtra("date"));
    }
}
