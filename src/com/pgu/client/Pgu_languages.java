package com.pgu.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.pgu.client.app.AsyncCallbackApp;
import com.pgu.shared.Symbol;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Pgu_languages implements EntryPoint {

    InitServiceAsync initService = GWT.create(InitService.class);
    GameServiceAsync gameService = GWT.create(GameService.class);

    private static final int SIZE = 4;

    @Override
    public void onModuleLoad() {

        final Button btnInitGame = new Button("initGame");
        RootPanel.get().add(btnInitGame);
        RootPanel.get().add(new HTML(""));

        final Button btnDeleteData = new Button("deleteData");
        RootPanel.get().add(btnDeleteData);
        final Button btnInitData = new Button("initData");
        RootPanel.get().add(btnInitData);
        RootPanel.get().add(new HTML(""));

        final FlexTable ft = new FlexTable();
        RootPanel.get().add(ft);

        int index = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                final FlowPanelCard cellFormat = new FlowPanelCard(index++, this);
                final Style style = cellFormat.getElement().getStyle();
                style.setBackgroundColor("limegreen");
                style.setWidth(25, Unit.PX);
                style.setHeight(25, Unit.PX);

                ft.setWidget(row, col, cellFormat);

            }
        }

        btnInitData.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                btnInitData.setEnabled(false);
                initService.initData(new AsyncCallbackApp<Void>() {

                    @Override
                    public void onSuccess(final Void result) {
                        GWT.log("init is done");
                    }
                });
            }
        });

        btnDeleteData.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                initService.deleteData(new AsyncCallbackApp<Void>() {

                    @Override
                    public void onSuccess(final Void result) {
                        GWT.log("clean data is done");
                    }
                });
            }
        });

        btnInitGame.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                gameService.initGame(new AsyncCallbackApp<List<Symbol>>() {

                    @Override
                    public void onSuccess(final List<Symbol> result) {
                        symbols.clear();
                        symbols.addAll(result);

                        symbolGames.clear();
                        for (int i = 0; i < SIZE * 2; i++) {
                            symbolGames.add(symbols.get(Random.nextInt(symbols.size())));
                        }

                        int j = 0;
                        for (int row = 0; row < SIZE / 2; row++) {
                            for (int col = 0; col < SIZE; col++) {
                                final FlowPanelCard fpc = (FlowPanelCard) ft.getWidget(row, col);
                                fpc.label.setHTML(symbolGames.get(j++).getAlpha());
                            }
                        }

                        j = 0;
                        for (int row = SIZE / 2; row < SIZE; row++) {
                            for (int col = 0; col < SIZE; col++) {
                                final FlowPanelCard fpc = (FlowPanelCard) ft.getWidget(row, col);
                                fpc.label.setHTML(symbolGames.get(j++).getUnicode());
                            }
                        }

                    }
                });
            }
        });

    }

    List<Symbol> symbols = new ArrayList<Symbol>();
    List<Symbol> symbolGames = new ArrayList<Symbol>();

    public static class FlowPanelCard extends FlowPanel {

        private final HTML label = new HTML();

        public FlowPanelCard(final int index, final Pgu_languages board) {
            add(label);

            addDomHandler(new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    // label.setHTML(board.symbols.get(index).get);

                }
            }, ClickEvent.getType());

        }

    }

}
