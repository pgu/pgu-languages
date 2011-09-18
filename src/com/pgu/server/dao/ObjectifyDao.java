package com.pgu.server.dao;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.pgu.shared.Score;
import com.pgu.shared.Symbol;
import com.pgu.shared.UserAccount;

public class ObjectifyDao {
    static {
        ObjectifyService.register(Symbol.class);
        ObjectifyService.register(UserAccount.class);
        ObjectifyService.register(Score.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.begin();
    }
}
