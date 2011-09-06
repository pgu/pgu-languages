package com.pgu.server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.pgu.client.GameService;
import com.pgu.shared.GameConfig;
import com.pgu.shared.Symbol;

@SuppressWarnings("serial")
public class GameServiceImpl extends RemoteServiceServlet implements GameService {

    @Override
    public List<Symbol> initGame(final GameConfig gc) {
        System.out.println(gc);

        final Objectify ofy = ObjectifyDao.ofy();

        final List<Symbol> symbs = ofy.query(Symbol.class).filter("group", gc.group).list();

        final LinkedList<Symbol> symbols = new LinkedList<Symbol>(symbs);

        final List<String> filters = new ArrayList<String>();
        filters.addAll(gc.vowels);
        filters.addAll(gc.consonants);
        if (!filters.isEmpty()) {

            final List<Symbol> toRemoves = new ArrayList<Symbol>(symbols.size());

            for (final Symbol symbol : symbols) {
                boolean hasFilter = false;
                for (final String filter : filters) {
                    if (symbol.getAlpha().toUpperCase().indexOf(filter) > -1) {
                        hasFilter = true;
                        break;
                    }
                }
                if (!hasFilter) {
                    toRemoves.add(symbol);
                }
            }
            symbols.removeAll(toRemoves);
        }

        final Random random = new Random();

        final List<Symbol> symbolGames = new ArrayList<Symbol>();
        for (int i = 0; i < gc.size / 2; i++) {
            final int size = symbols.size();
            if (size > 0) {
                symbolGames.add(symbols.remove(random.nextInt(size)));
            } else {
                break;
            }
        }

        return symbolGames;
    }

}
