package vn.monpay.monchat.Utilities;

import android.text.TextUtils;

/**
 * Created by mobilechatsystem@gmail.com on 06/03/2018.
 */

public class F {
    public static boolean isEmpty(String value) {
        return TextUtils.isEmpty(value) || value==null || value.equals("");
    }

}
