package com.pgu.client.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.pgu.shared.Symbol.Group;

public class AlphabetFilter extends Composite {

    private static AlphabetFilterUiBinder uiBinder = GWT.create(AlphabetFilterUiBinder.class);

    interface AlphabetFilterUiBinder extends UiBinder<Widget, AlphabetFilter> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    HTMLPanel containerVowels;
    @UiField
    HTMLPanel containerConsonants;

    private static List<String> JAPANESE_FILTER_VOWELS = Arrays.asList("A", "E", "I", "O", "U");
    private static List<String> JAPANESE_FILTER_CONSONANTS = Arrays.asList("-", "K", "S", "T", "N", "H", "M", "Y", "R",
            "W");
    private Group currentGroup;
    private final EnumMap<Group, Set<String>> gr2filters = new EnumMap<Group, Set<String>>(Group.class);
    private final List<HTMLToggle> togConsonants = new ArrayList<HTMLToggle>();
    private final List<HTMLToggle> togVowels = new ArrayList<HTMLToggle>();

    public AlphabetFilter() {
        initWidget(uiBinder.createAndBindUi(this));
        UiHelper.id("container-alphabet-filter", container);

        gr2filters.put(Group.HIRAGANA, new HashSet<String>());
        gr2filters.put(Group.KATAKANA, new HashSet<String>());
        gr2filters.put(Group.RUSSIAN, new HashSet<String>());
    }

    public void updateAlphabet(final Group group) {
        currentGroup = group;
        containerVowels.clear();
        containerConsonants.clear();
        togVowels.clear();
        togConsonants.clear();

        if (isJapanese(group)) {
            for (final String letter : JAPANESE_FILTER_VOWELS) {
                final HTMLToggle togVowel = new HTMLToggle(letter, true);
                containerVowels.add(togVowel);
                togVowels.add(togVowel);
            }
            for (final String letter : JAPANESE_FILTER_CONSONANTS) {
                final HTMLToggle togConsonant = new HTMLToggle(letter);
                containerConsonants.add(togConsonant);
                togConsonants.add(togConsonant);
            }
        }

        final Set<String> filters = gr2filters.get(group);
        selectFilters(filters, containerConsonants);
        selectFilters(filters, containerVowels);
    }

    private static void selectFilters(final Set<String> filters, final HTMLPanel container) {
        for (int i = 0; i < container.getWidgetCount(); i++) {
            final HTMLToggle btn = (HTMLToggle) container.getWidget(i);
            if (filters.contains(btn.getText())) {
                btn.select();
            }
        }
    }

    private static boolean isJapanese(final Group gr) {
        return gr == Group.HIRAGANA || gr == Group.KATAKANA;
    }

    public class HTMLToggle extends HTML {
        public boolean isSelected = false;
        private boolean isVowel = false;

        private HTMLToggle(final String html) {
            this(html, false);
        }

        private HTMLToggle(final String html, final boolean isVowel) {
            super(html);
            this.isVowel = isVowel;
            setWidth("15px");
            final Style style = getElement().getStyle();
            style.setDisplay(Display.INLINE_BLOCK);
            style.setCursor(Cursor.POINTER);
            style.setProperty("border", "1px solid black");

            addClickHandler(new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    if (isSelected) {
                        deselect();
                    } else {
                        select();
                    }
                }

            });
        }

        public void deselect() {
            getElement().getStyle().setBackgroundColor("white");
            gr2filters.get(currentGroup).remove(getText());
            isSelected = false;
        }

        public void select() {
            getElement().getStyle().setBackgroundColor("lightgrey");
            gr2filters.get(currentGroup).add(getText());
            isSelected = true;
            if (isVowel) {
                for (final HTMLToggle conso : togConsonants) {
                    if (conso.isSelected) {
                        conso.deselect();
                    }
                }
            } else {
                for (final HTMLToggle vow : togVowels) {
                    if (vow.isSelected) {
                        vow.deselect();
                    }
                }
            }
        }

    }

    public List<String> getFilters() {
        return new ArrayList<String>(gr2filters.get(currentGroup));
    }

}
