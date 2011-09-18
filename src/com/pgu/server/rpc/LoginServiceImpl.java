package com.pgu.server.rpc;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.pgu.client.rpc.LoginService;
import com.pgu.shared.UserAccount;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    @Override
    public UserAccount getLoggedInUser() {
        return null;
    }

}
