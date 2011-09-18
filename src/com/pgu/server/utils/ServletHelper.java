package com.pgu.server.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.utils.SystemProperty;
import com.googlecode.objectify.Objectify;
import com.pgu.server.dao.ObjectifyDao;
import com.pgu.shared.UserAccount;
import com.pgu.shared.UserAccount.ProviderAuth;

public class ServletHelper {

    public enum Attributes {
        REQUEST_TOKEN, FACEBOOK_CODE, USER_ID, IS_LOGGED_IN
    }

    public static String getApplicationURL(final HttpServletRequest request) {
        if (ServletHelper.isDevelopment(request)) {
            return "http://127.0.0.1:8888/Pgu_languages.html?gwt.codesvr=127.0.0.1:9997";
        } else {
            return ServletHelper.getBaseUrl(request);
        }
    }

    public static String getResponse(final String thisUrl) {
        URL url = null;
        try {
            url = new URL(thisUrl);
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        try {
            return getString(con.getInputStream(), "utf-8");
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isDevelopment(final HttpServletRequest request) {
        return SystemProperty.environment.value() != SystemProperty.Environment.Value.Production;
    }

    public static String getBaseUrl(final HttpServletRequest request) {
        String port = "";
        if (!(request.getServerPort() == 80 || request.getServerPort() == 443)) {
            port = ":" + request.getServerPort();
        }
        return request.getScheme() + "://" + request.getServerName() + port + request.getContextPath();
    }

    public static String buildCallBackURL(final HttpServletRequest request) {
        return request.getRequestURL().toString() + "callback";
    }

    private static String getString(final InputStream is, final String charEncoding) {
        try {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            final byte ba[] = new byte[8192];
            int read;
            read = is.read(ba);
            while (read > -1) {
                out.write(ba, 0, read);
                read = is.read(ba);
            }
            String returnString = out.toString(charEncoding);
            if (returnString.equalsIgnoreCase("{}")) {
                returnString = "[{}]";
            }
            return returnString;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (final IOException e) {
                    // fail silently
                }
            }
        }
        return "";
    }

    public static void setUserInDBAndSession(final UserAccount userFromProvider, final HttpServletRequest request) {
        final Objectify ofy = ObjectifyDao.ofy();

        UserAccount uDB = null;
        if (ProviderAuth.GOOGLE == userFromProvider.getProviderAuth()) {
            uDB = ofy.query(UserAccount.class)//
                    .filter("name", userFromProvider.getName()) //
                    .filter("providerAuth", ProviderAuth.GOOGLE) //
                    .get();
        } else {
            uDB = ofy.find(UserAccount.class, userFromProvider.getId());
        }

        // //////////////////
        if (null == uDB) {
            ofy.put(userFromProvider);
            uDB = userFromProvider;
        }

        // //////////////////
        final HttpSession session = request.getSession();
        session.setAttribute(Attributes.USER_ID.toString(), uDB.getId());
        session.setAttribute(Attributes.IS_LOGGED_IN.toString(), true);
    }

    public static boolean isLoggedIn(final HttpServletRequest req) {

        if (req == null) {
            return false;
        }

        final HttpSession session = req.getSession();
        if (session == null) {
            return false;
        }

        final Boolean isLoggedIn = (Boolean) session.getAttribute(Attributes.IS_LOGGED_IN.toString());
        if (isLoggedIn == null) {
            return false;
        }

        return isLoggedIn;
    }

}
