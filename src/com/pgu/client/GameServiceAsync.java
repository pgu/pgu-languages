package com.pgu.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pgu.shared.Symbol;

public interface GameServiceAsync {

    void initGame(AsyncCallback<List<Symbol>> asyncCallback);

}
