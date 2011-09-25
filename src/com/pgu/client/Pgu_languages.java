package com.pgu.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.pgu.client.app.AsyncCallbackApp;
import com.pgu.client.presenter.GamePresenter;
import com.pgu.client.presenter.LoginPresenter;
import com.pgu.client.presenter.OptionsPresenter;
import com.pgu.client.presenter.ResetPresenter;
import com.pgu.client.presenter.ScorePresenter;
import com.pgu.client.presenter.StartPresenter;
import com.pgu.client.rpc.GameService;
import com.pgu.client.rpc.GameServiceAsync;
import com.pgu.client.rpc.LoginService;
import com.pgu.client.rpc.LoginServiceAsync;
import com.pgu.client.rpc.PlayerService;
import com.pgu.client.rpc.PlayerServiceAsync;
import com.pgu.client.ui.GameBoard;
import com.pgu.client.ui.GameBoard.Card;
import com.pgu.client.ui.LoginButton;
import com.pgu.client.ui.LoginMenu;
import com.pgu.client.ui.OptionsButton;
import com.pgu.client.ui.OptionsMenu;
import com.pgu.client.ui.ResetButton;
import com.pgu.client.ui.ScoreMenu;
import com.pgu.client.ui.StartButton;
import com.pgu.client.ui.UiHelper;
import com.pgu.shared.GameConfig;
import com.pgu.shared.Symbol;
import com.pgu.shared.UserAccount;
import com.pgu.shared.UserAccount.ProviderAuth;

