package com.pgu.server;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.pgu.shared.Symbol;

public class ObjectifyDao {
    static {
        ObjectifyService.register(Symbol.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.begin();
    }
}
