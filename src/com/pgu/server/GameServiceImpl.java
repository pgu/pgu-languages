package com.pgu.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.pgu.client.GameService;
import com.pgu.shared.Symbol;

@SuppressWarnings("serial")
public class GameServiceImpl extends RemoteServiceServlet implements GameService {

    @Override
    public List<Symbol> initGame() {
        final Objectify ofy = ObjectifyDao.ofy();
        return ofy.query(Symbol.class).list();
    }

}
