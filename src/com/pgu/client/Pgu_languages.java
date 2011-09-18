package com.pgu.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pgu.client.app.AsyncCallbackApp;
import com.pgu.client.rpc.GameService;
import com.pgu.client.rpc.GameServiceAsync;
import com.pgu.client.rpc.InitService;
import com.pgu.client.rpc.InitServiceAsync;
import com.pgu.client.rpc.LoginService;
import com.pgu.client.rpc.LoginServiceAsync;
import com.pgu.shared.GameConfig;
import com.pgu.shared.Symbol;
import com.pgu.shared.Symbol.Group;
import com.pgu.shared.UserAccount;

public class Pgu_languages implements EntryPoint {

    InitServiceAsync initService = GWT.create(InitService.class);
    GameServiceAsync gameService = GWT.create(GameService.class);
    LoginServiceAsync loginService = GWT.create(LoginService.class);

    private int N = 4;

    final PopupPanel popupSuccess = new PopupPanel(true);
    final ListBox group = new ListBox();
    final ListBox gameSize = new ListBox();
    final ListBox vowels = new ListBox(true);
    final ListBox consonants = new ListBox(true);
    final Button btnInitGame = new Button("initGame");
    final Button btnResetGame = new Button("resetGame");
    final FlexTable ft = new FlexTable();
    final Button btnDeleteData = new Button("deleteData");
    final Button btnInitData = new Button("initData");
    final FlexTable ftDebug = new FlexTable();
    Label timeSpent = new Label("00:00");

    final HorizontalPanel hp = new HorizontalPanel();

    @Override
    public void onModuleLoad() {

        final VerticalPanel vp = new VerticalPanel();
        vp.add(btnInitGame);
        vp.add(group);
        vp.add(gameSize);

        final HorizontalPanel hpFilters = new HorizontalPanel();
        hpFilters.add(vowels);
        hpFilters.add(consonants);
        vp.add(hpFilters);
        vp.add(btnResetGame);

        final FlowPanel fp2 = new FlowPanel();
        fp2.add(ft);
        fp2.add(timeSpent);

        hp.add(fp2);
        hp.add(vp);
        hp.add(ftDebug);

        hp.setSpacing(20);

        RootPanel.get().add(hp);
        addSeparator();

        formatBoard();

        // pour admin getLoggedInUser()
        getLoggedInUser();
        setActionInitData();
        setActionDeleteData();

        setActionInitGame();
        setActionResetGame();

        setSelectionGroup();
        setSelectionGameSize();
        setSelectionVowels();
        setSelectionConsonants();

        popupSuccess.add(new Label("Bravo, you win!"));

        final Style styleTS = timeSpent.getElement().getStyle();
        styleTS.setProperty("marginLeft", "auto");
        styleTS.setProperty("marginRight", "auto");
        styleTS.setWidth(30, Unit.PX);
    }

    private void getLoggedInUser() {
        loginService.getLoggedInUser(new AsyncCallbackApp<UserAccount>() {

            @Override
            public void onSuccess(final UserAccount loggedInUser) {
                if (null == loggedInUser) {
                    showLoginView();
                } else {
                    setCurrentUser(loggedInUser);
                    goAfterLogin();
                }
            }

            private void setCurrentUser(final UserAccount loggedInUser) {
                // TODO PGU
            }

        });
    }

    final Button btnFacebook = new Button("Facebook");
    final Button btnGoogle = new Button("Google");
    final Button btnTwitter = new Button("Twitter");

    private void showLoginView() {
        final FlowPanel fp = new FlowPanel();
        fp.add(btnFacebook);
        fp.add(btnGoogle);
        fp.add(btnTwitter);
        hp.add(fp);

        setActionLoginFacebook();
        setActionLoginGoogle();
        setActionLoginTwitter();
    }

