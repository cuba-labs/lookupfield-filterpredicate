package com.company.lookupfieldfilterpredicate.web.screens;

import com.google.common.collect.Lists;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.components.LookupField;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Map;

public class IgnoreAccentsDemo extends AbstractWindow {

    @Inject
    private CheckBox strategyEnabled;
    @Inject
    private LookupField lookupField;

    @Override
    public void init(Map<String, Object> params) {
        // populate LookupField with data
        ArrayList<String> frenchNames = Lists.newArrayList(getMessage("names").split(" "));
        lookupField.setOptionsList(frenchNames);

        strategyEnabled.addValueChangeListener(e -> {
            boolean ignoreAccents = BooleanUtils.isTrue((Boolean) e.getValue());
            lookupField.setFilterPredicate(ignoreAccents ?
                    this::test : null);
        });
    }

    private boolean test(String name, String searchString) {
        return StringUtils.replaceChars(name, "ÉÈËÏÎ", "EEEII")
                .toLowerCase()
                .contains(searchString);
    }
}