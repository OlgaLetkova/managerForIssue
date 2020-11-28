package ru.netology.domain;

import java.util.Comparator;

public class SortByDateOfUpdateAscComparator implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getDateOfUpdate().compareTo(o2.getDateOfUpdate());
    }
}
