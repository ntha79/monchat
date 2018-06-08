package vn.monpay.monchat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import vn.monpay.monchat.Models.ContactAdapter;
import vn.monpay.monchat.Models.MerchantAdapter;
import vn.monpay.monchat.Models.MerchantItem;
import vn.monpay.monchat.Models.MerchantItem;
import vn.monpay.monchat.Utilities.F;

public class MerchantActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener  {

    final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 9999;

    private RelativeLayout relativeLayout_merchant_title;
    private TextView textView_merchant_title;
    private Button button_merchant_menu;
    private EditText editText_merchant_search;
    private Button button_merchant_search;
    private Button button_merchant_map;
    private ListView listView_merchant_allmerchant;

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Location mLastLocationOld;
    LocationRequest mLocationRequest;

    SupportMapFragment mapFragment;
    GoogleMap mainMap;


    private boolean isShowMap=false;
    private boolean editText_merchant_search_visible=false;
    private List<MerchantItem> listDataMerchantItem= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        listDataMerchantItem = MerchantItem.GetListDemo();

        relativeLayout_merchant_title = (RelativeLayout)findViewById(R.id.relativeLayout_merchant_title);
        textView_merchant_title = (TextView)findViewById(R.id.textView_merchant_title);
        button_merchant_menu = (Button)findViewById(R.id.button_merchant_menu);
        button_merchant_menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        editText_merchant_search = (EditText)findViewById(R.id.editText_merchant_search);
        editText_merchant_search.setVisibility((editText_merchant_search_visible? View.VISIBLE:View.GONE));
        editText_merchant_search.addTextChangedListener(new TextWatcher() {

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
                    ShowData(listDataMerchantItem);
                    //listView_merchant_allmerchant.setAdapter(new MerchantAdapter(getApplicationContext(), listDataMerchantItem));
                }
                else
                {
                    List<MerchantItem> seachList = MerchantAdapter.Search(listDataMerchantItem,s.toString());

                    ShowData(seachList);

                    //listView_merchant_allmerchant.setAdapter(new MerchantAdapter(getApplicationContext(), seachList));
                }
            }
        });
        button_merchant_search = (Button)findViewById(R.id.button_merchant_search);
        //button_merchant_search.setText(Language.GetContent(""));
        button_merchant_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(editText_merchant_search_visible)
                {
                    F.EndEditing(MerchantActivity.this);
                    editText_merchant_search.setVisibility(View.GONE);
                    editText_merchant_search_visible = false;

                    Drawable img = getResources().getDrawable(R.drawable.ic_search);
                    button_merchant_search.setCompoundDrawablesWithIntrinsicBounds(null, null,img, null);
                    listView_merchant_allmerchant.setAdapter(new MerchantAdapter(getApplicationContext(), listDataMerchantItem));
                }
                else
                {
                    editText_merchant_search.setVisibility(View.VISIBLE);
                    editText_merchant_search_visible = true;

                    Drawable img = getResources().getDrawable(R.drawable.ic_close_color);
                    button_merchant_search.setCompoundDrawablesWithIntrinsicBounds(null, null,img, null);
                }
            }
        });
        button_merchant_map = (Button)findViewById(R.id.button_merchant_map);
        //button_merchant_map.setText(Language.GetContent(""));
        button_merchant_map.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(isShowMap)
                {
                    isShowMap = false;
                    getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();
                    listView_merchant_allmerchant.setVisibility(View.VISIBLE);
                    Drawable img = getResources().getDrawable(R.drawable.ic_location_white);
                    button_merchant_map.setCompoundDrawablesWithIntrinsicBounds(null, null,img, null);
                }
                else
                {
                    isShowMap = true;
                    getSupportFragmentManager().beginTransaction().show(mapFragment).commit();
                    listView_merchant_allmerchant.setVisibility(View.GONE);
                    Drawable img = getResources().getDrawable(R.drawable.ic_menu);
                    button_merchant_map.setCompoundDrawablesWithIntrinsicBounds(null, null,img, null);
                }

                String searchText = editText_merchant_search.getText().toString();
                if(TextUtils.isEmpty(searchText)||editText_merchant_search_visible==false)
                {
                    ShowData(listDataMerchantItem);
                }
                else
                {
                    List<MerchantItem> seachList = MerchantAdapter.Search(listDataMerchantItem,searchText);
                    ShowData(seachList);
                }
            }
        });
        listView_merchant_allmerchant = (ListView)findViewById(R.id.listView_merchant_allmerchant);
        listView_merchant_allmerchant.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
                Object o = listView_merchant_allmerchant.getItemAtPosition(position);
                MerchantItem contactItem = (MerchantItem) o;
                if(contactItem!=null)
                {
                    Show_About(contactItem.getWeblink(), contactItem.getFullName());
                }
            }
        });
        ShowData(listDataMerchantItem);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_merchant_map);
        mapFragment.getMapAsync(this);
        getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();

        checkMapsPermission();
    }

    public void Show_About(String link, String title)
    {
        Intent intent = new Intent(MerchantActivity.this, AboutActivity.class);
        intent.putExtra("Link", link);
        intent.putExtra("Title", title);
        startActivityForResult(intent,0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mainMap = googleMap;

        buildGoogleApiClient();

        mainMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mainMap.getUiSettings().setZoomControlsEnabled(true);
        mainMap.animateCamera(CameraUpdateFactory.zoomTo(SessionInfo.CameraZoom));

        mainMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(inputMethodManager!=null) {
                    View vi = getCurrentFocus();
                    if(vi!=null) {
                        IBinder ib = getCurrentFocus().getWindowToken();
                        if(ib!=null)
                            inputMethodManager.hideSoftInputFromWindow(ib, 0);
                    }
                }

            }
        });

        int zoomHeight = 0;
        View zoomControls = mapFragment.getView().findViewById(0x1);
        if (zoomControls != null && zoomControls.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            // ZoomControl is inside of RelativeLayout
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) zoomControls.getLayoutParams();

            // Align it to - parent top|left
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            // Update margins, set to 10dp
            final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200,
                    getResources().getDisplayMetrics());
            params.setMargins(0, 0, margin/5, margin);
            zoomControls.setLayoutParams(params);

            zoomHeight = params.height+margin;
        }


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mainMap.setMyLocationEnabled(true);
        View myLocation = mapFragment.getView().findViewById(0x2);
        if (myLocation != null && myLocation.getLayoutParams() instanceof RelativeLayout.LayoutParams) {

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) myLocation.getLayoutParams();

            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

            final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics());
            params.setMargins(0, 0, margin, zoomHeight);

            myLocation.setLayoutParams(params);
        }
        if(mLastLocation==null) {
            if(mGoogleApiClient!=null) {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            }
            else
            {
                buildGoogleApiClient();
            }
        }
        if(mLastLocation==null) {
            mLastLocation = getMyLocation();
        }
        if(mLastLocationOld == null)
            mLastLocationOld = mLastLocation;

        if(mLastLocation!=null && mainMap!=null) {
            LatLng currentLatLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            mainMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, SessionInfo.CameraZoom));
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void ShowData(List<MerchantItem> listData) {
        if (isShowMap && mainMap != null) {
            if (mainMap != null && listData != null) {
                mainMap.clear();
                for (MerchantItem currentMerchant : listData) {
                    if (currentMerchant.getLatitude()>0) {
                        LatLng currLatLng = new LatLng(currentMerchant.getLatitude(),currentMerchant.getLongitude());
                        Marker currMarker = mainMap.addMarker(new MarkerOptions()
                                .position(currLatLng)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                                .title(currentMerchant.getFullName() + F.NewLine() + currentMerchant.getMobilePhone())
                        );
                        currMarker.setTag(currentMerchant);
                    }
                }
            }
        }
        else
        {
            listView_merchant_allmerchant.setAdapter(new MerchantAdapter(getApplicationContext(), listData));
        }
    }
    private void checkMapsPermission() {

        requestAppPermissions(new String[]{
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET//,
                        //android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        //android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, R.string.txt_to_use_this_function_you_need_to_grant_location_permission
                , REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);

       /*

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            SOUtility.ToastShowShort(MainDriverActivity.this, Language.getString(getApplicationContext(), R.string.txt_to_use_this_function_you_need_to_grant_location_permission));
            requestMapsPermissions();
            return;
        } else {

        }
        */
    }
    protected void requestAppPermissions(final String[] requestedPermissions,
                                         final int stringId, final int requestCode) {
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean shouldShowRequestPermissionRationale = false;
        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission);
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale) {
                Snackbar.make(findViewById(android.R.id.content), stringId,
                        Snackbar.LENGTH_INDEFINITE).setAction("GRANT",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(MerchantActivity.this, requestedPermissions, requestCode);
                            }
                        }).show();
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        } else {
            onPermissionsGranted(requestCode);
        }
    }

    public void onPermissionsGranted(int requestCode)
    {

    }
    protected synchronized void buildGoogleApiClient() {
        if(mGoogleApiClient==null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }
    }
    private Location getMyLocation()
    {
        checkMapsPermission();
        Location myLocation =null;
        try {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (myLocation == null) {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_COARSE);
                String provider = lm.getBestProvider(criteria, true);
                myLocation = lm.getLastKnownLocation(provider);
            }
        }
        catch (Exception ex)
        {

        }
        return myLocation;
    }

}
