package com.pgu.server.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pgu.server.utils.ServletHelper;

public class LoginFilter implements Filter {

    @Override
    public void init(final FilterConfig arg0) throws ServletException {
        // noop
    }

    @Override
    public void destroy() {
        // noop
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {
        try {
            final HttpServletRequest req = (HttpServletRequest) request;

            if (ServletHelper.isLoggedIn(req)) {
                filterChain.doFilter(request, response);
            } else {
                if (request.getContentType().contains("x-gwt-rpc")) { // GWT requests
                    final HttpServletResponse resp = (HttpServletResponse) response;
                    resp.setHeader("content-type", request.getContentType());
                    resp.getWriter().print("You are logged out...");
                } else {
                    final HttpServletResponse resp = (HttpServletResponse) response;
                    resp.sendRedirect("/");
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

}
