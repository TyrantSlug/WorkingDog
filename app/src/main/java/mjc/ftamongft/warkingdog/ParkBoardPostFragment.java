package mjc.ftamongft.warkingdog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ParkBoardPostFragment extends Fragment {

    private ViewFlipper vfParkImg;
    private ListView listReview, listRoute;
    private TextView tvImgCnt;
    private ArrayList<Review> reviewList = new ArrayList<>();
    private ArrayList<Route> routeList = new ArrayList<>();
    private ListAdapter reviewAdapter, routeAdapter;
    private MapFragment mapFragment;
    private View v;
    private Park park;
    private float xDown;
    private int vfImgNum = 1, vfImgCnt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentActivity activity = getActivity();
        v = inflater.inflate(R.layout.park_board_post, container, false);

        vfParkImg = v.findViewById(R.id.vfParkImg);
        listReview = v.findViewById(R.id.listReview);
        listRoute = v.findViewById(R.id.listRoute);
        tvImgCnt = v.findViewById(R.id.tvImgCnt);
        TextView tvName = v.findViewById(R.id.tvName);
        TextView tvAddress = v.findViewById(R.id.tvAddress);
        TextView tvUrl = v.findViewById(R.id.tvUrl);
        TextView tvTel = v.findViewById(R.id.tvTel);
        TextView tvOpenTime = v.findViewById(R.id.tvOpenTime);
        TextView tvCharge = v.findViewById(R.id.tvCharge);
        TextView tvParking = v.findViewById(R.id.tvParking);
        final ScrollView scrollView = v.findViewById(R.id.scrollView);

        park = (Park) getArguments().getSerializable("park"); // 이전 프래그먼트에서 전달한 공원 객체

        int position = (int) getArguments().getSerializable("position"); // (테스트를 위한 포지션 값) - 지워야함

        for (int i = 0; i < park.imageUrl.length; i++) { // 공원의 이미지 수 만큼 이미지 뷰 동적 생성
            ImageView iv = new ImageView(activity);
            iv.setImageResource(Park.imageRes[position][i]); // 이미지 뷰에 이미지 설정
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP); // 이미지 뷰 스케일 타입 설정
            vfParkImg.addView(iv); // 뷰플리퍼에 이미지뷰 동적 생성 후 추가
        }

        vfImgCnt = vfParkImg.getChildCount();
        tvImgCnt.setText(vfImgNum + " / " + vfImgCnt);

        tvName.setText(park.name);
        tvAddress.setText("주소: " + park.address);
        tvTel.setText("전화번호: " + park.tel);
        tvOpenTime.setText("운영시간: " + park.openTime);
        tvCharge.setText("입장료: " + park.charge);
        tvParking.setText("주차정보: " + park.parking);
        tvUrl.setText("홈페이지: " + park.url);

        mapFragment = (MapFragment)activity.getFragmentManager().findFragmentById(R.id.tabMap);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng location = new LatLng(park.latitude,park.longtitude); // 공원의 위도, 경도
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); // 일반 지도 설정
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));
                googleMap.getUiSettings().setZoomControlsEnabled(true);

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(location);
                markerOptions.title(park.name);
                markerOptions.snippet(park.address);
                markerOptions.alpha(0.7f);
                googleMap.addMarker(markerOptions);
            }
        });

        new Thread() { // 리뷰, 경로 DB 검색 쓰레드
            @Override
            public void run() {
                Review.readDB(reviewList,park); // 리뷰 객체 생성 & DB 검색 후 리스트에 추가
                Route.readDB(routeList,park); // 경로 객체 생성 & DB 검색 후 리스트에 추가

                reviewAdapter = new ListAdapter(activity, reviewList); // 어댑터에 리뷰 객체 리스트 설정
                routeAdapter = new ListAdapter(activity,routeList); // 어댑터에 경로 객체 리스트 설정

                activity.runOnUiThread(new Runnable() { // 서버에서 검색한 DB결과를 UI스레드로 뷰에 적용
                    @Override
                    public void run() { // UI 스레드를 통해 리스트뷰 설정
                        listReview.setAdapter(reviewAdapter); // 리뷰 목록을 담는 리스트뷰에 어댑터 설정
                        listRoute.setAdapter(routeAdapter); // 경로 목록을 담는 리스트뷰에 어댑터 설정
                    }
                });
            }
        }.start();

        v.findViewById(R.id.ivReviewWrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewWriteFragment fragment = new ReviewWriteFragment(); // 프래그먼트 생성
                Bundle bundle = new Bundle();// 다음 프래그먼트에 데이터를 전달하기 위한 번들객체
                bundle.putSerializable("park", park);// 번틀 객체에 공원 객체 설정
                fragment.setArguments(bundle); // 다음 프래그먼트에 번들 객체 설정
                // 다음 프래그먼트로 이동하며 백 스택에 프래그먼트 저장
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                // 프래그먼트 전환하며 태그 이름 reviewwrite로 설정, HomeActivity의 isEtEmpty 메소드에서
                // ReviewWriteFragment의 에디트텍스트의 내용이 비었는지 여부를 확일 할 때
                // 해당 프래그먼트 객체를 얻기 위해 사용하는 태그 이름을 설정한다.
                transaction.replace(R.id.frame_layout, fragment, "reviewwrite").addToBackStack("reviewwrite").commit();
            }
        });

        v.findViewById(R.id.ivLike).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "해당 공원을 추천하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        v.findViewById(R.id.ivBookMark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "해당 공원을 즐겨찾기 등록하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        listReview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ReviewPostFragment fragment = new ReviewPostFragment();// 프래그먼트 생성
                Bundle bundle = new Bundle(); // 다음 프래그먼트에 데이터를 전달하기 위한 번들객체
                bundle.putSerializable("review", reviewList.get(i)); // 번틀 객체에 리뷰 객체 설정
                fragment.setArguments(bundle);
                // 다음 프래그먼트로 이동하며 백 스택에 프래그먼트 저장
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, fragment, "reviewpost").addToBackStack("reviewpost").commit();
            }
        });

        listReview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true); // 스크롤 뷰 내부 리스트 뷰와 이벤트 겹침 해소
                return false;
            }
        });

        listRoute.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true); // 스크롤 뷰 내부 리스트 뷰와 이벤트 겹침 해소
                return false;
            }
        });

        vfParkImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) // 손이 화면에 닿을 때
                    xDown = motionEvent.getRawX(); // x 좌표를 얻음
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP) { // 손이 화면에 떨어질 때
                    float xDiff = xDown - motionEvent.getRawX(); // 화면에 닿을 떄의 x좌표와 떨어질 때의 x좌표의 차
                    if (xDiff > 100) { // x 좌표 차이가 100보다 크다면(양수:오른방향)
                        vfParkImg.showNext(); // 뷰플리퍼의 다음 내용
                        vfImgNum++; // 현재 뷰플리퍼의 순서 번호 증가
                        if (vfImgNum > vfImgCnt) // 뷰플리퍼 순서 번호가 뷰플리퍼 자식 갯수보다 크다면
                            vfImgNum = 1; // 초기 값인 1로 설정
                        tvImgCnt.setText(vfImgNum + " / " + vfImgCnt);
                    } else if (xDiff < -100) { // x 좌표 차이가 -100보다 작다면(음수:왼방향)
                        vfParkImg.showPrevious(); // 뷰플리퍼의 이전 내용
                        vfImgNum--; // 현재 뷰플리퍼의 순서 번호 감소
                        if (vfImgNum < 1) // 뷰플리퍼 순서 번호가 0이라면
                            vfImgNum = vfImgCnt; // 뷰플리퍼의 최대 갯수로 설정
                        tvImgCnt.setText(vfImgNum + " / " + vfImgCnt);
                    }
                }
                return true;
            }
        });

        scrollView.smoothScrollTo(0,0); // 스크롤 뷰 위치 초기화
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mapFragment != null)
            getActivity().getFragmentManager().beginTransaction().remove(mapFragment).commit();
        if(v != null){
            ViewGroup parent = (ViewGroup)v.getParent();
            if(parent != null)
                parent.removeView(v);
        }

        reviewList.clear();
        routeList.clear();

        // 테스트용 데이터 index 초기화
        Review.i = 0;
        Route.i = 0;
    }
}
