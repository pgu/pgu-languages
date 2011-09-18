package com.pgu.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pgu.shared.UserAccount;

public interface PlayerServiceAsync {

    void getScore(UserAccount user, AsyncCallback<Integer> asyncCallbackApp);

}
