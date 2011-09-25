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
import com.pgu.client.presenter.StartPresenter;

public class StartButton extends Composite {

    private static StartButtonUiBinder uiBinder = GWT.create(StartButtonUiBinder.class);

    interface StartButtonUiBinder extends UiBinder<Widget, StartButton> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    HTML start;

    private StartPresenter presenter;

    public void setPresenter(final StartPresenter presenter) {
        this.presenter = presenter;
    }

    public StartButton() {
        initWidget(uiBinder.createAndBindUi(this));

        UiHelper.id("container-start-btn", container);
        UiHelper.id("start-btn", start);

        container.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                presenter.start();
            }
        }, ClickEvent.getType());
    }

}
