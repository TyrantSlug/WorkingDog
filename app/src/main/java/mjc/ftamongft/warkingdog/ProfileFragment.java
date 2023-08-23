package mjc.ftamongft.warkingdog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

public class ProfileFragment extends Fragment {

    private ImageView ivProfile;
    private ImageButton ivUpdate;
    private Button btnUpdate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentActivity activity = getActivity();
        View v = inflater.inflate(R.layout.profile, container, false);

        ivProfile = v.findViewById(R.id.profile_image);
        ivUpdate = v.findViewById(R.id.IBprofileupdate);
        btnUpdate = v.findViewById(R.id.BtnUpdate);
        final TextView tvEmail = v.findViewById(R.id.TVemail);
        final TextView tvNickName = v.findViewById(R.id.TVname);

        ivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileUpdateFragment fragment = new ProfileUpdateFragment(); // 프래그먼트 생성
                Bundle bundle = new Bundle();// 다음 프래그먼트에 데이터를 전달하기 위한 번들객체

                String str = tvEmail.getText().toString();
                str = str.substring(str.indexOf(":")+2);
                bundle.putSerializable("email",str); // 번들 객체에 이메일 설정

                str = tvNickName.getText().toString();
                str = str.substring(str.indexOf(":")+2);
                bundle.putSerializable("nickname",str);// 번들 객체에 닉네임 설정

                fragment.setArguments(bundle); // 다음 프래그먼트에 번들 객체 설정
                // 다음 프래그먼트로 이동하며 백 스택에 프래그먼트 저장
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, fragment,"profileupdate").addToBackStack("profileupdate").commit();
            }
        });

        return v;
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    imageView.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}

