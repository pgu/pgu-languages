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
import com.pgu.client.presenter.GamePresenter;

public class GameBoard extends Composite {

    private static final int GAME_SIZE = 32;
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
    HTML seconds;
    @UiField
    HTMLPanel help;

    private GamePresenter presenter;

    public void setPresenter(final GamePresenter presenter) {
        this.presenter = presenter;
    }

    public GameBoard() {
        initWidget(uiBinder.createAndBindUi(this));
        UiHelper.id("container-game-board", container);
        UiHelper.id("container-game-cards", containerGame);
        UiHelper.id("container-timer", containerTimer);
        UiHelper.id("timer-panel", timerPanel);
        UiHelper.id("minutes", minutes);
        UiHelper.id("secondes", seconds);
        UiHelper.id("help", help);

        for (int i = 0; i < GAME_SIZE; i++) {
            containerGame.add(new Card(i));
        }

        initTimer();
        help.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                presenter.showSymbols();
            }
        }, ClickEvent.getType());
    }

    public void reset() {
        initTimer();

        for (int i = 0; i < containerGame.getWidgetCount(); i++) {
            ((Card) containerGame.getWidget(i)).reset();
        }
    }

    private void initTimer() {
        minutes.setText("00");
        seconds.setText("00");
    }

    public void setTime(final int sec) {
        minutes.setHTML(fmt(sec / 60));
        seconds.setHTML(fmt(sec % 60));
    }

    private static String fmt(final int n) {
        return (n < 10 ? "0" : "") + n;
    }

    public int getSize() {
        return GAME_SIZE;
    }

    public class Card extends HTML {
        private final int index;

        public Card(final int index) {
            super();
            this.index = index;
            addStyleName("card");
            addStyleName("card-inactive");

            addClickHandler(new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    presenter.clicks(Card.this);
                }
            });
        }

        public void reset() {
            removeStyleName("card-active");
            addStyleName("card-inactive");
            setHTML("");
        }

        public int getIndex() {
            return index;
        }

    }

}
