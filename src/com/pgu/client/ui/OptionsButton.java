package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class OptionsButton extends Composite {

    private static OptionsButtonUiBinder uiBinder = GWT.create(OptionsButtonUiBinder.class);

    interface OptionsButtonUiBinder extends UiBinder<Widget, OptionsButton> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    HTML options;

    public OptionsButton() {
        initWidget(uiBinder.createAndBindUi(this));

        UiHelper.id("container-options", container);
        UiHelper.id("options", options);

        // TODO PGU alphabet, filtres
    }

}
