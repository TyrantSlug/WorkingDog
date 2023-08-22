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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ParkBoardFragment extends Fragment {

    private ListView listPark;
    private ArrayList<Park> parkList = new ArrayList<>();
    private ParkAdapter parkAdapter;
    private View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentActivity activity = getActivity();
        v = inflater.inflate(R.layout.park_board,container,false);

        listPark = v.findViewById(R.id.listPark);
        Spinner spArea = v.findViewById(R.id.spArea);

        final String[] cities = {"전체", "서울", "경기", "충청", "전라", "강원", "경상", "제주"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, cities);
        spArea.setAdapter(arrayAdapter); // 검색 지역을 설정하는 스피너에 지역 리스트를 담은 어댑터 설정


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


        spArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                /* 해당 검색조건의 공원 DB를 검색하기 위해 반복적으로 서버 DB에 접근하지 않고
                    액티비티 실행 시 모든 공원 DB를 검색하여 저장된 parkList의 공원 객체 값에서
                    검색 조건을 비교하여 해당하는 공원들을 담은 어댑터를 생성하여 리스트뷰에 설정 */
                ParkAdapter adapter = null; // 검색 조건에 맞는 공원 리스트를 담는 어댑터
                String city = cities[index]; // 스피너에서 선택한 검색 조건의 시/도
                if (city.equals("전체")) // 스피너에서 선택한 검색 조건이 전체라면
                    adapter = parkAdapter; // 기존 모든 검색 정보를 가진 어댑터를 사용
                else { // 스피너 에서 선택한 검색 조건이 그 외라면
                    ArrayList<Park> list = new ArrayList<>(); // 검색 조건에 맞는 공원들을 담을 리스트
                    for (int i = 0; i < parkList.size(); i++) { // 기존 모든 검색 정보를 가진 리스트
                        Park park = parkList.get(i);
                        if (park.city.equals(cities[index])) // 공원의 시/도가 검색 조건의 시/도와 일치하다면
                            list.add(park); // 리스트에 공원 추가
                        adapter = new ParkAdapter(activity, list); // 어댑터에 리스트 설정
                    }
                }
                listPark.setAdapter(adapter); // 공원 목록을 담는 리스트뷰에 어댑터 설정
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
