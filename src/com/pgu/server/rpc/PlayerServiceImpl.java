package com.pgu.server.rpc;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.pgu.client.rpc.PlayerService;
import com.pgu.server.dao.ObjectifyDao;
import com.pgu.shared.Score;
import com.pgu.shared.UserAccount;

public class PlayerServiceImpl extends RemoteServiceServlet implements PlayerService {

    private static final long serialVersionUID = 9021240508887247485L;

    @Override
    public int getScore(final UserAccount user) {
        final Objectify ofy = ObjectifyDao.ofy();
        final List<Score> scores = ofy.query(Score.class).filter("playerId", user.getId()).list();
        return scores.isEmpty() ? 0 : scores.get(0).getValue();
    }

}
