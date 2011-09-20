package com.pgu.client;

import com.google.gwt.user.client.ui.RootPanel;

public enum ScreenLayout {
    VERTICAL("white"), HORIZONTAL("whitesmoke");
    private String color;

    private ScreenLayout(final String color) {
        this.color = color;
    }

    public boolean isHorizontal() {
        return HORIZONTAL.color.equals(RootPanel.get("pgu-tag").getElement().getStyle().getBackgroundColor());
    }
}
