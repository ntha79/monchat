package vn.monpay.monchat;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.monpay.monchat.Models.ChatMessageAdapter;
import vn.monpay.monchat.Models.ChatMessageItem;
import vn.monpay.monchat.Utilities.F;
import vn.monpay.monchat.Utilities.TestAutoMessage;

public class ChatPToPActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener{

    final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 9999;

    private RelativeLayout relativeLayout_chat_ptop_title;
    private TextView textView_chat_ptop_title;
    private Button button_chat_ptop_menu;
    private Button button_chat_ptop_right_menu;
    private ListView listView_chat_ptop_list;
    private RelativeLayout linearLayout_chat_ptop_bottom;
    private EditText editText_chat_ptop_messenger;
    private ImageButton button_chat_ptop_send;

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Location mLastLocationOld;
    LocationRequest mLocationRequest;

    SupportMapFragment mapFragment;
    GoogleMap mainMap;
    Marker frientMaker;

    private boolean isShowMap=false;

    private ChatMessageAdapter chatMessageAdapter;
    private List<ChatMessageItem> listDataChatMessageItem = new ArrayList<>();
    private int friendId = 0;
    private String friendFullName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_p_to_p);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle MBuddle = intent.getExtras();
            if (MBuddle != null && MBuddle.containsKey("friendFullName"))
            {
                friendFullName = MBuddle.getString("friendFullName");
            }
            if (MBuddle != null && MBuddle.containsKey("friendId"))
            {
                friendId = MBuddle.getInt("friendId");
            }
        }
        listDataChatMessageItem = ChatMessageItem.Get(friendId);
        chatMessageAdapter = new ChatMessageAdapter(getApplicationContext(),listDataChatMessageItem);
        chatMessageAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(chatMessageAdapter.getCount()>0)
                    listView_chat_ptop_list.setSelection(chatMessageAdapter.getCount() - 1);
            }
        });
        relativeLayout_chat_ptop_title = (RelativeLayout)findViewById(R.id.relativeLayout_chat_ptop_title);

        linearLayout_chat_ptop_bottom = (RelativeLayout)findViewById(R.id.linearLayout_chat_ptop_bottom);


        textView_chat_ptop_title = (TextView)findViewById(R.id.textView_chat_ptop_title);
        textView_chat_ptop_title.setText(friendFullName);
        button_chat_ptop_menu = (Button)findViewById(R.id.button_chat_ptop_menu);
        //button_chat_ptop_menu.setText(Language.GetContent(""));
        button_chat_ptop_menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        button_chat_ptop_right_menu = (Button)findViewById(R.id.button_chat_ptop_right_menu);
        //button_chat_ptop_right_menu.setText(Language.GetContent(""));
        button_chat_ptop_right_menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(isShowMap)
                {
                    isShowMap = false;
                    getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();
                    Drawable img = getResources().getDrawable(R.drawable.ic_location_white);
                    button_chat_ptop_right_menu.setCompoundDrawablesWithIntrinsicBounds(null, null,img, null);
                    chatMessageAdapter.SetIsShowInMap(isShowMap);

                    ViewGroup.LayoutParams params = listView_chat_ptop_list.getLayoutParams();
                    //if(listViewHeight>1)
                    {
                        params.height = ActionBar.LayoutParams.MATCH_PARENT;// listViewHeight;
                        listView_chat_ptop_list.setLayoutParams(params);
                    }
                }
                else
                {
                    isShowMap = true;
                    chatMessageAdapter.SetIsShowInMap(isShowMap);

                    getSupportFragmentManager().beginTransaction().show(mapFragment).commit();
                    Drawable img = getResources().getDrawable(R.drawable.ic_location_red);
                    button_chat_ptop_right_menu.setCompoundDrawablesWithIntrinsicBounds(null, null,img, null);

                    ViewGroup.LayoutParams params = listView_chat_ptop_list.getLayoutParams();
                    params.height = 300;
                    listView_chat_ptop_list.setLayoutParams(params);
                    if(frientMaker==null) {
                        Bitmap bmp = TestAutoMessage.getBitmapAvatar(getApplicationContext(),friendId);

                        LatLng currLatLng = new LatLng(21.022767, 105.813434);
                        frientMaker = mainMap.addMarker(new MarkerOptions()
                                .position(currLatLng)
                                .icon((bmp==null? BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN):BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(bmp))))
                                .title(friendFullName)
                        );
                        frientMaker.setTag(friendId);
                    }
                }
            }
        });
        listView_chat_ptop_list = (ListView)findViewById(R.id.listView_chat_ptop_list);
        listView_chat_ptop_list.setOnItemClickListener(new AdapterView.OnItemClickListener() { @Override public void onItemClick(AdapterView<?> a, View v, int position, long id)
        {}
        });
        listView_chat_ptop_list.setAdapter(chatMessageAdapter);

        editText_chat_ptop_messenger = (EditText)findViewById(R.id.editText_chat_ptop_messenger);
        editText_chat_ptop_messenger.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() ==       KeyEvent.KEYCODE_ENTER)
                {
                    SendMessage();
                    return true;
                }

                return false;
            }
        });
        button_chat_ptop_send = (ImageButton)findViewById(R.id.button_chat_ptop_send);
        //button_chat_ptop_send.setText(Language.GetContent(""));
        button_chat_ptop_send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SendMessage();
            }
        });
        if(chatMessageAdapter.getCount()>0)
        {
            listView_chat_ptop_list.setSelection(chatMessageAdapter.getCount() -1);
        }
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chat_ptop_map);
        mapFragment.getMapAsync(this);
        getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();
    }
    private void SendMessage()
    {
        String message = editText_chat_ptop_messenger.getText().toString();
        if(F.isEmpty(message))
            return;
        String subMessage = message;
        if(message.startsWith("QC"))
        {
            subMessage= "QC:"+TestAutoMessage.AutoReply("QC");
            if(!F.isEmpty(subMessage))
            {
                ChatMessageItem itemResult = ChatMessageItem.Add(SessionInfo.getOwnerId(), friendId,SessionInfo.getOwnerId(),friendFullName,SessionInfo.getFullName(),subMessage);
                listDataChatMessageItem.add(itemResult);
                chatMessageAdapter.notifyDataSetChanged();
                MainActivity.openActivity.SetChatTitle(friendId,SessionInfo.getOwnerId(),friendFullName, SessionInfo.getFullName(),subMessage);
            }
        }
        else {

            if (message.startsWith("R:"))
                subMessage = subMessage.substring(2);
            ChatMessageItem item = ChatMessageItem.Add(SessionInfo.getOwnerId(), SessionInfo.getOwnerId(), friendId, SessionInfo.getFullName(), friendFullName, subMessage);
            listDataChatMessageItem.add(item);
            chatMessageAdapter.notifyDataSetChanged();
            MainActivity.openActivity.SetChatTitle(SessionInfo.getOwnerId(), friendId, SessionInfo.getFullName(), friendFullName, subMessage);
            if (message.startsWith("R:")) {
                ChatMessageItem itemResult = ChatMessageItem.Add(SessionInfo.getOwnerId(), friendId, SessionInfo.getOwnerId(), friendFullName, SessionInfo.getFullName(), " Auto reply " + F.DateToStringMMM_dd_yyyy_HH_mm_ss(new Date()));
                listDataChatMessageItem.add(itemResult);
                chatMessageAdapter.notifyDataSetChanged();
                MainActivity.openActivity.SetChatTitle(friendId, SessionInfo.getOwnerId(), friendFullName, SessionInfo.getFullName(), subMessage);
            }
            else {
                subMessage = TestAutoMessage.AutoReply(message);
                if (!F.isEmpty(subMessage)) {
                    ChatMessageItem itemResult = ChatMessageItem.Add(SessionInfo.getOwnerId(), friendId, SessionInfo.getOwnerId(), friendFullName, SessionInfo.getFullName(), subMessage);
                    listDataChatMessageItem.add(itemResult);
                    chatMessageAdapter.notifyDataSetChanged();
                    MainActivity.openActivity.SetChatTitle(friendId, SessionInfo.getOwnerId(), friendFullName, SessionInfo.getFullName(), subMessage);
                }
            }
        }
        listView_chat_ptop_list.setSelection(chatMessageAdapter.getCount() -1);
        editText_chat_ptop_messenger.setText("") ;
        editText_chat_ptop_messenger.requestFocus();
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
    public void onLocationChanged(Location location) {

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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //======================
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
                                ActivityCompat.requestPermissions(ChatPToPActivity.this, requestedPermissions, requestCode);
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

    public Bitmap drawTextToBitmap( Bitmap imageBitmap) {
        int ww = imageBitmap.getWidth();
        int hh = imageBitmap.getHeight();
        int rr = 10;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(ww+rr, hh+rr, conf);
        Canvas canvas1 = new Canvas(bmp);

        Paint color = new Paint();
        color.setTextSize(30);
        color.setColor(Color.WHITE);

        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;

        Paint paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setStyle(Paint.Style.FILL);
        canvas1.drawRoundRect(0,0,ww+rr,hh+rr,50,50,paint2);
        //Bitmap resized = Bitmap.createScaledBitmap(imageBitmap, 80, 80, true);
        canvas1.drawBitmap(imageBitmap, rr/2, rr/2, color);

        //canvas1.drawText(friendFullName, 0, 40, color);



        return bmp;
    }
}