    private void setActionLoginFacebook() {
        btnFacebook.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                Window.Location.assign("/loginfacebook");
            }
        });
    }

    private void setActionLoginGoogle() {
        btnGoogle.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                Window.Location.assign("/logingoogle");
            }
        });
    }

    private void setActionLoginTwitter() {
        btnFacebook.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                Window.Location.assign("/logintwitter");
            }
        });
    }

    private void goAfterLogin() {
        final DisclosurePanel dp = new DisclosurePanel("Admin");
        final FlowPanel fp = new FlowPanel();
        fp.add(btnDeleteData);
        fp.add(btnInitData);
        dp.add(fp);
        hp.add(dp);
    }

    private void setActionResetGame() {
        btnResetGame.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                resetGameFlags();
                resetUI();
            }

        });
    }

    private void resetUI() {
        timeSpent.setText("00:00");
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                final FlowPanelCard fpc = (FlowPanelCard) ft.getWidget(row, col);
                fpc.label.setHTML("");
                final Style style = fpc.getElement().getStyle();
                style.setBackgroundColor("limegreen");
            }
        }
    }

    private void addSeparator() {
        final HTML sp = new HTML("");
        sp.setHeight("5px");
        RootPanel.get().add(sp);
    }

    private void setSelectionConsonants() {
        consonants.addItem("");
        consonants.addItem("-");
        consonants.addItem("K");
        consonants.addItem("S");
        consonants.addItem("T");
        consonants.addItem("N");
        consonants.addItem("H");
        consonants.addItem("M");
        consonants.addItem("Y");
        consonants.addItem("R");
        consonants.addItem("W");
        consonants.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                for (int i = 0; i < vowels.getItemCount(); i++) {
                    vowels.setItemSelected(i, false);
                }
            }
        });
        consonants.setHeight("100px");
    }

    private void setSelectionVowels() {
        vowels.addItem("");
        vowels.addItem("A");
        vowels.addItem("E");
        vowels.addItem("I");
        vowels.addItem("O");
        vowels.addItem("U");
        vowels.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                for (int i = 0; i < consonants.getItemCount(); i++) {
                    consonants.setItemSelected(i, false);
                }
            }
        });
        vowels.setHeight("100px");
    }

    private void setSelectionGroup() {
        for (final Group gr : Symbol.Group.values()) {
            group.addItem(gr.toString());
        }
        group.addChangeHandler(new ChangeHandler() {

            @Override
            public void onChange(final ChangeEvent event) {
                final Group gr = Group.valueOf(group.getItemText(group.getSelectedIndex()));
                final boolean isJapanese = isJapanese(gr);
                vowels.setVisible(isJapanese);
                consonants.setVisible(isJapanese);
            }

            private boolean isJapanese(final Group gr) {
                return gr == Group.HIRAGANA || gr == Group.KATAKANA;
            }
        });
    }

    private void setSelectionGameSize() {
        gameSize.addItem("2");
        gameSize.addItem("4");
        gameSize.addItem("6");
        gameSize.setSelectedIndex(1);
    }

    private void setActionInitGame() {
        btnInitGame.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                initGame();
            }

        });
    }

    int seconds = 0;
    Timer timerSeconds = new Timer() {

        @Override
        public void run() {
            seconds++;
            final int minutes = seconds / 60;
            final int sec = seconds % 60;
            timeSpent.setText(fmt(minutes) + ":" + fmt(sec));
        }

        private String fmt(final int n) {
            return (n < 10 ? "0" : "") + n;
        }
    };

    boolean isGameOn = false;

    private void initGame() {
        resetGameFlags();

        final GameConfig gc = new GameConfig();
        setConfigGroup(gc);
        setConfigSize(gc);
        setConfigVowels(gc);
        setConfigConsonants(gc);

        gameService.initGame(gc, new AsyncCallbackApp<List<Symbol>>() {

            @Override
            public void onSuccess(final List<Symbol> symbols) {

                formatBoard();
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

                isGameOn = true;
                timerSeconds.scheduleRepeating(1000);
                formatFtDebug(symbols);
            }

        });
    }

    private void formatFtDebug(final List<Symbol> symbols) {
        ftDebug.clear();
        int row = 0;
        for (final Symbol symbol : symbols) {
            ftDebug.setWidget(row, 0, new HTML(symbol.getAlpha()));
            ftDebug.setWidget(row++, 1, new HTML(symbol.getUnicode()));
        }
    }

    private void setConfigConsonants(final GameConfig gc) {
        for (int i = 1; i < consonants.getItemCount(); i++) {
            if (consonants.isItemSelected(i)) {
                gc.consonants.add(consonants.getValue(i));
            }
        }
    }

    private void setConfigVowels(final GameConfig gc) {
        for (int i = 1; i < vowels.getItemCount(); i++) {
            if (vowels.isItemSelected(i)) {
                gc.vowels.add(vowels.getValue(i));
            }
        }
    }

    private void setConfigSize(final GameConfig gc) {
        final int selectedIndex = gameSize.getSelectedIndex();
        if (isNothingSelected(selectedIndex)) {
            N = 4;
        } else {
            N = Integer.parseInt(gameSize.getItemText(selectedIndex));
        }
        gc.size = N * N;
    }

    private void setConfigGroup(final GameConfig gc) {
        final int selectedIndex = group.getSelectedIndex();
        if (isNothingSelected(selectedIndex)) {
            gc.group = Group.HIRAGANA;
        } else {
            gc.group = Group.valueOf(group.getItemText(selectedIndex));
        }
    }

    private boolean isNothingSelected(final int selectedIndex) {
        return selectedIndex == -1;
    }

    private void setActionDeleteData() {
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
    }

    private void setActionInitData() {
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
    }

    private void formatBoard() {
        ft.clear();
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

    public class RowCol {
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
        if (isGameOn) {

            final RowCol rowCol = new RowCol(flowPanelCard.index);
            final HalfPair halfPair = rowCol2HalfPair.get(rowCol);

            if (halfPair == null) {
                flowPanelCard.label.setHTML("");
                flowPanelCard.getElement().getStyle().setBackgroundColor("blue");
                return;
            }

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
                            S1 = null;
                            S2 = null;
                            nbSymbolsToFind--;
                        } else {
                            fpcS2 = flowPanelCard;
                            t.schedule(2000);
                        }
                    }
                } else {
                    t.cancel();
                    resetTour();
                }

            }
            if (nbSymbolsToFind == 0) {
                resetGameFlags();
                popupSuccess.show();
                popupSuccess.center();
            }
        }
    }

    private void resetGameFlags() {
        isGameOn = false;
        seconds = 0;
        timerSeconds.cancel();
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
