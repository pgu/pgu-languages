package com.pgu.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pgu.shared.GameConfig;
import com.pgu.shared.Symbol;

public interface GameServiceAsync {

    void initGame(GameConfig gc, AsyncCallback<List<Symbol>> asyncCallback);

}
