package com.pgu.server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.pgu.client.GameService;
import com.pgu.shared.Symbol;

@SuppressWarnings("serial")
public class GameServiceImpl extends RemoteServiceServlet implements GameService {

    @Override
    public List<Symbol> initGame(final int nbOfSquares) {
        final Objectify ofy = ObjectifyDao.ofy();
        final LinkedList<Symbol> symbols = new LinkedList<Symbol>(ofy.query(Symbol.class).list());

        final Random random = new Random();
        final List<Symbol> symbolGames = new ArrayList<Symbol>();
        for (int i = 0; i < nbOfSquares / 2; i++) {
            symbolGames.add(symbols.remove(random.nextInt(symbols.size())));
        }

        return symbolGames;
    }

}
