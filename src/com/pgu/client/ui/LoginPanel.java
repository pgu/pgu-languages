package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginPanel extends Composite {

    private static LoginPanelUiBinder uiBinder = GWT.create(LoginPanelUiBinder.class);

    interface LoginPanelUiBinder extends UiBinder<Widget, LoginPanel> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    LoginMenu loginMenu;
    @UiField
    LoginButton loginBtn;

    public LoginPanel() {
        initWidget(uiBinder.createAndBindUi(this));
        UiHelper.id("container-login-panel", container);

        loginMenu.setVisible(false);

        container.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                final Style style = loginMenu.getElement().getStyle();
                style.setBottom(container.getOffsetHeight(), Unit.PX);
                loginMenu.setVisible(!loginMenu.isVisible());
            }
        }, ClickEvent.getType());

    }

}
