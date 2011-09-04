package com.pgu.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InitServiceAsync {

    void initData(AsyncCallback<Void> asyncCallback);

    void deleteData(AsyncCallback<Void> asyncCallback);

}
