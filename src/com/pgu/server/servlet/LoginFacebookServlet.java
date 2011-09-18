package com.pgu.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.pgu.server.utils.ConfigApp;
import com.pgu.server.utils.ServletHelper;
import com.pgu.server.utils.StringHelper;
import com.pgu.shared.UserAccount;
import com.pgu.shared.UserAccount.ProviderAuth;

public class LoginFacebookServlet extends HttpServlet {

    private static final long serialVersionUID = -5107506228581735361L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {

        final String clientSecret = ConfigApp.FB_APP_SECRET.getValue();
        final String appId = ConfigApp.FB_APP_KEY.getValue();
        final String callbackURL = ConfigApp.FB_CALLBACK_URL.getValue();
        final String code = request.getParameter("code");

        if (!StringHelper.isEmpty(code)) {
            // request.getSession().setAttribute(Attributes.FACEBOOK_CODE.toString(), code); TODO PGU util?

            final String tokenURL = "https://graph.facebook.com/oauth/access_token" + //
                    "?client_id=%s" + //
                    "&redirect_uri=%s" + //
                    "&client_secret=%s" + //
                    "&code=%s";
            String resp = ServletHelper.getResponse(String.format(tokenURL, //
                    appId, //
                    callbackURL, //
                    clientSecret, //
                    code));

            final int beginIndex = "access_token=".length();
            final String token = resp.substring(beginIndex);
            final String url = "https://graph.facebook.com/me?access_token=" + token;
            resp = ServletHelper.getResponse(url);

            JSONObject j;
            try {
                j = new JSONObject(resp);
                final String first = j.getString("first_name");
                final String last = j.getString("last_name");
                final String id = j.getString("id");

                final UserAccount userFromProvider = ProviderAuth.FACEBOOK.get(id, first + " " + last);
                ServletHelper.setUserInDBAndSession(userFromProvider, request);

            } catch (final JSONException e) {
                e.printStackTrace();
            }

            response.sendRedirect(ServletHelper.getBaseUrl(request));
        } else {

            final String fbLoginPage = "https://graph.facebook.com/oauth/authorize" + //
                    "?client_id=%s" + //
                    "&redirect_uri=%s";
            response.sendRedirect(String.format(fbLoginPage, //
                    appId, //
                    callbackURL//
                    ));
        }
    }

}