public class Pgu_languages implements EntryPoint, ValueChangeHandler<String>, LoginPresenter, ScorePresenter,
        ResetPresenter, GamePresenter, OptionsPresenter, StartPresenter {

    private final GameServiceAsync gameService = GWT.create(GameService.class);
    private final LoginServiceAsync loginService = GWT.create(LoginService.class);
    private final PlayerServiceAsync playerService = GWT.create(PlayerService.class);

    private final GameBoard gameBoard = new GameBoard();
    private final OptionsMenu menuOptions = new OptionsMenu();

    private final OptionsButton btnOptions = new OptionsButton();
    private final ResetButton btnReset = new ResetButton();
    private final StartButton btnStart = new StartButton();

    private final LoginMenu menuLogin = new LoginMenu();
    private final ScoreMenu menuScore = new ScoreMenu();
    private final LoginButton btnLogin = new LoginButton();

    private final FlowPanel mainArea = new FlowPanel();
    private final FlowPanel noticeArea = new FlowPanel();

    private UserAccount player;

    @Override
    public void onModuleLoad() {
        btnLogin.setPresenter(this);
        menuScore.setPresenter(this);
        btnReset.setPresenter(this);
        gameBoard.setPresenter(this);
        btnOptions.setPresenter(this);
        btnStart.setPresenter(this);

        getLoggedInUser();

        UiHelper.id("mainArea", mainArea);
        UiHelper.id("noticeArea", noticeArea);

        RootPanel.get().add(mainArea);
        mainArea.add(menuOptions);
        mainArea.add(gameBoard);
        RootPanel.get().add(noticeArea);
        RootPanel.get().add(btnLogin);
        RootPanel.get().add(btnStart);
        RootPanel.get().add(btnReset);
        RootPanel.get().add(btnOptions);
        RootPanel.get().add(menuLogin);

        final String initToken = History.getToken();
        if (initToken.length() == 0) {
            History.newItem("game");
        }
        History.addValueChangeHandler(this);
        History.fireCurrentHistoryState();
    }

    private void getLoggedInUser() {
        loginService.getLoggedInUser(new AsyncCallbackApp<UserAccount>() {

            @Override
            public void onSuccess(final UserAccount loggedInUser) {
                if (null == loggedInUser) {
                    showLoginView();
                } else {
                    goAfterLogin(loggedInUser);
                }
            }

        });
    }

    private void showLoginView() {
        player = null;
        btnLogin.showLogin();
    }

    private void goAfterLogin(final UserAccount loggedInUser) {
        player = loggedInUser;

        btnLogin.showUser(player);

        playerService.getScore(player, new AsyncCallbackApp<Integer>() {

            @Override
            public void onSuccess(final Integer result) {
                menuScore.setText("" + result);
            }
        });
    }

    private boolean isGameOn = false;
    private int seconds = 0;
    private final Timer timerSeconds = new Timer() {

        @Override
        public void run() {
            seconds++;
            gameBoard.setTime(seconds);
        }

    };

    @Override
    public void start() {
        resetGameFlags();

        final GameConfig gc = new GameConfig();
        gc.group = menuOptions.getCurrentGroup();
        gc.filters.clear();
        gc.filters.addAll(menuOptions.getFilters());

        gameService.initGame(gc, new AsyncCallbackApp<List<Symbol>>() {

            @Override
            public void onSuccess(final List<Symbol> symbols) {
                resetGame();

                nbSymbolsToFind = symbols.size();
                rowCol2HalfPair.clear();

                final LinkedList<Integer> indices = new LinkedList<Integer>();
                for (int index = 0; index < gameBoard.getSize(); index++) {
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

                symbolIngames.clear();
                symbolIngames.addAll(symbols);
            }

        });
    }

    private final List<Symbol> symbolIngames = new ArrayList<Symbol>();
    private final Map<RowCol, HalfPair> rowCol2HalfPair = new HashMap<RowCol, HalfPair>();

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
            final int size = gameBoard.getSize();
            row = index / size;
            col = index % size;
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

    private HalfPair S1;
    private HalfPair S2;

    private Card fpcS1;
    private Card fpcS2;

    private int nbSymbolsToFind = 0;

    private final Timer t = new Timer() {

        @Override
        public void run() {
            resetTour();
        }
    };

    protected void isCellClicked(final Card card) {
        if (isGameOn) {

            final RowCol rowCol = new RowCol(card.getIndex());
            final HalfPair halfPair = rowCol2HalfPair.get(rowCol);

            if (halfPair == null) {
                card.setHTML("");
                card.getElement().getStyle().setBackgroundColor("blue");
                return;
            }

            if ("".equals(card.getHTML())) {
                if (S1 == null) {
                    setS1(card, halfPair);
                    return;
                }

                if (S2 == null) {
                    if (halfPair != S1) {
                        S2 = halfPair;
                        card.setHTML(S2.value);

                        if (S1.parent.equals(S2.parent)) {
                            S1 = null;
                            S2 = null;
                            nbSymbolsToFind--;
                        } else {
                            fpcS2 = card;
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
                // TODO PGU
                Window.alert("Success");
            }
        }
    }

    private void resetGameFlags() {
        isGameOn = false;
        seconds = 0;
        timerSeconds.cancel();
    }

    private void resetTour() {
        fpcS1.setHTML("");
        fpcS2.setHTML("");
        S1 = null;
        S2 = null;
    }

    private void setS1(final Card card, final HalfPair halfPair) {
        if ("".equals(card.getHTML())) {
            S1 = halfPair;
            card.setHTML(S1.value);
            fpcS1 = card;
        }
    }

    @Override
    public void onValueChange(final ValueChangeEvent<String> event) {
        final String token = event.getValue();
        if ("game".equals(token)) {
            gameBoard.setVisible(true);
            menuOptions.setVisible(false);
        } else if ("options".equals(token)) {
            gameBoard.setVisible(false);
            menuOptions.setVisible(true);
        }
        // TODO PGU help view, score view
    }

    @Override
    public LoginMenu getMenuLogin() {
        return menuLogin;
    }

    @Override
    public ScoreMenu getMenuScore() {
        return menuScore;
    }

    @Override
    public void logout() {
        if (ProviderAuth.FACEBOOK == player.getProviderAuth()) {
            Window.Location.assign("/facebooklogout.jsp");
        } else {
            loginService.logout(new AsyncCallbackApp<Void>() {

                @Override
                public void onSuccess(final Void result) {
                    showLoginView();
                }
            });
        }
    }

    @Override
    public void resetGame() {
        resetGameFlags();
        gameBoard.reset();
    }

    @Override
    public void showSymbols() {
        final StringBuffer sb = new StringBuffer();
        for (final Symbol symbol : symbolIngames) {
            sb.append(symbol.getAlpha());
            sb.append("/");
            sb.append(symbol.getUnicode());
            sb.append(",");
        }
        Window.alert(sb.toString());
    }

    @Override
    public void clicks(final Card card) {
        isCellClicked(card);
    }

    @Override
    public void toggleOptionsMenu() {
        History.newItem(!menuOptions.isVisible() ? "options" : "game");
    }

}
