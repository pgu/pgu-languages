package com.pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.pgu.shared.Symbol;
import com.pgu.shared.Symbol.Group;

public class OptionsMenu extends Composite {

    private static OptionsMenuUiBinder uiBinder = GWT.create(OptionsMenuUiBinder.class);

    interface OptionsMenuUiBinder extends UiBinder<Widget, OptionsMenu> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    ListBox language;
    @UiField
    AlphabetFilter filter;

    public OptionsMenu() {
        initWidget(uiBinder.createAndBindUi(this));
        container.setVisible(false);

        UiHelper.id("container-options-menu", container);
        UiHelper.id("options-menu-language", language);

        initLanguages();
    }

    private void initLanguages() {
        for (final Group gr : Symbol.Group.values()) {
            language.addItem(gr.toString());
        }

        filter.updateAlphabet(Group.valueOf(language.getItemText(0)));

        language.addChangeHandler(new ChangeHandler() {

            @Override
            public void onChange(final ChangeEvent event) {
                filter.updateAlphabet(Symbol.Group.valueOf(language.getValue(language.getSelectedIndex())));
            }
        });
    }

}
