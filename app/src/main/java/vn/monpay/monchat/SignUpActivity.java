package vn.monpay.monchat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import vn.monpay.monchat.Utilities.F;
import vn.monpay.monchat.Utilities.L;
import vn.monpay.monchat.Utilities.Policy;

/**
 * Created by mobilechatsystem@gmail.com on 06/04/2018.
 */
public class SignUpActivity extends AppCompatActivity {
    private TextView textView_signup_title;
    private Button button_signup_back;
    private EditText editText_registration_username;
    private EditText editText_registration_email;
    private EditText editText_registration_password;
    private EditText editText_registration_confirm_password;
    private Button button_signup_register;
    private FloatingActionButton fab_signup_about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fab_signup_about = (FloatingActionButton)findViewById(R.id.fab_signup_about);
        fab_signup_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Show_About();
            }
        });

        textView_signup_title = (TextView)findViewById(R.id.textView_signup_title);
        button_signup_back = (Button)findViewById(R.id.button_signup_back);
        button_signup_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        editText_registration_username = (EditText)findViewById(R.id.editText_registration_username);
        editText_registration_email = (EditText)findViewById(R.id.editText_registration_email);
        editText_registration_password = (EditText)findViewById(R.id.editText_registration_password);
        editText_registration_confirm_password = (EditText)findViewById(R.id.editText_registration_confirm_password);
        button_signup_register = (Button)findViewById(R.id.button_signup_register);
        button_signup_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String username = editText_registration_username.getText().toString();
                String email = editText_registration_email.getText().toString();
                String password = editText_registration_password.getText().toString();
                String confirm_password = editText_registration_confirm_password.getText().toString();

                if(F.isEmpty(username))
                {
                    F.ToastShort(getApplicationContext(), L.getString(getApplicationContext(),R.string.txt_username_is_required));
                    editText_registration_username.requestFocus();
                    return;
                }
                if(Policy.isShortUsername(username))
                {
                    F.ToastShort(getApplicationContext(), L.getString(getApplicationContext(),R.string.txt_username_is_short));
                    editText_registration_username.requestFocus();
                    return;
                }
                if(Policy.isLongUsername(username))
                {
                    F.ToastShort(getApplicationContext(), L.getString(getApplicationContext(),R.string.txt_username_is_long));
                    editText_registration_username.requestFocus();
                    return;
                }

                if(F.isEmpty(email))
                {
                    F.ToastShort(getApplicationContext(), L.getString(getApplicationContext(),R.string.txt_email_is_required));
                    editText_registration_email.requestFocus();
                    return;
                }
                if(!email.contains("@"))
                {
                    F.ToastShort(getApplicationContext(), L.getString(getApplicationContext(),R.string.txt_email_is_invalid));
                    editText_registration_email.requestFocus();
                    return;
                }
                if(Policy.isShortEmail(email))
                {
                    F.ToastShort(getApplicationContext(), L.getString(getApplicationContext(),R.string.txt_email_is_short));
                    editText_registration_email.requestFocus();
                    return;
                }
                if(Policy.isLongEmail(email))
                {
                    F.ToastShort(getApplicationContext(), L.getString(getApplicationContext(),R.string.txt_email_is_long));
                    editText_registration_email.requestFocus();
                    return;
                }

                if(F.isEmpty(password))
                {
                    F.ToastShort(getApplicationContext(), L.getString(getApplicationContext(),R.string.txt_password_is_required));
                    editText_registration_password.requestFocus();
                    return;
                }
                if(F.isEmpty(confirm_password))
                {
                    F.ToastShort(getApplicationContext(), L.getString(getApplicationContext(),R.string.txt_confirm_password_is_required));
                    editText_registration_confirm_password.requestFocus();
                    return;
                }

                if(!password.equals(confirm_password))
                {
                    F.ToastShort(getApplicationContext(), L.getString(getApplicationContext(),R.string.txt_confirm_password_is_invalid));
                    editText_registration_confirm_password.requestFocus();
                    return;
                }
            }
        });


    }
    public void Show_About()
    {
        Intent intent = new Intent(SignUpActivity.this, AboutActivity.class);
        startActivityForResult(intent,0);
    }
}
