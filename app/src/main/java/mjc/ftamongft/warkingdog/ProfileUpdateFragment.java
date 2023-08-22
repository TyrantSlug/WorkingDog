package mjc.ftamongft.warkingdog;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileUpdateFragment extends Fragment {
    private EditText etPassword;
    private EditText etnewPassword;
    private EditText etnewPasswordConfirm;
    private EditText etNickname;
    private EditText etEmail;
    private Button btnDone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentActivity activity = getActivity();
        View v = inflater.inflate(R.layout.profileupdate, container, false);

        etPassword = v.findViewById(R.id.etPassword);
        etnewPassword = v.findViewById(R.id.etnewPassword);
        etnewPasswordConfirm = v.findViewById(R.id.etnewPasswordConfirm);
        etNickname = v.findViewById(R.id.name);
        etEmail = v.findViewById(R.id.etEmail);
        btnDone = v.findViewById(R.id.btnDone);

        String email = (String)getArguments().getSerializable("email");
        String nickName = (String)getArguments().getSerializable("nickname");

        etNickname.setText(nickName);
        etEmail.setText(email);

        // 비밀번호 일치 검사
        etnewPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = etnewPassword.getText().toString();
                String confirm = etnewPasswordConfirm.getText().toString();

                if (password.equals(confirm)) {
                    etnewPassword.setBackgroundColor(Color.GREEN);
                    etnewPasswordConfirm.setBackgroundColor(Color.GREEN);
                } else {
                    etnewPassword.setBackgroundColor(Color.RED);
                    etnewPasswordConfirm.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 비밀번호 입력 확인
                if (etPassword.getText().toString().length() == 0) {
                    Toast.makeText(activity, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }


                // 새 비밀번호 입력 확인
                if (etnewPassword.getText().toString().length() == 0) {
                    Toast.makeText(activity, "새 비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etnewPassword.requestFocus();
                    return;
                }

                // 새 비밀번호 확인 입력 확인
                if (etnewPasswordConfirm.getText().toString().length() == 0) {
                    Toast.makeText(activity, "새 비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etnewPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if (!etnewPassword.getText().toString().equals(etnewPasswordConfirm.getText().toString())) {
                        Toast.makeText(activity, "새 비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                        etnewPassword.setText("");
                        etnewPasswordConfirm.setText("");
                        etnewPassword.requestFocus();
                        return;
                }

                // 닉네임 입력 확인

                if (etNickname.getText().toString().length() == 0) {
                    Toast.makeText(activity, "닉네임을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etNickname.requestFocus();
                    return;
                }

                // 이메일 입력 확인
                if (etEmail.getText().toString().length() == 0) {
                    Toast.makeText(activity, "이메일을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etEmail.requestFocus();
                    return;
                }

                Toast.makeText(activity, "회원정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                activity.getSupportFragmentManager().popBackStack(); // DB 입력 후, 백 스택에서 현재 프래그먼트 pop
            }
        });

        return v;
    }
}
