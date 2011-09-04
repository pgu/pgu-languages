package com.pgu.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.pgu.client.InitService;
import com.pgu.shared.Symbol;
import com.pgu.shared.Symbol.Group;

@SuppressWarnings("serial")
public class InitServiceImpl extends RemoteServiceServlet implements InitService {

    @Override
    public void initData() {
        final List<Symbol> symbols = new ArrayList<Symbol>();

        symbols.add(new Symbol(Group.HIRAGANA, "A", "&#x3042;"));

        symbols.add(new Symbol(Group.HIRAGANA, "I", "&#x3044;"));

        symbols.add(new Symbol(Group.HIRAGANA, "U", "&#x3046;"));

        symbols.add(new Symbol(Group.HIRAGANA, "E", "&#x3048;"));

        symbols.add(new Symbol(Group.HIRAGANA, "O", "&#x304A;"));

        symbols.add(new Symbol(Group.HIRAGANA, "KA", "&#x304B;"));

        symbols.add(new Symbol(Group.HIRAGANA, "GA", "&#x304C;"));

        symbols.add(new Symbol(Group.HIRAGANA, "KI", "&#x304D;"));

        symbols.add(new Symbol(Group.HIRAGANA, "GI", "&#x304E;"));

        symbols.add(new Symbol(Group.HIRAGANA, "KU", "&#x304F;"));

        symbols.add(new Symbol(Group.HIRAGANA, "GU", "&#x3050;"));

        symbols.add(new Symbol(Group.HIRAGANA, "KE", "&#x3051;"));

        symbols.add(new Symbol(Group.HIRAGANA, "GE", "&#x3052;"));

        symbols.add(new Symbol(Group.HIRAGANA, "KO", "&#x3053;"));

        symbols.add(new Symbol(Group.HIRAGANA, "GO", "&#x3054;"));

        symbols.add(new Symbol(Group.HIRAGANA, "SA", "&#x3055;"));

        symbols.add(new Symbol(Group.HIRAGANA, "ZA", "&#x3056;"));

        symbols.add(new Symbol(Group.HIRAGANA, "SHI", "&#x3057;"));

        symbols.add(new Symbol(Group.HIRAGANA, "ZI", "&#x3058;"));

        symbols.add(new Symbol(Group.HIRAGANA, "SU", "&#x3059;"));

        symbols.add(new Symbol(Group.HIRAGANA, "ZU", "&#x305A;"));

        symbols.add(new Symbol(Group.HIRAGANA, "SE", "&#x305B;"));

        symbols.add(new Symbol(Group.HIRAGANA, "ZE", "&#x305C;"));

        symbols.add(new Symbol(Group.HIRAGANA, "SO", "&#x305D;"));

        symbols.add(new Symbol(Group.HIRAGANA, "ZO", "&#x305E;"));

        symbols.add(new Symbol(Group.HIRAGANA, "TA", "&#x305F;"));

        symbols.add(new Symbol(Group.HIRAGANA, "DA", "&#x3060;"));

        symbols.add(new Symbol(Group.HIRAGANA, "CHI", "&#x3061;"));

        symbols.add(new Symbol(Group.HIRAGANA, "DI", "&#x3062;"));

        symbols.add(new Symbol(Group.HIRAGANA, "TSU", "&#x3064;"));

        symbols.add(new Symbol(Group.HIRAGANA, "DU", "&#x3065;"));

        symbols.add(new Symbol(Group.HIRAGANA, "TE", "&#x3066;"));

        symbols.add(new Symbol(Group.HIRAGANA, "DE", "&#x3067;"));

        symbols.add(new Symbol(Group.HIRAGANA, "TO", "&#x3068;"));

        symbols.add(new Symbol(Group.HIRAGANA, "DO", "&#x3069;"));

        symbols.add(new Symbol(Group.HIRAGANA, "NA", "&#x306A;"));

        symbols.add(new Symbol(Group.HIRAGANA, "NI", "&#x306B;"));

        symbols.add(new Symbol(Group.HIRAGANA, "NU", "&#x306C;"));

        symbols.add(new Symbol(Group.HIRAGANA, "NE", "&#x306D;"));

        symbols.add(new Symbol(Group.HIRAGANA, "NO", "&#x306E;"));

        symbols.add(new Symbol(Group.HIRAGANA, "HA", "&#x306F;"));

        symbols.add(new Symbol(Group.HIRAGANA, "BA", "&#x3070;"));

        symbols.add(new Symbol(Group.HIRAGANA, "PA", "&#x3071;"));

        symbols.add(new Symbol(Group.HIRAGANA, "HI", "&#x3072;"));

        symbols.add(new Symbol(Group.HIRAGANA, "BI", "&#x3073;"));

        symbols.add(new Symbol(Group.HIRAGANA, "PI", "&#x3074;"));

        symbols.add(new Symbol(Group.HIRAGANA, "FU", "&#x3075;"));

        symbols.add(new Symbol(Group.HIRAGANA, "BU", "&#x3076;"));

        symbols.add(new Symbol(Group.HIRAGANA, "PU", "&#x3077;"));

        symbols.add(new Symbol(Group.HIRAGANA, "HE", "&#x3078;"));

        symbols.add(new Symbol(Group.HIRAGANA, "BE", "&#x3079;"));

        symbols.add(new Symbol(Group.HIRAGANA, "PE", "&#x307A;"));

        symbols.add(new Symbol(Group.HIRAGANA, "HO", "&#x307B;"));

        symbols.add(new Symbol(Group.HIRAGANA, "BO", "&#x307C;"));

        symbols.add(new Symbol(Group.HIRAGANA, "PO", "&#x307D;"));

        symbols.add(new Symbol(Group.HIRAGANA, "MA", "&#x307E;"));

        symbols.add(new Symbol(Group.HIRAGANA, "MI", "&#x307F;"));

        symbols.add(new Symbol(Group.HIRAGANA, "MU", "&#x3080;"));

        symbols.add(new Symbol(Group.HIRAGANA, "ME", "&#x3081;"));

        symbols.add(new Symbol(Group.HIRAGANA, "MO", "&#x3082;"));

        symbols.add(new Symbol(Group.HIRAGANA, "YA", "&#x3084;"));

        symbols.add(new Symbol(Group.HIRAGANA, "YU", "&#x3086;"));

        symbols.add(new Symbol(Group.HIRAGANA, "YO", "&#x3088;"));

        symbols.add(new Symbol(Group.HIRAGANA, "RA", "&#x3089;"));

        symbols.add(new Symbol(Group.HIRAGANA, "RI", "&#x308A;"));

        symbols.add(new Symbol(Group.HIRAGANA, "RU", "&#x308B;"));

        symbols.add(new Symbol(Group.HIRAGANA, "RE", "&#x308C;"));

        symbols.add(new Symbol(Group.HIRAGANA, "RO", "&#x308D;"));

        symbols.add(new Symbol(Group.HIRAGANA, "WA", "&#x308F;"));

        symbols.add(new Symbol(Group.HIRAGANA, "WI", "&#x3090;"));

        symbols.add(new Symbol(Group.HIRAGANA, "WE", "&#x3091;"));

        symbols.add(new Symbol(Group.HIRAGANA, "WO", "&#x3092;"));

        symbols.add(new Symbol(Group.HIRAGANA, "N", "&#x3093;"));

        final Objectify ofy = ObjectifyDao.ofy();
        ofy.put(symbols);
    }

    @Override
    public void deleteData() {
        final Objectify ofy = ObjectifyDao.ofy();
        ofy.delete(ofy.query(Symbol.class).list());
    }

}
