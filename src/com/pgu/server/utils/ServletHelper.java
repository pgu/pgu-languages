package com.pgu.server.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.utils.SystemProperty;

public class ServletHelper {

    public enum Attributes {
        REQUEST_TOKEN, FACEBOOK_CODE
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
            int read = is.read(ba);
            while (read > -1) {
                out.write(ba, 0, read);
                read = is.read(ba);
            }
            String returnString = out.toString(charEncoding);
            if (returnString.equalsIgnoreCase("{}")) {
                returnString = "[{}]";
            }
            return returnString;
        } catch (final Exception e) {
            return null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (final Exception e) {
            }
        }
    }

}
