package com.pgu.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.pgu.server.utils.ServletHelper;

public class LoginGoogleServlet extends HttpServlet {

    private static final long serialVersionUID = 1200232606602981237L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        final String callbackURL = ServletHelper.buildCallBackURL(request);
        final UserService userService = UserServiceFactory.getUserService();
        final String googleLoginUrl = userService.createLoginURL(callbackURL);
        response.sendRedirect(googleLoginUrl);
    }

}
