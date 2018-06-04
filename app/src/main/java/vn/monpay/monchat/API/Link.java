package vn.monpay.monchat.API;

/**
 * Created by mobilechatsystem@gmail.com on 06/03/2018.
 */

public class Link {
    public static final String link_public = "http://35.198.246.74:8080/";//http://35.198.245.187:8080/";//auth/login

    public static final String link_auth_login = "auth/login";

    public static String getLink(String function)
    {
        return link_public+function;
    }
}
