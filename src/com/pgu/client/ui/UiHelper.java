package com.pgu.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.UIObject;

public class UiHelper {

    public static void id(final String id, final UIObject w) {
        DOM.setElementAttribute(w.getElement(), "id", "pgu-" + id);
    }
}
