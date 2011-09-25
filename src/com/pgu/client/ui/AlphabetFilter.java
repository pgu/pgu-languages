package com.pgu.client.ui;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.GWT;
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

    public AlphabetFilter() {
        initWidget(uiBinder.createAndBindUi(this));
        UiHelper.id("container-alphabet-filter", container);

        gr2filters.put(Group.HIRAGANA, new HashSet<String>());
        gr2filters.put(Group.KATAKANA, new HashSet<String>());
        gr2filters.put(Group.RUSSIAN, new HashSet<String>());
    }

    private Group currentGroup;

    public void updateAlphabet(final Group group) {
        currentGroup = group;
        container.clear();

        if (isJapanese(group)) {
            container.add(new HTMLToggle("A"));
            container.add(new HTMLToggle("E"));
            container.add(new HTMLToggle("I"));
            container.add(new HTMLToggle("O"));
            container.add(new HTMLToggle("U"));

            container.add(new HTMLToggle("K"));
            container.add(new HTMLToggle("S"));
            container.add(new HTMLToggle("T"));
            container.add(new HTMLToggle("N"));
            container.add(new HTMLToggle("H"));
            container.add(new HTMLToggle("M"));
            container.add(new HTMLToggle("Y"));
            container.add(new HTMLToggle("R"));
            container.add(new HTMLToggle("W"));
        }

        final Set<String> filters = gr2filters.get(group);
        for (int i = 0; i < container.getWidgetCount(); i++) {
            final HTMLToggle btn = (HTMLToggle) container.getWidget(i);
            if (filters.contains(btn.getText())) {
                btn.select();
            }
        }
    }

    private final EnumMap<Group, Set<String>> gr2filters = new EnumMap<Group, Set<String>>(Group.class);

    private static boolean isJapanese(final Group gr) {
        return gr == Group.HIRAGANA || gr == Group.KATAKANA;
    }

    public class HTMLToggle extends HTML {
        private boolean isSelected = false;

        private HTMLToggle(final String html) {
            super(html);
            setWidth("15px");
            getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
            getElement().getStyle().setCursor(Cursor.POINTER);
            getElement().getStyle().setProperty("border", "1px solid black");

            addClickHandler(new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    if (isSelected) {
                        deselect();
                    } else {
                        select();
                    }
                }

                private void deselect() {
                    getElement().getStyle().setBackgroundColor("white");
                    gr2filters.get(currentGroup).remove(getText());
                    isSelected = false;
                }
            });
        }

        public void select() {
            getElement().getStyle().setBackgroundColor("lightgrey");
            gr2filters.get(currentGroup).add(getText());
            isSelected = true;
        }

    }

}
