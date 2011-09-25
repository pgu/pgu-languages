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
import com.pgu.client.presenter.ResetPresenter;

public class ResetButton extends Composite {

    private static ResetButtonUiBinder uiBinder = GWT.create(ResetButtonUiBinder.class);

    interface ResetButtonUiBinder extends UiBinder<Widget, ResetButton> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    HTML reset;

    private ResetPresenter presenter;

    public void setPresenter(final ResetPresenter presenter) {
        this.presenter = presenter;
    }

    public ResetButton() {
        initWidget(uiBinder.createAndBindUi(this));

        UiHelper.id("container-reset-btn", container);
        UiHelper.id("reset-btn", reset);

        container.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                GWT.log("reset...");
                // TODO PGU turn off the cards, the timer
                presenter.resetGame();
            }
        }, ClickEvent.getType());

    }

}
