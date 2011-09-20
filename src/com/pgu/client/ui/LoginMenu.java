package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginMenu extends Composite {

    private static LoginMenuUiBinder uiBinder = GWT.create(LoginMenuUiBinder.class);

    interface LoginMenuUiBinder extends UiBinder<Widget, LoginMenu> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    HTML facebook;
    @UiField
    HTML twitter;
    @UiField
    HTML google;

    public LoginMenu() {
        initWidget(uiBinder.createAndBindUi(this));

        UiHelper.id("container-login-menu", container);
        UiHelper.id("facebook", facebook);
        UiHelper.id("twitter", twitter);
        UiHelper.id("google", google);

    }

}
