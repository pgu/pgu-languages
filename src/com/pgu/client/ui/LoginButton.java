package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.pgu.client.presenter.LoginPresenter;
import com.pgu.shared.UserAccount;

public class LoginButton extends Composite {

    private static LoginButtonUiBinder uiBinder = GWT.create(LoginButtonUiBinder.class);

    interface LoginButtonUiBinder extends UiBinder<Widget, LoginButton> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    HTML login;

    private LoginPresenter presenter;

    public LoginButton() {
        initWidget(uiBinder.createAndBindUi(this));

        UiHelper.id("container-login-btn", container);
        UiHelper.id("login-btn", login);

    }

    public void setPresenter(final LoginPresenter presenter) {
        this.presenter = presenter;
    }

    private HandlerRegistration loginHandler = null;

    public void showLogin() {
        if (null != loginHandler) {
            loginHandler.removeHandler();
        }

        login.setText("Login");
        loginHandler = container.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                final LoginMenu menuLogin = presenter.getMenuLogin();
                menuLogin.setVisible(!menuLogin.isVisible());
                final Style style = menuLogin.getElement().getStyle();
                style.setTop(login.getAbsoluteTop() - menuLogin.getOffsetHeight(), Unit.PX);
                style.setLeft(login.getAbsoluteLeft(), Unit.PX);
            }
        }, ClickEvent.getType());

    }

    public void showUser(final UserAccount user) {
        if (null != loginHandler) {
            loginHandler.removeHandler();
        }

        login.setText(user.getName() + " " + user.getProviderAuth());
        loginHandler = container.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                final ScoreMenu menuScore = presenter.getMenuScore();
                menuScore.setVisible(!menuScore.isVisible());
                final Style style = menuScore.getElement().getStyle();
                style.setTop(login.getAbsoluteTop() - menuScore.getOffsetHeight(), Unit.PX);
                style.setLeft(login.getAbsoluteLeft(), Unit.PX);
            }
        }, ClickEvent.getType());

    }

}
