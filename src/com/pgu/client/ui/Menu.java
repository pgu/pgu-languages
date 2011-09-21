package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class Menu extends Composite {

    private static MenuUiBinder uiBinder = GWT.create(MenuUiBinder.class);

    interface MenuUiBinder extends UiBinder<Widget, Menu> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    StartButton startBtn;
    @UiField
    StopButton stopBtn;
    @UiField
    OptionsButton optionsBtn;
    @UiField
    LoginPanel loginPanel;

    public Menu() {
        initWidget(uiBinder.createAndBindUi(this));
        UiHelper.id("container-menu", container);
    }

}
