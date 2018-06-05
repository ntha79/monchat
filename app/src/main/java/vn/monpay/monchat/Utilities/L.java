package vn.monpay.monchat.Utilities;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import org.w3c.dom.Text;

import java.util.Locale;

/**
 * Created by mobilechatsystem@gmail.com on 06/04/2018.
 */

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

    public static void forceLocale(Context ctx, String langValue) {
        String lang = langValue;
        if (TextUtils.isEmpty(lang) || lang.equals(""))
            lang = current;

        if (lang.contains("en-EN") || lang.contains("en-US")|| lang.contains("en"))
            lang = "en";
        else if (lang.contains("vi-VN"))
            lang = "vi";
        else if (lang.contains("VN"))
            lang = "vi";
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration conf = ctx.getResources().getConfiguration();
        conf.locale = locale;
        ctx.getResources().updateConfiguration(conf, ctx.getResources().getDisplayMetrics());
        Configuration systemConf = ctx.getResources().getSystem().getConfiguration();
        systemConf.locale = locale;
        ctx.getResources().getSystem().updateConfiguration(systemConf, ctx.getResources().getSystem().getDisplayMetrics());
        Locale.setDefault(locale);
    }
}
