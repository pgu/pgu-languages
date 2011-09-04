package com.pgu.client.app;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AsyncCallbackApp<T> implements AsyncCallback<T> {

    @Override
    public void onFailure(final Throwable caught) {
        Window.alert(">>> " + caught.getMessage());
    }

}
