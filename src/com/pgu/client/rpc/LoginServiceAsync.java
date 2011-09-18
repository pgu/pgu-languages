package com.pgu.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pgu.shared.UserAccount;

public interface LoginServiceAsync {

    void getLoggedInUser(AsyncCallback<UserAccount> asyncCallback);

    void logout(AsyncCallback<Void> asyncCallbackApp);

    void isAdmin(UserAccount user, AsyncCallback<Boolean> asyncCallbackApp);

}
