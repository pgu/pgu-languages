package com.pgu.server.rpc;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.pgu.client.rpc.LoginService;
import com.pgu.server.dao.ObjectifyDao;
import com.pgu.server.utils.ServletHelper.Attributes;
import com.pgu.shared.UserAccount;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    private static final long serialVersionUID = 1968438698848800288L;

    private final SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

    @Override
    public UserAccount getLoggedInUser() {
        final HttpSession session = getThreadLocalRequest().getSession();

        if (session == null) {
            return null; // user not logged in
        }

        final Long userId = (Long) session.getAttribute(Attributes.USER_ID.toString());
        if (userId == null) {
            return null; // user not logged in
        }

        final Objectify ofy = ObjectifyDao.ofy();
        final UserAccount user = ofy.get(UserAccount.class, userId);

        user.setLastActive(fmt.format(new Date()));

        ofy.put(user);

        return user;
    }

    @Override
    public void logout() {
        getThreadLocalRequest().getSession().invalidate();
    }

    @Override
    public boolean isAdmin(final UserAccount user) {
        return System.getProperty("ADMIN_DATA").equals(user.getName());
    }

}
