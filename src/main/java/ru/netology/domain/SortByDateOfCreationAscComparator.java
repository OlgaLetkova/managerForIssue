package ru.netology.domain;

import java.util.Comparator;

public class SortByDateOfCreationAscComparator implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getDateOfCreation().compareTo(o2.getDateOfCreation());
    }
}
