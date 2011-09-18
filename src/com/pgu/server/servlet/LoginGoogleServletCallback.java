package com.pgu.server.servlet;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pgu.server.utils.ServletHelper;
import com.pgu.shared.UserAccount.ProviderAuth;

public class LoginGoogleServletCallback extends HttpServlet {

    private static final long serialVersionUID = 5154766769728322503L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        final Principal googleUser = request.getUserPrincipal();
        if (googleUser != null) {
            ProviderAuth.GOOGLE.setUserInDBAndSession(null, googleUser.getName(), request);
        } else {
            System.out.println("googleUser is not found");
        }
        response.sendRedirect(ServletHelper.getApplicationURL(request));

    }

}
