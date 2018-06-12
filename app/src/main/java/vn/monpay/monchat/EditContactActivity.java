package vn.monpay.monchat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vn.monpay.monchat.Models.ContactItem;

public class EditContactActivity extends AppCompatActivity {

    private int baseId = 0;			//Y nghia:
    private String firstName = "";			//Y nghia:
    private String lastName = "";			//Y nghia:
    private String mobilePhone = "";			//Y nghia:
    private String email = "";			//Y nghia:

    private RelativeLayout relativeLayout_editcontact_title;
    private TextView textView_editcontact_title;
    private Button button_editcontact_menu;
    private Button button_editcontact_add;
    private ImageView imageView_editcontact_avatar;
    private TextView textView_editcontact_name;
    private EditText editText_editcontact_firstname;
    private EditText editText_editcontact_lastname;
    private Button button_editcontact_country;
    private TextView textView_editcontact_code;
    private EditText editText_editcontact_mobilephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle MBuddle = intent.getExtras();
            if (MBuddle != null && MBuddle.containsKey("baseId"))
            {
                baseId = MBuddle.getInt("baseId");
            }
            if (MBuddle != null && MBuddle.containsKey("firstName"))
            {
                firstName = MBuddle.getString("firstName");
            }
            if (MBuddle != null && MBuddle.containsKey("lastName"))
            {
                lastName = MBuddle.getString("lastName");
            }
            if (MBuddle != null && MBuddle.containsKey("mobilePhone"))
            {
                mobilePhone = MBuddle.getString("mobilePhone");
            }
            if (MBuddle != null && MBuddle.containsKey("email"))
            {
                email = MBuddle.getString("email");
            }
        }

        relativeLayout_editcontact_title = (RelativeLayout)findViewById(R.id.relativeLayout_editcontact_title);


        textView_editcontact_title = (TextView)findViewById(R.id.textView_editcontact_title);
        button_editcontact_menu = (Button)findViewById(R.id.button_editcontact_menu);
        button_editcontact_menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        button_editcontact_add = (Button)findViewById(R.id.button_editcontact_add);
        button_editcontact_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                firstName = editText_editcontact_firstname.getText().toString();
                lastName = editText_editcontact_lastname.getText().toString();
                mobilePhone = editText_editcontact_mobilephone.getText().toString();

                ContactItem.Edit(SessionInfo.getOwnerId(), baseId,firstName,lastName,mobilePhone,email);
                finish();
            }
        });
        imageView_editcontact_avatar = (ImageView)findViewById(R.id.imageView_editcontact_avatar);

        textView_editcontact_name = (TextView)findViewById(R.id.textView_editcontact_name);
        editText_editcontact_firstname = (EditText)findViewById(R.id.editText_editcontact_firstname);
        editText_editcontact_firstname.setText(firstName);

        editText_editcontact_lastname = (EditText)findViewById(R.id.editText_editcontact_lastname);
        editText_editcontact_lastname.setText(lastName);
        button_editcontact_country = (Button)findViewById(R.id.button_editcontact_country);
        button_editcontact_country.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });
        textView_editcontact_code = (TextView)findViewById(R.id.textView_editcontact_code);
        editText_editcontact_mobilephone = (EditText)findViewById(R.id.editText_editcontact_mobilephone);
        editText_editcontact_mobilephone.setText(mobilePhone);
    }
}
