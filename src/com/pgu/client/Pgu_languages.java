package com.pgu.client;

import java.util.ArrayList;
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
import com.pgu.client.presenter.ResetPresenter;
import com.pgu.client.presenter.ScorePresenter;
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
        ResetPresenter, GamePresenter {

    // private final InitServiceAsync initService = GWT.create(InitService.class);
    private final GameServiceAsync gameService = GWT.create(GameService.class);
    private final LoginServiceAsync loginService = GWT.create(LoginService.class);
    private final PlayerServiceAsync playerService = GWT.create(PlayerService.class);

    private final FlowPanel mainArea = new FlowPanel();
    private final GameBoard gameBoard = new GameBoard();
    private final OptionsMenu menuOptions = new OptionsMenu();
    private final FlowPanel noticeArea = new FlowPanel();

    private final OptionsButton btnOptions = new OptionsButton();
    private final ResetButton btnReset = new ResetButton();
    private final StartButton btnStart = new StartButton();

    private final LoginMenu menuLogin = new LoginMenu();
    private final ScoreMenu menuScore = new ScoreMenu();
    private final LoginButton btnLogin = new LoginButton();

    // private int N = 4;

    // final PopupPanel popupSuccess = new PopupPanel(true);
    // final ListBox group = new ListBox();
    // final ListBox gameSize = new ListBox();
    // final ListBox vowels = new ListBox(true);
    // final ListBox consonants = new ListBox(true);
    // final Button btnInitGame = new Button("initGame");
    // final Button btnResetGame = new Button("resetGame");
    // final FlexTable ft = new FlexTable();
    // final Button btnDeleteData = new Button("deleteData");
    // final Button btnInitData = new Button("initData");

    // final FlexTable ftDebug = new FlexTable();
    // Label timeSpent = new Label("00:00");
    // Label playerScore;

    // final HorizontalPanel hp = new HorizontalPanel();
    // final DisclosurePanel adminPanel = new DisclosurePanel("Admin");
    // FlowPanel playerPanel;
    // FlowPanel loginPanel;

    // final Button btnFacebook = new Button("Facebook");
    // final Button btnGoogle = new Button("Google");
    // final Button btnTwitter = new Button("Twitter");

    @Override
    public void onModuleLoad() {
        btnLogin.setPresenter(this);
        menuScore.setPresenter(this);
        btnReset.setPresenter(this);
        gameBoard.setPresenter(this);
        getLoggedInUser();

        // final VerticalPanel vp = new VerticalPanel();
        // vp.add(btnInitGame);
        // vp.add(group);
        // vp.add(gameSize);
        //
        // final HorizontalPanel hpFilters = new HorizontalPanel();
        // hpFilters.add(vowels);
        // hpFilters.add(consonants);
        // vp.add(hpFilters);
        // vp.add(btnResetGame);
        //
        // final FlowPanel fp2 = new FlowPanel();
        // fp2.add(ft);
        // fp2.add(timeSpent);
        //
        // hp.add(fp2);
        // hp.add(vp);
        // hp.add(ftDebug);
        //
        // hp.setSpacing(20);
        //
        // RootPanel.get().add(hp);
        // addSeparator();
        //
        // formatBoard();
        //
        // addAdminMenu();
        // setActionInitData();
        // setActionDeleteData();

        // setActionInitGame();
        // setActionResetGame();

        // setSelectionGroup();
        // setSelectionGameSize();
        // setSelectionVowels();
        // setSelectionConsonants();

        // popupSuccess.add(new Label("Bravo, you win!"));

        // final Style styleTS = timeSpent.getElement().getStyle();
        // styleTS.setProperty("marginLeft", "auto");
        // styleTS.setProperty("marginRight", "auto");
        // styleTS.setWidth(30, Unit.PX);
        // /////////////////////////////////////////////////////////////
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

        btnLogin.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                menuLogin.setVisible(!menuLogin.isVisible());
                final Style style = menuLogin.getElement().getStyle();
                style.setTop(btnLogin.getAbsoluteTop() - menuLogin.getOffsetHeight(), Unit.PX);
                style.setLeft(btnLogin.getAbsoluteLeft(), Unit.PX);
            }
        }, ClickEvent.getType());

        btnOptions.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                History.newItem(!menuOptions.isVisible() ? "options" : "game");
            }
        }, ClickEvent.getType());

        // history mng
        final String initToken = History.getToken();
        if (initToken.length() == 0) {
            History.newItem("game");
        }
        History.addValueChangeHandler(this);
        History.fireCurrentHistoryState();
    }

    // private void addPlayerPanel() {
    // playerPanel = new FlowPanel();
    // playerPanel.add(new InlineLabel("Account: "));
    //
    // final InlineLabel playerName = new InlineLabel();
    // playerPanel.add(playerName);
    //
    // final Label playerProvider = new Label();
    // playerPanel.add(playerProvider);
    //
    // playerPanel.add(new InlineLabel("Score: "));
    //
    // playerScore = new Label();
    // playerPanel.add(playerScore);
    //
    // final Button btnLogout = new Button("logout");
    // playerPanel.add(btnLogout);
    //
    // hp.add(playerPanel);
    // setActionLogout(btnLogout);
    //
    // playerName.setText(user.getName());
    // playerProvider.setText(user.getProviderAuth().toString());
    // }

    // private void addLoginPanel() {
    // loginPanel = new FlowPanel();
    // loginPanel.add(btnFacebook);
    // loginPanel.add(btnGoogle);
    // loginPanel.add(btnTwitter);
    // hp.add(loginPanel);
    //
    // setActionLoginFacebook();
    // setActionLoginGoogle();
    // setActionLoginTwitter();
    // }

    // private void addAdminMenu() {
    // final FlowPanel fpAdmin = new FlowPanel();
    // fpAdmin.add(btnDeleteData);
    // fpAdmin.add(btnInitData);
    // adminPanel.add(fpAdmin);
    // hp.add(adminPanel);
    // }

    private UserAccount user;

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
        btnLogin.showLogin();
        // addLoginPanel();
        // adminPanel.setVisible(false);
        // if (null != playerPanel) {
        // playerPanel.removeFromParent();
        // }
    }

    // private void setActionLoginFacebook() {
    // btnFacebook.addClickHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(final ClickEvent event) {
    // Window.Location.assign("/loginfacebook");
    // }
    // });
    // }
    //
    // private void setActionLoginGoogle() {
    // btnGoogle.addClickHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(final ClickEvent event) {
    // Window.Location.assign("/logingoogle");
    // }
    // });
    // }
    //
    // private void setActionLoginTwitter() {
    // btnTwitter.addClickHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(final ClickEvent event) {
    // Window.Location.assign("/logintwitter");
    // }
    // });
    // }
    //
    private void goAfterLogin(final UserAccount loggedInUser) {
        user = loggedInUser;

        btnLogin.showUser(user);
        // if (null != loginPanel) {
        // loginPanel.removeFromParent();
        // }
        // addPlayerPanel();

        // adminPanel.setVisible(false);

        playerService.getScore(user, new AsyncCallbackApp<Integer>() {

            @Override
            public void onSuccess(final Integer result) {
                menuScore.setText("" + result);
            }
        });
    }

    // private void setActionLogout(final Button btnLogout) {
    // btnLogout.addClickHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(final ClickEvent event) {
    // if (ProviderAuth.FACEBOOK == user.getProviderAuth()) {
    // Window.Location.assign("/facebooklogout.jsp");
    // } else {
    // loginService.logout(new AsyncCallbackApp<Void>() {
    //
    // @Override
    // public void onSuccess(final Void result) {
    // GWT.log("logout");
    // showLoginView();
    // }
    // });
    // }
    // }
    // });
    // }
    //
    // private void setActionResetGame() {
    // btnResetGame.addClickHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(final ClickEvent event) {
    // resetGameFlags();
    // resetUI();
    // }
    //
    // });
    // }

    private void resetUI() {
        gameBoard.reset();
        // timeSpent.setText("00:00");
        // for (int row = 0; row < N; row++) {
        // for (int col = 0; col < N; col++) {
        // final FlowPanelCard fpc = (FlowPanelCard) ft.getWidget(row, col);
        // fpc.label.setHTML("");
        // final Style style = fpc.getElement().getStyle();
        // style.setBackgroundColor("limegreen");
        // }
        // }
    }

    // private void addSeparator() {
    // final HTML sp = new HTML("");
    // sp.setHeight("5px");
    // RootPanel.get().add(sp);
    // }
    //
    // private void setSelectionConsonants() {
    // consonants.addItem("");
    // consonants.addItem("-");
    // consonants.addItem("K");
    // consonants.addItem("S");
    // consonants.addItem("T");
    // consonants.addItem("N");
    // consonants.addItem("H");
    // consonants.addItem("M");
    // consonants.addItem("Y");
    // consonants.addItem("R");
    // consonants.addItem("W");
    // consonants.addClickHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(final ClickEvent event) {
    // for (int i = 0; i < vowels.getItemCount(); i++) {
    // vowels.setItemSelected(i, false);
    // }
    // }
    // });
    // consonants.setHeight("100px");
    // }

    // private void setSelectionVowels() {
    // vowels.addItem("");
    // vowels.addItem("A");
    // vowels.addItem("E");
    // vowels.addItem("I");
    // vowels.addItem("O");
    // vowels.addItem("U");
    // vowels.addClickHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(final ClickEvent event) {
    // for (int i = 0; i < consonants.getItemCount(); i++) {
    // consonants.setItemSelected(i, false);
    // }
    // }
    // });
    // vowels.setHeight("100px");
    // }

    // private void setSelectionGroup() {
    // for (final Group gr : Symbol.Group.values()) {
    // group.addItem(gr.toString());
    // }
    // group.addChangeHandler(new ChangeHandler() {
    //
    // @Override
    // public void onChange(final ChangeEvent event) {
    // final Group gr = Group.valueOf(group.getItemText(group.getSelectedIndex()));
    // final boolean isJapanese = isJapanese(gr);
    // vowels.setVisible(isJapanese);
    // consonants.setVisible(isJapanese);
    // }
    //
    // private boolean isJapanese(final Group gr) {
    // return gr == Group.HIRAGANA || gr == Group.KATAKANA;
    // }
    // });
    // }

    // private void setSelectionGameSize() {
    // gameSize.addItem("2");
    // gameSize.addItem("4");
    // gameSize.addItem("6");
    // gameSize.setSelectedIndex(1);
    // }

    // private void setActionInitGame() {
    // btnInitGame.addClickHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(final ClickEvent event) {
    // initGame();
    // }
    //
    // });
    // }

    int seconds = 0;
    Timer timerSeconds = new Timer() {

        @Override
        public void run() {
            seconds++;
            gameBoard.setTime(seconds);
        }

    };

    boolean isGameOn = false;

    private void initGame() {
        resetGameFlags();

        final GameConfig gc = new GameConfig();
        gc.group = menuOptions.getCurrentGroup();
        // setConfigGroup(gc);
        // setConfigSize(gc);
        // setConfigVowels(gc);
        // setConfigConsonants(gc);
        gc.filters.clear();
        gc.filters.addAll(menuOptions.getFilters());

        gameService.initGame(gc, new AsyncCallbackApp<List<Symbol>>() {

            @Override
            public void onSuccess(final List<Symbol> symbols) {
                // formatBoard();

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

    // private void formatFtDebug(final List<Symbol> symbols) {
    // ftDebug.clear();
    // int row = 0;
    // for (final Symbol symbol : symbols) {
    // ftDebug.setWidget(row, 0, new HTML(symbol.getAlpha()));
    // ftDebug.setWidget(row++, 1, new HTML(symbol.getUnicode()));
    // }
    // }

    // private void setConfigConsonants(final GameConfig gc) {
    // for (int i = 1; i < consonants.getItemCount(); i++) {
    // if (consonants.isItemSelected(i)) {
    // gc.consonants.add(consonants.getValue(i));
    // }
    // }
    // }
    //
    // private void setConfigVowels(final GameConfig gc) {
    // for (int i = 1; i < vowels.getItemCount(); i++) {
    // if (vowels.isItemSelected(i)) {
    // gc.vowels.add(vowels.getValue(i));
    // }
    // }
    // }

    // private void setConfigSize(final GameConfig gc) {
    // final int selectedIndex = gameSize.getSelectedIndex();
    // if (isNothingSelected(selectedIndex)) {
    // N = 4;
    // } else {
    // N = Integer.parseInt(gameSize.getItemText(selectedIndex));
    // }
    // gc.size = N * N;
    // }
    //
    // private void setConfigGroup(final GameConfig gc) {
    // final int selectedIndex = group.getSelectedIndex();
    // if (isNothingSelected(selectedIndex)) {
    // gc.group = Group.HIRAGANA;
    // } else {
    // gc.group = Group.valueOf(group.getItemText(selectedIndex));
    // }
    // }
    //
    // private boolean isNothingSelected(final int selectedIndex) {
    // return selectedIndex == -1;
    // }

    // private void setActionDeleteData() {
    // btnDeleteData.addClickHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(final ClickEvent event) {
    // initService.deleteData(new AsyncCallbackApp<Void>() {
    //
    // @Override
    // public void onSuccess(final Void result) {
    // GWT.log("clean data is done");
    // }
    // });
    // }
    // });
    // }

    // private void setActionInitData() {
    // btnInitData.addClickHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(final ClickEvent event) {
    // btnInitData.setEnabled(false);
    // initService.initData(new AsyncCallbackApp<Void>() {
    //
    // @Override
    // public void onSuccess(final Void result) {
    // GWT.log("init is done");
    // }
    // });
    // }
    // });
    // }

    // private void formatBoard() {
    // ft.clear();
    // int index = 0;
    // for (int row = 0; row < N; row++) {
    // for (int col = 0; col < N; col++) {
    //
    // final FlowPanelCard cellFormat = new FlowPanelCard(index++, this);
    // final Style style = cellFormat.getElement().getStyle();
    // style.setBackgroundColor("limegreen");
    // style.setWidth(25, Unit.PX);
    // style.setHeight(25, Unit.PX);
    //
    // ft.setWidget(row, col, cellFormat);
    //
    // }
    // }
    // }

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

    // public class FlowPanelCard extends FlowPanel {
    //
    // private final HTML label = new HTML();
    // private final int index;
    //
    // public FlowPanelCard(final int index, final Pgu_languages board) {
    // this.index = index;
    // add(label);
    //
    // addDomHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(final ClickEvent event) {
    // board.isCellClicked(FlowPanelCard.this);
    // }
    // }, ClickEvent.getType());
    //
    // }
    // }
    //
    HalfPair S1;
    HalfPair S2;

    Card fpcS1;
    Card fpcS2;

    Timer t = new Timer() {

        @Override
        public void run() {
            resetTour();
        }
    };

    int nbSymbolsToFind = 0;

    // protected void isCellClicked(final FlowPanelCard flowPanelCard) {
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
                // popupSuccess.show();
                // popupSuccess.center();
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
        if (ProviderAuth.FACEBOOK == user.getProviderAuth()) {
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
        resetUI();
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

}
