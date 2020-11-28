package ru.netology.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class FilterByLabel implements Predicate<Issue> {
    private String label;

    public FilterByLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean test(Issue issue) {
        return issue.getLabels().contains(label);
    }
}
