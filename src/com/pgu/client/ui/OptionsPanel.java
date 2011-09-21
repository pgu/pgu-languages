package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class OptionsPanel extends Composite {

    private static OptionsPanelUiBinder uiBinder = GWT.create(OptionsPanelUiBinder.class);

    interface OptionsPanelUiBinder extends UiBinder<Widget, OptionsPanel> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    OptionsButton optionsBtn;

    // TODO PGU add options menu

    public OptionsPanel() {
        initWidget(uiBinder.createAndBindUi(this));

        UiHelper.id("container-options-panel", container);

        container.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                // TODO PGU show options
            }
        }, ClickEvent.getType());

    }

}
