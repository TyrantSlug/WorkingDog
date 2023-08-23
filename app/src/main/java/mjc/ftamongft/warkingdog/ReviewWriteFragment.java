package mjc.ftamongft.warkingdog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewWriteFragment extends Fragment {
    EditText etTitle, etContent;
    private View v;
    private Park park;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.review_write, container, false);
        final FragmentActivity activity = getActivity();

        etTitle = v.findViewById(R.id.etTitle);
        etContent = v.findViewById(R.id.etContent);
        TextView tvName = v.findViewById(R.id.tvName);

        park = (Park) getArguments().getSerializable("park"); // 이전 프래그먼트에서 전달한 공원 객체

        tvName.setText(park.name);

        v.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HomeActivity.isEtEmpty(etTitle, etContent) > 0) // 내용이 없는 에디트텍스트의 갯수를 반환하는 메소드 호출
                    Toast.makeText(activity, "리뷰의 제목 혹은 내용이 없습니다.", Toast.LENGTH_SHORT).show();
                else {
                    new Thread(){ // 리뷰 DB 입력 스레드
                        @Override
                        public void run() { // 리뷰 DB 입력 로직으로 바꿔야함.
                            activity.getSupportFragmentManager().popBackStack(); // DB 입력 후, 백 스택에서 현재 프래그먼트 pop
                        }
                    }.start();
                    Toast.makeText(activity, "리뷰가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //사진 첨부 기능
        v.findViewById(R.id.ivGallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(v != null){
            ViewGroup parent = (ViewGroup)v.getParent();
            if(parent != null)
                parent.removeView(v);
        }
    }
}
