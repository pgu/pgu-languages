package com.pgu.server.rpc;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.pgu.client.rpc.InitService;
import com.pgu.server.dao.ObjectifyDao;
import com.pgu.shared.Symbol;
import com.pgu.shared.Symbol.Group;

public class InitServiceImpl extends RemoteServiceServlet implements InitService {

    private static final long serialVersionUID = -7873557792672762717L;

    @Override
    public void initData() {
        final List<Symbol> symbols = new ArrayList<Symbol>();
        addHiragana(symbols);
        addKatakana(symbols);
        addRussian(symbols);

        final Objectify ofy = ObjectifyDao.ofy();
        ofy.put(symbols);
    }

    private void addRussian(final List<Symbol> symbols) {
        symbols.add(new Symbol(Group.RUSSIAN, "A", "&#x0410;"));
        symbols.add(new Symbol(Group.RUSSIAN, "B", "&#x0411;"));
        symbols.add(new Symbol(Group.RUSSIAN, "V", "&#x0412;"));
        symbols.add(new Symbol(Group.RUSSIAN, "G", "&#x0413;"));
        symbols.add(new Symbol(Group.RUSSIAN, "D", "&#x0414;"));
        symbols.add(new Symbol(Group.RUSSIAN, "YE", "&#x0415;"));
        symbols.add(new Symbol(Group.RUSSIAN, "YO", "&#x0401;"));
        symbols.add(new Symbol(Group.RUSSIAN, "&#x0290;", "&#x0416;"));
        symbols.add(new Symbol(Group.RUSSIAN, "Z", "&#x0417;"));
        symbols.add(new Symbol(Group.RUSSIAN, "I", "&#x0418;"));
        symbols.add(new Symbol(Group.RUSSIAN, "Y", "&#x0419;"));
        symbols.add(new Symbol(Group.RUSSIAN, "K", "&#x041A;"));
        symbols.add(new Symbol(Group.RUSSIAN, "L", "&#x041B;"));
        symbols.add(new Symbol(Group.RUSSIAN, "M", "&#x041C;"));
        symbols.add(new Symbol(Group.RUSSIAN, "N", "&#x041D;"));
        symbols.add(new Symbol(Group.RUSSIAN, "O", "&#x041E;"));
        symbols.add(new Symbol(Group.RUSSIAN, "P", "&#x041F;"));
        symbols.add(new Symbol(Group.RUSSIAN, "R", "&#x0420;"));
        symbols.add(new Symbol(Group.RUSSIAN, "S", "&#x0421;"));
        symbols.add(new Symbol(Group.RUSSIAN, "T", "&#x0422;"));
        symbols.add(new Symbol(Group.RUSSIAN, "U", "&#x0423;"));
        symbols.add(new Symbol(Group.RUSSIAN, "F", "&#x0424;"));
        symbols.add(new Symbol(Group.RUSSIAN, "X", "&#x0425;"));
        symbols.add(new Symbol(Group.RUSSIAN, "TS", "&#x0426;"));
        symbols.add(new Symbol(Group.RUSSIAN, "CH", "&#x0427;"));
        symbols.add(new Symbol(Group.RUSSIAN, "&#x0282;", "&#x0428;"));
        symbols.add(new Symbol(Group.RUSSIAN, "&#x0285;&#x0285;", "&#x0429;"));
        symbols.add(new Symbol(Group.RUSSIAN, "&#x0268;", "&#x042B;"));
        symbols.add(new Symbol(Group.RUSSIAN, "E", "&#x042D;"));
        symbols.add(new Symbol(Group.RUSSIAN, "YU", "&#x042E;"));
        symbols.add(new Symbol(Group.RUSSIAN, "YA", "&#x042F;"));
    }

