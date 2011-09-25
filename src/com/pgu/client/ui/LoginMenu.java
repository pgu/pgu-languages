package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
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
        container.setVisible(false);

        UiHelper.id("container-login-menu", container);
        UiHelper.id("facebook", facebook);
        UiHelper.id("twitter", twitter);
        UiHelper.id("google", google);

        facebook.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                Window.Location.assign("/loginfacebook");
            }
        });
        twitter.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                Window.Location.assign("/logintwitter");
            }
        });
        google.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                Window.Location.assign("/logingoogle");
            }
        });
    }

}
