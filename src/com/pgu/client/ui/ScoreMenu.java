package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.pgu.client.presenter.ScorePresenter;

public class ScoreMenu extends Composite {

    private static ScoreMenuUiBinder uiBinder = GWT.create(ScoreMenuUiBinder.class);

    interface ScoreMenuUiBinder extends UiBinder<Widget, ScoreMenu> {
    }

    @UiField
    Label score;
    @UiField
    Button logout;

    private ScorePresenter presenter;

    public void setPresenter(final ScorePresenter presenter) {
        this.presenter = presenter;
    }

    public ScoreMenu() {
        initWidget(uiBinder.createAndBindUi(this));

        logout.setText("Logout");
        logout.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                presenter.logout();
            }
        });

    }

    public void setText(final String string) {
        // TODO PGU
        score.setText(string);
    }

}
