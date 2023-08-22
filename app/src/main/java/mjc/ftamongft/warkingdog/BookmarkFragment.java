package mjc.ftamongft.warkingdog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class BookmarkFragment extends Fragment {

    private ArrayList<Park> parkList = new ArrayList<>();
    private ParkAdapter parkAdapter;
    private ListView listPark;
    private View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.bookmark, container, false);
        final FragmentActivity activity = getActivity();

        listPark = v.findViewById(R.id.bookmark_list);


        new Thread() { // 공원 DB 검색 쓰레드
            @Override
            public void run() {
                Park.readDB(parkList); // 공원 객체 생성 & DB 검색 후 리스트에 추가
                parkAdapter = new ParkAdapter(activity, parkList); // 어댑터에 공원 객체 리스트 설정
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listPark.setAdapter(parkAdapter); // 공원 리스트 목록에 어댑터 설정
                    }
                });
            }
        }.start();

        listPark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ParkBoardPostFragment fragment = new ParkBoardPostFragment(); // 프래그먼트 생성
                Bundle bundle = new Bundle(); // 다음 프래그먼트에 데이터를 전달하기 위한 번들객체
                bundle.putSerializable("park",parkList.get(i)); // 번틀 객체에 공원 객체 설정

                bundle.putSerializable("position",i); // (테스트를 위해서 추가된 포지션 값) - 지워야함

                fragment.setArguments(bundle); // 다음 프래그먼트에 번들 객체 설정
                // 다음 프래그먼트로 이동하며 백 스택에 프래그먼트 저장
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,fragment,"parkboardpost").addToBackStack("parkboardpost").commit();
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

        parkList.clear();

        Park.i = 0; // 테스트용 데이터 index 초기화
    }
}
