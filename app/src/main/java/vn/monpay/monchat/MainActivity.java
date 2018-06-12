package vn.monpay.monchat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import io.fabric.sdk.android.Fabric;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import vn.monpay.monchat.API.Link;
import vn.monpay.monchat.Models.ChatTitleAdapter;
import vn.monpay.monchat.Models.ChatTitleItem;
import vn.monpay.monchat.Models.ContactItem;
import vn.monpay.monchat.Utilities.F;
import vn.monpay.monchat.Utilities.L;
import vn.monpay.monchat.Utilities.Token;
import vn.monpay.monchat.Utilities.VolleySingleton;

/**
 * Created by mobilechatsystem@gmail.com on 06/03/2018.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static MainActivity openActivity;
    public String LogTag ="MonChat";
    private int intent_result_signup = 1001;
    private int intent_result_contact_show = 1002;
    private int intent_result_contact_message = 1003;
    private int intent_result_merchant = 1004;


    FloatingActionButton fabMain;
    DrawerLayout drawerMain;
    NavigationView navigationView;
    Menu menuMain;
    RelativeLayout relativeLayout_content_main;

    private ProgressDialog progressDialog;

    //++login form=======================
    private EditText editText_login_username;
    private EditText editText_login_password;
    private Button button_login_signin;
    private Button button_login_signup;
    private Button button_login_with_facebook;
    private Button button_login_with_google;
    //--
    //++main===============================
    private ListView listView_main_chattitle;
    private Button button_main_menu;
    private Button button_main_search;
    private EditText editText_main_search;
    private boolean editText_main_search_visible = false;



    //--main===============================

    List<ChatTitleItem> listDataChatTitle = new ArrayList<>();
    ChatTitleAdapter chatTitleAdapter;

    private void LoadDemoData()
    {
        ContactItem.listDataContactItem = ContactItem.GetListDemo();
    }

    public void SetChatTitle(int fromIdValue, int toIdValue,String fromFullNameValue, String toFullNameValue,  String messageValue1)
    {
        boolean isExits = false;
        String code = F.FormatTopic(""+fromIdValue,""+toIdValue);
        for (ChatTitleItem obj: listDataChatTitle)
        {
            if(obj.getOwnerId()==SessionInfo.getOwnerId() && obj.getCode().equals(code))
            {
                isExits = true;
                obj.setLastMessage(messageValue1);
                obj.setLastTime(F.DateToStringHH_mm(new Date()));
            }
        }
        if(!isExits)
        {
            String fullName =fromFullNameValue;
            if(fromIdValue==SessionInfo.getOwnerId())
                fullName = toFullNameValue;
            ChatTitleItem it = new ChatTitleItem(SessionInfo.getOwnerId(),fromIdValue,toIdValue,code,fullName,true,0,messageValue1,F.DateToStringHH_mm(new Date()),true,true,F.DateToStringHH_mm(new Date()),F.DateToStringHH_mm(new Date()));
            listDataChatTitle.add(it);
        }
        chatTitleAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MultiDex.install(getApplicationContext());
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Answers(), new Crashlytics());
        F.CrashlyticsLogUser(this.getLocalClassName());

        setContentView(R.layout.activity_main);

        openActivity = this;
        LoadDemoData();

        L.LoadLanguage(getApplicationContext());
        L.forceLocale(getApplicationContext(),"");

        fabMain = (FloatingActionButton) findViewById(R.id.fab);
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SessionInfo.isLogin()) {
                    //GetListUsers();
                    Show_Contact(L.getString(getApplicationContext(),R.string.txt_create_new_message),true);
                    //Snackbar.make(view, "New message...", Snackbar.LENGTH_LONG)
                    //        .setAction("Action", null).show();
                }
                else
                {
                    Show_About("");
                }
            }
        });

        drawerMain = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        menuMain = navigationView.getMenu();

        relativeLayout_content_main = (RelativeLayout)findViewById(R.id.relativeLayout_content_main);

        listDataChatTitle = ChatTitleItem.GetListDemo();
        ChangeLanguage(false);
        ReloadUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == intent_result_contact_message && resultCode == RESULT_OK && data != null)
        {
            Bundle MBuddle = data.getExtras();
            if (MBuddle != null && MBuddle.containsKey("SELECTITEM"))
            {
                String SELECTITEM = MBuddle.getString("SELECTITEM");
                ContactItem contactItem = new ContactItem(SELECTITEM);
                Show_ChatPToP(contactItem.getFullName(),contactItem.getId());
            }
        }
        if (requestCode == intent_result_contact_show && resultCode == RESULT_OK && data != null)
        {
            Bundle MBuddle = data.getExtras();
            if (MBuddle != null && MBuddle.containsKey("SELECTITEM"))
            {
                String SELECTITEM = MBuddle.getString("SELECTITEM");
                ContactItem contactItem = new ContactItem(SELECTITEM);
                Show_ChatPToP(contactItem.getFullName(),contactItem.getId());
            }
        }

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu_new_group) {

        }
        else if (id == R.id.nav_menu_new_secret_chat) {
            Show_Contact(L.getString(getApplicationContext(),R.string.txt_create_new_message),true);
        }
        else if (id == R.id.nav_menu_new_chanel) {

        }
        else if (id == R.id.nav_menu_contact) {
            Show_Contact(L.getString(getApplicationContext(),R.string.txt_contact),false);
        }
        else if (id == R.id.nav_menu_saved_message) {

        }

        else if (id == R.id.nav_menu_invite_friends) {
            Show_Share();
        }
        else if (id == R.id.nav_menu_merchant) {
            Show_Merchant();
        }

        else if (id == R.id.nav_menu_settings) {

        }
        else if (id == R.id.nav_menu_language) {
            ChangeLanguage(true);
        }
        else if (id == R.id.nav_menu_logout) {
            Logout();
        }


        if(drawerMain!=null)
            drawerMain.closeDrawer(GravityCompat.START);
        return true;
    }

    //=============================================
    //Thuc hien load giao dien

    private void ChangeLanguage(boolean isChange){
        if(isChange) {
            if (L.IsEnglish()) {
                L.current = "vi-VN";
            } else
                L.current = "en-US";
            L.forceLocale(getApplicationContext(), "");
            L.SaveLanguage(getApplicationContext());
        }

        MenuItem it_nav_menu_new_group = menuMain.findItem(R.id.nav_menu_new_group);
        it_nav_menu_new_group.setTitle(L.getString(getApplicationContext(),R.string.txt_new_group));
        //it_nav_menu_new_group.setEnabled(false);

        MenuItem it_nav_menu_new_secret_chat = menuMain.findItem(R.id.nav_menu_new_secret_chat);
        it_nav_menu_new_secret_chat.setTitle(L.getString(getApplicationContext(),R.string.txt_new_secret_chat));
        //it_nav_menu_new_secret_chat.setEnabled(false);

        MenuItem it_nav_menu_new_chanel = menuMain.findItem(R.id.nav_menu_new_chanel);
        it_nav_menu_new_chanel.setTitle(L.getString(getApplicationContext(),R.string.txt_new_chanel));
        //it_nav_menu_new_chanel.setEnabled(false);

        MenuItem it_nav_menu_contact = menuMain.findItem(R.id.nav_menu_contact);
        it_nav_menu_contact.setTitle(L.getString(getApplicationContext(),R.string.txt_contact));
        //it_nav_menu_contact.setEnabled(false);

        MenuItem it_nav_menu_saved_message = menuMain.findItem(R.id.nav_menu_saved_message);
        it_nav_menu_saved_message.setTitle(L.getString(getApplicationContext(),R.string.txt_saved_message));
        //it_nav_menu_saved_message.setEnabled(false);



        MenuItem it_nav_menu_invite_friends = menuMain.findItem(R.id.nav_menu_invite_friends);
        it_nav_menu_invite_friends.setTitle(L.getString(getApplicationContext(),R.string.txt_invite_friends));
        //it_nav_menu_invite_friends.setEnabled(false);

        MenuItem it_nav_menu_settings = menuMain.findItem(R.id.nav_menu_settings);
        it_nav_menu_settings.setTitle(L.getString(getApplicationContext(),R.string.txt_settings));
        //it_nav_menu_settings.setEnabled(false);

        MenuItem it_nav_menu_language = menuMain.findItem(R.id.nav_menu_language);
        it_nav_menu_language.setTitle(L.getString(getApplicationContext(),R.string.txt_language));
        //it_nav_menu_L.setEnabled(false);

        MenuItem it_nav_menu_logout = menuMain.findItem(R.id.nav_menu_logout);
        it_nav_menu_logout.setTitle(L.getString(getApplicationContext(),R.string.txt_logout));
        //it_nav_menu_logout.setEnabled(false);

        MenuItem it_nav_menu_merchant = menuMain.findItem(R.id.nav_menu_merchant);
        it_nav_menu_merchant.setTitle(L.getString(getApplicationContext(),R.string.txt_merchant));
        //it_nav_menu_logout.setEnabled(false);

    }

    private void ReloadUI()
    {
        if(SessionInfo.isLogin())
        {
            drawerMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            relativeLayout_content_main.removeAllViews();
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View promptView = layoutInflater.inflate(R.layout.layout_main_access, null);
            relativeLayout_content_main.addView(promptView);

            fabMain.setImageResource(R.drawable.ic_pen_edit_white);

            button_main_menu= (Button)promptView.findViewById(R.id.button_main_menu);
            button_main_menu.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    F.EndEditing(MainActivity.this);
                    if(drawerMain!=null)
                        drawerMain.openDrawer(Gravity.LEFT);
                }
            });

            button_main_search = (Button)findViewById(R.id.button_main_search);
            button_main_search.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(editText_main_search_visible)
                    {
                        editText_main_search.setVisibility(View.GONE);
                        editText_main_search_visible = false;

                        Drawable img = getResources().getDrawable(R.drawable.ic_search);
                        button_main_search.setCompoundDrawablesWithIntrinsicBounds(null, null,img, null);
                        chatTitleAdapter = new ChatTitleAdapter(getApplicationContext(), listDataChatTitle);
                        listView_main_chattitle.setAdapter(chatTitleAdapter);
                    }
                    else
                    {
                        editText_main_search.setVisibility(View.VISIBLE);
                        editText_main_search_visible = true;
                        editText_main_search.requestFocus();

                        Drawable img = getResources().getDrawable(R.drawable.ic_close_color);
                        button_main_search.setCompoundDrawablesWithIntrinsicBounds(null, null,img, null);
                    }
                }
            });
            editText_main_search = (EditText)findViewById(R.id.editText_main_search);
            editText_main_search.setVisibility((editText_main_search_visible? View.VISIBLE:View.GONE));
            editText_main_search.addTextChangedListener(new TextWatcher() {

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
                        chatTitleAdapter = new ChatTitleAdapter(getApplicationContext(), listDataChatTitle);
                        listView_main_chattitle.setAdapter(chatTitleAdapter);
                    }
                    else
                    {
                        List<ChatTitleItem> seachList = ChatTitleAdapter.Search(listDataChatTitle,s.toString());
                        listView_main_chattitle.setAdapter(new ChatTitleAdapter(getApplicationContext(), seachList));
                    }
                }
            });

            listView_main_chattitle = (ListView) promptView.findViewById(R.id.listView_main_chattitle);
            listView_main_chattitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
                Object o = listView_main_chattitle.getItemAtPosition(position);
                ChatTitleItem contactItem = (ChatTitleItem) o;
                if(contactItem!=null) {
                    Show_ChatPToP(contactItem.getFullName(), contactItem.getToId());
                }
            }
        });
            chatTitleAdapter = new ChatTitleAdapter(getApplicationContext(), listDataChatTitle);
            listView_main_chattitle.setAdapter(chatTitleAdapter);

        }
        else
        {
            fabMain.setImageResource(android.R.drawable.ic_dialog_info);
            if(drawerMain!=null)
            {
                drawerMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
            relativeLayout_content_main.removeAllViews();
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View promptView = layoutInflater.inflate(R.layout.layout_main_login, null);
            relativeLayout_content_main.addView(promptView);

            editText_login_username = (EditText)promptView.findViewById(R.id.editText_login_username);
            editText_login_password = (EditText)promptView.findViewById(R.id.editText_login_password);

            button_login_signin = (Button)promptView.findViewById(R.id.button_login_signin);
            button_login_signin.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                    String email = editText_login_username.getText().toString();
                    String password = editText_login_password.getText().toString();

                    if(F.isEmpty(email))
                    {
                        F.ToastShort(getApplicationContext(),L.getString(getApplicationContext(),R.string.txt_email_is_required));
                        editText_login_username.requestFocus();
                        return;
                    }
                    if(F.isEmpty(password))
                    {
                        F.ToastShort(getApplicationContext(),L.getString(getApplicationContext(),R.string.txt_password_is_required));
                        editText_login_password.requestFocus();
                        return;
                    }
                    LoginProcess(email,password);
                }
            });
            button_login_signup = (Button)promptView.findViewById(R.id.button_login_signup);
            button_login_signup.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Show_Signup();
                }
            });
            button_login_with_facebook = (Button)promptView.findViewById(R.id.button_login_with_facebook);
            button_login_with_facebook.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Show_Facebook();
                }
            });
            button_login_with_google = (Button)promptView.findViewById(R.id.button_login_with_google);
            button_login_with_google.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Show_Google();
                }
            });

            editText_login_username.setText("root");//test
            editText_login_password.setText("1234");//test
        }
    }


    //++Show intent=====================================
    public  void Show_Share()
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,"MonChat - Ứng dụng chát tuyệt vời, Bạn tải ứng dụng tại https://monpay.vn nhé");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MonChat");
        startActivity(Intent.createChooser(shareIntent,  L.getString(getApplicationContext(),R.string.txt_share_monchat)));
    }
    public void Show_About(String link)
    {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        intent.putExtra("Link", link);
        startActivityForResult(intent,0);
    }
    public void Show_Signup()
    {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivityForResult(intent,intent_result_signup);
    }
    public void Show_Facebook()
    {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivityForResult(intent,intent_result_signup);
    }

    public void Show_Google()
    {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivityForResult(intent,intent_result_signup);
    }

    public void Show_Contact(String title, boolean isSelect)
    {
        Intent intent = new Intent(MainActivity.this, ContactActivity.class);
        intent.putExtra("Title", title);
        intent.putExtra("Select", isSelect);
        startActivityForResult(intent,(isSelect? intent_result_contact_message:intent_result_contact_show));
    }

    public void Show_ChatPToP(String friendFullName, int friendId)
    {
        Intent intent = new Intent(MainActivity.this, ChatPToPActivity.class);
        intent.putExtra("friendFullName", friendFullName);
        intent.putExtra("friendId", friendId);
        startActivityForResult(intent,0);
    }
    public void Show_Merchant()
    {
        Intent intent = new Intent(MainActivity.this, MerchantActivity.class);
        intent.putExtra("Select", false);
        startActivityForResult(intent,intent_result_merchant);
    }
    //--Show intent=====================================

    private void Show_ProgressDialog(String message)
    {
        if(progressDialog==null)
            progressDialog = new ProgressDialog(MainActivity.this);

        this.progressDialog.setMessage(message);
        this.progressDialog.show();
    }
    private void Dismiss_ProgressDialog()
    {
        if(progressDialog!=null)
            progressDialog.dismiss();

    }

    //===========================

    public void LoginProcess(String username, String password) {

        // Showing progress dialog at user registration time.
        Show_ProgressDialog(L.getString(getApplicationContext(),R.string.txt_please_wait));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(getApplicationContext());


        RequestQueue queue = volleySingleton.getRequestQueue();

        SessionInfo.setUserName(username);

        /*Post data*/
        JSONObject jsonObject = F.NewJSONObject("grant_type", "password","username",username,"password",password );
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Link.getLink(Link.link_auth_login), jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Link.LogResult(LogTag,response.toString(),"OK","","");
                Dismiss_ProgressDialog();

                String access_token = F.GetStringFromJSONObject(response,"access_token");
                if (!F.isEmpty(access_token))
                {
                    SessionInfo.InitLoginValue(response);
                    ReloadUI();

                    /*
                    mSocket.connect();
                    JSONObject njson = new JSONObject();
                    njson.putOpt("token", response.getString("access_token"));


                    mSocket.emit("authenticate",njson);
                    // Opening the user profile activity using intent.
                    Intent intent = new Intent(SignInActivity.this, ProfileActivity.class);

                    // Sending User Email to another activity using intent.
                    intent.putExtra("UserEmailTAG", EmailHolder+"; Access_Token: "+response.getString("access_token").toString());


                    startActivity(intent);
                    */

                } else {
                    //Send message when something goes wrong
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(MainActivity.this);

                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                        }
                    });
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Dismiss_ProgressDialog();
                Link.LogResult(LogTag,error.toString(),"ERR","","");
                F.ToastLong(getApplicationContext(),L.TransResult(getApplicationContext(), error.toString()));
                //test - de login lam tiep
                JSONObject jsonObject = F.NewJSONObject("access_token","eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJyb290Iiwic2NvcGUiOlsib3BlbmlkIl0sImV4cCI6MTUyODEyNzQ0OSwiaWF0IjoxNTI4MTI3MTQ5LCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIiwiUk9MRV9VU0VSIl0sImp0aSI6ImJiYTNmNjFiLTE5NzItNGJkNy1hZmJhLWZlNGExMDJjNzA4NyIsImNsaWVudF9pZCI6IndlYl9hcHAifQ.kdY_PWP1Ny_Uw1hS2ztlf3QILq7doMNQVGX2BE0x6xHn6MFXPZJNWCnNk4jY-xz8ZuhpxC79m9HP1MoWg93VAXJawarbNlelPGXRLpxkdE2kLFlKrnrJ6W1VzNkRuqRNom3aK6emywCwccZ-iMuoEz7r5xZmLMGWy7jxDMaqxgZPJ4dgYkwO-AV-AjmZN_uYObO2e56TZcRcyen3T_ukEGwF_bLH0b76Lctl_e1lqGJKY39fOhh3HoipvmmdA8JhNTTxMubl9gi59T9e9Q8cdpwY7ASRQmGqgkXnYUTAl6TVX63T4fJ4PA-Jw_BhuPFtfh_hSRSxALFd2wS8eVV49w","token_type","bearer","refresh_token","eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJyb290Iiwic2NvcGUiOlsib3BlbmlkIl0sImF0aSI6ImJiYTNmNjFiLTE5NzItNGJkNy1hZmJhLWZlNGExMDJjNzA4NyIsImV4cCI6MTUyODczMTk0OSwiaWF0IjoxNTI4MTI3MTQ5LCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIiwiUk9MRV9VU0VSIl0sImp0aSI6IjljZDBhMDA3LTZlMmItNDljMi1hOWVlLTkzM2ZmNjAwMmFhZCIsImNsaWVudF9pZCI6IndlYl9hcHAifQ.QxWY69N_BsTFDoOx3sfmmwbMey-3AURWBB3R0tOoFACmR1ruiCA44_AcdhiQ0bdhEytaomcio4ScaQKk9xCr67397kGkXip2AdPetg48Jck9rNDYEcaqbJ5u0T_5y_5SYFuhyyaxB9pfJ7cE08E7ki2EAJCfX9y5f1oCP9Tx39XXQb4u4093EnbGfwL5U4yQQ8NfUIo-VVS5_NVbEeCnI1Wsk8Gj0jxH-TM3Q6d4XUSIR5mH8i7B5fkHPl93o3Si3EtNsE3k3dilctLuyeTlvPF95bnIlhjjBMH761kp8yDRVja_LWacQWFWDQ-4zzLawKda3ydOONVV7_8R8gZVxg","expires_in",299,"scope","openid","iat",1528127149,"jti","bba3f61b-1972-4bd7-afba-fe4a102c7087");
                SessionInfo.InitLoginValue(jsonObject);
                ReloadUI();

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return Token.getHeaders();
            }
        };
        queue.add(jsonObjectRequest);
    }

    private void Logout()
    {
        SessionInfo.InitLogout();
        ReloadUI();
    }


    public void GetListUsers() {

        // Showing progress dialog at user registration time.
        Show_ProgressDialog(L.getString(getApplicationContext(),R.string.txt_please_wait));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(getApplicationContext());


        RequestQueue queue = volleySingleton.getRequestQueue();

        /*Post data*/
        JSONArray jsonObject = new JSONArray();
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Link.getLink(Link.link_uaa_api_users), jsonObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Link.LogResult(LogTag,response.toString(),"OK","","");
                Dismiss_ProgressDialog();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Dismiss_ProgressDialog();
                Link.LogResult(LogTag,error.toString(),"ERR","","");
                F.ToastLong(getApplicationContext(),L.TransResult(getApplicationContext(), error.toString()));

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return Token.getHeaders_BearerToken();
            }
        };
        queue.add(jsonObjectRequest);
    }

}
