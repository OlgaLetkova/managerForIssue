package ru.netology.domain;

import lombok.AllArgsConstructor;

import java.util.function.Predicate;
@AllArgsConstructor
public class FilterByAssignee implements Predicate<Issue> {
    private String assignee;

    @Override
    public boolean test(Issue issue) {
        return issue.getAssignees().contains(assignee);
    }
}
