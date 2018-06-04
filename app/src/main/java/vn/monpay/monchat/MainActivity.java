package vn.monpay.monchat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.monpay.monchat.API.Link;
import vn.monpay.monchat.Utilities.F;
import vn.monpay.monchat.Utilities.L;
import vn.monpay.monchat.Utilities.VolleySingleton;

/**
 * Created by mobilechatsystem@gmail.com on 06/03/2018.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String LogTag ="MonChat";
    private int intent_result_signup = 1001;

    FloatingActionButton fab;
    DrawerLayout drawerMain;
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
    private Button button_main_logout;



    //--main===============================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SessionInfo.isLogin()) {
                    Snackbar.make(view, "New message...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
                    Show_About();
                }
            }
        });

        drawerMain = (DrawerLayout) findViewById(R.id.drawer_layout);
       // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //        this, drawerMain, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerMain.addDrawerListener(toggle);
        //toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        relativeLayout_content_main = (RelativeLayout)findViewById(R.id.relativeLayout_content_main);

        ReloadUI();
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //=============================================
    //Thuc hien load giao dien

    private void ReloadUI()
    {
        if(SessionInfo.isLogin())
        {
            drawerMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            relativeLayout_content_main.removeAllViews();
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View promptView = layoutInflater.inflate(R.layout.layout_main_access, null);
            relativeLayout_content_main.addView(promptView);

            fab.setImageResource(android.R.drawable.ic_dialog_email);

            button_main_logout = (Button)promptView.findViewById(R.id.button_main_logout);
            //button_main_logout.setText(Language.GetContent(""));
            button_main_logout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    SessionInfo.InitLogout();
                    ReloadUI();
                }
            });

        }
        else
        {
            fab.setImageResource(android.R.drawable.ic_dialog_info);
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
                        Toast.makeText(getApplicationContext(),"Email is required", Toast.LENGTH_SHORT).show();
                        editText_login_username.requestFocus();
                        return;
                    }
                    if(F.isEmpty(password))
                    {
                        Toast.makeText(getApplicationContext(),"Password is required", Toast.LENGTH_SHORT).show();
                        editText_login_password.requestFocus();
                        return;
                    }

                    //SessionInfo.setUserName("root");
                    //SessionInfo.setAccess_token("--");
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
    public void Show_About()
    {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
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

                Log.d(LogTag,response.toString());

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

//                        Toast.makeText(SignInActivity.this, response.toString(), Toast.LENGTH_LONG).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Dismiss_ProgressDialog();
                F.ToastShort(MainActivity.this,error.toString());

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<String, String>();
                headers.put("cache-control", "no-cache");
                headers.put("X-XSRF-TOKEN", "abcnnnd");
                headers.put("Cookie", "Idea-d0fd0ee6=4bf9a6bc-4dc4-4a06-aeba-6b1af486b719; _ga=GA1.1.855509286.1523590451; io=Xq7P4BWpCunPpKLLAAAF; XSRF-TOKEN=abcnnnd");
                headers.put("content-type", "application/json; charset=utf-8");
                // add headers <key,value>
                String credentials = "client"+":"+"secret";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(),
                        Base64.NO_WRAP);
                headers.put("Authorization", auth);
                return headers;
            }

//            @Override
//            public String getBodyContentType() {
//                return super.getBodyContentType();
//            }
            //            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                // Creating Map String Params.
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("grant_type", "password");
//                params.put("username", EmailHolder);
//                params.put("password", PasswordHolder);
//
//                return params;
//            }
        };
        queue.add(jsonObjectRequest);
    }

}
