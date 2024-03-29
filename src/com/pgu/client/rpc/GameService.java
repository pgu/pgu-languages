package com.pgu.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pgu.shared.GameConfig;
import com.pgu.shared.Symbol;

@RemoteServiceRelativePath("game")
public interface GameService extends RemoteService {

    List<Symbol> initGame(GameConfig gc);

}
