package mjc.ftamongft.warkingdog;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistActivity extends AppCompatActivity {

    private EditText etID;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private EditText etNickname;
    private EditText etEmail;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        etID = findViewById(R.id.etID);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        etNickname = findViewById(R.id.etNickname);
        etEmail = findViewById(R.id.etEmail);
        btnDone = findViewById(R.id.btnDone);

        // 비밀번호 일치 검사
        etPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = etPassword.getText().toString();
                String confirm = etPasswordConfirm.getText().toString();

                if (password.equals(confirm)) {
                    etPassword.setBackgroundColor(Color.GREEN);
                    etPasswordConfirm.setBackgroundColor(Color.GREEN);
                } else {
                    etPassword.setBackgroundColor(Color.RED);
                    etPasswordConfirm.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 아이디 입력 확인
                if (etID.getText().toString().length() == 0) {
                    Toast.makeText(RegistActivity.this, "아이디를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etID.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if (etPassword.getText().toString().length() == 0) {
                    Toast.makeText(RegistActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                if (etPasswordConfirm.getText().toString().length() == 0) {
                    Toast.makeText(RegistActivity.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if (!etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())) {
                    Toast.makeText(RegistActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    etPassword.requestFocus();
                    return;
                }

                // 닉네임 입력 확인

                if (etNickname.getText().toString().length() == 0) {
                    Toast.makeText(RegistActivity.this, "닉네임을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etNickname.requestFocus();
                    return;
                }

                // 이메일 입력 확인
                if (etEmail.getText().toString().length() == 0) {
                    Toast.makeText(RegistActivity.this, "이메일을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etEmail.requestFocus();
                    return;
                }

                Toast.makeText(RegistActivity.this,"회원가입이 성공적으로 되었습니다.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

}