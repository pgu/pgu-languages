package com.pgu.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pgu.shared.UserAccount;

public interface LoginServiceAsync {

    void getLoggedInUser(AsyncCallback<UserAccount> asyncCallback);

}
