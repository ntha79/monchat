package vn.monpay.monchat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.monpay.monchat.Models.ChatTitleAdapter;
import vn.monpay.monchat.Models.ChatTitleItem;
import vn.monpay.monchat.Models.ContactAdapter;
import vn.monpay.monchat.Models.ContactItem;
import vn.monpay.monchat.Models.MerchantItem;
import vn.monpay.monchat.Utilities.F;
import vn.monpay.monchat.Utilities.L;

public class ContactActivity extends AppCompatActivity {

    private int intent_result_addContact = 2001;
    private int intent_result_editContact = 2002;

    private String title = "";
    private boolean isSelect = false;

    private RelativeLayout relativeLayout_contact_title;
    private TextView textView_contact_title;
    private Button button_contact_menu;
    private EditText editText_contact_search;
    private Button button_contact_search;
    private Button button_contact_add;
    private ListView listView_contact_chattitle;

    private boolean editText_contact_search_visible = false;
    //private List<ContactItem> listDataContactItem= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        title = L.getString(getApplicationContext(),R.string.txt_contact);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle MBuddle = intent.getExtras();
            if (MBuddle != null && MBuddle.containsKey("Title"))
            {
                title = MBuddle.getString("Title");
            }
            if (MBuddle != null && MBuddle.containsKey("Select"))
            {
                isSelect = MBuddle.getBoolean("Select");
            }
        }

        //listDataContactItem = ContactItem.GetListDemo();
        relativeLayout_contact_title = (RelativeLayout)findViewById(R.id.relativeLayout_contact_title);


        textView_contact_title = (TextView)findViewById(R.id.textView_contact_title);
        textView_contact_title.setText(title);

        button_contact_menu = (Button)findViewById(R.id.button_contact_menu);
        //button_contact_menu.setText(Language.GetContent(""));
        button_contact_menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                F.EndEditing(ContactActivity.this);
                finish();
            }
        });
        editText_contact_search = (EditText)findViewById(R.id.editText_contact_search);
        editText_contact_search.setVisibility((editText_contact_search_visible? View.VISIBLE:View.GONE));
        editText_contact_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s))
                {
                    listView_contact_chattitle.setAdapter(new ContactAdapter(getApplicationContext(), ContactItem.listDataContactItem));
                }
                else
                {
                    List<ContactItem> seachList = ContactAdapter.Search(ContactItem.listDataContactItem,s.toString());
                    listView_contact_chattitle.setAdapter(new ContactAdapter(getApplicationContext(), seachList));
                }
            }
        });

        button_contact_search = (Button)findViewById(R.id.button_contact_search);
        button_contact_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(editText_contact_search_visible)
                {
                    F.EndEditing(ContactActivity.this);
                    editText_contact_search.setVisibility(View.GONE);
                    editText_contact_search_visible = false;

                    Drawable img = getResources().getDrawable(R.drawable.ic_search);
                    button_contact_search.setCompoundDrawablesWithIntrinsicBounds(null, null,img, null);
                    listView_contact_chattitle.setAdapter(new ContactAdapter(getApplicationContext(), ContactItem.listDataContactItem));
                }
                else
                {
                    editText_contact_search.setVisibility(View.VISIBLE);
                    editText_contact_search_visible = true;

                    Drawable img = getResources().getDrawable(R.drawable.ic_close_color);
                    button_contact_search.setCompoundDrawablesWithIntrinsicBounds(null, null,img, null);
                }
            }
        });
        button_contact_add = (Button)findViewById(R.id.button_contact_add);
        //button_contact_add.setText(Language.GetContent(""));
        button_contact_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                F.EndEditing(ContactActivity.this);
                Intent intent = new Intent(ContactActivity.this, EditContactActivity.class);
                //intent.putExtra("Link", link);
                startActivityForResult(intent,intent_result_addContact);
            }
        });
        listView_contact_chattitle = (ListView)findViewById(R.id.listView_contact_chattitle);

        listView_contact_chattitle.setAdapter(new ContactAdapter(getApplicationContext(), ContactItem.listDataContactItem));

        listView_contact_chattitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
                Object o = listView_contact_chattitle.getItemAtPosition(position);
                ContactItem contactItem = (ContactItem) o;
                if(contactItem!=null) {
                    if (isSelect) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("SELECTITEM", contactItem.getJSON());
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    } else {
                        Intent intent = new Intent(ContactActivity.this, EditContactActivity.class);
                        intent.putExtra("baseId", contactItem.getId());
                        intent.putExtra("firstName", contactItem.getFirstName());
                        intent.putExtra("lastName", contactItem.getLastName());
                        intent.putExtra("mobilePhone", contactItem.getMobilePhone());
                        intent.putExtra("email", contactItem.getEmail());
                        startActivityForResult(intent,intent_result_editContact);
                    }
                }
            }
        });
        listView_contact_chattitle.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> a, View v, int position, long id)
            {
                Object o = listView_contact_chattitle.getItemAtPosition(position);
                ContactItem contactItem = (ContactItem) o;
                if(contactItem!=null) {

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("SELECTITEM", contactItem.getJSON());
                        setResult(RESULT_OK, returnIntent);
                        finish();

                }
                return true;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == intent_result_addContact||requestCode == intent_result_editContact)
        {
            listView_contact_chattitle.setAdapter(new ContactAdapter(getApplicationContext(), ContactItem.listDataContactItem));
        }
    }
}
