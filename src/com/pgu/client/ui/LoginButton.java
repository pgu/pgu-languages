package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginButton extends Composite {

    private static LoginButtonUiBinder uiBinder = GWT.create(LoginButtonUiBinder.class);

    interface LoginButtonUiBinder extends UiBinder<Widget, LoginButton> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    HTML login;

    public LoginButton() {
        initWidget(uiBinder.createAndBindUi(this));

        UiHelper.id("container-login-btn", container);
        UiHelper.id("login-btn", login);

    }

}
