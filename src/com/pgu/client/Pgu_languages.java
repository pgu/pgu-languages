package com.pgu.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
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

    private static final int N = 4;

    @Override
    public void onModuleLoad() {

        final Button btnInitGame = new Button("initGame");
        RootPanel.get().add(btnInitGame);
        final HTML sep = new HTML("");
        sep.setHeight("5px");
        RootPanel.get().add(sep);

        final FlexTable ft = new FlexTable();
        RootPanel.get().add(ft);
        final HTML sep2 = new HTML("");
        sep2.setHeight("5px");
        RootPanel.get().add(sep2);

        final Button btnDeleteData = new Button("deleteData");
        RootPanel.get().add(btnDeleteData);
        final Button btnInitData = new Button("initData");
        RootPanel.get().add(btnInitData);
        final HTML sep3 = new HTML("");
        sep3.setHeight("5px");
        RootPanel.get().add(sep3);
        final FlexTable ftDebug = new FlexTable();
        RootPanel.get().add(ftDebug);

        int index = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {

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
                gameService.initGame(N * N, new AsyncCallbackApp<List<Symbol>>() {

                    @Override
                    public void onSuccess(final List<Symbol> symbols) {
                        nbSymbolsToFind = symbols.size();
                        rowCol2HalfPair.clear();

                        final LinkedList<Integer> indices = new LinkedList<Integer>();
                        for (int index = 0; index < N * N; index++) {
                            indices.addLast(index);
                        }

                        final LinkedList<Symbol> bufferSymbols = new LinkedList<Symbol>();
                        bufferSymbols.addAll(symbols);

                        while (!bufferSymbols.isEmpty()) {
                            final Symbol symbol = bufferSymbols.poll();

                            // set alpha
                            Integer index = indices.remove(Random.nextInt(indices.size()));
                            rowCol2HalfPair.put(new RowCol(index), new HalfPair(symbol.getAlpha(), symbol));

                            // set unicode
                            index = indices.remove(Random.nextInt(indices.size()));
                            rowCol2HalfPair.put(new RowCol(index), new HalfPair(symbol.getUnicode(), symbol));

                        }

                        int row = 0;
                        for (final Symbol symbol : symbols) {
                            ftDebug.setWidget(row, 0, new HTML(symbol.getAlpha()));
                            ftDebug.setWidget(row++, 1, new HTML(symbol.getUnicode()));
                        }

                    }

                });
            }
        });

    }

    final Map<RowCol, HalfPair> rowCol2HalfPair = new HashMap<RowCol, HalfPair>();

    public static class HalfPair {
        private final String value;
        private final Symbol parent;

        public HalfPair(final String value, final Symbol parent) {
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "HalfPair [value=" + value + ", parent=" + parent + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (parent == null ? 0 : parent.hashCode());
            result = prime * result + (value == null ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final HalfPair other = (HalfPair) obj;
            if (parent == null) {
                if (other.parent != null) {
                    return false;
                }
            } else if (!parent.equals(other.parent)) {
                return false;
            }
            if (value == null) {
                if (other.value != null) {
                    return false;
                }
            } else if (!value.equals(other.value)) {
                return false;
            }
            return true;
        }

    }

    public static class RowCol {
        private final int row;
        private final int col;

        public RowCol(final Integer index) {
            row = index / N;
            col = index % N;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + col;
            result = prime * result + row;
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final RowCol other = (RowCol) obj;
            if (col != other.col) {
                return false;
            }
            if (row != other.row) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "RowCol [row=" + row + ", col=" + col + "]";
        }

    }

    public class FlowPanelCard extends FlowPanel {

        private final HTML label = new HTML();
        private final int index;

        public FlowPanelCard(final int index, final Pgu_languages board) {
            this.index = index;
            add(label);

            addDomHandler(new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    board.isCellClicked(FlowPanelCard.this);
                }
            }, ClickEvent.getType());

        }
    }

    HalfPair S1;
    HalfPair S2;

    FlowPanelCard fpcS1;
    FlowPanelCard fpcS2;

    Timer t = new Timer() {

        @Override
        public void run() {
            resetTour();
        }
    };

    int nbSymbolsToFind = 0;

    protected void isCellClicked(final FlowPanelCard flowPanelCard) {
        final RowCol rowCol = new RowCol(flowPanelCard.index);
        final HalfPair halfPair = rowCol2HalfPair.get(rowCol);

        if ("".equals(flowPanelCard.label.getHTML())) {
            if (S1 == null) {
                setS1(flowPanelCard, halfPair);
                return;
            }

            if (S2 == null) {
                if (halfPair != S1) {
                    S2 = halfPair;
                    flowPanelCard.label.setHTML(S2.value);

                    if (S1.parent.equals(S2.parent)) {
                        GWT.log("symbol is found!");
                        S1 = null;
                        S2 = null;
                        nbSymbolsToFind--;
                        if (nbSymbolsToFind == 0) {
                            Window.alert("Bravo, you win!");
                        }
                    } else {
                        GWT.log("symbol is not found!");
                        fpcS2 = flowPanelCard;
                        t.schedule(2000);
                    }
                }
            } else {
                t.cancel();
                resetTour();
            }
        } else {

        }
    }

    private void resetTour() {
        fpcS1.label.setHTML("");
        fpcS2.label.setHTML("");
        S1 = null;
        S2 = null;
    }

    private void setS1(final FlowPanelCard flowPanelCard, final HalfPair halfPair) {
        if ("".equals(flowPanelCard.label.getHTML())) {
            S1 = halfPair;
            flowPanelCard.label.setHTML(S1.value);
            fpcS1 = flowPanelCard;
        }
    }

}
