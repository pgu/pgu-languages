package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class GameBoard extends Composite {

    private static GameBoardUiBinder uiBinder = GWT.create(GameBoardUiBinder.class);

    interface GameBoardUiBinder extends UiBinder<Widget, GameBoard> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    HTMLPanel containerGame;
    @UiField
    HTMLPanel containerTimer;
    @UiField
    HTMLPanel timerPanel;
    @UiField
    HTML minutes;
    @UiField
    HTML secondes;

    public GameBoard() {
        initWidget(uiBinder.createAndBindUi(this));
        UiHelper.id("container-game-board", container);
        UiHelper.id("container-game-cards", containerGame);
        UiHelper.id("container-timer", containerTimer);
        UiHelper.id("timer-panel", timerPanel);
        UiHelper.id("minutes", minutes);
        UiHelper.id("secondes", secondes);

        for (int i = 0; i < 32; i++) {
            final HTML card = new HTML();
            card.addStyleName("card");
            card.addStyleName("card-inactive");
            containerGame.add(card);
        }

        minutes.setText("00");
        secondes.setText("00");
    }

}
