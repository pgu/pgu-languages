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
import com.pgu.client.presenter.OptionsPresenter;

public class OptionsButton extends Composite {

    private static OptionsButtonUiBinder uiBinder = GWT.create(OptionsButtonUiBinder.class);

    interface OptionsButtonUiBinder extends UiBinder<Widget, OptionsButton> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    HTML options;

    private OptionsPresenter presenter;

    public void setPresenter(final OptionsPresenter presenter) {
        this.presenter = presenter;
    }

    public OptionsButton() {
        initWidget(uiBinder.createAndBindUi(this));

        UiHelper.id("container-options-btn", container);
        UiHelper.id("options-btn", options);

        container.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                presenter.toggleOptionsMenu();
            }
        }, ClickEvent.getType());

    }

}
