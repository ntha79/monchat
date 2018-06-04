package vn.monpay.monchat.Utilities;

import android.content.Context;
import android.support.annotation.StringRes;
public class L {

    public static String current = "en-US";

    public static boolean IsEnglish()
    {
        return current.contains("en-US")||current.contains("en")||current.contains("US");
    }

    public static String getString(Context context, @StringRes int stringId)
    {
        return context.getString(stringId);
    }
}
