package com.pgu.server.servlet;

import static com.pgu.server.utils.ServletHelper.Attributes.REQUEST_TOKEN;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import com.pgu.server.utils.ConfigApp;
import com.pgu.server.utils.ServletHelper;

public class LoginTwitterServlet extends HttpServlet {

    private static final long serialVersionUID = 8897022618440729068L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        final Twitter twitter = new TwitterFactory().getInstance();
        final String key = ConfigApp.TWITTER_APP_KEY.getValue();
        final String secret = ConfigApp.TWITTER_APP_SECRET.getValue();

        try {
            twitter.setOAuthConsumer(key, secret);
            final String callbackURL = ServletHelper.buildCallBackURL(request);
            final RequestToken token = twitter.getOAuthRequestToken(callbackURL);

            request.getSession().setAttribute(REQUEST_TOKEN.toString(), token);

            final String loginURL = token.getAuthenticationURL() + "&force_login=true";
            response.sendRedirect(loginURL);

        } catch (final TwitterException e) {
            e.printStackTrace();
        }

    }

}
