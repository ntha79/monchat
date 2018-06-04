package vn.monpay.monchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import vn.monpay.monchat.Utilities.F;

/**
 * Created by mobilechatsystem@gmail.com on 06/03/2018.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

                    SessionInfo.setUserName("root");
                    SessionInfo.setAccess_token("--");
                    ReloadUI();
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
    private void Dismiss_ProgressDialog(String message)
    {
        if(progressDialog!=null)
            progressDialog.dismiss();

    }
}
