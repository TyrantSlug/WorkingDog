package mjc.ftamongft.warkingdog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private long initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setItemIconSize(90); // 네비게이션 바 아이콘 크기 설정
        navigation.setItemIconTintList(null); // 네비게이션 바 색조 사용 하지 않음
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            // 네비게이션 바 클릭 시, 프레그먼트 전환
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.route:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.chatt:
                        selectedFragment = new ChatmenuFragment();
                        break;
                    case R.id.bookmark:
                        selectedFragment = new BookmarkFragment();
                        break;
                    case R.id.friend:
                        selectedFragment = new ProfileFragment();
                        break;
                }
                FragmentManager manager = getSupportFragmentManager();
                // 네비게이션 바 클릭 시 백 스택에 저장된 프레그먼트 모두 pop
                manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                manager.beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        int cnt = manager.getBackStackEntryCount(); // 백 스택에 저장된 프래그먼트 갯수
        long initTime = System.currentTimeMillis(); // 디바이스 부팅 후 지난 시간(밀리세컨드)

        if (cnt < 1)  // 백 스택에 프레그먼트가 존재하지 않는다면
            if (initTime - this.initTime > 3000) { // 지난 번 이전 버튼을 눌렀던 시간과 현재의 시간 차이가 3초 이상인 경우
                Toast.makeText(this, "이전 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
                this.initTime = System.currentTimeMillis(); // 현재의 디바이스 부팅 시간 저장
            } else // 3초 이하인 경우 애플리케이션 종료
                ActivityCompat.finishAffinity(this);
        else {
            String fragmentName = manager.getBackStackEntryAt(cnt - 1).getName(); // 백 스택 최상단의 프레그먼트 객체의 이름을 얻음
            if (fragmentName.equals("reviewwrite")) { // 프레그먼트의 이름 비교
                // 해당 태그 이름을 갖는 프래그먼트 객체를 얻음
                ReviewWriteFragment fragment = (ReviewWriteFragment) manager.findFragmentByTag(fragmentName);

                if (isEtEmpty(fragment.etTitle, fragment.etContent) == 2) { // 해당 프레그먼트의 에디트 텍스트 내용이 모두 비었다면
                    super.onBackPressed(); // 백 스택에서 프래그먼트 pop
                    return;
                }

                if (initTime - this.initTime > 3000) {// 지난 번 이전 버튼을 눌렀던 시간과 현재의 시간 차이가 3초 이상인 경우
                    Toast.makeText(this, "이전 버튼을 한번 더 누르면 작성된 글을 저장하지 않고, 리뷰 작성을 종료합니다.", Toast.LENGTH_SHORT).show();
                    this.initTime = System.currentTimeMillis(); // 현재의 디바이스 부팅 시간 저장
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("정말로 종료하시겠습니까?").setNegativeButton("No", null)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    HomeActivity.super.onBackPressed(); // 백 스택에서 프래그먼트 pop
                                }
                            }).show();
                }
            } else
                super.onBackPressed();
        }
    }

    public static int isEtEmpty(EditText... editTexts) {
        // 가변인자로 EditText 객체를 받아 내용이 없는 에디트텍스트의 갯수를 반환하는 메소드
        int cnt = 0;
        for (EditText et : editTexts)
            if (et.getText().toString().equals(""))
                cnt++;
        return cnt;
    }
}
