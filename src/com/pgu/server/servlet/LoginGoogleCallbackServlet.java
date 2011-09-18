package com.pgu.server.servlet;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pgu.server.utils.ServletHelper;
import com.pgu.shared.UserAccount;
import com.pgu.shared.UserAccount.ProviderAuth;

public class LoginGoogleCallbackServlet extends HttpServlet {

    private static final long serialVersionUID = 5154766769728322503L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        final Principal googleUser = request.getUserPrincipal();
        if (googleUser != null) {
            final UserAccount userFromProvider = ProviderAuth.GOOGLE.get(null, googleUser.getName());
            ServletHelper.setUserInDBAndSession(userFromProvider, request);
        }
        response.sendRedirect(ServletHelper.getApplicationURL(request));

    }

}