    private void addKatakana(final List<Symbol> symbols) {
        symbols.add(new Symbol(Group.KATAKANA, "A", "&#x30A2;"));
        symbols.add(new Symbol(Group.KATAKANA, "I", "&#x30A4;"));
        symbols.add(new Symbol(Group.KATAKANA, "U", "&#x30A6;"));
        symbols.add(new Symbol(Group.KATAKANA, "E", "&#x30A8;"));
        symbols.add(new Symbol(Group.KATAKANA, "O", "&#x30AA;"));
        symbols.add(new Symbol(Group.KATAKANA, "KA", "&#x30AB;"));
        symbols.add(new Symbol(Group.KATAKANA, "GA", "&#x30AC;"));
        symbols.add(new Symbol(Group.KATAKANA, "KI", "&#x30AD;"));
        symbols.add(new Symbol(Group.KATAKANA, "GI", "&#x30AE;"));
        symbols.add(new Symbol(Group.KATAKANA, "KU", "&#x30AF;"));
        symbols.add(new Symbol(Group.KATAKANA, "GU", "&#x30A0;"));
        symbols.add(new Symbol(Group.KATAKANA, "KE", "&#x30B1;"));
        symbols.add(new Symbol(Group.KATAKANA, "GE", "&#x30B2;"));
        symbols.add(new Symbol(Group.KATAKANA, "KO", "&#x30B3;"));
        symbols.add(new Symbol(Group.KATAKANA, "GO", "&#x30B4;"));
        symbols.add(new Symbol(Group.KATAKANA, "SA", "&#x30B5;"));
        symbols.add(new Symbol(Group.KATAKANA, "ZA", "&#x30B6;"));
        symbols.add(new Symbol(Group.KATAKANA, "SHI", "&#x30B7;"));
        symbols.add(new Symbol(Group.KATAKANA, "ZI", "&#x30B8;"));
        symbols.add(new Symbol(Group.KATAKANA, "SU", "&#x30B9;"));
        symbols.add(new Symbol(Group.KATAKANA, "ZU", "&#x30BA;"));
        symbols.add(new Symbol(Group.KATAKANA, "SE", "&#x30BB;"));
        symbols.add(new Symbol(Group.KATAKANA, "ZE", "&#x30BC;"));
        symbols.add(new Symbol(Group.KATAKANA, "SO", "&#x30BD;"));
        symbols.add(new Symbol(Group.KATAKANA, "ZO", "&#x30BE;"));
        symbols.add(new Symbol(Group.KATAKANA, "TA", "&#x30BF;"));
        symbols.add(new Symbol(Group.KATAKANA, "DA", "&#x30C0;"));
        symbols.add(new Symbol(Group.KATAKANA, "CHI", "&#x30C1;"));
        symbols.add(new Symbol(Group.KATAKANA, "DI", "&#x30C2;"));
        symbols.add(new Symbol(Group.KATAKANA, "TSU", "&#x30C4;"));
        symbols.add(new Symbol(Group.KATAKANA, "DU", "&#x30C5;"));
        symbols.add(new Symbol(Group.KATAKANA, "TE", "&#x30C6;"));
        symbols.add(new Symbol(Group.KATAKANA, "DE", "&#x30C7;"));
        symbols.add(new Symbol(Group.KATAKANA, "TO", "&#x30C8;"));
        symbols.add(new Symbol(Group.KATAKANA, "DO", "&#x30C9;"));
        symbols.add(new Symbol(Group.KATAKANA, "NA", "&#x30CA;"));
        symbols.add(new Symbol(Group.KATAKANA, "NI", "&#x30CB;"));
        symbols.add(new Symbol(Group.KATAKANA, "NU", "&#x30CC;"));
        symbols.add(new Symbol(Group.KATAKANA, "NE", "&#x30CD;"));
        symbols.add(new Symbol(Group.KATAKANA, "NO", "&#x30CE;"));
        symbols.add(new Symbol(Group.KATAKANA, "HA", "&#x30CF;"));
        symbols.add(new Symbol(Group.KATAKANA, "BA", "&#x30D0;"));
        symbols.add(new Symbol(Group.KATAKANA, "PA", "&#x30D1;"));
        symbols.add(new Symbol(Group.KATAKANA, "HI", "&#x30D2;"));
        symbols.add(new Symbol(Group.KATAKANA, "BI", "&#x30D3;"));
        symbols.add(new Symbol(Group.KATAKANA, "PI", "&#x30D4;"));
        symbols.add(new Symbol(Group.KATAKANA, "FU", "&#x30D5;"));
        symbols.add(new Symbol(Group.KATAKANA, "BU", "&#x30D6;"));
        symbols.add(new Symbol(Group.KATAKANA, "PU", "&#x30D7;"));
        symbols.add(new Symbol(Group.KATAKANA, "HE", "&#x30D8;"));
        symbols.add(new Symbol(Group.KATAKANA, "BE", "&#x30D9;"));
        symbols.add(new Symbol(Group.KATAKANA, "PE", "&#x30DA;"));
        symbols.add(new Symbol(Group.KATAKANA, "HO", "&#x30DB;"));
        symbols.add(new Symbol(Group.KATAKANA, "BO", "&#x30DC;"));
        symbols.add(new Symbol(Group.KATAKANA, "PO", "&#x30DD;"));
        symbols.add(new Symbol(Group.KATAKANA, "MA", "&#x30DE;"));
        symbols.add(new Symbol(Group.KATAKANA, "MI", "&#x30DF;"));
        symbols.add(new Symbol(Group.KATAKANA, "MU", "&#x30E0;"));
        symbols.add(new Symbol(Group.KATAKANA, "ME", "&#x30E1;"));
        symbols.add(new Symbol(Group.KATAKANA, "MO", "&#x30E2;"));
        symbols.add(new Symbol(Group.KATAKANA, "YA", "&#x30E4;"));
        symbols.add(new Symbol(Group.KATAKANA, "YU", "&#x30E6;"));
        symbols.add(new Symbol(Group.KATAKANA, "YO", "&#x30E8;"));
        symbols.add(new Symbol(Group.KATAKANA, "RA", "&#x30E9;"));
        symbols.add(new Symbol(Group.KATAKANA, "RI", "&#x30EA;"));
        symbols.add(new Symbol(Group.KATAKANA, "RU", "&#x30EB;"));
        symbols.add(new Symbol(Group.KATAKANA, "RE", "&#x30EC;"));
        symbols.add(new Symbol(Group.KATAKANA, "RO", "&#x30ED;"));
        symbols.add(new Symbol(Group.KATAKANA, "WA", "&#x30EF;"));
        symbols.add(new Symbol(Group.KATAKANA, "WI", "&#x30F0;"));
        symbols.add(new Symbol(Group.KATAKANA, "WE", "&#x30F1;"));
        symbols.add(new Symbol(Group.KATAKANA, "WO", "&#x30F2;"));
        symbols.add(new Symbol(Group.KATAKANA, "N", "&#x30F3;"));
        symbols.add(new Symbol(Group.KATAKANA, "VU", "&#x30F4;"));
        symbols.add(new Symbol(Group.KATAKANA, "VA", "&#x30F7;"));
        symbols.add(new Symbol(Group.KATAKANA, "VI", "&#x30F8;"));
        symbols.add(new Symbol(Group.KATAKANA, "VE", "&#x30F9;"));
        symbols.add(new Symbol(Group.KATAKANA, "VO", "&#x30FA;"));

    }

    private void addHiragana(final List<Symbol> symbols) {
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
        symbols.add(new Symbol(Group.HIRAGANA, "VU", "&#x3094;"));
    }

    @Override
    public void deleteData() {
        final Objectify ofy = ObjectifyDao.ofy();
        ofy.delete(ofy.query(Symbol.class).list());
    }

}
