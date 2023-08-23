package mjc.ftamongft.warkingdog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ReviewPostFragment extends Fragment {
    private ViewFlipper vfReviewImg;
    private TextView tvImgCnt;
    private View v;
    private float xDown;
    private int vfImgNum = 1, vfImgCnt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.review_post,container,false);
        final FragmentActivity activity = getActivity();

        vfReviewImg = v.findViewById(R.id.vfReviewImg);
        tvImgCnt = v.findViewById(R.id.tvImgCnt);
        TextView tvName = v.findViewById(R.id.tvName);
        TextView tvTitle = v.findViewById(R.id.tvTitle2);
        TextView tvContent = v.findViewById(R.id.tvContent);

        Review review = (Review) getArguments().getSerializable("review"); // 이전 액티비티에서 전달한 리뷰 객체
        for(int i =0; i < review.imageUrl.length; i++){ // 공원의 이미지 수 만큼 이미지 뷰 동적 생성
            ImageView iv = new ImageView(activity);
            iv.setImageResource(R.drawable.park); // 이미지 뷰에 이미지 설정
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP); // 이미지 뷰 스케일 타입 설정
            vfReviewImg.addView(iv); // 뷰플리퍼에 이미지뷰 동적 생성 후 추가
        }
        vfImgCnt = vfReviewImg.getChildCount();
        tvImgCnt.setText(vfImgNum + " / " + vfImgCnt);
        tvName.setText(review.parkName);
        tvTitle.setText(review.title);
        tvContent.setText(review.content);

        //리뷰의 작성자와 로그인한 계정이 일치하다면 수정 및 삭제 권한 부여 및 visible
        /*LinearLayout ll = v.findViewById(R.id.lltool);
        ll.setVisibility(View.VISIBLE);
        Button btnModify = v.findViewById(R.id.btnModify);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){ // 리뷰 DB 삭제 스레드
                    @Override
                    public void run() { // 리뷰 DB 수정 로직으로 바꿔야함.
                        activity.getSupportFragmentManager().popBackStack(); // DB 삭제 후, 백 스택에서 현재 프래그먼트 pop
                    }
                }.start();
                Toast.makeText(activity, "리뷰가 수정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnDelete = v.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){ // 리뷰 DB 삭제 스레드
                    @Override
                    public void run() { // 리뷰 DB 삭제 로직으로 바꿔야함.
                        activity.getSupportFragmentManager().popBackStack(); // DB 수정 후, 백 스택에서 현재 프래그먼트 pop
                    }
                }.start();
                Toast.makeText(activity, "리뷰가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });*/

        vfReviewImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) // 손이 화면에 닿을 때
                    xDown = motionEvent.getRawX(); // x 좌표를 얻음
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP) { // 손이 화면에 떨어질 때
                    float xDiff = xDown - motionEvent.getRawX(); // 화면에 닿을 떄의 x좌표와 떨어질 때의 x좌표의 차
                    if (xDiff > 100) { // x 좌표 차이가 100보다 크다면(양수:오른방향)
                        vfReviewImg.showNext(); // 뷰플리퍼의 다음 내용
                        vfImgNum++; // 현재 뷰플리퍼의 순서 번호 증가
                        if (vfImgNum > vfImgCnt) // 뷰플리퍼 순서 번호가 뷰플리퍼 자식 갯수보다 크다면
                            vfImgNum = 1; // 초기 값인 1로 설정
                        tvImgCnt.setText(vfImgNum + " / " + vfImgCnt);
                    } else if (xDiff < -100) { // x 좌표 차이가 -100보다 작다면(음수:왼방향)
                        vfReviewImg.showPrevious(); // 뷰플리퍼의 이전 내용
                        vfImgNum--; // 현재 뷰플리퍼의 순서 번호 감소
                        if (vfImgNum < 1) // 뷰플리퍼 순서 번호가 0이라면
                            vfImgNum = vfImgCnt; // 뷰플리퍼의 최대 갯수로 설정
                        tvImgCnt.setText(vfImgNum + " / " + vfImgCnt);
                    }
                }
                return true;
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
