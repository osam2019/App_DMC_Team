package com.milcam.deep.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.milcam.deep.R;

public class UserPWActivity extends AppCompatActivity {
    private EditText user_pw1;
    private EditText user_pw2;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpw);

        user_pw1 = findViewById(R.id.user_pw1);
        user_pw2 = findViewById(R.id.user_pw2);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(saveBtnClickListener);
    }


    Button.OnClickListener saveBtnClickListener = new View.OnClickListener() {
        public void onClick(final View view) {
            String pw1 = user_pw1.getText().toString().trim();
            if (pw1.length()<8) {
                Toast.makeText(getApplicationContext(), "Please enter at least eight characters.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!pw1.equals(user_pw2.getText().toString().trim())) {
                Toast.makeText(getApplicationContext(), "Password does not match the confirm password.", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user.updatePassword(pw1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Password changed", Toast.LENGTH_SHORT).show();

                    InputMethodManager imm= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(user_pw2.getWindowToken(), 0);

                    onBackPressed();
                }
            });
        }
    };
}
