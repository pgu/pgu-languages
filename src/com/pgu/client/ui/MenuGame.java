package com.pgu.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;

public class MenuGame extends FlowPanel {

    private final Button btnInitGame = new Button("initGame");
    private final Button btnResetGame = new Button("resetGame");
    private final ListBox group = new ListBox();
    private final ListBox gameSize = new ListBox();
    private final ListBox vowels = new ListBox(true);
    private final ListBox consonants = new ListBox(true);

    public MenuGame() {
        UiHelper.id("menuGame", this);

        UiHelper.id("btnInitGame", btnInitGame);
        add(btnInitGame);

        UiHelper.id("btnResetGame", btnResetGame);
        add(btnResetGame);

        UiHelper.id("group", group);
        add(group);

        UiHelper.id("gameSize", gameSize);
        add(gameSize);

        UiHelper.id("vowels", vowels);
        add(vowels);

        UiHelper.id("consonants", consonants);
        add(consonants);
    }

}
