package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
    @UiField
    LoginMenu loginMenu;

    public LoginButton() {
        initWidget(uiBinder.createAndBindUi(this));
        loginMenu.setVisible(false);

        UiHelper.id("container-login", container);
        UiHelper.id("login", login);

        container.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                loginMenu.setVisible(!loginMenu.isVisible());
            }
        }, ClickEvent.getType());
    }

}
