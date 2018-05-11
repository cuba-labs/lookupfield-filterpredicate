package com.company.lookupfieldfilterpredicate.web.screens;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.OptionsGroup;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class PredicateDemo extends AbstractWindow {

    private Map<String, BiFunction<String, String, Boolean>> filterStrategies =
            ImmutableMap.of(
                    "contains", String::contains,
                    "startsWith", String::startsWith,
                    "endsWith", String::endsWith
            );

    @Inject
    private LookupField lookupField;
    @Inject
    private OptionsGroup strategyOptionsGroup;

    private BiFunction<String, String, Boolean> predicate = String::contains;

    @Override
    public void init(Map<String, Object> params) {
        prepareDemo();

        // enable custom filtering strategy
        lookupField.setFilterPredicate((itemCaption, searchString) ->
                predicate.apply(itemCaption.toLowerCase(), searchString));
    }

    private void prepareDemo() {
        // populate LookupField with data
        ArrayList<String> fruitNames = Lists.newArrayList(getMessage("fruitNames").split(" "));
        lookupField.setOptionsList(fruitNames);

        Map<String, String> options = filterStrategies.keySet().stream()
                .collect(Collectors.toMap(this::getMessage, v -> v));
        strategyOptionsGroup.setOptionsMap(options);
        strategyOptionsGroup.setValue("contains");

        //noinspection SuspiciousMethodCalls
        strategyOptionsGroup.addValueChangeListener(e ->
                predicate = filterStrategies.get(e.getValue()));
    }
}