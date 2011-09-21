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

public class StopButton extends Composite {

    private static StopButtonUiBinder uiBinder = GWT.create(StopButtonUiBinder.class);

    interface StopButtonUiBinder extends UiBinder<Widget, StopButton> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    HTML stop;

    public StopButton() {
        initWidget(uiBinder.createAndBindUi(this));

        UiHelper.id("container-stop-btn", container);
        UiHelper.id("stop-btn", stop);

        container.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                GWT.log("stop...");
                // TODO PGU turn off the cards
            }
        }, ClickEvent.getType());

    }

}
