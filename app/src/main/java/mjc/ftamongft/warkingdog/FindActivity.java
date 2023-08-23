package mjc.ftamongft.warkingdog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FindActivity extends AppCompatActivity {

    private EditText etidemail;
    private EditText etpwid;
    private EditText etpwemail;
    private Button btnidDone;
    private Button btnpwDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);

        etidemail = findViewById(R.id.etidemail);
        etpwid = findViewById(R.id.etpwid);
        etpwemail = findViewById(R.id.etpwemail);
        btnidDone = findViewById(R.id.btnidDone);
        btnpwDone = findViewById(R.id.btnpwDone);


        btnidDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 이메일 입력 확인
                if (etidemail.getText().toString().length() == 0) {
                    Toast.makeText(FindActivity.this, "이메일을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etidemail.requestFocus();
                    return;
                }

                Intent result = new Intent();
                result.putExtra("ID", etidemail.getText().toString());

                // 데이터베이스에서 불러온 아이디
     //           tvid.setText(id);

                // 자신을 호출한 Activity로 데이터를 보낸다.
                setResult(RESULT_OK, result);
                finish();
            }
        });
        btnpwDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 아이디 입력 확인
                if (etpwid.getText().toString().length() == 0) {
                    Toast.makeText(FindActivity.this, "아이디를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etpwid.requestFocus();
                    return;
                }

                // 이메일 입력 확인

                if (etpwemail.getText().toString().length() == 0) {
                    Toast.makeText(FindActivity.this, "이메일을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etpwemail.requestFocus();
                    return;
                }

                Intent result = new Intent();
                result.putExtra("ID", etpwid.getText().toString());

                // 데이터베이스에서 불러온 아이디
      //          tvpw.setText(id);

                // 자신을 호출한 Activity로 데이터를 보낸다.
                setResult(RESULT_OK, result);
                finish();
            }
        });


    }
}

