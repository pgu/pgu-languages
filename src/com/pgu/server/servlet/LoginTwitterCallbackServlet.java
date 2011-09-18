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
import twitter4j.User;
import twitter4j.auth.RequestToken;

import com.pgu.server.utils.ConfigApp;
import com.pgu.server.utils.ServletHelper;
import com.pgu.shared.UserAccount;
import com.pgu.shared.UserAccount.ProviderAuth;

public class LoginTwitterCallbackServlet extends HttpServlet {

    private static final long serialVersionUID = 3106835721550792835L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        final Twitter twitter = new TwitterFactory().getInstance();
        final String key = ConfigApp.TWITTER_APP_KEY.getValue();
        final String secret = ConfigApp.TWITTER_APP_SECRET.getValue();

        final RequestToken token = (RequestToken) request.getSession().getAttribute(REQUEST_TOKEN.toString());
        final String verifier = request.getParameter("oauth_verifier");
        twitter.setOAuthConsumer(key, secret);

        User userTwitter = null;
        try {
            twitter.getOAuthAccessToken(token, verifier); // TODO PGU util?
            userTwitter = twitter.verifyCredentials();
        } catch (final TwitterException e) {
            e.printStackTrace();
        }

        System.out.println("Twitter user found:" + userTwitter.getName());

        request.getSession().removeAttribute(REQUEST_TOKEN.toString());

        final UserAccount userFromProvider = ProviderAuth.TWITTER.get(Long.toString(userTwitter.getId()),
                userTwitter.getName());
        ServletHelper.setUserInDBAndSession(userFromProvider, request);

        response.sendRedirect(ServletHelper.getApplicationURL(request));

    }

}
