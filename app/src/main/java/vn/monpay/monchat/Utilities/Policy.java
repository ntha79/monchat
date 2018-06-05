package vn.monpay.monchat.Utilities;

/**
 * Created by mobilechatsystem@gmail.com on 06/04/2018.
 */

public class Policy
{
    public static boolean isShort(String value, int minLength)
    {
        if(value.isEmpty()||value.length()<minLength)
            return true;
        return false;
    }
    public static boolean isLong(String value, int maxLength)
    {
        if(!value.isEmpty() && value.length()>maxLength)
            return true;
        return false;
    }

    public static boolean isShortUsername(String value)
    {
        int minLength = 6;
        return isShort(value,minLength);
    }
    public static boolean isLongUsername(String value)
    {
        int maxLength = 100;
        return isLong(value,maxLength);
    }
    public static boolean isShortEmail(String value)
    {
        int minLength = 6;
        return isShort(value,minLength);
    }

    public static boolean isLongEmail(String value)
    {
        int maxLength = 100;
        return isLong(value,maxLength);
    }
}
