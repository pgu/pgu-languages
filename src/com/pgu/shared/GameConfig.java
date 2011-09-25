package com.pgu.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.pgu.shared.Symbol.Group;

public class GameConfig implements IsSerializable {
    public Group group;
    // public int size;
    public List<String> filters = new ArrayList<String>();

    // public List<String> vowels = new ArrayList<String>();
    // public List<String> consonants = new ArrayList<String>();

    @Override
    public String toString() {
        return "GameConfig [group=" + group + //
                ", filters=" + toStr(filters) + //
                // ", consonants=" + toStr(consonants) + //
                "]";
    }

    private static String toStr(final List<String> li) {
        final StringBuffer sb = new StringBuffer();
        for (final String string : li) {
            sb.append(string);
            sb.append(",");
        }
        return sb.toString();
    }
}
