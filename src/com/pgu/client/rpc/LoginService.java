package com.pgu.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pgu.shared.UserAccount;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

    UserAccount getLoggedInUser();

    void logout();

}
