package vn.monpay.monchat.Utilities;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mobilechatsystem@gmail.com on 06/03/2018.
 */

public class VolleySingleton {
    private static final String TAG = "VolleySingleton";
    private RequestQueue mRequestQueue;
    private static VolleySingleton sInstance;


    private VolleySingleton(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (sInstance == null)
            sInstance = new VolleySingleton(context);
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}