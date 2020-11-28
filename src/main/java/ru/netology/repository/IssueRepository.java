package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    private List<Issue> items = new ArrayList<>();

    public boolean add(Issue item) {
        return items.add(item);
    }

    public List<Issue> getAll() {
        return items;
    }

    public List<Issue> getOpenedIssues() {
        List<Issue> open = new ArrayList<>();
        for (Issue item : items) {
            if (item.isStatusOpened()) {
                open.add(item);
            }
        }
        return open;
    }

    public List<Issue> getClosedIssues() {
        List<Issue> close = new ArrayList<>();
        for (Issue item : items) {
            if (!item.isStatusOpened()) {
                close.add(item);
            }
        }
        return close;
    }
}

