package ru.netology.domain;

import java.util.Comparator;

public class SortByQuantityOfComments implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getQuantityOfComments() - o2.getQuantityOfComments();
    }
}
